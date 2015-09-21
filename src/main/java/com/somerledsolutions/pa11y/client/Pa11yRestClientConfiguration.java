package com.somerledsolutions.pa11y.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Pa11yRestClientConfiguration {

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new Pa11yRestClientErrorHandler());
        return restTemplate;
    }

}
