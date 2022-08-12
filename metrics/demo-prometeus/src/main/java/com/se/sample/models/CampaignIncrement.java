package com.se.sample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignIncrement {

    private String campaignName;
    private CampaignState campaignStatus;
}
