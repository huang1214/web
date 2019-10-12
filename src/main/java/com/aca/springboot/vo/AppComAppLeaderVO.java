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
    public Competition competion;
    public Student appplicantStu;
    public Student leaderStu;

    public AppComAppLeaderVO() {
    }

    public Competition getCompetion() {
        return competion;
    }

    public void setCompetion(Competition competion) {
        this.competion = competion;
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
