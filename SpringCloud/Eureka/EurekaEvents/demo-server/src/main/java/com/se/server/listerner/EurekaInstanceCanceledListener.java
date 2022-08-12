package com.se.server.listerner;


import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * eureka event listener
 * For example: for monitoring eureka service down notification
 *
 * @author hrabbit
 */
@Configuration
@EnableScheduling
public class EurekaInstanceCanceledListener implements ApplicationListener {
    private Logger logger = LoggerFactory.getLogger(EurekaInstanceCanceledListener.class);

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        // EurekaInstanceCanceledEvent service offline event
        if (applicationEvent instanceof EurekaInstanceCanceledEvent) {
            EurekaInstanceCanceledEvent event = (EurekaInstanceCanceledEvent) applicationEvent;
                         // Get the node information in the current Eureka instance
            PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
            Applications applications = registry.getApplications();
            // Traverse to get the node information of the registered node that is consistent with the current invalid node ID.
            applications.getRegisteredApplications().forEach((registeredApplication) -> {
                registeredApplication.getInstances().forEach((instance) -> {
                    if (instance.getInstanceId().equals(event.getServerId())) {
                        logger.info(" **** Service:" + instance.getAppName() + " Hang up...");
                        // TODO: 2018-09-13 Extended Message Reminder Email, SMS, WeChat, etc.
                    }
                });
            });
        }
        //EurekaInstanceRegisteredEvent service registration event
        if (applicationEvent instanceof EurekaInstanceRegisteredEvent) {
            EurekaInstanceRegisteredEvent event = (EurekaInstanceRegisteredEvent) applicationEvent;
            logger.info(" ******* Service:" + event.getInstanceInfo().getAppName() + " Registration is successful...");
        }
        //EurekaInstanceRenewedEvent service renewal event
        if (applicationEvent instanceof EurekaInstanceRenewedEvent) {
            EurekaInstanceRenewedEvent event = (EurekaInstanceRenewedEvent) applicationEvent;
            logger.info("**** Service:" + event.getInstanceInfo().getAppName() + "Renewal...");
        }
        //EurekaRegistryAvailableEvent Eureka Registry Startup Event
        if (applicationEvent instanceof EurekaRegistryAvailableEvent) {
            logger.info("**** Eureka Registration Center started...");
        }

    }
}
