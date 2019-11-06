package com.aca.springboot.vo;

import com.aca.springboot.entities.Student;

/**
 * @ClassName StudentCountVO
 * @Author HWG
 * @Time 2019/11/6 16:41
 */

public class StudentCountVO extends Student {
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
