
package com.example.huhh;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Student {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("semcamp_student_id")
    @Expose
    private int semcampStudentId;
    @SerializedName("offering_subject_id")
    @Expose
    private int offeringSubjectId;
    @SerializedName("mt")
    @Expose
    private String mt;
    @SerializedName("ft")
    @Expose
    private String ft;
    @SerializedName("fg")
    @Expose
    private String fg;
    @SerializedName("re")
    @Expose
    private String re;
    @SerializedName("passed")
    @Expose
    private boolean passed;
    @SerializedName("is_approved")
    @Expose
    private boolean isApproved;
    @SerializedName("is_grade_approved")
    @Expose
    private boolean isGradeApproved;
    @SerializedName("is_locked")
    @Expose
    private boolean isLocked;
    @SerializedName("is_submitted")
    @Expose
    private boolean isSubmitted;
    @SerializedName("grade_submission_id")
    @Expose
    private GradeSubmissionId gradeSubmissionId;
    @SerializedName("action")
    @Expose
    private int action;
    @SerializedName("remarks")
    @Expose
    private String remarks;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("updated_by")
    @Expose
    private int updatedBy;
    @SerializedName("student_id")
    @Expose
    private int studentId;
    @SerializedName("student_name")
    @Expose
    private String studentName;
    @SerializedName("is_checked")
    @Expose
    private boolean isChecked;
    @SerializedName("mt_failed")
    @Expose
    private boolean mtFailed;
    @SerializedName("ft_failed")
    @Expose
    private boolean ftFailed;
    @SerializedName("fg_failed")
    @Expose
    private boolean fgFailed;
    @SerializedName("re_failed")
    @Expose
    private boolean reFailed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSemcampStudentId() {
        return semcampStudentId;
    }

    public void setSemcampStudentId(int semcampStudentId) {
        this.semcampStudentId = semcampStudentId;
    }

    public int getOfferingSubjectId() {
        return offeringSubjectId;
    }

    public void setOfferingSubjectId(int offeringSubjectId) {
        this.offeringSubjectId = offeringSubjectId;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public String getFt() {
        return ft;
    }

    public void setFt(String ft) {
        this.ft = ft;
    }

    public String getFg() {
        return fg;
    }

    public void setFg(String fg) {
        this.fg = fg;
    }

    public String getRe() {
        return re;
    }

    public void setRe(String re) {
        this.re = re;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isIsGradeApproved() {
        return isGradeApproved;
    }

    public void setIsGradeApproved(boolean isGradeApproved) {
        this.isGradeApproved = isGradeApproved;
    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public boolean isIsSubmitted() {
        return isSubmitted;
    }

    public void setIsSubmitted(boolean isSubmitted) {
        this.isSubmitted = isSubmitted;
    }

    public GradeSubmissionId getGradeSubmissionId() {
        return gradeSubmissionId;
    }

    public void setGradeSubmissionId(GradeSubmissionId gradeSubmissionId) {
        this.gradeSubmissionId = gradeSubmissionId;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isMtFailed() {
        return mtFailed;
    }

    public void setMtFailed(boolean mtFailed) {
        this.mtFailed = mtFailed;
    }

    public boolean isFtFailed() {
        return ftFailed;
    }

    public void setFtFailed(boolean ftFailed) {
        this.ftFailed = ftFailed;
    }

    public boolean isFgFailed() {
        return fgFailed;
    }

    public void setFgFailed(boolean fgFailed) {
        this.fgFailed = fgFailed;
    }

    public boolean isReFailed() {
        return reFailed;
    }

    public void setReFailed(boolean reFailed) {
        this.reFailed = reFailed;
    }

}
