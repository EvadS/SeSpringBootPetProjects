package com.se.sample.models;

import lombok.Data;

@Data
public class CampaignValueModel {

    private double value;
    private String campaignName;
    private CampaignState campaignStatus;
}
