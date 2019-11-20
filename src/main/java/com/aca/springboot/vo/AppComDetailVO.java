package com.aca.springboot.vo;

import com.aca.springboot.entities.*;

import java.util.List;

/**
 * @ClassName AppComDetailVO
 * @Author HWG
 * @Time 2019/10/14 10:22
 */

public class AppComDetailVO extends Application {
    private Competition com;
    private Student appStu;
    private Award awar;
    private int res;
    private List<StudentAMVO> stus;
    private List<TeacherAMVO> teas;

    public Award getAwar() {
        return awar;
    }

    public void setAwar(Award awar) {
        this.awar = awar;
    }

    public Competition getCom() {
        return com;
    }

    public void setCom(Competition com) {
        this.com = com;
    }

    public Student getAppStu() {
        return appStu;
    }

    public void setAppStu(Student appStu) {
        this.appStu = appStu;
    }

    public List<StudentAMVO> getStus() {
        return stus;
    }

    public void setStus(List<StudentAMVO> stus) {
        this.stus = stus;
    }

    public List<TeacherAMVO> getTeas() {
        return teas;
    }

    public void setTeas(List<TeacherAMVO> teas) {
        this.teas = teas;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }
}
