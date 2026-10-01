package com.owaizz.studentapi.service;

import com.owaizz.studentapi.dto.StudentPatchRequest;
import com.owaizz.studentapi.dto.StudentRequest;
import com.owaizz.studentapi.dto.StudentResponse;
import com.owaizz.studentapi.entity.Student;
import com.owaizz.studentapi.exception.StudentNotFoundException;
import com.owaizz.studentapi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public List<StudentResponse> getAllStudents() {

        logger.info("Fetching all students");

        List<StudentResponse> response = new ArrayList<>();

        List<Student> studentList = repository.findAll();

        for (int i = 0; i < studentList.size(); i++) {

            Student student = studentList.get(i);

            StudentResponse response1 = new StudentResponse(
                    student.getId(),
                    student.getName(),
                    student.getSubject(),
                    student.getMarks()
            );

            response.add(response1);
        }

        logger.info("Fetched {} students", response.size());

        return response;
    }

    public StudentResponse getStudent(int id) {

        logger.info("Fetching student with id: {}", id);

        Student student = repository.findById(id).orElse(null);

        if (student == null) {
            logger.warn("Student not found with id: {}", id);
            throw new StudentNotFoundException("student not found");
        }

        StudentResponse response = new StudentResponse(
                student.getId(),
                student.getName(),
                student.getSubject(),
                student.getMarks()
        );

        logger.info("Student found with id: {}", id);

        return response;
    }

    public StudentResponse addStudent(StudentRequest request) {

        logger.info("Adding student with id: {}", request.getId());

        Student student = new Student();

        student.setId(request.getId());
        student.setName(request.getName());
        student.setSubject(request.getSubject());
        student.setMarks(request.getMarks());

        Student savedStudent = repository.save(student);

        StudentResponse response = new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getSubject(),
                savedStudent.getMarks()
        );

        logger.info("Student added successfully with id: {}", savedStudent.getId());

        return response;
    }

    public StudentResponse updateStudent(int id, StudentRequest request) {

        logger.info("Updating student with id: {}", id);

        Student student = new Student();

        student.setId(id);
        student.setName(request.getName());
        student.setSubject(request.getSubject());
        student.setMarks(request.getMarks());

        Student savedStudent = repository.save(student);

        StudentResponse response = new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getSubject(),
                savedStudent.getMarks()
        );

        logger.info("Student updated successfully with id: {}", id);

        return response;
    }

    public boolean deleteStudent(int id) {

        logger.info("Deleting student with id: {}", id);

        repository.deleteById(id);

        if (!repository.existsById(id)) {
            logger.info("Student deleted successfully with id: {}", id);
            return true;
        } else {
            logger.error("Failed to delete student with id: {}", id);
            return false;
        }
    }

    public boolean studentExistanceById(int id) {

        boolean exists = repository.existsById(id);

        logger.debug("Checking student existence for id: {} -> {}", id, exists);

        return exists;
    }

    public StudentResponse patchStudent(
            int id,
            StudentPatchRequest request) {

        logger.info("Partially updating student with id: {}", id);

        Student student = repository.findById(id).orElse(null);

        if (student == null) {
            logger.warn("Student not found for patch with id: {}", id);
            throw new StudentNotFoundException("student not found");
        }

        if (request.getName() != null)
            student.setName(request.getName());

        if (request.getSubject() != null)
            student.setSubject(request.getSubject());

        if (request.getMarks() != null)
            student.setMarks(request.getMarks());

        Student savedStudent = repository.save(student);

        StudentResponse response = new StudentResponse(
                savedStudent.getId(),
                savedStudent.getName(),
                savedStudent.getSubject(),
                savedStudent.getMarks()
        );

        logger.info("Student patched successfully with id: {}", id);

        return response;
    }
}