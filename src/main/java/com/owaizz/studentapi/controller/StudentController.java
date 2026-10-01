package com.owaizz.studentapi.controller;

import com.owaizz.studentapi.dto.MessageResponse;
import com.owaizz.studentapi.dto.StudentPatchRequest;
import com.owaizz.studentapi.dto.StudentRequest;
import com.owaizz.studentapi.dto.StudentResponse;
import com.owaizz.studentapi.entity.Student;
import com.owaizz.studentapi.exception.InvalidStudentException;
import com.owaizz.studentapi.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public List<StudentResponse> getAllStudents(){

        return service.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id) {

            StudentResponse student = service.getStudent(id);

            return ResponseEntity.ok(student);

    }

    @PostMapping("/students")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest student){

        int id = student.getId();

        if(service.studentExistanceById(id)) {

            return ResponseEntity
                    .status(409)
                    .body(new MessageResponse("stundet already exist"));

        }else {

            return ResponseEntity
                    .status(201)
                    .body(service.addStudent(student));
        }
    }

    @PostMapping("/students/{id}")
    public ResponseEntity<MessageResponse> badResponseId(@PathVariable int id){

        return ResponseEntity
                .status(400)
                .body(new MessageResponse("id : "+ id +" should not be mentioned"));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id ,@Valid @RequestBody StudentRequest student){

        if(student.getMarks() < 0){
            throw new InvalidStudentException("marks can't be negative");
        }

        if(service.studentExistanceById(id)) {

            return ResponseEntity.ok(service.updateStudent(id ,student));

        }else {

            return ResponseEntity
                    .status(404)
                    .body(new MessageResponse("stundet not found"));
        }
    }

    @PutMapping("/students")
    public ResponseEntity<MessageResponse> badResponse(){

        return ResponseEntity
                .status(400)
                .body(new MessageResponse("student not mentioned"));
    }

    @PatchMapping("/students/{id}")
    public ResponseEntity<StudentResponse> patchStudent(
            @PathVariable int id,
            @RequestBody StudentPatchRequest request
            ){

        return ResponseEntity.ok(service.patchStudent(id, request));
    }

    @PatchMapping("/students")
    public ResponseEntity<MessageResponse> badRequest(){

        return ResponseEntity
                .status(400)
                .body(new MessageResponse("student not mentioned"));
    }

    @DeleteMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){

        if(service.studentExistanceById(id)) {

            if(service.deleteStudent(id))
                return ResponseEntity.ok(new MessageResponse("deletion successfull"));
            else
                return ResponseEntity
                        .status(500)
                        .body(new MessageResponse("deletion failed"));

        }else {

            return ResponseEntity
                    .status(404)
                    .body(new MessageResponse("student not found"));
        }

    }

    @DeleteMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteStudent(){

        return ResponseEntity
                .status(400)
                .body(new MessageResponse("student not mentioned"));
    }
}
