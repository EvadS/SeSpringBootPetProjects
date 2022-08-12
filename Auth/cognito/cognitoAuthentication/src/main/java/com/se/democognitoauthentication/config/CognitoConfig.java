package com.se.democognitoauthentication.config;

public class CognitoConfig {

    String clientId = "3kipo3pal7lr67tdrso392r82s";
    String userPoolId = "us-west-2_E82Y8UlDe";
    String region = "us-west-2";
    String identityPoolId = "us-west-2:140384f3-0c16-4aaa-bd1e-79d2f66138d";
    String endpoint = "cognito-idp.eu-west-2.amazonaws.com";


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getUserPoolId() {
        return userPoolId;
    }

    public void setUserPoolId(String userPoolId) {
        this.userPoolId = userPoolId;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIdentityPoolId() {
        return identityPoolId;
    }

    public void setIdentityPoolId(String identityPoolId) {
        this.identityPoolId = identityPoolId;
    }
}
