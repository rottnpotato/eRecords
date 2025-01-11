package com.example.huhh;

public class QuizViewingItem {
        String name;
        String section;
        String scoreObtained;
        String hps;
        String date;

    public QuizViewingItem(String name, String section, String scoreObtained, String hps, String date) {
        this.name = name;
        this.section = section;
        this.scoreObtained = scoreObtained;
        this.hps = hps;
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

    public String getScoreObtained() {
        return scoreObtained;
    }

    public void setScoreObtained(String scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public String getHps() {
        return hps;
    }

    public void setHps(String hps) {
        this.hps = hps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
