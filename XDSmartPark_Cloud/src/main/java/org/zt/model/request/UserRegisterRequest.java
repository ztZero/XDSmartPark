package org.zt.model.request;

public class UserRegisterRequest {
	
    String userName;
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
    
    public UserRegisterRequest() {
    }

    public UserRegisterRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
}
