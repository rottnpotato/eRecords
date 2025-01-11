package com.example.huhh;

public class Me {

    private String id;
    private String name;
    private String full_name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    //constructors

    public Me(String id, String name, String full_name) {
        this.id = id;
        this.name = name;
        this.full_name = full_name;
    }
}

