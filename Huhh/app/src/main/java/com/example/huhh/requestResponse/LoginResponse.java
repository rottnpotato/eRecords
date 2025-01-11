package com.example.huhh.requestResponse;

public class LoginResponse {
    private String user_id;
    private String jwt;
    private String rft;
    private String jwt_exp;
    private Boolean remember;

    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getRft() {
        return rft;
    }

    public void setRft(String rft) {
        this.rft = rft;
    }

    public String getJwt_exp() {
        return jwt_exp;
    }

    public void setJwt_exp(String jwt_exp) {
        this.jwt_exp = jwt_exp;
    }

    public Boolean getRemember() {
        return remember;
    }

    public void setRemember(Boolean remember) {
        this.remember = remember;
    }
}
