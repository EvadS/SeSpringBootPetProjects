package com.se.sample;

import com.se.sample.models.BaseResponse;
import com.se.sample.models.RerlfAboutResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Skiba Evgeniy
 * @date 13.09.2021
 */
@RestController
@RequestMapping("/hier")
public class HierarchyController {


    @GetMapping("/base")
    public BaseResponse getBaseResponse() {
        BaseResponse baseResponse = new RerlfAboutResponse(1, "base");

        return baseResponse;
    }


    @GetMapping("/relf")
    public RerlfAboutResponse getRelfResponse() {
        RerlfAboutResponse baseResponse = new RerlfAboutResponse(1, "relf");

        return baseResponse;
    }

}
