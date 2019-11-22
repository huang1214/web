package com.aca.springboot.service;

import com.aca.springboot.utils.StrUtils;
import com.aca.springboot.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @ClassName CommenService
 * @Author HWG
 * @Time 2019/11/15 15:32
 */
@Service
public class CommenService {
    @Value("${web.basePath}")
    private String basePath; //文件路径

    public String upoad(MultipartFile file, int type) throws Exception {
        //重命名
        String filename = file.getOriginalFilename();
        String suffixName = filename.substring(filename.lastIndexOf("."));
        String rename = StrUtils.timeStamp() + StrUtils.randomNum(true, 3) + suffixName;
        String serverPath = "";
        String certPath= basePath+"/"+TimeUtil.getFormatYYYY()+"/cert/";
        String docPath=basePath+"/"+TimeUtil.getFormatYYYY()+"/doc/";
        String packagePath=basePath+"/"+TimeUtil.getFormatYYYY()+"/package/";
        switch (type) {
            case 1:
                serverPath = certPath + rename;
                break;
            case 2:
                serverPath = docPath + rename;
                break;
            case 3:
                serverPath = packagePath + rename;
                break;
            default:
                throw new Exception("文件类型错误");
        }
        try {
            File cert = new File(certPath);
            File doc=new File(docPath);
            File pac=new File(packagePath);
            if (!cert.exists()) {
                cert.mkdirs();
            }
            if (!doc.exists()) {
                doc.mkdirs();
            }
            if (!pac.exists()) {
                pac.mkdirs();
            }
            File dest = new File(serverPath);
            file.transferTo(dest);
            return rename;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("文件保存失败！请重试");
        }
    }

//    public CommenService() {
//        File file = new File(certPath);
//        File doc=new File(docPath);
//        File pac=new File(packagePath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        if (!doc.exists()) {
//            doc.mkdirs();
//        }
//        if (!pac.exists()) {
//            pac.mkdirs();
//        }
//    }
}
