package com.aca.springboot.vo;

import com.aca.springboot.entities.Student;

/**
 * @ClassName StudentAMVO
 * @Author HWG
 * @Time 2019/11/20 16:53
 */

public class StudentAMVO extends Student {
    private String appOrder;
    private int propertion;
    private int money;

    @Override
    public String toString() {
        return super.toString()+"StudentAMVO{" +
                "appOrder='" + appOrder + '\'' +
                ", propertion=" + propertion +
                ", money=" + money +
                '}';
    }

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
