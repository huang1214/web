package com.aca.springboot.mapper;

import com.aca.springboot.entities.Attachment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommonMapper {
    /**
     * 添加附件信息
     * @param attachment 实体类
     * @return  返回该条记录ID
     */
    public Integer addNotice(Attachment attachment);

}
