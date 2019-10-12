package com.aca.springboot.mapper;

import com.aca.springboot.entities.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    /**
     * 通过学号查询学生
     */
    public Student selectStudent(@Param("sno") String sno);
}
