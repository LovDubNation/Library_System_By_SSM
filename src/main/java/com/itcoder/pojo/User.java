package com.itcoder.pojo;


import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;


public class User implements Serializable {

  private long userId;
  private String userName;
  private String userPassword;
  private String userEmail;
  private String userRole;
  private String userStatus;


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


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


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public String getUserRole() {
    return userRole;
  }

  public void setUserRole(String userRole) {
    this.userRole = userRole;
  }


  public String getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(String userStatus) {
    this.userStatus = userStatus;
  }

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", userName='" + userName + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", userRole='" + userRole + '\'' +
            ", userStatus='" + userStatus + '\'' +
            '}';
  }
}
