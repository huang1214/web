package com.aca.springboot.vo;

import com.aca.springboot.entities.Administrator;
import com.aca.springboot.entities.Attachment;
import com.aca.springboot.entities.Notice;

import java.util.List;

/**
 * @Author: 周全
 * @Date: 2019/11/19 17:58
 * @Description:
 */
public class NoticeVO extends Notice {
    private List<Attachment> attachments;
    private Administrator administrator;

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
