package com.aca.springboot.service;

import com.aca.springboot.entities.Bill;
import com.aca.springboot.entities.BillMember;
import com.aca.springboot.entities.JsonMessage;
import com.aca.springboot.mapper.BillMapper;
import com.aca.springboot.utils.Define;
import com.aca.springboot.vo.BillVO;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BillService {

    private final BillMapper billMapper;

    @Autowired
    public BillService(BillMapper billMapper){
        this.billMapper=billMapper;
    }

    /**
     * 添加一条报销记录
     */
    public Bill addBill(Bill bill){
        //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
      //  String year=simpleDateFormat.format(bill.getCyear());  //只要年份
        Bill bill1=billMapper.selectBillByCtidCyearGroupleader(bill.getCtid(),bill.getCyear(),bill.getGroupleader());
        if(bill1!=null){
            return bill1;  //已经申请过了
        }else {
            billMapper.addBill(bill);  //插入bill表
            List<BillMember> billMembers=bill.getBillMembers();
            for(int i=0;i<billMembers.size();i++){
                billMembers.get(i).setBillId(bill.getBid());
            }
            addMoreBillMember(billMembers);  //写bill对应表
            return null;   //申请提交成功
        }
    }
    //根据比赛ID，年份，团队负责人ID来查询报销记录
    public Bill queryBillByCtidCyearGroupleader(String ctid,String cyear,String groupLeader){
        return billMapper.selectBillByCtidCyearGroupleader(ctid,cyear,groupLeader);
    }
    //返回该生报销记录
    public List<BillVO> queryAllBill(String sno){
        JsonMessage j = new JsonMessage();
        Map map=new HashMap();
        map.put("SNO",sno);
        List<BillVO> listBill=billMapper.get_bill_list(map);
        return listBill;
    }
    //返回该生报销记录,分页
    public JsonMessage queryAllBillWithPage(String sno,int pageNum,int pageSize){
        //设置从第几页查询N条
        PageHelper.startPage(pageNum,pageSize);
        Map map=new HashMap();
        map.put("SNO",sno);
        List<BillVO> listBill=billMapper.get_bill_list(map);
        PageInfo<BillVO> pageInfo=new PageInfo(listBill);
        List list = pageInfo.getList();
        JsonMessage jsonMessage=new JsonMessage();
        jsonMessage.setCode(0);
        jsonMessage.setMsg("查询成功");
        jsonMessage.setCount(pageInfo.getSize());
        jsonMessage.setData(new JSONArray(list));
        return jsonMessage;
    }
    //返回所有的报销记录,分页
    public JsonMessage queryAllBill(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<BillVO> listBill=billMapper.get_all_bill_list();
        PageInfo<BillVO> pageInfo=new PageInfo(listBill);
        List list=pageInfo.getList();
        JsonMessage jsonMessage=new JsonMessage();
        jsonMessage.setCode(0);
        jsonMessage.setMsg("查询成功");
        jsonMessage.setCount(pageInfo.getSize());
        jsonMessage.setData(new JSONArray(list));
        return jsonMessage;
    }
    /**
     * 插入一条备案对应表数据
     */
    public int addBillMember(BillMember billMember){

        return billMapper.addBillMember(billMember);
    }
    /**
     * 多条备案对应表插入
     */
    public int addMoreBillMember(List<BillMember> billMembers){
        int result;
        int moreResult=0;    //成功条数
        for(int i=0;i<billMembers.size();i++){
            result = addBillMember(billMembers.get(i));
            moreResult = moreResult+result;
        }
        return moreResult;
    }
    //更新报销表
    public int updateBill(Bill bill){
        Map<String,String> map=new HashMap<>();
        map.put("BID",bill.getBid());
        map.put("STATE",bill.getState());
        map.put("NOTE",bill.getNote());
        int res=billMapper.change_bill_state(map);
        return res;

    }
    public int deleteBill(String bid){
        Map<String,String> map=new HashMap<>();
        map.put("BID",bid);
        int res=billMapper.deleteBill(map);
        res+=billMapper.deleteBillMember(map);
        return res;
    }
}
