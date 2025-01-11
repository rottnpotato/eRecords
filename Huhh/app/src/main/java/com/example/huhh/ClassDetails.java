
package com.example.huhh;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Generated("jsonschema2pojo")
public class ClassDetails {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("section")
    @Expose
    private String section;
    @SerializedName("subject_codes")
    @Expose
    private String subjectCodes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ClassDetails() {
    }

    /**
     * 
     * @param subjectCodes
     * @param section
     * @param id
     */
    public ClassDetails(int id, String section, String subjectCodes) {
        super();
        this.id = id;
        this.section = section;
        this.subjectCodes = subjectCodes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubjectCodes() {
        return subjectCodes;
    }

    public void setSubjectCodes(String subjectCodes) {
        this.subjectCodes = subjectCodes;
    }

}
