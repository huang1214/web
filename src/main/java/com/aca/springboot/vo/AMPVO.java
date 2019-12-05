package com.aca.springboot.vo;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.ApplicationMember;
import com.aca.springboot.entities.Award;
import com.aca.springboot.entities.Competition;

import java.util.List;

/**
 * @ClassName AMPVO
 * @Author HWG
 * @Time 2019/12/5 16:12
 */

public class AMPVO extends Application {
    private Competition com;
    private Award awar;
    private List<StudentAMVO> stus;
    private List<TeacherAMVO> teas;

    public AMPVO() {
    }

    public Competition getCom() {
        return com;
    }

    public void setCom(Competition com) {
        this.com = com;
    }

    public Award getAwar() {
        return awar;
    }

    public void setAwar(Award awar) {
        this.awar = awar;
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
}
