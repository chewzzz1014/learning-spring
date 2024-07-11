package com.example.spring_boot_hello_world;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) // for creating mock obj and inject them
public class GreetingServiceTest {
    @Mock // initialize properties with mock implementation of this class
    private ApplicationProperties properties;
    @InjectMocks
    private GreetingService greetingService;

    @BeforeEach
    void setup() {
        given(properties.getGreeting()).willReturn("Hello");
    }

    @Test
    void shouldGreetWithDefaultNameWhenNameIsNotProvided() {
        given(properties.getDefaultName()).willReturn("World");

        String greeting = greetingService.sayHello(null);

        Assertions.assertEquals("Hello World", greeting); // junit 5 assertion
        assertThat(greeting).isEqualTo("Hello World"); // assertj assertion
    }

    @Test
    void shouldGreetWithGivenName() {
        String greeting = greetingService.sayHello("John");
        assertThat(greeting).isEqualTo("Hello John");
    }
}
