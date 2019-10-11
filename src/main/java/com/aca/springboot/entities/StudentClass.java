package com.aca.springboot.entities;

/**
 * @Author: 周全
 * @Date: 2019/10/11 11:07
 * @Description:
 */
public class StudentClass {
    private String cno;
    private String cname;
    private String dno;  //部门ID
    private String cgrade;
    private String cadmin;
    private String cadmintel;
    private String cmaster;
    private String cmastertel;
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getCgrade() {
        return cgrade;
    }

    public void setCgrade(String cgrade) {
        this.cgrade = cgrade;
    }

    public String getCadmin() {
        return cadmin;
    }

    public void setCadmin(String cadmin) {
        this.cadmin = cadmin;
    }

    public String getCadmintel() {
        return cadmintel;
    }

    public void setCadmintel(String cadmintel) {
        this.cadmintel = cadmintel;
    }

    public String getCmaster() {
        return cmaster;
    }

    public void setCmaster(String cmaster) {
        this.cmaster = cmaster;
    }

    public String getCmastertel() {
        return cmastertel;
    }

    public void setCmastertel(String cmastertel) {
        this.cmastertel = cmastertel;
    }
}
