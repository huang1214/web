package com.aca.springboot.vo;

import com.aca.springboot.entities.Teacher;

/**
 * @ClassName TeacherAMVO
 * @Author HWG
 * @Time 2019/11/20 16:54
 */

public class TeacherAMVO extends Teacher {
    private String appOrder;
    private int propertion;
    private int money;

    public String getAppOrder() {
        return appOrder;
    }

    public void setAppOrder(String appOrder) {
        this.appOrder = appOrder;
    }

    public int getPropertion() {
        return propertion;
    }

    public void setPropertion(int propertion) {
        this.propertion = propertion;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
