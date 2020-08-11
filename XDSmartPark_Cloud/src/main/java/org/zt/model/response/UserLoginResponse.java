package org.zt.model.response;

public class UserLoginResponse {
	
	private int userId;
    private String userName;
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

    public UserLoginResponse(int userId, String userName, String phone) {
    	this.userId = userId;
    	this.userName = userName;
        this.phone = phone;
    }
    
    public UserLoginResponse() {}

}
