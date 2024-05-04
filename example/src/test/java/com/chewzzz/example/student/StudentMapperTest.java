package com.chewzzz.example.student;

import com.chewzzz.example.school.School;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class StudentMapperTest {
    
    private StudentMapper studentMapper;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all test methods");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all test methods");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each test method");
        studentMapper = new StudentMapper();
    }

    @AfterEach
    void tearDown() {
        System.out.println("After each test method");
    }

    @Test
    public void testMethod1() {
        System.out.println("My first test method");
    }
    
    @Test
    public void shouldMapStudentDtoToStudent() {
        StudentDto dto = new StudentDto("Zi Qing", "Chew", "123@gmail.com", 1);
        Student student = studentMapper.toStudent(dto);
        
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.schoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        var exception = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("Student Dto should not be null", exception.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        Student student = new Student("Zi Qing", "Chew", "123@gmail.com", 22);
        StudentResponseDto dto = studentMapper.toStudentResponseDto(student);

        assertEquals(student.getFirstname(), dto.firstname());
        assertEquals(student.getLastname(), dto.lastname());
        assertEquals(student.getEmail(), dto.email());
    }
}
