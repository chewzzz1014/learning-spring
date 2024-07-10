package com.example.gs_consuming_rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Quote(
    String type,
    Value value
) { }
