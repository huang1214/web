package com.aca.springboot.entities;


public class ApplicationMember {

  private String appId;
  private String appTmId;
  private String appType;
  private String appOrder;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
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

}
