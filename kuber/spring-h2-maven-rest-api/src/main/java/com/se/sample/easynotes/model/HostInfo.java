package com.se.sample.easynotes.model;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HostInfo {
    private String ip;
    private String port;
    private List<String> profiles = new ArrayList<>();

    private LocalDate localDate;
    private LocalTime localTime;

    public HostInfo() {
    }

    public HostInfo(String ip, String port, List<String> profiles, LocalDate localDate, LocalTime localTime) {
        this.ip = ip;
        this.port = port;
        this.profiles = profiles;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public String toString() {
        return "HostInfo{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", profiles=" + profiles +
                ", localDate=" + localDate +
                ", localTime=" + localTime +
                '}';
    }
}
