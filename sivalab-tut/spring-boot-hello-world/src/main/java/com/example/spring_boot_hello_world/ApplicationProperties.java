package com.example.spring_boot_hello_world;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app") // bind properties with common prefix "app" into this class fields
@Setter
@Getter
public class ApplicationProperties {
    private String greeting = "Hello";
    private String defaultName = "World";
}
