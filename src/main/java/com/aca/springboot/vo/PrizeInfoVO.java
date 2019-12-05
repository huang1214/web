package com.aca.springboot.vo;

/**
 * @ClassName PrizeInfoVO
 * @Author HWG
 * @Time 2019/12/5 16:01
 */

public class PrizeInfoVO {
    private int index;
    private String teachers;
    private String students;
    private String workName;
    private String prizeName;
    private String date;

    public PrizeInfoVO(int index, String teachers, String students, String workName, String prizeName, String date) {
        this.index = index;
        this.teachers = teachers;
        this.students = students;
        this.workName = workName;
        this.prizeName = prizeName;
        this.date = date;
    }

    @Override
    public String  toString() {
        return "PrizeInfoVO{" +
                "index=" + index +
                ", teachers='" + teachers + '\'' +
                ", students='" + students + '\'' +
                ", workName='" + workName + '\'' +
                ", prizeName='" + prizeName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public PrizeInfoVO() {
        this.teachers = "";
        this.students = "";
        this.workName = "";
        this.prizeName = "";
        this.date = "";
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }

    public String getStudents() {
        return students;
    }

    public void setStudents(String students) {
        this.students = students;
    }

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
