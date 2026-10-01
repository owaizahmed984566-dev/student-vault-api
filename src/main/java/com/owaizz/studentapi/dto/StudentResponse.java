package com.owaizz.studentapi.dto;

public class StudentResponse {

    private int id;
    private String name;
    private String subject;
    private int marks;

    public StudentResponse(int id, String name, String subject, int marks) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.marks = marks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public int getMarks() {
        return marks;
    }
}