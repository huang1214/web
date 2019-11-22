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
}

