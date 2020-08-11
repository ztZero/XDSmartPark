package org.zt.model.request;

public class UserLoginRequest {
	
    String userName;
    String userPassword;

    public UserLoginRequest(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }
    
    public UserLoginRequest() {}

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

}
