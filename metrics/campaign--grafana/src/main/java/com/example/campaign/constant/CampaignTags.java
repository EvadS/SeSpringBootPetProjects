package com.example.campaign.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.EnumSet;

@AllArgsConstructor
@Getter
public enum CampaignTags {
    ID("id"),
    NAME("name"),
    STATE("state");

    private String tag;

    public static CampaignTags getByCampaignState(String val) {
        return EnumSet.allOf(CampaignTags.class)
                .stream()
                .filter(e -> e.getTag().equals(val))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", val)));
    }
}
