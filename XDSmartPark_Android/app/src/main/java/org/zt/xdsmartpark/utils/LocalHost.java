package org.zt.xdsmartpark.utils;

public enum LocalHost {
    INSTANCE;

    private static HostInfo sHostInfo;


    public void  init(){
        if (sHostInfo==null)
        sHostInfo=new HostInfo();
    }

    public int getUserId(){
        return sHostInfo.getUserId();
    }
    public String getUserName(){
        return sHostInfo.getUserName();
    }
    public String getCarPlate(){
        return sHostInfo.getCarPlate();
    }


    public void setUserId(int userId){
        sHostInfo.setUserId(userId);
    }
    public void setUserName(String userName){
        sHostInfo.setUserName(userName);
    }
    public void setCarPlate(String carPlate){
        sHostInfo.setCarPlate(carPlate);
    }

    static class HostInfo{
        private int userId;

        private String userName;

        private String carPlate;

        public String getCarPlate() {
            return carPlate;
        }

        public void setCarPlate(String carPlate) {
            this.carPlate = carPlate;
        }


        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }


}
