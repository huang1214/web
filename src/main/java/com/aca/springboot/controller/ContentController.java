package com.aca.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ContentController
 * @Author HWG
 * @Time 2019/11/18 14:49
 */
@Controller
@RequestMapping("/page")
public class ContentController {
    @RequestMapping("/application_state_student")
    public String state_stu(){
        return "user/application_state_student";
    }
    @RequestMapping("/application_state_teacher")
    public String state_t(){
        return "user/application_state_teacher";
    }
    @RequestMapping("/appFileManage")
    public String aaaa(){
        return "admin/appFileManage";
    }
    @RequestMapping("/applicationForm")
    public String applicationForm(){
        return "user/application_form";
    }
    @RequestMapping("/applicationNotice")
    public String applicationNotice(){
        return "user/application_notice";
    }
    @RequestMapping("/billNotice")
    public String billNotice(){
        return "user/bill_notice";
    }
    @RequestMapping("/billForm")
    public String billForm(){
        return "user/bill_form";
    }
    @RequestMapping("/billState")
    public String billState(){
        return "user/bill_state";
    }
    @RequestMapping("/dashboard")
    public String dashboard(){
        return "user/dashboard";
    }
    @RequestMapping("/teacher")
    public String teacher(){
        return "admin/teacher";
    }
    @RequestMapping("/comSearch")
    public String comSearch(){
        return "admin/com_search";
    }
    @RequestMapping("/audit")
    public String audit(){
        return "admin/audit";
    }
    @RequestMapping("/billAudit")
    public String billAudit(){
        return "admin/bill_audit";
    }
    @RequestMapping("/billRelease")
    public String billRelease(){
        return "admin/billRelease";
    }
    @RequestMapping("/userInfo")
    public String userInfo(){
        return "user/user_info";
    }
    @RequestMapping("/priceInfo")
    public String priceInfo(){
        return "admin/priceInfo";
    }
}

