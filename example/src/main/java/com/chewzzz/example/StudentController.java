package com.chewzzz.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello from my first controller";
    }

    @GetMapping("/students")
    public List<Student> findAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/search/{student-name}")
    public List<Student> findStudentByName(@PathVariable("student-name") String name) {
        return studentRepository.findAllByFirstnameContaining(name);
    }

    @GetMapping("/students/{student-id}")
    public Student findStudentById(@PathVariable("student-id") Integer id) {
        return studentRepository.findById(id).orElse(new Student());
    }

    @PostMapping("/post")
    public String postMessage(@RequestBody String message) {
        return "Request accepted. Message accepted is: " + message;
    }

    @PostMapping("/students")
    public StudentResponseDto postStudent(@RequestBody StudentDto dto) {
        var student = toStudent(dto);
        var savedStudent = studentRepository.save(student);
        return toStudentResponseDto(savedStudent);
    }

    private Student toStudent(StudentDto dto) {
        var student = new Student();
        student.setFirstname(dto.firstname());
        student.setLastname(dto.lastname());
        student.setEmail(dto.email());

        var school = new School();
        school.setId(dto.schoolId());

        student.setSchool(school);

        return student;
    }

    private StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(student.getFirstname(), student.getLastname(), student.getEmail());
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer id) {
        studentRepository.deleteById(id);
    }
}
