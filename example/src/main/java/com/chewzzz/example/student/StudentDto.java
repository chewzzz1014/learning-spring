package com.chewzzz.example.student;

public record StudentDto(
        String firstname,
        String lastname,
        String email,
        Integer schoolId
) {
}
