package com.se.sample.actuator;


import com.se.sample.product.Product;
import com.se.sample.product.ProductController;
import com.se.sample.product.ProductRepository;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.MultiGauge;
import io.micrometer.core.instrument.Tags;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class MyMetricsCustomBean {

    private static AtomicLong atomicLong = new AtomicLong(0);
    public static final String FIRST_COUNTER ="TENDLC_application_address_request_metric";// "PENDING.count";

    private final static String METRICS_NAME_PREFIX = "TENDLC_";

    Logger logger = LoggerFactory.getLogger(MyMetricsCustomBean.class);

    // REST controller for metrics
    @Lazy
    @Autowired
    protected ProductController productController;

    // Database for metrics
    @Lazy
    @Autowired
    protected ProductRepository productRepository;


    // multigauge for low inventory (dimensions on pid and pname)
    MultiGauge lowInventoryCounts = null;

    // multigauge for environment keys (dimensions on key and value)
    MultiGauge sysEnvKeys = null;

    public void setSysEnvKeys() {
        boolean overWrite = true;
        Map<String, String> all_env = System.getenv();
        sysEnvKeys.register(
                all_env.keySet().stream().
                        filter( res -> res.startsWith("JAVA") || res.startsWith("K8S_") ).
                        map(
                                (String res) -> MultiGauge.Row.of( Tags.of("key",res,"value",all_env.get(res)), 0 )
                        ).collect(Collectors.toList()
                        )
                ,overWrite);

    }

    public void updateLowInventoryGauges() {
        boolean overWrite = true;

        //low_inventory2_count
        logger.info("Product count: {}",     productRepository.count());

        // create MultiGauge.Row for each product with low inventory count
        lowInventoryCounts.register(
                //  productRepository.findProductWithLowInventoryCount().stream().
                productRepository.findAll().stream().
                        map(
                                (Product p) -> MultiGauge.Row.of(Tags.of("id",""+p.getId(),"name:",p.getStatus()),p.getCount())
                        ).
                        collect(Collectors.toList()
                        )
                ,overWrite);
    }

    public Supplier<Number> fetchSalesCounter() {
        return ()->productController.getSaleCounter();
    }
    public Supplier<Number> fetchRevenueCounter() {
        return ()->(float)productController.getRevenueCounter()/100;
    }
    public Supplier<Number> fetchProductCount(Product p) {
        return ()->p.getCount();
    }

    public MyMetricsCustomBean(MeterRegistry registry) {

        // hardcoded metric with no tags
        registry.gauge("foo1.test", 0);
        // hardcoded metric with tags
        registry.gauge("foo2.test", Tags.of("foo","bar","another","tag"), 2);
        // builder to create metric with tags and random value
        Supplier<Number> randomValue = () -> Math.random();
        Gauge.builder("foo3.test",randomValue).
                tag("name","foo").
                tag("name3","foo3").
                description("foo descrip").
                register(registry);

        // dynamic metrics from controller
        Gauge.builder("number.of.sales",fetchSalesCounter()).register(registry);
        Gauge.builder("total.revenue",fetchRevenueCounter()).register(registry);

        // dynamically sized dimensions from database
        // rely on updateLowInventoryGauges() to populate because data is not available here
        lowInventoryCounts = MultiGauge.builder(FIRST_COUNTER).tag("pid","pname").register(registry);


        // multi-dimenstional environment keys
        sysEnvKeys = MultiGauge.builder("sys.env").tag("key","value").register(registry);
        // can immediately populate system env keys because they will not change
        setSysEnvKeys();




        // creating same multidimensional env keys, but adding each Gauge separately
        // easier syntax but only works when values are static and available by constructor
/*
        Map<String, String> all_env = System.getenv();
        for(Iterator<String> it = all_env.keySet().iterator(); it.hasNext(); ) {
            String key = it.next();
            if ( key.startsWith("JAVA_") || key.startsWith("K8S_") || key.startsWith("XDG_") ) {
                Gauge.builder("new.sys.env",()->Double.valueOf(0.0)).
                tag("key","key"+key).
                tag("value",""+all_env.get(key)).
                register(registry);
            }
        }
*/



    } // constructor

/*
    @Lazy
    @Autowired
    protected UserController userController;

    public Supplier<Number> fetchUserCount() {
        return ()->userController.fetchUserCount();
    }

    public MyMetricsCustomBean(MeterRegistry registry) {
        // simple, non-dimensional prometheus metric
        Gauge.builder("number.of.users",fetchUserCount()).register(registry);
    }
  */

} // class