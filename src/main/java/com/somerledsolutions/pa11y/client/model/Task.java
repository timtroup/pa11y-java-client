package com.somerledsolutions.pa11y.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Task {

    private String id;
    private String name;
    private String url;
    private String standard;
    private List<String> ignore;
    private List<Annotation> annotations;
    private LastResult last_result;

}

