package com.example.huhh.requestResponse;

public class QuizItem {
        String name;
        String section;
        String hps;
        String score;

    public QuizItem(String name, String section, String hps, String score) {
        this.name = name;
        this.section = section;
        this.hps = hps;
        this.score = score;
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

    public String getHps() {
        return hps;
    }

    public void setHps(String hps) {
        this.hps = hps;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
