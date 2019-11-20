package com.aca.springboot.mapper;

import com.aca.springboot.entities.Attachment;
import com.aca.springboot.entities.Notice;
import com.aca.springboot.entities.NoticeAttachment;

import com.aca.springboot.vo.NoticeVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
    /**
     * 添加附件信息
     * @param attachment 实体类
     * @return  返回影响条数
     */
    public Integer addNotice(Attachment attachment);
    /**
     * 添加公告信息
     * @param notice 实体类
     * @return  返回影响条数
     */
    public Integer releaseNotice(Notice notice);
    /**
     * 添加公告附件对应信息
     * @param noticeAttachment 实体类
     * @return  返回影响条数
     */
    public Integer noticeAttachment(NoticeAttachment noticeAttachment);
    //获取公告
    public NoticeVO getBillNotice();







    /*@InsertProvider(type = Provider.class,method = "insertBatch")
    public Integer noticeAttachment(List<NoticeAttachment> noticeAttachments);
    class Provider{
        //批量插入
        public String insertBatch(Map map) {
            List<NoticeAttachment> notices = (List<NoticeAttachment>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT All INTO bill_notice_attachment (id,notice_id,attachment_id) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(bill_notice_attachment_id_Seq.nextval, #'{'list[{0}].noticeId}, " +
                            "#'{'list[{0}].attachmentId})");
            for (int i = 0; i < notices.size(); i++) {
                sb.append(mf.format(new Object[] {i}));
                if (i < notices.size() - 1)
                    sb.append("INTO bill_notice_attachment VALUES");
            }
            sb.append(" SELECT 1 FROM DUAL");
            return sb.toString();
        }
    }*/
}
