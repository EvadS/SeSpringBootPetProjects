package com.se.sample.controller;


import com.se.sample.utils.DoubleWrapper;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DemoGaugeController {
    double value;

//    DemoGaugeController(MeterRegistry meterRegistry) {
//
//
//    }
////http://localhost:8080/api/inc
//    @GetMapping("/inc")
//    private String increment() {
//
//    }
//
//    @GetMapping("/dec")
//    private String decrement() {
//
//    }
}
