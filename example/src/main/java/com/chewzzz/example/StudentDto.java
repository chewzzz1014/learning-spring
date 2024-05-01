package com.chewzzz.example;

public record StudentDto(
        String firstname,
        String lastname,
        String email,
        Integer schoolId
) {
}
