
package com.example.huhh;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Header {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("subject_ids")
    @Expose
    private List<Integer> subjectIds;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("max_slots")
    @Expose
    private String maxSlots;
    @SerializedName("schedule_raw")
    @Expose
    private ScheduleRaw scheduleRaw;
    @SerializedName("is_offsem")
    @Expose
    private boolean isOffsem;
    @SerializedName("semcamp_id")
    @Expose
    private int semcampId;
    @SerializedName("instructor_id")
    @Expose
    private int instructorId;
    @SerializedName("updated_by")
    @Expose
    private int updatedBy;
    @SerializedName("subjects")
    @Expose
    private List<Subject> subjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Integer> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<Integer> subjectIds) {
        this.subjectIds = subjectIds;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMaxSlots() {
        return maxSlots;
    }

    public void setMaxSlots(String maxSlots) {
        this.maxSlots = maxSlots;
    }

    public ScheduleRaw getScheduleRaw() {
        return scheduleRaw;
    }

    public void setScheduleRaw(ScheduleRaw scheduleRaw) {
        this.scheduleRaw = scheduleRaw;
    }

    public boolean isIsOffsem() {
        return isOffsem;
    }

    public void setIsOffsem(boolean isOffsem) {
        this.isOffsem = isOffsem;
    }

    public int getSemcampId() {
        return semcampId;
    }

    public void setSemcampId(int semcampId) {
        this.semcampId = semcampId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

}
