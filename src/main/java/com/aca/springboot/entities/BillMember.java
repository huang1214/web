package com.aca.springboot.entities;


public class BillMember {

  private String billId;
  private String billTmId;
  private String billType;
  private String billOrder;


  public String getBillId() {
    return billId;
  }

  public void setBillId(String billId) {
    this.billId = billId;
  }


  public String getBillTmId() {
    return billTmId;
  }

  public void setBillTmId(String billTmId) {
    this.billTmId = billTmId;
  }


  public String getBillType() {
    return billType;
  }

  public void setBillType(String billType) {
    this.billType = billType;
  }


  public String getBillOrder() {
    return billOrder;
  }

  public void setBillOrder(String billOrder) {
    this.billOrder = billOrder;
  }

}
