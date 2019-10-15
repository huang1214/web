package com.aca.springboot.entities;


public class BillMember {

  private String billId;
  private String billTmId;
  private int billType;
  private int billOrder;

  public int getBillType() {
    return billType;
  }

  public void setBillType(int billType) {
    this.billType = billType;
  }

  public int getBillOrder() {
    return billOrder;
  }

  public void setBillOrder(int billOrder) {
    this.billOrder = billOrder;
  }

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


}
