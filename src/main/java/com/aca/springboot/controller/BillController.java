package com.aca.springboot.controller;

import com.aca.springboot.entities.*;
import com.aca.springboot.service.BillService;
import com.aca.springboot.service.CommonService;
import com.aca.springboot.utils.TimeUtil;
import com.aca.springboot.vo.NoticeVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@Api
@RequestMapping(value = "/bill")
public class BillController {
    private final BillService billService;
    private CommonService commonService;
    @Autowired
    public BillController(BillService billService,CommonService commonService){
        this.billService=billService;
        this.commonService=commonService;
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
    public Message addAward(@RequestParam(value = "ctid", required = false) String ctid,
                            @RequestParam(value = "cyear", required = false) String cyear,
                            @RequestParam(value = "clevel", required = false) String clevel,
                            @RequestParam(value = "cdesc", required = false) String cdesc,
                            @RequestParam(value = "groupleader", required = false) String groupleader,
                            @RequestParam(value = "groupname", required = false,defaultValue = "个人") String groupname,
                            @RequestParam(value = "workName", required = false,defaultValue = "非作品类") String workName,
                            @RequestParam(value = "preditfeedesc", required = false) String preditfeedesc,
                            @RequestParam(value = "predictfee", required = false) String predictfee,
                            @RequestParam(value = "attachfile", required = false) String attachfile,
                            @RequestParam(value = "invitation", required = false,defaultValue = "") String invitation,
                            @RequestParam(value = "ts",required = false) String teachers,
                            @RequestParam(value = "tms",required = false) String students,
                            HttpSession session
                            ){
        Message addAwardMessage=new Message();
        int type = (int)session.getAttribute("type");
        if(type==1){
            Student student =(Student) session.getAttribute("loginUser");
            String sno=student.getSno();
            System.out.println("执行1："+sno);
            boolean flag=false;
            students=students.substring(1);
            String[] studentList=students.split(",");
            for(int i=0;i<studentList.length;i++){
                if(sno.equals(studentList[i])){
                    System.out.println("执行2："+sno);
                    flag=true;
                }
            }
            if(flag){
                System.out.println("有你");
                Bill bill=new Bill();
                bill.setBid(TimeUtil.getBillNumber());  //获取报销编号
                bill.setCtid(ctid);
                bill.setCyear(cyear);
                bill.setClevel(clevel);
                bill.setCdesc(cdesc);
                bill.setGroupleader(groupleader);
                bill.setGroupname(groupname);
                bill.setWorkName(workName);
                bill.setInvitation(invitation);
                bill.setPreditfeedesc(preditfeedesc);
                bill.setState("0"); //初始状态都是未审核
                bill.setPredictfee(predictfee);
                bill.setAttachfile(attachfile);
                teachers=teachers.substring(1);
                String[] teacherList=teachers.split(",");
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
            }else {
                System.out.println("没有你");
                addAwardMessage.setCode(204);
                addAwardMessage.setMessage("你不在队伍中，申请失败！");
            }
            return addAwardMessage;
        }else {
            addAwardMessage.setCode(203);
            addAwardMessage.setMessage("权限不足！");
            return addAwardMessage;
        }
    }
    //获取该登录学生的所有报销记录,ok
    @GetMapping(value = "queryAll")
    @ResponseBody
    public JsonMessage getAllBillByID(@RequestParam(value = "limit",required = false,defaultValue = "10")int pageSize,
                                      @RequestParam(value = "page",required = false,defaultValue = "1")int pageNum,
                                      @RequestParam(value = "type", required = false, defaultValue = "3") String state,
                                      HttpSession session){
       Student student =(Student) session.getAttribute("loginUser");
       String sno=student.getSno();
       if(state.equals("3")){
           return billService.queryAllBillWithPage(sno,pageNum,pageSize);
       }else {
           return billService.queryAllBillWithPageState(sno,pageNum,pageSize,state);
       }
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
    public JsonMessage getAllBill(@RequestParam(value = "limit",required = false,defaultValue = "1")int pageSize,
                              @RequestParam(value = "page",required = false,defaultValue = "1")int pageNum,
                              @RequestParam(value = "type", required = false, defaultValue = "3") String state){
        System.out.println(state);
        JsonMessage billListAdminMessage;
        if(state.equals("3")){
            billListAdminMessage=billService.queryAllBill(pageNum,pageSize);
        }else {
            billListAdminMessage=billService.queryAllBillState(pageNum,pageSize,state);
        }
        return billListAdminMessage;
    }
    //通过报销审核
    @PostMapping(value = "accept")
    @ResponseBody
    public Message acceptBill(@RequestParam(value = "bid", required = true) String bid,
                              @RequestParam(value = "state", required = false,defaultValue = "2") String state,
                              @RequestParam(value = "note", required = false,defaultValue = "同意报销") String note){
        Message acceptBillMessage=new Message();
        billService.updateBill(bid,state,note);
        acceptBillMessage.setCode(200);
        acceptBillMessage.setMessage("同意申请");
        return acceptBillMessage;
    }
    //通过报销审核
    @PostMapping(value = "ackaccount")
    @ResponseBody
    public Message ensureAccount(@RequestParam(value = "bid", required = true) String bid,
                              @RequestParam(value = "state", required = false,defaultValue = "3") String state,
                              @RequestParam(value = "ack", required = false,defaultValue = "0") String ack){
        Message acceptBillMessage=new Message();
        billService.AckAccountBill(bid,state,ack);
        acceptBillMessage.setCode(200);
        acceptBillMessage.setMessage("确认成功！");
        return acceptBillMessage;
    }
    @PostMapping(value = "modify")
    @ResponseBody
    public Message modifyBill(@RequestParam(value = "bid", required = true) String bid,
    @RequestParam(value = "state", required = false,defaultValue = "0") String state,
    @RequestParam(value = "budget", required = false,defaultValue = "0") String budget,
    @RequestParam(value = "budgetDetail", required = false,defaultValue = "0") String budgetDetail,
    @RequestParam(value = "tripDetail", required = false,defaultValue = "0") String tripDetail){
        Message acceptBillMessage=new Message();
        billService.modifyBill(bid,state,budget,budgetDetail,tripDetail);
        acceptBillMessage.setCode(200);
        acceptBillMessage.setMessage("修改成功！");
        return acceptBillMessage;
    }
    //拒绝报销审核
    @PostMapping(value = "refuse")
    @ResponseBody
    public Message refuseBill(@RequestParam(value = "bid", required = true) String bid,
                              @RequestParam(value = "state", required = false,defaultValue = "1") String state,
                              @RequestParam(value = "note", required = false,defaultValue = "不同意报销")String note){
        Message refuseBillMessage=new Message();
        billService.updateBill(bid,state,note);
        refuseBillMessage.setCode(200);
        refuseBillMessage.setMessage("拒绝申请");
        return refuseBillMessage;
    }
    //删除通过bid
    @PostMapping(value = "delete")
    @ResponseBody
    public JsonMessage deleteBill(@RequestParam(value = "bid", required = false) String bid){
        JsonMessage deleteMessage=new JsonMessage();
        int res=billService.deleteBill(bid);
        deleteMessage.setMsg("删除成功");
        deleteMessage.setCode(0);
        deleteMessage.setCount(res);
        return deleteMessage;
    }
    @PostMapping(value = "notice")
    @ResponseBody
    public Message releaseNotice(@RequestParam(value = "title", required = false) String title,
                                 @RequestParam(value = "content", required = false) String content,
                                 @RequestParam(value = "attachments", required = false) String attachments,
                                 HttpSession session){
        Message noticeMessage=new Message();
        Administrator administrator =(Administrator) session.getAttribute("loginUser");
        //System.out.println(title+"---"+attachments);
        Notice notice=new Notice();
        notice.setFlag("0");
        notice.setNoticeTitle(title);
        notice.setNoticeContent(content);
        notice.setUpdateTime(new Date());
        notice.setUpdateUserid(administrator.getAdm_id());
        commonService.addNotice(notice);
        if(attachments!=null&&!attachments.isEmpty()){
            String[] attachmentList=attachments.split(",");
            List<NoticeAttachment> list=new ArrayList<>();
            String nID=notice.getId();
            System.out.println("返回ID:"+nID);
            for(int i=0;i<attachmentList.length;i++){
                NoticeAttachment noticeAttachment=new NoticeAttachment();
                noticeAttachment.setNoticeId(nID);
                noticeAttachment.setAttachmentId(attachmentList[i]);
                list.add(noticeAttachment);
            }
            commonService.addNoticeAttachment(list);
        }
        noticeMessage.setCode(200);
        noticeMessage.setMessage("发布成功！");
        noticeMessage.setData(notice.getId());
        return noticeMessage;
    }
    //获取最新的公告
    @GetMapping(value = "lnotice")
    @ResponseBody
    public Message getLastNotice(){
        Message lastNoticeMessage=new Message();
        NoticeVO noticeVO=commonService.getLastNotice();
        lastNoticeMessage.setCode(200);
        lastNoticeMessage.setMessage("获取成功！");
        lastNoticeMessage.setData(noticeVO);
        return lastNoticeMessage;
    }


}
