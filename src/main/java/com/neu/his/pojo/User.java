package com.neu.his.pojo;

import java.io.Serializable;

/**
 * 数据库表user对应的实体类User
 *
 * @author 软英1702 马洪升 20175188
 * @date 2019年7月22日11:49:38
 */
public class User implements Serializable {
    private int userID;
    private String userLoginName;
    private String userPSW;
    private String userName;
    private int userType;

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserPSW() {
        return userPSW;
    }

    public void setUserPSW(String userPSW) {
        this.userPSW = userPSW;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }
}
