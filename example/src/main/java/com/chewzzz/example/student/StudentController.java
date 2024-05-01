package com.chewzzz.example.student;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentResponseDto> findAllStudent() {
        return studentService.findAllStudent();
    }

    @GetMapping("/students/search/{student-name}")
    public List<StudentResponseDto> findStudentByName(@PathVariable("student-name") String name) {
        return studentService.findStudentByName(name);
    }

    @GetMapping("/students/{student-id}")
    public StudentResponseDto findStudentById(@PathVariable("student-id") Integer id) {
        return studentService.findStudentById(id);
    }

    @PostMapping("/students")
    public StudentResponseDto postStudent(@RequestBody StudentDto dto) {
        return studentService.createStudent(dto);
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer id) {
        studentService.deleteStudent(id);
    }
}
