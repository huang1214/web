package com.aca.springboot.entities;


public class Application {

  private String appid;
  private String ctid;  //比赛的ID
  private String applicantid;  //申请人ID
  private String unit;
  private String leader;
  private String studentprice;
  private String teacherprice;
  private String awardtypeid;
  private java.sql.Date awarddate;
  private String applicantbankcard;
  private String workname;
  private String workbriefintro;
  private String certificateimg;
  private String getawardimg;
  private String highlight;
  private String status;
  private String note;
  private Competition competition;

  public Competition getCompetition() {
    return competition;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }

  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }


  public String getCtid() {
    return ctid;
  }

  public void setCtid(String ctid) {
    this.ctid = ctid;
  }


  public String getApplicantid() {
    return applicantid;
  }

  public void setApplicantid(String applicantid) {
    this.applicantid = applicantid;
  }


  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }


  public String getLeader() {
    return leader;
  }

  public void setLeader(String leader) {
    this.leader = leader;
  }


  public String getStudentprice() {
    return studentprice;
  }

  public void setStudentprice(String studentprice) {
    this.studentprice = studentprice;
  }


  public String getTeacherprice() {
    return teacherprice;
  }

  public void setTeacherprice(String teacherprice) {
    this.teacherprice = teacherprice;
  }


  public String getAwardtypeid() {
    return awardtypeid;
  }

  public void setAwardtypeid(String awardtypeid) {
    this.awardtypeid = awardtypeid;
  }


  public java.sql.Date getAwarddate() {
    return awarddate;
  }

  public void setAwarddate(java.sql.Date awarddate) {
    this.awarddate = awarddate;
  }


  public String getApplicantbankcard() {
    return applicantbankcard;
  }

  public void setApplicantbankcard(String applicantbankcard) {
    this.applicantbankcard = applicantbankcard;
  }


  public String getWorkname() {
    return workname;
  }

  public void setWorkname(String workname) {
    this.workname = workname;
  }


  public String getWorkbriefintro() {
    return workbriefintro;
  }

  public void setWorkbriefintro(String workbriefintro) {
    this.workbriefintro = workbriefintro;
  }


  public String getCertificateimg() {
    return certificateimg;
  }

  public void setCertificateimg(String certificateimg) {
    this.certificateimg = certificateimg;
  }


  public String getGetawardimg() {
    return getawardimg;
  }

  public void setGetawardimg(String getawardimg) {
    this.getawardimg = getawardimg;
  }


  public String getHighlight() {
    return highlight;
  }

  public void setHighlight(String highlight) {
    this.highlight = highlight;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

}
