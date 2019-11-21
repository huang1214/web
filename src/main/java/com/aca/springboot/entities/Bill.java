package com.aca.springboot.entities;


import java.util.List;

public class Bill {

  private String bid;
  private String ctid;
  private String cyear;
  private String clevel;
  private String cdesc;
  private String groupleader;
  private String groupname;
  private String resultType;
  private String workName;
  private String preditfeedesc;
  private String predictfee;
  private String state;
  private String attachfile;
  private String note;
  private String invitation;
  private List<BillMember> billMembers;

  public String getInvitation() {
    return invitation;
  }

  public void setInvitation(String invitation) {
    this.invitation = invitation;
  }

  public List<BillMember> getBillMembers() {
    return billMembers;
  }

  public void setBillMembers(List<BillMember> billMembers) {
    this.billMembers = billMembers;
  }

  public String getBid() {
    return bid;
  }

  public void setBid(String bid) {
    this.bid = bid;
  }


  public String getCtid() {
    return ctid;
  }

  public void setCtid(String ctid) {
    this.ctid = ctid;
  }


  public String getCyear() {
    return cyear;
  }

  public void setCyear(String cyear) {
    this.cyear = cyear;
  }

  public String getClevel() {
    return clevel;
  }

  public void setClevel(String clevel) {
    this.clevel = clevel;
  }


  public String getCdesc() {
    return cdesc;
  }

  public void setCdesc(String cdesc) {
    this.cdesc = cdesc;
  }


  public String getGroupleader() {
    return groupleader;
  }

  public void setGroupleader(String groupleader) {
    this.groupleader = groupleader;
  }


  public String getGroupname() {
    return groupname;
  }

  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }


  public String getResultType() {
    return resultType;
  }

  public void setResultType(String resultType) {
    this.resultType = resultType;
  }


  public String getWorkName() {
    return workName;
  }

  public void setWorkName(String workName) {
    this.workName = workName;
  }


  public String getPreditfeedesc() {
    return preditfeedesc;
  }

  public void setPreditfeedesc(String preditfeedesc) {
    this.preditfeedesc = preditfeedesc;
  }


  public String getPredictfee() {
    return predictfee;
  }

  public void setPredictfee(String predictfee) {
    this.predictfee = predictfee;
  }


  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }


  public String getAttachfile() {
    return attachfile;
  }

  public void setAttachfile(String attachfile) {
    this.attachfile = attachfile;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  @Override
  public String toString() {
    return "Bill{" +
            "bid='" + bid + '\'' +
            ", ctid='" + ctid + '\'' +
            ", cyear=" + cyear +
            ", clevel='" + clevel + '\'' +
            ", cdesc='" + cdesc + '\'' +
            ", groupleader='" + groupleader + '\'' +
            ", groupname='" + groupname + '\'' +
            ", resultType='" + resultType + '\'' +
            ", workName='" + workName + '\'' +
            ", preditfeedesc='" + preditfeedesc + '\'' +
            ", predictfee='" + predictfee + '\'' +
            ", state='" + state + '\'' +
            ", attachfile='" + attachfile + '\'' +
            ", note='" + note + '\'' +
            '}';
  }
}
