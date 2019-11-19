package com.aca.springboot.entities;

/**
 * @Author: 周全
 * @Date: 2019/11/19 10:48
 * @Description:
 */
public class NoticeAttachment {
    private String id;
    private String noticeId;
    private String attachmentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }
}
