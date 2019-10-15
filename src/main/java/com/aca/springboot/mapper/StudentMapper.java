package com.aca.springboot.mapper;

import com.aca.springboot.entities.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 通过学号查询学生
     */
    public Student selectStudent(@Param("sno") String sno);

    List<Student> selectByName(Map map);
}
