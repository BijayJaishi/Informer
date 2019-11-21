package com.heartsun.informer;

/**
 * Created by Bijay on 11/20/2019.
 */

public class LoginModel {
    public   String MobileNo;
    public   String password;

    public LoginModel() {

    }

    public LoginModel(String mobileNo, String password) {
        MobileNo = mobileNo;
        this.password = password;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


