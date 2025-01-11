package com.example.huhh;

public class QuizAddItem {
        int entryNumber;
        int scoreObtained;
        int hps;
        String section;
        String subjectCode;
        String date;
        String name;

    public QuizAddItem(int entryNumber, int scoreObtained, int hps, String section, String subjectCode, String date, String name) {
        this.entryNumber = entryNumber;
        this.scoreObtained = scoreObtained;
        this.hps = hps;
        this.section = section;
        this.subjectCode = subjectCode;
        this.date = date;
        this.name = name;
    }

    public int getEntryNumber() {
        return entryNumber;
    }

    public void setEntryNumber(int entryNumber) {
        this.entryNumber = entryNumber;
    }

    public int getScoreObtained() {
        return scoreObtained;
    }

    public void setScoreObtained(int scoreObtained) {
        this.scoreObtained = scoreObtained;
    }

    public int getHps() {
        return hps;
    }

    public void setHps(int hps) {
        this.hps = hps;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
