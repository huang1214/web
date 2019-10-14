package com.aca.springboot.vo;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.Competition;
import com.aca.springboot.entities.Student;
import com.aca.springboot.entities.Teacher;

import java.util.List;

/**
 * @ClassName AppComDetailVO
 * @Author HWG
 * @Time 2019/10/14 10:22
 */

public class AppComDetailVO extends Application {
    private Competition com;
    private Student appStu;
    private List<Student> stus;
    private List<Teacher> teas;

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

    public List<Student> getStus() {
        return stus;
    }

    public void setStus(List<Student> stus) {
        this.stus = stus;
    }

    public List<Teacher> getTeas() {
        return teas;
    }

    public void setTeas(List<Teacher> teas) {
        this.teas = teas;
    }
}
