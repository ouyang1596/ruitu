package com.example.retrofitdemo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table DB_USER.
 */
public class Db_user {

    private Long id;
    private String user_name;
    private String loginName;
    private String pwd;
    private String qq;
    private String wechat;
    private String phone;
    private String deviceno;

    public Db_user() {
    }

    public Db_user(Long id) {
        this.id = id;
    }

    public Db_user(Long id, String user_name, String loginName, String pwd, String qq, String wechat, String phone, String deviceno) {
        this.id = id;
        this.user_name = user_name;
        this.loginName = loginName;
        this.pwd = pwd;
        this.qq = qq;
        this.wechat = wechat;
        this.phone = phone;
        this.deviceno = deviceno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDeviceno() {
        return deviceno;
    }

    public void setDeviceno(String deviceno) {
        this.deviceno = deviceno;
    }

}
