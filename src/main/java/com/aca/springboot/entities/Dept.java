package com.aca.springboot.entities;

/**
 * @Author: 周全
 * @Date: 2019/10/11 11:29
 * @Description:
 */
public class Dept {
    private String dno;
    private String dname;
    private String dadmin;
    private String dtel;
    private String dcollege;

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDadmin() {
        return dadmin;
    }

    public void setDadmin(String dadmin) {
        this.dadmin = dadmin;
    }

    public String getDtel() {
        return dtel;
    }

    public void setDtel(String dtel) {
        this.dtel = dtel;
    }

    public String getDcollege() {
        return dcollege;
    }

    public void setDcollege(String dcollege) {
        this.dcollege = dcollege;
    }
}
