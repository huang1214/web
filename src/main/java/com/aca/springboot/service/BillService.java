package com.aca.springboot.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.aca.springboot.entities.Bill;
import com.aca.springboot.entities.BillMember;
import com.aca.springboot.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class BillService {
    @Autowired
    BillMapper billMapper;

    /**
     * 添加一条报销记录
     */
    public Bill addBill(Bill bill){
        //SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
      //  String year=simpleDateFormat.format(bill.getCyear());  //只要年份
        Bill bill1=billMapper.selectBillByCtidCyearGroupleader(bill.getCtid(),bill.getCyear(),bill.getGroupleader());
        System.out.println(bill1);
        if(bill1!=null){
            System.out.println("进来1");
            return null;
        }else {
            int result=billMapper.addBill(bill);
            if(result!=0){
                System.out.println("进来2");
                return bill;
            }else {
                System.out.println("进来3");
                return null;
            }
        }
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
}
