package com.example.campaign.service.impl;

import com.example.campaign.constant.CampaignState;
import com.example.campaign.constant.CampaignTags;
import com.example.campaign.model.MultiTaggedCounter;
import com.example.campaign.service.MetricService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class MetricServiceImpl implements MetricService {

    private final MeterRegistry meterRegistry;

    private final static String METRICS_NAME_PREFIX = "TENDLC_";

    private final Map<String, AtomicLong> campaignStateData = new ConcurrentHashMap<>();


    @PostConstruct
    private void init(){
//        Arrays.stream(CampaignState.values())
//                .forEach( i -> Gauge.builder(METRICS_NAME_PREFIX + "campaign_state_metric",
//                                () -> getCampaignStateData(i.getState()))
//                        .tags("state", i.getState())
//                        .register(meterRegistry));


//        Arrays.stream(CampaignState.values())
//                .forEach(i-> {
//                    String[] tags = Arrays.stream(CampaignTags.values()).map(j -> j.getTag()).toArray(String[]::new);
//                    MultiTaggedCounter counter = new MultiTaggedCounter(METRICS_NAME_PREFIX + "campaign_state",
//                                    meterRegistry,
//                            tags);
//
//                            Double initialValue = getInitialValue(i);
//
//                    counter.increment(new String []{"1", "test", i.getState()});
//                    log.info("created counter: {} ", counter);
//                        }
//                );

        initRemovedCounter( );
    }
    private  MultiTaggedCounter removedCounter;
    private  MultiTaggedCounter removedCounter2;
    private void initRemovedCounter( ){

        List<String> collect = Arrays.stream(CampaignTags.values()).map(j -> j.getTag()).collect(Collectors.toList());

        removedCounter = new MultiTaggedCounter(METRICS_NAME_PREFIX + "campaign_state",
                meterRegistry,
                collect);

        removedCounter.increment(CampaignState.REMOVED.getState() , Arrays.asList(new String []{"1", "test", CampaignState.REMOVED.getState()}));


        removedCounter2 = new MultiTaggedCounter(METRICS_NAME_PREFIX + "campaign_state",
                meterRegistry,
                collect);

        removedCounter2.increment(CampaignState.REMOVED.getState() , Arrays.asList(new String []{"1", "test", CampaignState.REMOVE_FAILED.getState()}));



        log.info("created counter: {} ", removedCounter);
    }

    private Double getInitialValue() {
        return  0.0;
    }


    private long getCampaignStateData(String state) {
        AtomicLong value = campaignStateData.getOrDefault(state, new AtomicLong(0));
        return value.getAndSet(0);
    }
}
