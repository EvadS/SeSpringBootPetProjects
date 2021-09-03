package com.se.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author Skiba Evgeniy
 * @date 03.09.2021
 */
@RestController
@RequestMapping
public class IndexController {

    private static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/mode2str")
    public String getStringToMode(@RequestParam("mode") Modes mode) {
        // ...


        logger.info("mode2str. mode: {}",mode);
        return    responseByType(mode);
    }

    private String responseByType(Modes mode) {
        switch (mode){
            case BETA:
                return  "beta response";


            case ALPHA:
                return  "alfa response";

            default:
                return "EMPTY";
        }
    }

    @GetMapping("/findbymode/{mode}")
    public String findByEnum(@PathVariable("mode") Modes mode) {
        // ...
        logger.info("findbymode. mode: {}",mode);
        return   responseByType(mode);
    }
}
