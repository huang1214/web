package com.aca.springboot.controller;

import com.aca.springboot.entities.Attachment;
import com.aca.springboot.entities.Message;
import com.aca.springboot.service.CommenService;
import com.aca.springboot.service.CommonService;
import com.aca.springboot.utils.TimeUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: 周全
 * @Date: 2019/10/15 09:39
 * @Description: 该Controller为所有请求服务
 */
@Controller
@Api("公共控制器")
@RequestMapping(value = "/common")
public class CommonController {
    @Value("${web.upload-path}")
    private String path; //文件路径

    @Autowired
    private CommonService commonService;
    @Autowired
    private CommenService commenService;

    //文件上传
    @PostMapping(value = "/upload")
    @ResponseBody
    public Message pictureUp(@RequestParam(value = "file") MultipartFile picture){
        Message addFileMessage=new Message();
        //System.out.println(picture.getOriginalFilename());
        String fileName=picture.getOriginalFilename();
        String extend=fileName.substring(fileName.lastIndexOf("."));
        String newName= TimeUtil.getFileID()+extend;
        //System.out.println(newName);
        try {
            FileOutputStream outputStream=new FileOutputStream(path+newName);
            FileCopyUtils.copy(picture.getInputStream(),outputStream);
            addFileMessage.setCode(200);
            addFileMessage.setMessage("文件上传成功！");
            addFileMessage.setData(newName);
        } catch (IOException e) {
            e.printStackTrace();
            addFileMessage.setCode(202);
            addFileMessage.setMessage("文件上传失败！");
        }
        return addFileMessage;
    }
    @PostMapping("/uploadM")
    @ResponseBody
    public Message upload_m(@RequestParam(value = "file") MultipartFile file,
                            @RequestParam(value = "type",required = false,defaultValue = "1") int type
    ){
        Message message=new Message();
        try{
            message.setData(commenService.upoad(file,type));
            message.setCode(0);
        }catch (Exception e){
            message.setCode(-1);
            message.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return message;
    }
    //文件上传
    @PostMapping(value = "/attachment")
    @ResponseBody
    public Message attachmentUpload(@RequestParam(value = "file") MultipartFile attachment){
        Message addAttachmentMessage=new Message();
        Attachment pojo=new Attachment();
        System.out.println(attachment.getOriginalFilename());
        String fileName=attachment.getOriginalFilename();
        String extend=fileName.substring(fileName.lastIndexOf("."));
        String newName= TimeUtil.getFileID()+extend;
        System.out.println(newName);
        pojo.setAttachmentName(fileName);
        pojo.setAttachmentPath(newName);
        pojo.setCreateTime(new Date());
        pojo.setFlag("0");
        commonService.addBillNotice(pojo);
        String id=pojo.getId();
        try {
            FileOutputStream outputStream=new FileOutputStream(path+newName);
            FileCopyUtils.copy(attachment.getInputStream(),outputStream);
            addAttachmentMessage.setCode(200);
            addAttachmentMessage.setMessage("附件上传成功！");
            addAttachmentMessage.setData(id);
        } catch (IOException e) {
            e.printStackTrace();
            addAttachmentMessage.setCode(202);
            addAttachmentMessage.setMessage("附件上传失败！");
        }
        return addAttachmentMessage;
    }

}
