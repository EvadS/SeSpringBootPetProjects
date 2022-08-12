package com.se.sample.controller;


import com.se.sample.components.MultiTaggedCounter;
import com.se.sample.config.ProjectConstants;
import com.se.sample.models.CampaignIncrement;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * counter in actuator
 * <p>
 * IN prometheus campaign_status_total
 * <p>
 * increase(campaign_status_total{campaign_state="PENDING")
 * All :  -- > campaign_status_gauge_total
 */



@RestController
@RequestMapping("/campaign-counter")
public class CampaignCounterController {
    private  MultiTaggedCounter multiTaggedCounter;

    public CampaignCounterController(MeterRegistry meterRegistry) {
        multiTaggedCounter = new MultiTaggedCounter(ProjectConstants.FIRST_COUNTER_NAME_COUNTER,
                meterRegistry,
                ProjectConstants.CAMPAIGN_TAG_NAME_COUNTER,
                ProjectConstants.CAMPAIGN_STATE_NAME_COUNTER);
    }

    @PostMapping("/increment")
    public String updateCampaign(@Validated @RequestBody CampaignIncrement campaignIncrement) {
        multiTaggedCounter.increment(campaignIncrement.getCampaignName(), campaignIncrement.getCampaignStatus().getState());
        return String.format("updated %s", ProjectConstants.FIRST_COUNTER_NAME_COUNTER);
    }
}
