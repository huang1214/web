package com.aca.springboot.controller;

import com.aca.springboot.entities.*;
import com.aca.springboot.service.ApplicationService;
import com.aca.springboot.utils.StrUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
@RequestMapping("/app")
@Api(value = "激励")
@CrossOrigin
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
    public Message add(@RequestParam(value = "ctId", required = false) String ctId,
                       @RequestParam(value = "workName", required = false) String workName,
                       @RequestParam(value = "awardDate", required = false) String awardDate,
                       @RequestParam(value = "unit", required = false) String unit,
                       @RequestParam(value = "level_type", required = false) String level_type,
                       @RequestParam(value = "prize_type", required = false) String prize_type,
                       @RequestParam(value = "applicantBankCard", required = false) String applicantBankCard,
                       @RequestParam(value = "leader", required = false) String leader,
                       @RequestParam(value = "workBriefIntro", required = false, defaultValue = "") String workBriefIntro,
                       @RequestParam(value = "tms", required = false, defaultValue = "") String tms,
                       @RequestParam(value = "ts", required = false, defaultValue = "") String ts,
                       @RequestParam(value = "certificateImg", required = false) String certificateImg,
                       @RequestParam(value = "getawardImg", required = false) String getawardImg,
                       @RequestParam(value = "highLight", required = false) String highLight,
                       HttpSession session) throws Exception {


        ModelAndView mv = new ModelAndView("redirect:/user/application_form");
        //首先检查权限
        Object user = session.getAttribute("loginUser");
        Object type = session.getAttribute("type");
        String sno = "";
        Message m = new Message();
        Application app = new Application();
        if (null == user) {
            m.setCode(-1);
            m.setMessage("未登录");
            return m;
        } else if ((int) type == 1) {
            sno = ((Student) user).getSno();
        } else if ((int) type == 2) {
            sno = ((Teacher) user).getTno();
        } else {
            m.setCode(3);
            m.setMessage("当前用户无权限！");
            return m;
        }
        String awardTypeId = getawardtype(ctId, level_type, prize_type);  //获取获奖类型编号
        //获取学生获奖金额stu_price
        String studentPrice = applicationService.get_price(awardTypeId).get("STUDENT_PRICE").toString();
        //获取老师获奖金额tea_price
        String teacherPrice = applicationService.get_price(awardTypeId).get("TEACHER_PRICE").toString();

        String appid = StrUtils.timeStamp();
        if (null == tms || "".equals(tms)) {
            tms = sno + ":100";
        }List<ApplicationMember> applicationMembers=null;
        try {
//            System.out.println("tms:"+tms+"\nts:"+ts);
            //插入相关学生
            applicationMembers = applicationService.addMultMember(tms, ts, Integer.parseInt(studentPrice) * 100, Integer.parseInt(teacherPrice) * 100, awardDate, appid);
//            System.out.println(tms);
        } catch (Exception e) {
            m.setCode(5);
            m.setMessage(e.getMessage());
            e.printStackTrace();
            return m;
        }
        app.setStatus("1");
        if(applicationMembers!=null&&applicationMembers.size()>0){
            leader=applicationMembers.get(0).getAppTmId();
            //查看是否有指导老师
            if((int)type==1){
                for(ApplicationMember am:applicationMembers){
                    if(am.getAppType().equals("2")){
                        app.setStatus("0");
                        break;
                    }
                }
            }
        }else{
            m.setCode(-1);
            m.setMessage("参赛人员不足");
            return m;
        }
        try {
            app.setAppid(appid);
            app.setCtid(ctId);
            app.setApplicantId(sno);
            app.setUnit(unit);
            app.setLeader(leader);
            app.setStudentPrice((Integer.parseInt(studentPrice) * 100) + "");
            app.setTeacherPrice((Integer.parseInt(teacherPrice) * 100) + "");
            app.setAwardDate(awardDate);
            app.setApplicantBankCard(applicantBankCard);
            app.setWorkName(workName);
            app.setWorkBriefIntro(workBriefIntro);
            app.setAwardTypeId(awardTypeId);
            app.setCertificateImg(certificateImg);
            app.setGetawardImg(getawardImg);
            app.setHighLight(highLight);
            System.out.println(app.toString());
            applicationService.add(app);
        } catch (Exception e) {
            m.setCode(4);
            m.setMessage("添加时出错，请检查是否漏填！");
            e.printStackTrace();
            return m;
        }
        m.setCode(0);
        m.setMessage("申请成功！");
        return m;
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
    public JsonMessage get_list(@RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                @RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize,
                                @RequestParam(value = "type", required = false, defaultValue = "1") int rtype,
                                HttpSession session) {
        //首先检查权限
//        System.out.println("trype:"+rtype+"\npageNum:"+pageNum+"\nsize:"+pageSize);
        Object user = session.getAttribute("loginUser");
        Object type = session.getAttribute("type");
        String sno = "";
        if (null == user) {
            JsonMessage j = new JsonMessage();
            j.setCode(-1);
            j.setMsg("未登录");
            return j;
        } else if ((int) type == 1) {
            sno = ((Student) user).getSno();
        } else if ((int) type == 2) {
            sno = ((Teacher) user).getTno();
        } else {
            JsonMessage j = new JsonMessage();
            j.setCode(-2);
            j.setMsg("位置错误，请重新登录");
            return j;
        }
        return applicationService.get_list_json(sno, pageNum, pageSize, rtype);
    }


    /*学长的代码（他不让我动 →_→ ）：
    @ResponseBody
    @RequestMapping("/list_test")
    public Message get_list_test(@RequestParam(value = "type", required = false, defaultValue = "0") int type,
                                 @RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) {
        return new Message(0, "成功", applicationService.get_list_with_page_m(type, pageNum, pageSize));
    }*/
    /*这以下是机智的我写的*/
    @ResponseBody
    @RequestMapping("/list_test")
    public JsonMessage get_list_test(@RequestParam(value = "type", required = false, defaultValue = "0") int type,
                                     @RequestParam(value = "page", required = false, defaultValue = "1") int pageNum,
                                     @RequestParam(value = "limit", required = false, defaultValue = "10") int pageSize,
                                     HttpSession session) {
        //首先检查权限
        Object user = session.getAttribute("loginUser");
        Object type1 = session.getAttribute("type");
        String sno = "";
        if (null == user) {
            JsonMessage j = new JsonMessage();
            j.setCode(-1);
            j.setMsg("未登录");
            return j;
        } else if ((int) type1 == 1 || (int) type1 == 2) {
            JsonMessage j = new JsonMessage();
            j.setCode(3);
            j.setMsg("权限不足");
            return j;
        }
        return applicationService.get_list_with_page_m(type, pageNum, pageSize);
    }
    /*这以上是机智的我写的*/

    @ResponseBody
    @RequestMapping("/list_file")
    public JsonMessage get_list_file(@RequestParam(value = "type", required = false, defaultValue = "1") int type,
                                     @RequestParam(value = "year", required = false, defaultValue = "2019") String year,
                                     @RequestParam(value = "op",required = false,defaultValue = "count")String op,
                                     HttpSession session) {
        //首先检查权限
        Object user = session.getAttribute("loginUser");
        Object type1 = session.getAttribute("type");
        String sno = "";
        if (null == user) {
            JsonMessage j = new JsonMessage();
            j.setCode(-1);
            j.setMsg("未登录");
            return j;
        } else if ((int) type1 == 1 || (int) type1 == 2) {
            JsonMessage j = new JsonMessage();
            j.setCode(3);
            j.setMsg("权限不足");
            return j;
        }
        if("delete".equals(op)){
            return applicationService.clear_file_dir(type,year);
        }
        return applicationService.get_list_file(type,year,op);
    }

    @ResponseBody
    @RequestMapping("/list_file_dir")
    public Message get_list_file_dir() {
        return applicationService.get_file_dir();
    }

    /**
     * 获取app详情
     * add By hwg
     *
     * @param appid
     * @return
     */
    @ResponseBody
    @RequestMapping("/detail")
    public Message get_detail(@RequestParam(value = "appid", required = true, defaultValue = "1") String appid, HttpSession session
    ) {
        Object user = session.getAttribute("loginUser");
        Object type = session.getAttribute("type");
        String sno = "";
        if (null == user) {
            Message j = new Message();
            j.setCode(-1);
            j.setMessage("未登录");
            return j;
        } else if ((int) type == 1) {
            sno = ((Student) user).getSno();
        } else if ((int) type == 2) {
            sno = ((Teacher) user).getTno();
        } else if ((int) type == 3) {
            sno = ((Administrator) user).getAdm_id();
        } else {
            Message j = new Message();
            j.setCode(-2);
            j.setMessage("未知错误，请重新登录");
            return j;
        }
        return new Message(0, "成功", applicationService.get_detail(appid, sno));
    }

    @ResponseBody
    @PostMapping("/change")
    public Message change(@RequestParam(value = "appid", required = true) String appid,
                          @RequestParam(value = "operation", required = false, defaultValue = "refuse") String op,
                          @RequestParam(value = "note", required = false, defaultValue = "") String note,
                          HttpSession session) {
        boolean re = false;
        //首先检查权限
        Object user = session.getAttribute("loginUser");
        Object type = session.getAttribute("type");
        String sno = "";
        if (null == user) {
            Message j = new Message();
            j.setCode(-1);
            j.setMessage("未登录");
            return j;
        }
        if ("refuse".equals(op)) {
            if ((int) type != 3) {
                Message j = new Message();
                j.setCode(3);
                j.setMessage("权限不足");
                return j;
            }
            re = applicationService.update_state(appid, 2, note);
        } else if ("pass".equals(op)) {
            if ((int) type != 3) {
                Message j = new Message();
                j.setCode(3);
                j.setMessage("权限不足");
                return j;
            }
            re = applicationService.update_state_pass(appid, 3, note);
        } else if ("assure".equals(op)) {
            if ((int) type != 2) {
                Message j = new Message();
                j.setCode(3);
                j.setMessage("权限不足");
                return j;
            }
            re = applicationService.update_state(appid, 1, note);
        }else if("refresh".equals(op)){
            Message j = new Message();
            if ((int) type != 3) {
                j.setCode(3);
                j.setMessage("权限不足");
                return j;
            }
            j.setCode(-1);
            j.setMessage("待开放");
            return j;
        }

        if (re)
            return new Message(0, "成功", null);
        return new Message(-1, "请重试！", null);
    }

    @ResponseBody
    @PostMapping("/delete")
    public Message de(@RequestParam(value = "appid", required = true) String appid,
                      HttpSession session) {
        //首先检查权限
        Object user = session.getAttribute("loginUser");
        Object type = session.getAttribute("type");
        Message m = new Message();
        if (null == user) {
            m.setCode(-1);
            m.setMessage("未登录");
            return m;
        } else if ((int) type != 3) {
            m.setCode(3);
            m.setMessage("权限不足");
            return m;
        }
        try {
            m.setData(applicationService.delete(appid));
            m.setCode(0);
            m.setMessage("成功");
        } catch (Exception e) {
            m.setCode(-1);
            m.setMessage("失败");
            e.printStackTrace();
        }
        return m;
    }
}
