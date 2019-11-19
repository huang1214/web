package com.aca.springboot.service;

import com.aca.springboot.entities.Attachment;
import com.aca.springboot.entities.Notice;
import com.aca.springboot.entities.NoticeAttachment;
import com.aca.springboot.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    //添加附件
    public int addBillNotice(Attachment attachment){
        int res=commonMapper.addNotice(attachment);
        return res;
    }
    //添加公告
    public int addNotice(Notice notice){
        return commonMapper.releaseNotice(notice);
    }
    //添加公告附件对应关系
    public int addNoticeAttachment(List<NoticeAttachment> noticeAttachments){
        int sum=0;
        for(int i=0;i<noticeAttachments.size();i++){
            sum+=commonMapper.noticeAttachment(noticeAttachments.get(i));
        }
        return sum;
    }

}
