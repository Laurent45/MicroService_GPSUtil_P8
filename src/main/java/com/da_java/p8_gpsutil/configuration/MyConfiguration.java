package com.da_java.p8_gpsutil.configuration;

import gpsUtil.GpsUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean
    public GpsUtil getGpsUtil() {
        return new GpsUtil();
    }
}
