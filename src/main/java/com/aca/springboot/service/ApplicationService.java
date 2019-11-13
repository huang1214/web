package com.aca.springboot.service;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.ApplicationMember;
import com.aca.springboot.entities.JsonMessage;
import com.aca.springboot.mapper.ApplicationMapper;
import com.aca.springboot.vo.AppComAppLeaderVO;
import com.aca.springboot.vo.AppComDetailVO;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ApplicationService {
    @Resource
    ApplicationMapper applicationMapper;

    /**
     * 插入申请表数据
     */
/*    add(comName,applicantId,teacher1Id,teacher2Id,unit,leader,teamNum,team,studentPrice,teacherPrice,
        awardTypeId,awardDate,applicantBankCard,workName,workBriefIntro)*/
    public int add(Application app) {
        /*Map map = new HashMap<String,String>();
        map.put("comName",comName);
        map.put("applicantId",applicantId);
        map.put("teacher1Id",teacher1Id);
        map.put("teacher2Id",teacher2Id);
        map.put("unit",unit);
        map.put("leader",leader);
        map.put("teamNum",teamNum);
        map.put("team",team);
        map.put("studentPrice",studentPrice);
        map.put("teacherPrice",teacherPrice);
        map.put("awardTypeId",awardTypeId);
        map.put("awardDate",awardDate);
        map.put("applicantBankCard",applicantBankCard);
        map.put("workName",workName);
        map.put("workBriefIntro",workBriefIntro);

*//*        String strImg = new String(img);*//*
         *//*  map.put("certificateImg",strImg);*//*
        byte[] imgs=img;*/
        System.out.println(app.toString());
        return applicationMapper.add(app);
    }

    /**
     * 插入一条申请对应表数据
     *
     * @return
     */
    public int addApplicationMember(ApplicationMember applicationMember) {
        return applicationMapper.addApplicationMember(applicationMember);
    }

    /**
     * 插入申请表对于数据
     * 多条插入
     *
     * @return
     */
    public int addMoreApplicationMember(List<ApplicationMember> addMoreApplicationMember) {
        int result;
        int moreResult = 0;    //成功条数
        for (int i = 0; i < addMoreApplicationMember.size(); i++) {
            result = addApplicationMember(addMoreApplicationMember.get(i));
            moreResult = moreResult + result;
        }
        return moreResult;
    }

    public int addMultMember(String tms, String ts, int stuPrice, int teaPrice, String date, String appid) throws Exception {
        int maxAmount = 2000000, temp = 0;
        boolean isEmpty = true;
        if (null == tms)
            return 0;
        String st, et, year, month;
        year = date.substring(0, 4);
        month = date.substring(4, 6);
        if (Integer.parseInt(month) >= 9 || Integer.parseInt(month) <= 2) {
            st = year + "09";
            et = (Integer.parseInt(year) + 1) + "03";
        } else {
            st = year + "03";
            et = year + "09";
        }
        Map map = new HashMap();
        map.put("st", st);
        map.put("et", et);
        map.put("status", 2);
        List<ApplicationMember> list1 = new ArrayList<>();
        String[] split = tms.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                String id = (split[i].split(":"))[0];
                int proportion = Integer.parseInt((split[i].split(":"))[1]);
                int nowMount = (stuPrice * proportion / 100);
                map.put("id", id);
                int getMount = selectMoney(map);
                if ((nowMount + getMount) >= maxAmount) {
                    throw new Exception("学号为[" + map.get("id") + "]的同学在" + st + "-" + et + "中已获得共计" + getMount / 100 + "元奖金，超出上限" + maxAmount / 100 + "元");
                }
                list1.add(new ApplicationMember(appid, id, "1", i + 1 + "", proportion, nowMount));
                temp += proportion;
                isEmpty = false;
            }
        }
        if (!isEmpty) {
            if (temp != 100)
                throw new Exception("学生奖金占比总和须为100");
        }
        temp = 0;isEmpty=true;
        split = ts.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                String id = (split[i].split(":"))[0];
                int proportion = Integer.parseInt((split[i].split(":"))[1]);
                int nowMount = (teaPrice * proportion / 100);
                map.put("id", id);
                int getMount = selectMoney(map);
                if ((nowMount + getMount) >= maxAmount) {
                    throw new Exception("编号为[" + map.get("id") + "]的指导老师在" + st + "-" + et + "中已获得共计" + getMount / 100 + "元奖金，超出上限" + maxAmount / 100 + "元");
                }
                list1.add(new ApplicationMember(appid, id, "2", i + 1 + "", proportion, nowMount));
                temp += proportion;
                isEmpty=false;
            }
        }
        if (!isEmpty) {
            if (temp != 100)
                throw new Exception("老师奖金占比总和须为100");
        }
        return addMoreApplicationMember(list1);
    }

    public List com_name() {
        return applicationMapper.com_name();
    }

    /**
     * 通过输入的竞赛名称，作品类型，所获奖项类型，所获奖项等级，来获得奖金类型
     *
     * @param ctId
     * @param level_type
     * @param prize_type
     */
    public String getawardtype(String ctId,
                               String level_type,
                               String prize_type) {
        Map map = new HashMap<String, String>();
        map.put("u_ctId", ctId);
        map.put("u_level_type", level_type);
        map.put("u_prize_type", prize_type);
        System.out.println(map.toString());
        applicationMapper.getawardtype(map);
        String rewardtype = (String) map.get("awardtype");
        return rewardtype;
    }

    public Map get_price(String atid) {
        return applicationMapper.get_price(atid);
    }


/*    public List workresult(String leader_id){
        Map map = new HashMap<String,String>();
        map.put("leader_id",leader_id);
        return applicationMapper.workresult(map);
    }*/
    /**
     * 获取用户id，查看自己全部获奖作品的审核情况
     * @param applicantId
     * @return
     */

    /**
     * 获取申请列表
     *
     * @return
     */
    public List<AppComAppLeaderVO> get_list(String sno) {
        Map map = new HashMap<String, String>();
        map.put("SNO", sno);
        return applicationMapper.get_application_list(map);
    }

    /**
     * 获取申请列表
     *
     * @return
     */
    public PageInfo<AppComAppLeaderVO> get_list_with_page(String sno, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Map map = new HashMap<String, String>();
        map.put("SNO", sno);
        PageInfo re = new PageInfo(applicationMapper.get_application_list(map));
        return re;
    }

    /**
     * 获取申请列表
     *
     * @return
     */
    /*学长的代码（他不让我动 →_→ ）：
     public PageInfo<AppComAppLeaderVO> get_list_with_page_m(int status, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String,String>();
        map.put("status",status);
        PageInfo re=new PageInfo(applicationMapper.get_application_list_m(map));
        return re;
    }*/
    /*这以下是机智的我写的*/
    public JsonMessage get_list_with_page_m(int status, int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String, String>();
        map.put("status", status);
        map.put("endIndex", pageNum * pageSize);
        map.put("startIndex", (pageNum - 1) * pageSize + 1);
        JsonMessage result = new JsonMessage();
//        PageInfo re=new PageInfo(applicationMapper.get_application_list_m(map));
//        List<AppComAppLeaderVO> list = re.getList();
        List<AppComAppLeaderVO> application_list_m = applicationMapper.get_application_list_m(map);
        result.setCode(0);
        result.setMsg("成功");
        result.setCount(applicationMapper.get_application_list_m_count(map));
        JSONArray jsonArray = new JSONArray((List) application_list_m);
        result.setData(jsonArray);
        return result;
    }
    /*这以上是机智的我写的*/

    /**
     * 获取申请列表
     *
     * @return
     */
    public JsonMessage get_list_json(String sno, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Map map = new HashMap<String, String>();
        map.put("SNO", sno);
        map.put("endIndex", pageNum * pageSize);
        map.put("startIndex", (pageNum - 1) * pageSize + 1);
        JsonMessage result = new JsonMessage();
//        PageInfo re=new PageInfo(applicationMapper.get_application_list(map));
        List<AppComAppLeaderVO> application_list = applicationMapper.get_application_list(map);
//        List<AppComAppLeaderVO> list = re.getList();
        for (int i = 0; i < application_list.size(); i++) {
            if (sno.equals(application_list.get(i).getLeader()))
                application_list.get(i).setRes(1);
        }
        result.setCode(0);
        result.setMsg("成功");
        result.setCount(applicationMapper.get_application_list_count(map));
        JSONArray jsonArray = new JSONArray((List) application_list);
        result.setData(jsonArray);
        return result;
    }

    /**
     * 获取详情
     *
     * @param appid
     * @return
     */
    public AppComDetailVO get_detail(String appid) {
        Map map = new HashMap<String, String>();
        map.put("APPID", appid);
        AppComDetailVO application_detail = applicationMapper.get_application_detail(map);
        return application_detail;
    }

    public boolean update_state(String appid, int status, String note) {
        Map map = new HashMap();
        map.put("appid", appid);
        map.put("status", status);
        map.put("note", note);

        int i = applicationMapper.update_state(map);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public int delete(String appid) {
        return applicationMapper.deleteApp(appid) + applicationMapper.deleteAppRes(appid);
    }

    public int selectMoney(Map map) {
        Object o = applicationMapper.selectMoney(map);
        if (o == null)
            return 0;
        else {
            java.math.BigDecimal bigDecimal = (BigDecimal) o;
            return Integer.parseInt(bigDecimal.toString());
        }
    }
}
