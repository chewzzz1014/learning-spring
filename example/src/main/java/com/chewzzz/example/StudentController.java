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
    public Student postStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }

    @DeleteMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable("student-id") Integer id) {
        studentRepository.deleteById(id);
    }
}
