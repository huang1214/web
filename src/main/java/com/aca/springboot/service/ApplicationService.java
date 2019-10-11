package com.aca.springboot.service;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.ApplicationMember;
import com.aca.springboot.entities.json;
import com.aca.springboot.mapper.ApplicationMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    //申请状态
    //表格初始化
    public JSONObject application_All(int page, int limit,String applicantId){
        List<Application> lists = applicationMapper.application_All(applicantId);   //select后结果放入lists集合中
        System.out.println(lists);
        List<Application> list = new ArrayList<>();
        int theLastPage = page * limit ;          //这里用于判断最后一页的最后一条理论上是第几条，然后跟实际的进行比较
        if( theLastPage > lists.size())   //如果是最后一页，就是说最后一页的最后一条大于此集合的大小，只显示到集合的最后一条
        {
            for( int i = (page-1)*limit; i <lists.size();i++){
                list.add(lists.get(i));
            }
        }else{
            for( int i = (page-1)*limit; i < theLastPage;i++){    //平时显示页面
                list.add(lists.get(i));
            }
        }
        String jsonString = JSON.toJSONString(list);     //将集合转变为json格式的字符串
        JSONArray objects = JSON.parseArray(jsonString);  //将字符串转变为json数组，这里是将集合转变json数组

        json js = new json();     //创建一个json对象
        js.setCount(lists.size());
        js.setCode(0);
        js.setMsg("");
        js.setData(objects);
        String jsonTheLast = JSON.toJSONString(js);       //先将json类对象（此时就是普通的类对象）转变为json格式的字符串
        JSONObject jsonObj = JSON.parseObject(jsonTheLast);   //将字符串串转变为json对象（json数据格式），将对象转变为json数据格式。
        System.out.println(jsonObj);
        return jsonObj;   //返回json对象（json数据）
    }

}
