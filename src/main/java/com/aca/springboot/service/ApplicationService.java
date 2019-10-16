package com.aca.springboot.service;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.ApplicationMember;
import com.aca.springboot.entities.json;
import com.aca.springboot.mapper.ApplicationMapper;
import com.aca.springboot.vo.AppComAppLeaderVO;
import com.aca.springboot.vo.AppComDetailVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApplicationService {
    @Autowired
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
     * @return
     */
    public int addApplicationMember(ApplicationMember applicationMember){
        return applicationMapper.addApplicationMember(applicationMember);
    }

    /**
     *插入申请表对于数据
     * 多条插入
     * @return
     */
    public int addMoreApplicationMember(List<ApplicationMember> addMoreApplicationMember){
        int result;
        int moreResult=0;    //成功条数
        for(int i=0;i<addMoreApplicationMember.size();i++){
            result = addApplicationMember(addMoreApplicationMember.get(i));
            moreResult = moreResult+result;
        }
        return moreResult;
    }

    public int addMultMember(String list,String appid,int type){
        List<ApplicationMember> list1=new ArrayList<>();
        if(type==1){
            String[] split = list.split(",");
            for(int i =0;i<split.length;i++){
                if(split[i].length()>0){
                    list1.add(new ApplicationMember(appid,split[i],"1",i+1+""));
                }
            }
        }else{
            String[] split = list.split(",");
            for(int i =0;i<split.length;i++){
                if(split[i].length()>0){
                    list1.add(new ApplicationMember(appid,split[i],"2",i+1+""));
                }
            }
        }
        return addMoreApplicationMember(list1);
    }

    public List com_name(){
        return applicationMapper.com_name();
    }

    /**
     * 通过输入的竞赛名称，作品类型，所获奖项类型，所获奖项等级，来获得奖金类型
     * @param ctId
     * @param level_type
     * @param prize_type
     */
    public String getawardtype(String ctId,
                             String level_type,
                             String prize_type){
        Map map = new HashMap<String,String>();
        map.put("u_ctId",ctId);
        map.put("u_level_type",level_type);
        map.put("u_prize_type",prize_type);
        System.out.println(map.toString());
        applicationMapper.getawardtype(map);
        String rewardtype = (String)map.get("awardtype");
        return rewardtype;
    }

    public Map get_price(String atid){
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
     * @return
     */
    public List<AppComAppLeaderVO> get_list(String sno){
        Map map = new HashMap<String,String>();
        map.put("SNO",sno);
        return applicationMapper.get_application_list(map);
    }
    /**
     * 获取申请列表
     * @return
     */
    public PageInfo<AppComAppLeaderVO> get_list_with_page(String sno, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String,String>();
        map.put("SNO",sno);
        PageInfo re=new PageInfo(applicationMapper.get_application_list(map));
        return re;
    }

    /**
     * 获取申请列表
     * @return
     */
    public PageInfo<AppComAppLeaderVO> get_list_with_page_m(int status, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String,String>();
        map.put("status",status);
        PageInfo re=new PageInfo(applicationMapper.get_application_list_m(map));
        return re;
    }

    /**
     * 获取申请列表
     * @return
     */
    public json get_list_json(String sno, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String,String>();
        map.put("SNO",sno);
        json result=new json();
        PageInfo re=new PageInfo(applicationMapper.get_application_list(map));
        result.setCode(0);
        result.setMsg("成功");
        result.setCount(re.getSize());
        JSONArray jsonArray=new JSONArray(re.getList());
        result.setData(jsonArray);
        return result;
    }
    /**
     * 获取详情
     * @param appid
     * @return
     */
    public AppComDetailVO get_detail(String appid){
        Map map = new HashMap<String,String>();
        map.put("APPID",appid);
        AppComDetailVO application_detail = applicationMapper.get_application_detail(map);

        return application_detail;
    }

    public boolean update_state(String appid,int status,String note){
        Map map=new HashMap();
        map.put("appid",appid);
        map.put("status",status);
        map.put("note",note);

        int i = applicationMapper.update_state(map);
        if(i==1){
            return true;
        }

        return false;

    }
}
