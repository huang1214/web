package com.aca.springboot.service;

import com.aca.springboot.entities.JsonMessage;
import com.aca.springboot.entities.test;
import com.aca.springboot.mapper.ConsoleDataMapper;
import com.aca.springboot.vo.StudentCountVO;
import com.aca.springboot.vo.TeacherCountVO;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class testService {
    @Autowired
    private ConsoleDataMapper consoleDataMapper;

    //表格初始化
    public JSONObject list_tset(int page,int limit){
        List<test> lists = consoleDataMapper.dept_All();   //select后结果放入lists集合中
        List<test> list = new ArrayList<>();
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

        JsonMessage js = new JsonMessage();     //创建一个json对象
        js.setCount(lists.size());
        js.setCode(0);
        js.setMsg("");
        js.setData(objects);
        String jsonTheLast = JSON.toJSONString(js);       //先将json类对象（此时就是普通的类对象）转变为json格式的字符串
        JSONObject jsonObj = JSON.parseObject(jsonTheLast);   //将字符串串转变为json对象（json数据格式），将对象转变为json数据格式。
        return jsonObj;   //返回json对象（json数据）
    }

    //表格条件查询，内容跟上面一模一样，就是加了两个where条件
    public JSONObject list_tset_search(int page,int limit,String dname,String dadmin){
        List<test> lists = consoleDataMapper.dept_search(dname,dadmin);
        List<test> list = new ArrayList<>();
        int theLastPage = page * limit ;
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
        String jsonString = JSON.toJSONString(list);
        JSONArray objects = JSON.parseArray(jsonString);

        JsonMessage js = new JsonMessage();
        js.setCount(lists.size());
        js.setCode(0);
        js.setMsg("");
        js.setData(objects);
        String jsonTheLast = JSON.toJSONString(js);
        JSONObject jsonObj = JSON.parseObject(jsonTheLast);
        return jsonObj;
    }


    //对部门的删除
    public int delete_dept(String dept_id){ return consoleDataMapper.delete_dept(dept_id); }

    //只获取部门名字
    public List dept_Name(){
        return consoleDataMapper.dept_name();
    }

    //只获取所属学院
    public List dept_College(){
        return consoleDataMapper.dept_college();
    }

    //获取部门编号
    public String get_dno(String dname,String dcollege){
        return consoleDataMapper.get_dno(dname,dcollege);
    }

    public int getApplicationCount(String no,int status){
        Map map=new HashMap<>();
        map.put("status",status);
        map.put("sno",no);
        return consoleDataMapper.selectCountFromApplication(map);
    }

    public Object getAppPrizeInfo(){
        Map map=new HashMap<String,String>();
        map.put("STATUS",3);
        map.put("LEVELTYPE",1);
        map.put("PRICETYPE",1);
        List a=new ArrayList<Integer>();
        for(int i = 0;i<4;i++){
            map.put("LEVELTYPE",i);
            a.add(consoleDataMapper.selectAppPrize(map));
        }
        return a;
    }

    //TODO 这里是可以根据传过来的时间来动态查询的，暂时不做
    public List<StudentCountVO> getTopStudent(String ST,String ET,int start,int end){
        Map map=new HashMap<String,String>();
        map.put("STATUS",2);
        map.put("ST",ST);
        map.put("ET",ET);
        map.put("END",end);
        map.put("START",start);
        return consoleDataMapper.getTopStudent(map);
    }
    public List<StudentCountVO> getTopStudentMoney(String ST,String ET,int start,int end){
        Map map=new HashMap<String,String>();
        map.put("STATUS",2);
        map.put("ST",ST);
        map.put("ET",ET);
        map.put("END",end);
        map.put("START",start);
        return consoleDataMapper.getTopStudentMoney(map);
    }

    //TODO 这里是可以根据传过来的时间来动态查询的，暂时不做
    public List<TeacherCountVO> getTopTeacher(String ST,String ET,int start,int end){
        Map map=new HashMap<String,String>();
        map.put("STATUS",2);
        map.put("ST",ST);
        map.put("ET",ET);
        map.put("END",end);
        map.put("START",start);
        return consoleDataMapper.getTopTeacher(map);
    }

    public List<TeacherCountVO> getTopTeacherMoney(String ST,String ET,int start,int end){
        Map map=new HashMap<String,String>();
        map.put("STATUS",2);
        map.put("ST",ST);
        map.put("ET",ET);
        map.put("END",end);
        map.put("START",start);
        return consoleDataMapper.getTopTeacherMoney(map);
    }
}
