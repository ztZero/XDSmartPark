package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("userName")
    String userName;
    @SerializedName("userPassword")
    String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public RegisterRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
