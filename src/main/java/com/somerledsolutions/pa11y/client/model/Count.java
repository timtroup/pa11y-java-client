package com.somerledsolutions.pa11y.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Count {
    private int error;
    private int warning;
    private int notice;
    private int total;
}
