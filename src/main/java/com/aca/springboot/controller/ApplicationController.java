package com.aca.springboot.controller;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.Message;
import com.aca.springboot.entities.json;
import com.aca.springboot.service.ApplicationService;
import com.aca.springboot.utils.StrUtils;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/app")
@Api(value = "激励")
public class ApplicationController {
    @Autowired
    ApplicationService applicationService;

/*    @Autowired
    SuccessService successService;*/

    /**
     * 插入数据，插入到两张表中
     *
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ModelAndView add(@RequestParam(value = "ctId", required = false) String ctId,
                            @RequestParam(value = "workName", required = false) String workName,
                            @RequestParam(value = "awardDate", required = false) String awardDate,
                            @RequestParam(value = "unit", required = false) String unit,
                            @RequestParam(value = "level_type", required = false) String level_type,
                            @RequestParam(value = "prize_type", required = false) String prize_type,
                            @RequestParam(value = "applicantId", required = false) String applicantId,
                            @RequestParam(value = "applicantBankCard", required = false) String applicantBankCard,
                            @RequestParam(value = "leader", required = false) String leader,
                            @RequestParam(value = "workBriefIntro", required = false) String workBriefIntro,
                            @RequestParam(value = "tms", required = false) String tms,
                            @RequestParam(value = "ts", required = false) String ts) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/user/application_form");
        Application app = new Application();
        String awardTypeId = getawardtype(ctId, level_type, prize_type);  //获取获奖类型编号

        //获取学生获奖金额stu_price
        String studentPrice = applicationService.get_price(awardTypeId).get("STUDENT_PRICE").toString();
        //获取老师获奖金额tea_price
        String teacherPrice = applicationService.get_price(awardTypeId).get("TEACHER_PRICE").toString();
/*        System.out.println("学生获奖金额"+studentPrice);
        System.out.println("老师获奖金额"+teacherPrice);*/
//        System.out.println("上传的图片" + file);
/*
        现在这三个数据还没有添加
        certificateImg blob ,
        getAwardImg blob,
        highLight blob,*/

        //pic.setImg(bytes);
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR)) + String.valueOf((currTime.get(Calendar.MONTH) + 1));
        String path = "d:" + File.separator + "img" + File.separator + time;   //图片保存路径
//        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        suffix = suffix.toLowerCase();      //文件格式
//        if (suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") || suffix.equals(".gif")) {
//            String fileName = UUID.randomUUID().toString() + suffix;
//            File targetFile = new File(path, fileName);
//            if (!targetFile.getParentFile().exists()) {             //注意，判断父级路径是否存在
//                targetFile.getParentFile().mkdirs();      //不存在创建文件夹
//            }
//            long size = 0;
//            //保存
//            try {
//                file.transferTo(targetFile);
//                size = file.getSize();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        //添加
        InputStream is = null;//得到文件流
        String appid = StrUtils.timeStamp();
        try {
//                is = new FileInputStream(targetFile);
//                byte[] bytes = FileCopyUtils.copyToByteArray(is);//得到byte
            app.setAppid(appid);
            app.setCtid(ctId);
            //TODO 从session里面读取sno
            app.setApplicantId(applicantId);
            app.setUnit(unit);
            app.setLeader(leader);
            app.setStudentPrice((Integer.parseInt(studentPrice) * 100) + "");
            app.setTeacherPrice((Integer.parseInt(teacherPrice) * 100) + "");
            app.setApplicantId(applicantId);
            app.setAwardDate(awardDate);
            app.setApplicantBankCard(applicantBankCard);
            app.setWorkName(workName);
            app.setWorkBriefIntro(workBriefIntro);
            app.setAwardTypeId(awardTypeId);
            //TODO 图片没有存储
//                app.setCertificateimg(bytes);
            /*int result = applicationService.add(comName, applicantId, teacher1Id, teacher2Id, unit, leader, teamNum, team, studentPrice, teacherPrice, awardTypeId, awardDate, applicantBankCard, workName, workBriefIntro, bytes);//添加到数据库中*/
            //插入application
            applicationService.add(app);
            //插入相关学生
            applicationService.addMultMember(tms, appid, 1);
            //插入相关老师
            applicationService.addMultMember(ts, appid, 2);
        } catch (Exception e) {
            System.out.println("失败");
            e.printStackTrace();
        }
        System.out.println("成功");
        return mv;
    }

    /**
     * 奖金公示
     *
     * @return
     */
/*    public List workresult(HttpSession session){
        String id = session.getAttribute("loginUser").toString();
        return applicationService.workresult(id);
    }*/

    /**
     * 获取全部的比赛名称
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/con_name")
    public List com_name() {
        return applicationService.com_name();
    }

    /**
     * 调用函数获取获奖类型
     *
     * @return
     */
    @ResponseBody
    public String getawardtype(String u_comName, String u_level_type, String u_prize_type) {
        return applicationService.getawardtype(u_comName, u_level_type, u_prize_type);
    }


    @ResponseBody
    @RequestMapping("/list")
    public json get_list(@RequestParam(value = "sno", required = false, defaultValue = "111") String sno,
                         @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        //TODO 从session里面获取SNO 或者管理员账号 需进行判断
        return applicationService.get_list_json(sno, pageNum, pageSize);
    }


    /*学长的代码（他不让我动 →_→ ）：
    @ResponseBody
    @RequestMapping("/list_test")
    public Message get_list_test(@RequestParam(value = "type", required = false, defaultValue = "0") int type,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        //TODO 暂时用做测试用
        return new Message(0, "成功", applicationService.get_list_with_page_m(type, pageNum, pageSize));
    }*/
    /*这以下是机智的我写的*/
    @ResponseBody
    @RequestMapping("/list_test")
    public json get_list_test(@RequestParam(value = "type", required = false, defaultValue = "0") int type,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        //TODO
        //return new Message(0, "成功", applicationService.get_list_with_page_m(type, pageNum, pageSize));
        return applicationService.get_list_with_page_m(type, pageNum, pageSize);
    }
    /*这以上是机智的我写的*/

    /**
     * 获取app详情
     * add By hwg
     *
     * @param sno
     * @param appid
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public Message get_detail(@RequestParam(value = "sno", required = false, defaultValue = "111") String sno,
                              @RequestParam(value = "appid", required = true, defaultValue = "1") String appid
    ) {
        //TODO 从session里面获取SNO
        System.out.println(appid);
        return new Message(0, "成功", applicationService.get_detail(appid));
    }

    @ResponseBody
    @PostMapping("/change")
    public Message change(@RequestParam(value = "appid", required = true) String appid,
                          @RequestParam(value = "operation", required = false, defaultValue = "refuse") String op,
                          @RequestParam(value = "note", required = false, defaultValue = "") String note) {
        boolean re = false;
        if ("refuse".equals(op)) {
            //TODO 审核不通过需要检查权限
            re = applicationService.update_state(appid, 1, note);
        } else if ("pass".equals(op)) {
            //TODO 审核不通过需要检查权限
            re = applicationService.update_state(appid, 2, note);
        } else if ("assure".equals(op)) {
            re = applicationService.update_state(appid, 3, note);
        }


        if (re)
            return new Message(0, "成功", null);
        return new Message(-1, "error", null);
    }
}
