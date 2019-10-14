package com.aca.springboot.vo;

import com.aca.springboot.entities.Bill;
import com.aca.springboot.entities.Competition;
import com.aca.springboot.entities.Student;

/**
 * @Author: 周全
 * @Date: 2019/10/14 19:05
 * @Description: 查询报销列表
 */
public class BillVO extends Bill {
    private Competition competition;
    private Student student;

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
