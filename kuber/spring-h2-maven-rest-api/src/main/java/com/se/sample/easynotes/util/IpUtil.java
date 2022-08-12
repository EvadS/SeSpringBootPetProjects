package com.se.sample.easynotes.util;

import com.se.sample.easynotes.model.HostInfo;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class IpUtil {


    private final  Environment environment;

    public IpUtil(Environment environment) {
        this.environment = environment;
    }

    public HostInfo getHostInfo(HttpServletRequest request) {
        String port = environment.getProperty("local.server.port");

        String ip = getIpAddr(request);

        List<String> profiles = new ArrayList<>();

        String[] activeProfiles = environment.getActiveProfiles();      // it will return String Array of all active profile.

        if(activeProfiles.length>0) {
            for (String profile : activeProfiles) {
                profiles.add(profile);
            }

        }
        else{
            profiles.add("default");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();

        LocalTime localTime = LocalTime.now();

        return new HostInfo(ip, port, profiles, localDate,localTime);
    }

    private String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // The card take the machine configuration of IP
                    try {
                        ipAddress = InetAddress.getLocalHost().getHostAddress();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                }
            }
            // by a plurality of agents, a first IP-client IP transactions, in accordance with a plurality of IP ',' split
            if (ipAddress != null) {
                if (ipAddress.contains(",")) {
                    return ipAddress.split(",")[0];
                } else {
                    return ipAddress;
                }
            } else {
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
