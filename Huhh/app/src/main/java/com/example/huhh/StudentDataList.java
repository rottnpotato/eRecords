package com.example.huhh;

public class StudentDataList {
    private String studentid;
    private String name;
    private String subject;
    private String section;

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public StudentDataList(String studentid, String name, String subject, String section) {
        this.studentid = studentid;
        this.name = name;
        this.subject = subject;
        this.section = section;
    }
}
