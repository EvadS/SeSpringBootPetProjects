package com.se.sample.test.sample.conditional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.util.Map;


public class EnvVariableTest {
    @Test
    @DisabledIfEnvironmentVariable(named = "CURRENT_ENV", matches = ".*development.*")
    void notOnDeveloperPC() {
        System.out.println("Do not run this if env variables 'CURRENT_ENV' matches .*development.* ");
    }
    @Disabled("print environment variables.")
    @Test
    void printEnvironmentProperties() {
        Map<String, String> env = System.getenv();
        env.forEach((k, v) -> System.out.println(k + ":" + v));
    }

}
