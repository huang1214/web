package com.aca.springboot.entities;

import java.util.Date;

/**
 * @Author: 周全
 * @Date: 2019/10/11 10:42
 * @Description:
 */
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private Date sbirthday;
    private String sdomitory;
    private String cno;   //班级ID
    private String spwd;
    private String stel;
    private String state;
    private String card_num;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }

    public Date getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(Date sbirthday) {
        this.sbirthday = sbirthday;
    }

    public String getSdomitory() {
        return sdomitory;
    }

    public void setSdomitory(String sdomitory) {
        this.sdomitory = sdomitory;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getSpwd() {
        return spwd;
    }

    public void setSpwd(String spwd) {
        this.spwd = spwd;
    }

    public String getStel() {
        return stel;
    }

    public void setStel(String stel) {
        this.stel = stel;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

}
