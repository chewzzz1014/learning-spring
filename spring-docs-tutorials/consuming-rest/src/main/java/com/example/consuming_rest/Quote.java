package com.example.consuming_rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // ignore nay properties not bound to this type
public record Quote(
    String type,
    Value value
) { }
