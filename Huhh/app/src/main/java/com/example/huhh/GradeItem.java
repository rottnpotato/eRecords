package com.example.huhh;

public class GradeItem {
        String subject;
        String section;

    public GradeItem(String subject, String section) {
        this.subject = subject;
        this.section = section;
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
}
