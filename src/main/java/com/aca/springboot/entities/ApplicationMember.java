package com.aca.springboot.entities;


public class ApplicationMember {

  private String appId;
  private String appTmId;
  private String appType;
  private String appOrder;
  private int propertion;
  private int money;
  private int status;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public int getPropertion() {
    return propertion;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public void setPropertion(int propertion) {
    this.propertion = propertion;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public String getAppTmId() {
    return appTmId;
  }

  public void setAppTmId(String appTmId) {
    this.appTmId = appTmId;
  }


  public String getAppType() {
    return appType;
  }

  public void setAppType(String appType) {
    this.appType = appType;
  }


  public String getAppOrder() {
    return appOrder;
  }

  public void setAppOrder(String appOrder) {
    this.appOrder = appOrder;
  }

  public ApplicationMember(String appId, String appTmId, String appType, String appOrder, int propertion, int money) {
    this.appId = appId;
    this.appTmId = appTmId;
    this.appType = appType;
    this.appOrder = appOrder;
    this.propertion = propertion;
    this.money = money;
  }

  public ApplicationMember(String appId, String appTmId, String appType, String appOrder) {
    this.appId = appId;
    this.appTmId = appTmId;
    this.appType = appType;
    this.appOrder = appOrder;
  }
}
