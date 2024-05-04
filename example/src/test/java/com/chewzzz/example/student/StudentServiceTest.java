package com.chewzzz.example.student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentMapper studentMapper;
    @Mock
    private StudentRepository studentRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        StudentDto dto = new StudentDto("Zi Qing", "Chew", "123@gmail.com", 1);
        Student student = new Student("Zi Qing", "Chew", "123@gmail.com", 22);
        Student savedStudent = new Student("Zi Qing", "Chew", "123@gmail.com", 22);
        savedStudent.setId(1);

        // mock the call
        when(studentMapper.toStudent(dto))
                .thenReturn(student);
        when(studentRepository.save(student))
                .thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto("Zi Qing", "Chew", "123@gmail.com"));

        // when
        StudentResponseDto responseDto = studentService.createStudent(dto);

        // then
        assertEquals(dto.firstname(), responseDto.firstname());
        assertEquals(dto.lastname(), responseDto.lastname());
        assertEquals(dto.email(), responseDto.email());

        // ensure each of them only called 1 times
        verify(studentMapper, times(1))
                .toStudent(dto);
        verify(studentRepository, times(1))
                .save(student);
        verify(studentMapper, times(1))
                .toStudentResponseDto(savedStudent);
    }
    
    @Test
    public void should_return_all_students() {
        List<Student> students = new ArrayList<>();
        students.add(
                new Student("Zi Qing", "Chew", "123@gmail.com", 22)
        );
        students.add(
                new Student("ZQ", "Chew", "zqc@gmail.com", 21)
        );

        // mock calls
        when(studentRepository.findAll())
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Zi Qing", "Chew", "123@gmail.com"));

        // when
        List<StudentResponseDto> responseDtos = studentService.findAllStudent();

        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        Integer studentId = 1;
        Student student = new Student("Zi Qing", "Chew", "123@gmail.com", 22);

        // mocks the call
        when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(student))
                .thenReturn(new StudentResponseDto("Zi Qing", "Chew", "123@gmail.com"));

        // when
        StudentResponseDto dto = studentService.findStudentById(studentId);

        // then
        assertEquals(dto.firstname(), student.getFirstname());
        assertEquals(dto.lastname(), student.getLastname());
        assertEquals(dto.email(), student.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void should_return_all_students_by_name() {
        String name = "Qing";
        List<Student> students = new ArrayList<>();
        students.add(
                new Student("Zi Qing", "Chew", "123@gmail.com", 22)
        );

        // mock calls
        when(studentRepository.findAllByFirstnameContaining(name))
                .thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("Zi Qing", "Chew", "123@gmail.com"));

        // when
        List<StudentResponseDto> responseDtos = studentService.findStudentByName(name);

        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, times(1)).findAllByFirstnameContaining(name);
    }

//    @Test
//    public void should_delete_student_by_id() {
//        Integer studentId = 1;
//        Student student = new Student("Zi Qing", "Chew", "123@gmail.com", 22);
//
//        // mock calls
//        when(studentRepository.deleteById(studentId));
//
//        // when
//        studentService.deleteStudent(1);
//
//        verify(studentRepository, times(1)).deleteById(studentId);
//    }
}
