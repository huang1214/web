package com.aca.springboot.vo;

import com.aca.springboot.entities.*;

import java.util.List;

/**
 * @Author: 周全
 * @Date: 2019/10/14 19:05
 * @Description: 查询报销列表
 */
public class BillVO extends Bill {

    private Competition competition;
    private Student student;
    private List<Student>  students;
    private List<Teacher> teachers;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

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
