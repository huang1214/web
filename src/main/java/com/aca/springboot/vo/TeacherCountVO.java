package com.aca.springboot.vo;

import com.aca.springboot.entities.Teacher;

/**
 * @ClassName StudentCountVO
 * @Author HWG
 * @Time 2019/11/6 16:41
 */

public class TeacherCountVO extends Teacher {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
