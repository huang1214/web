package com.aca.springboot.mapper;
import com.aca.springboot.entities.Application;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApplicationMapper {

    public int add(Application app);

    //获取学生和老师的奖金
    public Map get_price(@Param("atid") String atid);

    public Application get(int app_id);

    public List work_paaAll();

    public List com_name(); // 查询全部比赛名称

    public void getawardtype(Map map); //查询获奖类型

    //申请状态列出所有的申请信息
    public List application_All(@Param("applicantId") String applicantId);
    public List workresult(Map map);
}
