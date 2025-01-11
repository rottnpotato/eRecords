package com.example.huhh;

public class ViewItem {
        String name;
        String section;
        String attendance;
        String date;

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ViewItem(String name, String section, String attendance, String date) {
        this.name = name;
        this.section = section;
        this.attendance = attendance;
        this.date = date;
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
}
