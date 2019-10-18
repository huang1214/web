package com.aca.springboot.vo;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.Competition;
import com.aca.springboot.entities.Student;

/**
 * @ClassName AppComAppLeaderVO
 * @Author HWG
 * @Time 2019/10/12 9:33
 * 申请+竞赛+申请人信息+队长信息
 */

public class AppComAppLeaderVO extends Application {
    private Competition competion;
    private Student appplicantStu;
    private Student leaderStu;
    private int res;

    public AppComAppLeaderVO() {
    }

    public Competition getCompetion() {
        return competion;
    }

    public void setCompetion(Competition competion) {
        this.competion = competion;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public Student getAppplicantStu() {
        return appplicantStu;
    }

    public void setAppplicantStu(Student appplicantStu) {
        this.appplicantStu = appplicantStu;
    }

    public Student getLeaderStu() {
        return leaderStu;
    }

    public void setLeaderStu(Student leaderStu) {
        this.leaderStu = leaderStu;
    }
}
