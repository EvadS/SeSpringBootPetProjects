package com.se.sample.easynotes.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/profile")
public class ProfileCheckController {
    @Autowired
    private org.springframework.core.env.Environment environment;

    @GetMapping("/current")
    public String[] checkProfile() {
        String[] activeProfiles = environment.getActiveProfiles();      // it will return String Array of all active profile.
        for(String profile:activeProfiles) {
            System.out.print(profile);
        }
        return activeProfiles;
    }
}