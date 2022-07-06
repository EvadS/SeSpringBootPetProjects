package com.se.sample.models;

public enum CampaignState {

    PENDING("PENDING"),
    REGISTERED("REGISTERED"),
    REGISTER_FAILED("REGISTER_FAILED"),
    DEPLOYED("DEPLOYED"),
    DEPLOY_FAILED("DEPLOY_FAILED"),
    MARK_REMOVED("MARK_REMOVED"),
    REMOVE_FAILED("REMOVE_FAILED"),
    REMOVED("REMOVED");

    private String campaignState;

    CampaignState(final String campaignState) {
        this.campaignState = campaignState;
    }

    public String getState() {
        return campaignState;
    }

    public void setState(String campaignState) {
        this.campaignState = campaignState;
    }

    public static CampaignState getByCampaignState(String campaignState) {
        CampaignState ret = null;

        for (CampaignState type : CampaignState.values()) {

            if (type.getState().equals(campaignState))
                ret = type;
        }
        return ret;
    }

}
