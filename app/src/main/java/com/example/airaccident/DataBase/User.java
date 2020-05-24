package com.example.airaccident.DataBase;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String useracct;
    private String userpwd;

    public User() {
    }

    public User(String useracct, String userpwd) {
        this.useracct = useracct;
        this.userpwd = userpwd;
    }

    public String getUseracct() {
        return useracct;
    }

    public void setUseracct(String useracct) {
        this.useracct = useracct;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }
}
