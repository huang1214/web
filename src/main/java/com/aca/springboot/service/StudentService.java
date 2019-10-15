package com.aca.springboot.service;

import com.aca.springboot.entities.Student;
import com.aca.springboot.mapper.StudentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    StudentMapper studentMapper;

    /**
     * add by hwg
     * @param sno
     * @return
     */
    public Student selectBySno(String sno){
        return studentMapper.selectStudent(sno);
    }

    /**
     * add by hwg
     * @param name
     * @return
     */
    public PageInfo<Student> selectByName(String name,int pageNum,int pageSize){
        Map params=new HashMap();
        params.put("sname","%"+name+"%");
        PageHelper.startPage(pageNum,pageSize);
        PageInfo re=new PageInfo(studentMapper.selectByName(params));
        return re;
    }
}
