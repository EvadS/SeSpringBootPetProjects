package com.se.sample;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TaskConfiguration {

    private String test;

    private List<CustomCertificate> certificates;

    //private RemoteServerInfo serverInfo;
}
