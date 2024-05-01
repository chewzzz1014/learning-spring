package com.chewzzz.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SchoolController {

    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @GetMapping("/schools")
    public List<SchoolDto> getSchool() {
        return schoolRepository
                .findAll()
                .stream()
                .map(ele -> toSchoolDto(ele))
                .collect(Collectors.toList());
    }

    @PostMapping("/schools")
    public SchoolDto createSchool(@RequestBody SchoolDto dto) {
        var school = toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }

    private SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }

    private School toSchool(SchoolDto dto) {
        var school = new School();
        school.setName(dto.name());
        return school;
    }

}
