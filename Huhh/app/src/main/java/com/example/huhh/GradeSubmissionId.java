
package com.example.huhh;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class GradeSubmissionId {

    @SerializedName("Int64")
    @Expose
    private int int64;
    @SerializedName("Valid")
    @Expose
    private boolean valid;

    public int getInt64() {
        return int64;
    }

    public void setInt64(int int64) {
        this.int64 = int64;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
