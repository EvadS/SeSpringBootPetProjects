package com.se.sample.controller;


import com.se.sample.components.MultiTaggedGauge;
import com.se.sample.config.ProjectConstants;
import com.se.sample.models.CampaignValueModel;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * gauge in actuator
 * # HELP campaign_status_gauge
 * # TYPE campaign_status_gauge gauge
 *
 */



@RestController
@RequestMapping("/campaign-gauge")
public class CampaignGaugeController {

    @Autowired
    private MeterRegistry meterRegistry;

    private final MultiTaggedGauge multiTaggedGauge;

    public CampaignGaugeController(MeterRegistry meterRegistry) {

        multiTaggedGauge = new MultiTaggedGauge(ProjectConstants.FIRST_COUNTER_NAME_GAUGE,
                meterRegistry,
                ProjectConstants.CAMPAIGN_TAG_NAME_GAUGE,
                ProjectConstants.CAMPAIGN_STATE_NAME_GAUGE);
    }

    @PostMapping("/set-value")
    public String updateCampaign(@Validated @RequestBody CampaignValueModel campaignIncrement) {
        multiTaggedGauge.set(campaignIncrement.getValue(), campaignIncrement.getCampaignName(), campaignIncrement.getCampaignStatus().getState());
        return String.format("updated %s", ProjectConstants.FIRST_COUNTER_NAME_GAUGE);
    }
}
