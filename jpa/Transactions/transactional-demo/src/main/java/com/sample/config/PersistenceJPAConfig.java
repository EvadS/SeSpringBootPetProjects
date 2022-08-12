package com.sample.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
/**
 * turn on transaction support
 */
@EnableTransactionManagement
public class PersistenceJPAConfig {


}

