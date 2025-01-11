package com.example.huhh.requestResponse;

public class userData {
    private String name;
    private String fullname;
    private String sy;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSy() {
        return sy;
    }

    public void setSy(String sy) {
        this.sy = sy;
    }

    public userData(String name, String fullname, String sy) {
        this.name = name;
        this.fullname = fullname;
        this.sy = sy;
    }
}

