
package com.example.huhh;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Subject {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("sem")
    @Expose
    private String sem;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("year")
    @Expose
    private int year;
    @SerializedName("coreq")
    @Expose
    private List<Integer> coreq;
    @SerializedName("hours")
    @Expose
    private double hours;
    @SerializedName("units")
    @Expose
    private double units;
    @SerializedName("funits")
    @Expose
    private double funits;
    @SerializedName("prereq")
    @Expose
    private List<Object> prereq;
    @SerializedName("punits")
    @Expose
    private double punits;
    @SerializedName("lab_hrs")
    @Expose
    private double labHrs;
    @SerializedName("lec_hrs")
    @Expose
    private double lecHrs;
    @SerializedName("lab_type")
    @Expose
    private String labType;
    @SerializedName("standing")
    @Expose
    private int standing;
    @SerializedName("is_active")
    @Expose
    private boolean isActive;
    @SerializedName("is_in_gpa")
    @Expose
    private boolean isInGpa;
    @SerializedName("lab_units")
    @Expose
    private double labUnits;
    @SerializedName("lec_units")
    @Expose
    private double lecUnits;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("updated_by")
    @Expose
    private int updatedBy;
    @SerializedName("curriculum_id")
    @Expose
    private int curriculumId;
    @SerializedName("campus_id")
    @Expose
    private int campusId;
    @SerializedName("grade_system")
    @Expose
    private String gradeSystem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Integer> getCoreq() {
        return coreq;
    }

    public void setCoreq(List<Integer> coreq) {
        this.coreq = coreq;
    }

    public double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    public double getFunits() {
        return funits;
    }

    public void setFunits(double funits) {
        this.funits = funits;
    }

    public List<Object> getPrereq() {
        return prereq;
    }

    public void setPrereq(List<Object> prereq) {
        this.prereq = prereq;
    }

    public double getPunits() {
        return punits;
    }

    public void setPunits(double punits) {
        this.punits = punits;
    }

    public double getLabHrs() {
        return labHrs;
    }

    public void setLabHrs(double labHrs) {
        this.labHrs = labHrs;
    }

    public double getLecHrs() {
        return lecHrs;
    }

    public void setLecHrs(double lecHrs) {
        this.lecHrs = lecHrs;
    }

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsInGpa() {
        return isInGpa;
    }

    public void setIsInGpa(boolean isInGpa) {
        this.isInGpa = isInGpa;
    }

    public double getLabUnits() {
        return labUnits;
    }

    public void setLabUnits(double labUnits) {
        this.labUnits = labUnits;
    }

    public double getLecUnits() {
        return lecUnits;
    }

    public void setLecUnits(double lecUnits) {
        this.lecUnits = lecUnits;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(int curriculumId) {
        this.curriculumId = curriculumId;
    }

    public int getCampusId() {
        return campusId;
    }

    public void setCampusId(int campusId) {
        this.campusId = campusId;
    }

    public String getGradeSystem() {
        return gradeSystem;
    }

    public void setGradeSystem(String gradeSystem) {
        this.gradeSystem = gradeSystem;
    }

}
