package com.aca.springboot.controller;

import com.aca.springboot.entities.*;
import com.aca.springboot.service.BillService;
import com.aca.springboot.utils.TimeUtil;
import com.aca.springboot.vo.BillVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
   /* @PostMapping(value = "add")
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
    }*/
    //添加一条报销记录,通过测试
    @PostMapping(value = "add")
    @ResponseBody
    public ModelAndView addAward(@RequestParam(value = "ctid", required = false) String ctid,
                            @RequestParam(value = "cyear", required = false) String cyear,
                            @RequestParam(value = "clevel", required = false) String clevel,
                            @RequestParam(value = "cdesc", required = false) String cdesc,
                            @RequestParam(value = "groupleader", required = false) String groupleader,
                            @RequestParam(value = "groupname", required = false) String groupname,
                            @RequestParam(value = "workName", required = false) String workName,
                            @RequestParam(value = "preditfeedesc", required = false) String preditfeedesc,
                            @RequestParam(value = "predictfee", required = false) String predictfee,
                            @RequestParam(value = "attachfile", required = false) String attachfile,
                            @RequestParam(value = "ts",required = false) String teachers,
                            @RequestParam(value = "tms",required = false) String students
                            ){
        ModelAndView mv = new ModelAndView("redirect:/user/bill_form");
        Message addAwardMessage=new Message();
        Bill bill=new Bill();
        bill.setBid(TimeUtil.getBillNumber());  //获取报销编号
        bill.setCtid(ctid);
        bill.setCyear(cyear);
        bill.setClevel(clevel);
        bill.setCdesc(cdesc);
        bill.setGroupleader(groupleader);
        bill.setGroupname(groupname);
        bill.setWorkName(workName);
        bill.setPreditfeedesc(preditfeedesc);
        bill.setState("0"); //初始状态都是未审核
        bill.setPredictfee(predictfee);
        bill.setAttachfile("D:/local/picture");
        teachers=teachers.substring(1);
        students=students.substring(1);
        String[] teacherList=teachers.split(",");
        String[] studentList=students.split(",");
        List<BillMember> billMembers=new ArrayList<>();
        for(int i=0;i<teacherList.length;i++){
            BillMember billMember=new BillMember();
            billMember.setBillOrder(i);
            billMember.setBillType(2);
            billMember.setBillTmId(teacherList[i]);
            billMembers.add(billMember);
        }
        for(int i=0;i<studentList.length;i++){
            BillMember billMember=new BillMember();
            billMember.setBillOrder(i);
            billMember.setBillType(1);
            billMember.setBillTmId(studentList[i]);
            billMembers.add(billMember);
        }
        bill.setBillMembers(billMembers);
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
        mv.addObject("billData",addAwardMessage);
        return mv;
    }
    //获取该登录学生的所有报销记录,ok
    @GetMapping(value = "queryAll")
    @ResponseBody
    public JsonMessage getAllBillByID(@RequestParam(value = "limit",required = false,defaultValue = "1")int pageNum,
                                      @RequestParam(value = "page",required = false,defaultValue = "1")int pageSize,
                                      HttpSession session){
//        Message billListMessage=new Message();
        Student student =(Student) session.getAttribute("loginUser");
        String sno=student.getSno();
        System.out.println(sno);
        return billService.queryAllBillWithPage(sno,pageNum,pageSize);
    }
    //获取该登录学生的所有报销记录,分页
//    @GetMapping(value = "query/{sno}/{currPage}")
//    @ResponseBody
//    public Message getAllBillByIDWithPage(@PathVariable("sno") String sno,@PathVariable("currPage") Integer currPage){
//        Message billListWithPageMessage=new Message();
//        PageInfo<BillVO> pageInfo=billService.queryAllBillWithPage(sno,currPage);
//        if(pageInfo!=null){
//            billListWithPageMessage.setCode(200);
//            billListWithPageMessage.setMessage("分页查询成功");
//            billListWithPageMessage.setData(pageInfo);
//        }else {
//            billListWithPageMessage.setCode(202);
//            billListWithPageMessage.setMessage("分页查询失败");
//        }
//        return billListWithPageMessage;
//    }
    //获取所有报销记录
    @GetMapping(value = "query")
    @ResponseBody
    public Message getAllBill(){
        Message billListAdminMessage=new Message();
        List<BillVO> allListMessage=billService.queryAllBill();
        billListAdminMessage.setCode(200);
        billListAdminMessage.setMessage("查询成功");
        billListAdminMessage.setData(allListMessage);
        return billListAdminMessage;
    }
    //通过报销审核
    @PostMapping(value = "accept")
    @ResponseBody
    public Message acceptBill(@RequestBody Bill bill){
        Message acceptBillMessage=new Message();
        billService.updateBill(bill);
        acceptBillMessage.setCode(200);
        acceptBillMessage.setMessage("同意申请");
        return acceptBillMessage;
    }
    //拒绝报销审核
    @PostMapping(value = "refuse")
    @ResponseBody
    public Message refuseBill(@RequestBody Bill bill){
        Message refuseBillMessage=new Message();
        billService.updateBill(bill);
        refuseBillMessage.setCode(200);
        refuseBillMessage.setMessage("拒绝申请");
        return refuseBillMessage;
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
