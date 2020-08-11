package org.zt.xdsmartpark.model.response;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private int userId;

    @SerializedName("phone")
    private String phone;

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public int getUserId(){
        return userId;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public LoginResponse(String userName, int userId, String phone) {
        this.userName = userName;
        this.userId = userId;
        this.phone = phone;
    }
}
