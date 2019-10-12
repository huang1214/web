package com.aca.springboot.service;

import com.aca.springboot.entities.BillMember;
import com.aca.springboot.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    @Autowired
    BillMapper billMapper;

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
