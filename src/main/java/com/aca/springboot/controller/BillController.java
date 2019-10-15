package com.aca.springboot.controller;

import com.aca.springboot.entities.Bill;
import com.aca.springboot.entities.Message;
import com.aca.springboot.service.BillService;
import com.aca.springboot.utils.TimeUtil;
import com.aca.springboot.vo.BillVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api
@RequestMapping(value = "/bill")
public class BillController {

    private final BillService billService;


    @Autowired
    public BillController(BillService billService){
        this.billService=billService;
    }
    //添加一条报销记录,通过测试
    @PostMapping(value = "add")
    @ResponseBody
    public Message addAward(@RequestBody Bill bill){
        Message addAwardMessage=new Message();
        bill.setBid(TimeUtil.getBillNumber());  //获取报销编号
        Bill bill1=billService.addBill(bill);
        if(bill1==null){
            addAwardMessage.setCode(200);
            addAwardMessage.setMessage("提交申请成功！");
            addAwardMessage.setData(bill);  //返回新增记录
        }else {
            addAwardMessage.setCode(201);
            addAwardMessage.setMessage("已经申请过了，不能重复申请！");
            addAwardMessage.setData(bill1);   //返回之前申请记录
        }
        return addAwardMessage;
    }
    //获取所有的报销记录
    @GetMapping(value = "query")
    @ResponseBody
    public Message getAllBill(){
        Message billListMessage=new Message();
        List<BillVO> billList=billService.queryAllBill();
        billListMessage.setCode(200);
        billListMessage.setMessage("查询成功");
        billListMessage.setData(billList);
        return billListMessage;
    }





}
/*
package com.aca.springboot.controller;

import com.aca.springboot.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class AwardController {
    @Autowired
    AwardService awardService;

    */
/**
     * 获取全部奖金类型
     *//*

    @ResponseBody
    @GetMapping(value = "/getAllAward")
    public List getAllAward(){
        return awardService.getAllAward();
    }

    @ResponseBody
    @GetMapping(value = "/change_price")
    public ModelAndView change(@RequestParam("changeprice_id") String changeprice_id,
                               @RequestParam("teacher_price") String teacher_price,
                               @RequestParam("student_price") String student_price,
                               Map<String,Object> map){
        ModelAndView mv = new ModelAndView("redirect:/admin/prize");
        int result = awardService.change_price(teacher_price,student_price,changeprice_id);
        if(result == 1){
            map.put("msg","修改成功！");
            System.out.println(result);
            return mv;
        }else {
            map.put("msg","修改失败！");
            System.out.println(result );
*/
/*            return "修改失败";*//*

        }
        return mv;
    }
}
*/
