package com.chewzzz.example.school;

import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }

    public School toSchool(SchoolDto dto) {
        var school = new School();
        school.setName(dto.name());
        return school;
    }
}
