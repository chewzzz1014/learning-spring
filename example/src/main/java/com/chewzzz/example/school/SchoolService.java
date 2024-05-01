package com.chewzzz.example.school;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository schoolRepository) {
        this.schoolMapper = schoolMapper;
        this.schoolRepository = schoolRepository;
    }

    public List<SchoolDto> findAllSchool() {
        return schoolRepository
                .findAll()
                .stream()
                .map(ele -> schoolMapper.toSchoolDto(ele))
                .collect(Collectors.toList());
    }

    public SchoolDto createSchool(SchoolDto dto) {
        var school = schoolMapper.toSchool(dto);
        schoolRepository.save(school);
        return dto;
    }
}
