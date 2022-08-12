package com.se.sample.easynotes.controller;


import com.se.sample.easynotes.model.HostInfo;
import com.se.sample.easynotes.util.IpUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;


@ApiIgnore
@RestController
@RequestMapping("/")
public class IndexController {

    private final IpUtil ipUtil;

    public IndexController(IpUtil ipUtil) {
        this.ipUtil = ipUtil;
    }

//    @GetMapping(value = "/")
//    public String index() {
//        return "redirect:swagger-ui.html";
//    }


    @RequestMapping("")
    public String index(HttpServletRequest httpRequest) {

        HostInfo hostInfo = ipUtil.getHostInfo(httpRequest);
        return String.format("Easy application.\n Info: %s. The current api available on /swagger-ui.html page. ", hostInfo);
    }
}
