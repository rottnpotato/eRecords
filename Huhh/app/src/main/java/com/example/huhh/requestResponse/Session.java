package com.example.huhh.requestResponse;

public class Session {
    private String sy;
    private String sem;
    private String campus;
    private String campus_color;
    private String campus_address;

    public String getSy() {
        return sy;
    }

    public String getSem() {
        return sem;
    }

    public String getCampus() {
        return campus;
    }

    public String getCampus_color() {
        return campus_color;
    }

    public String getCampus_address() {
        return campus_address;
    }

    public Session(String sy, String sem, String campus, String campus_color, String campus_address) {
        this.sy = sy;
        this.sem = sem;
        this.campus = campus;
        this.campus_color = campus_color;
        this.campus_address = campus_address;
    }
}
