package com.aca.springboot.service;

import com.aca.springboot.entities.Attachment;
import com.aca.springboot.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 周全
 * @Date: 2019/11/18 15:03
 * @Description:
 */
@Service
public class CommonService {

    private final CommonMapper commonMapper;

    @Autowired
    public CommonService(CommonMapper commonMapper){
        this.commonMapper=commonMapper;
    }
    public int addBillNotice(Attachment attachment){
        int res=commonMapper.addNotice(attachment);
        return res;
    }
}
