package com.aca.springboot.entities;


import java.sql.Date;

public class Application {

  private String appid;
  private String ctid;  //比赛的ID
  private String applicantId;  //申请人ID
  private String unit;
  private String leader;
  private String studentPrice;
  private String teacherPrice;
  private String awardTypeId;
  private String awardDate;
  private String applicantBankCard;
  private String workName;
  private String workBriefIntro;
  private String certificateImg;
  private String getawardImg;
  private String highLight;
  private String status;
  private String note;

  @Override
  public String toString() {
    return "Application{" +
            "appid='" + appid + '\'' +
            ", ctid='" + ctid + '\'' +
            ", applicantId='" + applicantId + '\'' +
            ", unit='" + unit + '\'' +
            ", leader='" + leader + '\'' +
            ", studentPrice='" + studentPrice + '\'' +
            ", teacherPrice='" + teacherPrice + '\'' +
            ", awardTypeId='" + awardTypeId + '\'' +
            ", awardDate=" + awardDate +
            ", applicantBankCard='" + applicantBankCard + '\'' +
            ", workName='" + workName + '\'' +
            ", workBriefIntro='" + workBriefIntro + '\'' +
            ", certificateImg='" + certificateImg + '\'' +
            ", getawardImg='" + getawardImg + '\'' +
            ", highLight='" + highLight + '\'' +
            ", status='" + status + '\'' +
            ", note='" + note + '\'' +
            ", competition=" + competition +
            '}';
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

  public String getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(String applicantId) {
    this.applicantId = applicantId;
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

  public String getStudentPrice() {
    return studentPrice;
  }

  public void setStudentPrice(String studentPrice) {
    this.studentPrice = studentPrice;
  }

  public String getTeacherPrice() {
    return teacherPrice;
  }

  public void setTeacherPrice(String teacherPrice) {
    this.teacherPrice = teacherPrice;
  }

  public String getAwardTypeId() {
    return awardTypeId;
  }

  public void setAwardTypeId(String awardTypeId) {
    this.awardTypeId = awardTypeId;
  }

  public String getAwardDate() {
    return awardDate;
  }

  public void setAwardDate(String awardDate) {
    this.awardDate = awardDate;
  }

  public String getApplicantBankCard() {
    return applicantBankCard;
  }

  public void setApplicantBankCard(String applicantBankCard) {
    this.applicantBankCard = applicantBankCard;
  }

  public String getWorkName() {
    return workName;
  }

  public void setWorkName(String workName) {
    this.workName = workName;
  }

  public String getWorkBriefIntro() {
    return workBriefIntro;
  }

  public void setWorkBriefIntro(String workBriefIntro) {
    this.workBriefIntro = workBriefIntro;
  }

  public String getCertificateImg() {
    return certificateImg;
  }

  public void setCertificateImg(String certificateImg) {
    this.certificateImg = certificateImg;
  }

  public String getGetawardImg() {
    return getawardImg;
  }

  public void setGetawardImg(String getawardImg) {
    this.getawardImg = getawardImg;
  }

  public String getHighLight() {
    return highLight;
  }

  public void setHighLight(String highLight) {
    this.highLight = highLight;
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

  public Competition getCompetition() {
    return competition;
  }

  public void setCompetition(Competition competition) {
    this.competition = competition;
  }

  private Competition competition;

}
