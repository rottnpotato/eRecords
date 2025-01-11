package com.example.huhh.requestResponse;

import com.example.huhh.Me;

public class UserDetails {

    private Me me;
    private Session session;

    public UserDetails(Me me, Session session) {
        this.me = me;
        this.session = session;
    }

    public Me getMe() {
        return me;
    }

    public void setMe(Me me) {
        this.me = me;
    }

    public Session getSession() {
        return session;
    }

    public void setMeta(Session session) {
        this.session = session;
    }
}
