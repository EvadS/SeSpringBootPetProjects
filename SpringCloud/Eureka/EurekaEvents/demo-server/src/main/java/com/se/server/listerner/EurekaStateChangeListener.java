package com.se.server.listerner;

import com.netflix.appinfo.InstanceInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * eureka event listener
 * For example: for monitoring eureka service down notification
 *
 * @author hrabbit
 */
@Component
public class EurekaStateChangeListener {

    private static final Logger logger = LoggerFactory.getLogger(EurekaStateChangeListener.class);

    /**
     * EurekaInstanceCanceledEvent service offline event
     */
    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        logger.info(event.getServerId() + "\t" + event.getAppName() + "Service offline");

    }

    /**
     * EurekaInstanceRegisteredEvent service registration event
     */
    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        InstanceInfo instanceInfo = event.getInstanceInfo();
        logger.info("****************************************");
        logger.info("==== ** ===== :" + instanceInfo.getAppName() + " register");
        logger.info("==== ** ===== :" + instanceInfo.getStatus());
        logger.info(instanceInfo.toString());
        logger.info("****************************************");


    }

    /**
     * EurekaInstanceRenewedEvent service renewal event
     */
    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        logger.info("==== :" + event.getServerId() + "\t" + event.getAppName() + "Service Renewal");
    }

    /**
     * EurekaRegistryAvailableEvent Eureka Registration Center Launch Event
     */
    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
        logger.info("||| ==== : " + "Registration Startup");

    }

    /**
     * EurekaServerStartedEvent Eureka Server startup event
     */
    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        logger.info("==== : " + "Eureka Server Startup");
    }
}