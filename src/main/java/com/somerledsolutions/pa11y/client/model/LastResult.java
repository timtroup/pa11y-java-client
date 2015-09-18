package com.somerledsolutions.pa11y.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LastResult {

    private String id;
    private String task;
    private Date date;
    private Count count;
    private List<Result> results;


}
