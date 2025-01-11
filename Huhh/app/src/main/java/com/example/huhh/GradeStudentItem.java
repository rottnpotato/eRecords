package com.example.huhh;

public class GradeStudentItem {
        String name;
        String section;
        String grade;

    public GradeStudentItem(String name, String section, String grade) {
        this.name = name;
        this.section = section;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
