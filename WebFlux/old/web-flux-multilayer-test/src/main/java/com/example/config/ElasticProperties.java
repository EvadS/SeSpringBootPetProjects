package com.example.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Skiba Evgeniy
 * @date 10.09.2021
 */
@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "data")
public class ElasticProperties {

    private  String port;
}
