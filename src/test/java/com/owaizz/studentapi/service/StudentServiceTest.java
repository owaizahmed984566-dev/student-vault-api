//package com.owaizz.studentapi.service;
//
//import com.owaizz.studentapi.dto.StudentResponse;
//import com.owaizz.studentapi.entity.Student;
//import com.owaizz.studentapi.exception.StudentNotFoundException;
//import com.owaizz.studentapi.repository.StudentRepository;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class StudentServiceTest {
//
//    @Mock
//    private StudentRepository repository;
//
//    @InjectMocks
//    private StudentService service;
//
//    @Test
//    void getStudent_shouldReturnStudent() {
//
//        // Arrange
//        Student student = new Student();
//
//        student.setId(1);
//        student.setName("Owaiz");
//        student.setSubject("Java");
//        student.setMarks(90);
//
//        when(repository.findById(1))
//                .thenReturn(Optional.of(student));
//
//        // Act
//        StudentResponse result = service.getStudent(1);
//
//        // Assert
//        assertEquals(1, result.getId());
//        assertEquals("Owaiz", result.getName());
//        assertEquals("Java", result.getSubject());
//        assertEquals(90, result.getMarks());
//
//    }
//
//    @Test
//    void getStudent_shouldThrowException_whenStudentNotFound() {
//
//        // Arrange
//        when(repository.findById(99))
//                .thenReturn(Optional.empty());
//
//        // Act + Assert
//        assertThrows(
//                StudentNotFoundException.class,
//                () -> service.getStudent(99)
//        );
//    }
//}



















package com.owaizz.studentapi.service;

import com.owaizz.studentapi.dto.StudentPatchRequest;
import com.owaizz.studentapi.dto.StudentRequest;
import com.owaizz.studentapi.dto.StudentResponse;
import com.owaizz.studentapi.entity.Student;
import com.owaizz.studentapi.exception.StudentNotFoundException;
import com.owaizz.studentapi.repository.StudentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository repository;

    @InjectMocks
    private StudentService service;


    // --------------------------------------------------
    // 1. GET ALL STUDENTS
    // --------------------------------------------------

    @Test
    void getAllStudents_shouldReturnStudents() {

        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Owaiz");
        student1.setSubject("Java");
        student1.setMarks(90);

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Ahmed");
        student2.setSubject("SQL");
        student2.setMarks(85);

        when(repository.findAll())
                .thenReturn(List.of(student1, student2));

        List<StudentResponse> result =
                service.getAllStudents();

        assertEquals(2, result.size());

        assertEquals("Owaiz", result.get(0).getName());
        assertEquals("Java", result.get(0).getSubject());
        assertEquals(90, result.get(0).getMarks());

        assertEquals("Ahmed", result.get(1).getName());
        assertEquals("SQL", result.get(1).getSubject());
        assertEquals(85, result.get(1).getMarks());

        verify(repository).findAll();
    }


    // --------------------------------------------------
    // 2. GET STUDENT - SUCCESS
    // --------------------------------------------------

    @Test
    void getStudent_shouldReturnStudent() {

        Student student = new Student();

        student.setId(1);
        student.setName("Owaiz");
        student.setSubject("Java");
        student.setMarks(90);

        when(repository.findById(1))
                .thenReturn(Optional.of(student));

        StudentResponse result =
                service.getStudent(1);

        assertEquals(1, result.getId());
        assertEquals("Owaiz", result.getName());
        assertEquals("Java", result.getSubject());
        assertEquals(90, result.getMarks());

        verify(repository).findById(1);
    }


    // --------------------------------------------------
    // 3. GET STUDENT - NOT FOUND
    // --------------------------------------------------

    @Test
    void getStudent_shouldThrowException_whenStudentNotFound() {

        when(repository.findById(99))
                .thenReturn(Optional.empty());

        assertThrows(
                StudentNotFoundException.class,
                () -> service.getStudent(99)
        );

        verify(repository).findById(99);
    }


    // --------------------------------------------------
    // 4. ADD STUDENT
    // --------------------------------------------------

    @Test
    void addStudent_shouldReturnSavedStudent() {

        StudentRequest request = new StudentRequest();

        request.setId(1);
        request.setName("Owaiz");
        request.setSubject("Java");
        request.setMarks(90);

        Student savedStudent = new Student();

        savedStudent.setId(1);
        savedStudent.setName("Owaiz");
        savedStudent.setSubject("Java");
        savedStudent.setMarks(90);

        when(repository.save(any(Student.class)))
                .thenReturn(savedStudent);

        StudentResponse result =
                service.addStudent(request);

        assertEquals(1, result.getId());
        assertEquals("Owaiz", result.getName());
        assertEquals("Java", result.getSubject());
        assertEquals(90, result.getMarks());

        verify(repository).save(any(Student.class));
    }


    // --------------------------------------------------
    // 5. UPDATE STUDENT
    // --------------------------------------------------

    @Test
    void updateStudent_shouldReturnUpdatedStudent() {

        StudentRequest request = new StudentRequest();

        request.setName("Owaiz Updated");
        request.setSubject("Spring Boot");
        request.setMarks(95);

        Student savedStudent = new Student();

        savedStudent.setId(1);
        savedStudent.setName("Owaiz Updated");
        savedStudent.setSubject("Spring Boot");
        savedStudent.setMarks(95);

        when(repository.save(any(Student.class)))
                .thenReturn(savedStudent);

        StudentResponse result =
                service.updateStudent(1, request);

        assertEquals(1, result.getId());
        assertEquals("Owaiz Updated", result.getName());
        assertEquals("Spring Boot", result.getSubject());
        assertEquals(95, result.getMarks());

        verify(repository).save(any(Student.class));
    }


    // --------------------------------------------------
    // 6. DELETE STUDENT - SUCCESS
    // --------------------------------------------------

    @Test
    void deleteStudent_shouldReturnTrue_whenStudentDeleted() {

        when(repository.existsById(1))
                .thenReturn(false);

        boolean result =
                service.deleteStudent(1);

        assertTrue(result);

        verify(repository).deleteById(1);
        verify(repository).existsById(1);
    }


    // --------------------------------------------------
    // 7. DELETE STUDENT - STUDENT STILL EXISTS
    // --------------------------------------------------

    @Test
    void deleteStudent_shouldReturnFalse_whenStudentStillExists() {

        when(repository.existsById(1))
                .thenReturn(true);

        boolean result =
                service.deleteStudent(1);

        assertFalse(result);

        verify(repository).deleteById(1);
        verify(repository).existsById(1);
    }


    // --------------------------------------------------
    // 8. STUDENT EXISTENCE - TRUE
    // --------------------------------------------------

    @Test
    void studentExistanceById_shouldReturnTrue_whenStudentExists() {

        when(repository.existsById(1))
                .thenReturn(true);

        boolean result =
                service.studentExistanceById(1);

        assertTrue(result);

        verify(repository).existsById(1);
    }


    // --------------------------------------------------
    // 9. STUDENT EXISTENCE - FALSE
    // --------------------------------------------------

    @Test
    void studentExistanceById_shouldReturnFalse_whenStudentDoesNotExist() {

        when(repository.existsById(99))
                .thenReturn(false);

        boolean result =
                service.studentExistanceById(99);

        assertFalse(result);

        verify(repository).existsById(99);
    }


    // --------------------------------------------------
    // 10. PATCH STUDENT
    // --------------------------------------------------

    @Test
    void patchStudent_shouldUpdateStudent() {

        Student student = new Student();

        student.setId(1);
        student.setName("Old Name");
        student.setSubject("Java");
        student.setMarks(80);

        StudentPatchRequest request =
                new StudentPatchRequest();

        request.setName("Owaiz");
        request.setMarks(95);

        when(repository.findById(1))
                .thenReturn(Optional.of(student));

        when(repository.save(any(Student.class)))
                .thenReturn(student);

        StudentResponse result =
                service.patchStudent(1, request);

        assertEquals("Owaiz", result.getName());
        assertEquals("Java", result.getSubject());
        assertEquals(95, result.getMarks());

        verify(repository).findById(1);
        verify(repository).save(student);
    }


    // --------------------------------------------------
    // 11. PATCH STUDENT - NOT FOUND
    // --------------------------------------------------

    @Test
    void patchStudent_shouldThrowException_whenStudentNotFound() {

        when(repository.findById(99))
                .thenReturn(Optional.empty());

        StudentPatchRequest request =
                new StudentPatchRequest();

        assertThrows(
                StudentNotFoundException.class,
                () -> service.patchStudent(99, request)
        );

        verify(repository).findById(99);
    }
}