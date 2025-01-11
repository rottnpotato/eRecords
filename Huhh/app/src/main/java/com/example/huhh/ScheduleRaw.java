
package com.example.huhh;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class ScheduleRaw {

    @SerializedName("String")
    @Expose
    private String string;
    @SerializedName("Valid")
    @Expose
    private boolean valid;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
