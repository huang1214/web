package com.aca.springboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ConsoleDataMapper {
    //查询所有结果
    public List dept_All();
    public List dept_name();
    public List dept_college();


    //条件查询结果
    public List dept_search(@Param("dname") String dname, @Param("dadmin") String dadmin);

    public int delete_dept(@Param("dept_id") String dept_id);

    public String get_dno(@Param("dname") String dname,@Param("dcollege") String dcollege);

    int selectCountFromApplication(Map map);

    int selectAppPrize(Map map);
}
