package com.aca.springboot.service;

import com.aca.springboot.entities.JsonMessage;
import com.aca.springboot.entities.Student;
import com.aca.springboot.mapper.StudentMapper;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentService
 * @Author HWG
 * @Time 2019/10/15 9:45
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    /**
     * add by hwg
     * @param sno
     * @return
     */
    public JsonMessage selectBySno(String sno){
        JsonMessage j=new JsonMessage();
        List l=new ArrayList();
        Student student = studentMapper.selectStudent(sno);
        if (student==null){
            j.setCount(0);
            j.setData(null);
            j.setMsg("未找到该学生");
            j.setCode(2);
        }else{
            l.add(student);
            JSONArray ja=new JSONArray(l);
            j.setData(ja);
            j.setMsg("success");
            j.setCount(1);
            j.setCode(0);
        }

        return j;
    }

    public Student selectBySnoReturnObject(String sno){
        return studentMapper.selectStudent(sno);
    }
    /**
     * add by hwg
     * @param name
     * @return
     */
    public JsonMessage selectByName(String name, int pageNum, int pageSize){
        Map params=new HashMap();
        params.put("NAME","%"+name+"%");
        params.put("SNO",name);
        PageHelper.startPage(pageNum,pageSize);
        List students = studentMapper.selectByName(params);
        PageInfo re=new PageInfo(students);
        JSONArray ja=new JSONArray(students);
        JsonMessage j=new JsonMessage();
        j.setData(ja);
        j.setCode(0);
        j.setCount(re.getSize());
        return j;
    }

    public int update(Student student){
        return studentMapper.updateStudent(student);
    }
}
