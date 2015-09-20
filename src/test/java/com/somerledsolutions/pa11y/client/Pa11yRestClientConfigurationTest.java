package com.somerledsolutions.pa11y.client;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

import static org.junit.Assert.*;

public class Pa11yRestClientConfigurationTest {

    @Test
    public void shouldHaveConfigurationAnnotation() {
        assertNotNull(Pa11yRestClientConfiguration.class.getAnnotation(Configuration.class));
    }

}