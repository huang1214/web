package com.aca.springboot.mapper;
import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.ApplicationMember;

import com.aca.springboot.vo.AppComAppLeaderVO;
import com.aca.springboot.vo.AppComDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper
public interface ApplicationMapper {

    public int add(Application app);

    //插入奖金申请表对应表
    public int addApplicationMember(ApplicationMember applicationMember);

    //获取学生和老师的奖金
    public Map get_price(@Param("atid") String atid);

    public Application get(int app_id);

//    public List work_paaAll();

    public List com_name(); // 查询全部比赛名称

    public void getawardtype(Map map); //查询获奖类型

    //申请状态列出所有的申请信息
//    public List application_All(@Param("applicantId") String applicantId);
//    public List workresult(Map map);
    //获取列表
    List<AppComAppLeaderVO> get_application_list(Map map);
    int get_application_list_count(Map map);
    AppComDetailVO get_application_detail(Map map);
    List<AppComAppLeaderVO> get_application_list_m(Map map);
    List<AppComAppLeaderVO> get_application_file();
    int get_application_list_m_count(Map map);
    int get_application_file_count();
    int update_state(Map map);
    int update_am_state(Map map);
    int deleteApp(String appid);
    int deleteAppRes(String appid);
    Object selectMoney(Map map);
}
