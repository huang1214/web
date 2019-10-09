package com.aca.springboot.entities;

public class award {
    private String atid;
    private String com_type;
    private String result_type;
    private String level_type;
    private String prize_type;
    private String teacher_price;
    private String student_price;

    public String getAtid() {
        return atid;
    }

    public void setAtid(String atid) {
        this.atid = atid;
    }

    public String getCom_type() {
        return com_type;
    }

    public void setCom_type(String com_type) {
        this.com_type = com_type;
    }

    public String getResult_type() {
        return result_type;
    }

    public void setResult_type(String result_type) {
        this.result_type = result_type;
    }

    public String getLevel_type() {
        return level_type;
    }

    public void setLevel_type(String level_type) {
        this.level_type = level_type;
    }

    public String getPrize_type() {
        return prize_type;
    }

    public void setPrize_type(String prize_type) {
        this.prize_type = prize_type;
    }

    public String getTeacher_price() {
        return teacher_price;
    }

    public void setTeacher_price(String teacher_price) {
        this.teacher_price = teacher_price;
    }

    public String getStudent_price() {
        return student_price;
    }

    public void setStudent_price(String student_price) {
        this.student_price = student_price;
    }
}
