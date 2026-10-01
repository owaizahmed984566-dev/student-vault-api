package com.owaizz.studentapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudentRequest {

    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Subject cannot be empty")
    private String subject;

    @Min(value = 0, message = "Marks cannot be less than 0")
//    @Max(value = 100, message = "Marks cannot be greater than 100")
    private int marks;

    public StudentRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }
}