package com.aca.springboot.service;

import com.aca.springboot.entities.Application;
import com.aca.springboot.entities.ApplicationMember;
import com.aca.springboot.entities.JsonMessage;
import com.aca.springboot.entities.Message;
import com.aca.springboot.mapper.ApplicationMapper;
import com.aca.springboot.vo.AppComAppLeaderVO;
import com.aca.springboot.vo.AppComDetailVO;
import com.aca.springboot.vo.FileNameVO;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class ApplicationService {
    @Resource
    ApplicationMapper applicationMapper;
    @Value("${web.basePath}")
    private String basePath; //文件路径
    /**
     * 插入申请表数据
     */
/*    add(comName,applicantId,teacher1Id,teacher2Id,unit,leader,teamNum,team,studentPrice,teacherPrice,
        awardTypeId,awardDate,applicantBankCard,workName,workBriefIntro)*/
    public int add(Application app) {
        /*Map map = new HashMap<String,String>();
        map.put("comName",comName);
        map.put("applicantId",applicantId);
        map.put("teacher1Id",teacher1Id);
        map.put("teacher2Id",teacher2Id);
        map.put("unit",unit);
        map.put("leader",leader);
        map.put("teamNum",teamNum);
        map.put("team",team);
        map.put("studentPrice",studentPrice);
        map.put("teacherPrice",teacherPrice);
        map.put("awardTypeId",awardTypeId);
        map.put("awardDate",awardDate);
        map.put("applicantBankCard",applicantBankCard);
        map.put("workName",workName);
        map.put("workBriefIntro",workBriefIntro);

*//*        String strImg = new String(img);*//*
         *//*  map.put("certificateImg",strImg);*//*
        byte[] imgs=img;*/
        System.out.println(app.toString());
        return applicationMapper.add(app);
    }

    /**
     * 插入一条申请对应表数据
     *
     * @return
     */
    public int addApplicationMember(ApplicationMember applicationMember) {
        return applicationMapper.addApplicationMember(applicationMember);
    }

    /**
     * 插入申请表对于数据
     * 多条插入
     *
     * @return
     */
    public int addMoreApplicationMember(List<ApplicationMember> addMoreApplicationMember) {
        int result;
        int moreResult = 0;    //成功条数
        for (int i = 0; i < addMoreApplicationMember.size(); i++) {
            result = addApplicationMember(addMoreApplicationMember.get(i));
            moreResult = moreResult + result;
        }
        return moreResult;
    }

    public int addMultMember(String tms, String ts, int stuPrice, int teaPrice, String date, String appid) throws Exception {
        int maxAmount = 2000000, temp = 0;
        boolean isEmpty = true;
        if (null == tms)
            return 0;
        String st, et, year, month;
        year = date.substring(0, 4);
        month = date.substring(4, 6);
        if (Integer.parseInt(month) >= 9 || Integer.parseInt(month) <= 2) {
            st = year + "09";
            et = (Integer.parseInt(year) + 1) + "03";
        } else {
            st = year + "03";
            et = year + "09";
        }
        Map map = new HashMap();
        map.put("st", st);
        map.put("et", et);
        map.put("status", 2);
        List<ApplicationMember> list1 = new ArrayList<>();
        String[] split = tms.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                String id = (split[i].split(":"))[0];
                int proportion = Integer.parseInt((split[i].split(":"))[1]);
                int nowMount = (stuPrice * proportion / 100);
                map.put("id", id);
                int getMount = selectMoney(map);
                if ((nowMount + getMount) >= maxAmount) {
                    throw new Exception("学号为[" + map.get("id") + "]的同学在" + st + "-" + et + "中已获得共计" + getMount / 100 + "元奖金，超出上限" + maxAmount / 100 + "元");
                }
                list1.add(new ApplicationMember(appid, id, "1", i + 1 + "", proportion, nowMount));
                temp += proportion;
                isEmpty = false;
            }
        }
        if (!isEmpty) {
            if (temp != 100)
                throw new Exception("学生奖金占比总和须为100");
        }
        temp = 0;
        isEmpty = true;
        split = ts.split(",");
        for (int i = 0; i < split.length; i++) {
            if (split[i].length() > 0) {
                String id = (split[i].split(":"))[0];
                int proportion = Integer.parseInt((split[i].split(":"))[1]);
                int nowMount = (teaPrice * proportion / 100);
                map.put("id", id);
                int getMount = selectMoney(map);
                if ((nowMount + getMount) >= maxAmount) {
                    throw new Exception("编号为[" + map.get("id") + "]的指导老师在" + st + "-" + et + "中已获得共计" + getMount / 100 + "元奖金，超出上限" + maxAmount / 100 + "元");
                }
                list1.add(new ApplicationMember(appid, id, "2", i + 1 + "", proportion, nowMount));
                temp += proportion;
                isEmpty = false;
            }
        }
        if (!isEmpty) {
            if (temp != 100)
                throw new Exception("老师奖金占比总和须为100");
        }
        return addMoreApplicationMember(list1);
    }

    public List com_name() {
        return applicationMapper.com_name();
    }

    /**
     * 通过输入的竞赛名称，作品类型，所获奖项类型，所获奖项等级，来获得奖金类型
     *
     * @param ctId
     * @param level_type
     * @param prize_type
     */
    public String getawardtype(String ctId,
                               String level_type,
                               String prize_type) {
        Map map = new HashMap<String, String>();
        map.put("u_ctId", ctId);
        map.put("u_level_type", level_type);
        map.put("u_prize_type", prize_type);
        System.out.println(map.toString());
        applicationMapper.getawardtype(map);
        String rewardtype = (String) map.get("awardtype");
        return rewardtype;
    }

    public Map get_price(String atid) {
        return applicationMapper.get_price(atid);
    }


/*    public List workresult(String leader_id){
        Map map = new HashMap<String,String>();
        map.put("leader_id",leader_id);
        return applicationMapper.workresult(map);
    }*/
    /**
     * 获取用户id，查看自己全部获奖作品的审核情况
     * @param applicantId
     * @return
     */

    /**
     * 获取申请列表
     *
     * @return
     */
    public List<AppComAppLeaderVO> get_list(String sno) {
        Map map = new HashMap<String, String>();
        map.put("SNO", sno);
        return applicationMapper.get_application_list(map);
    }

    /**
     * 获取申请列表
     *
     * @return
     */
    public PageInfo<AppComAppLeaderVO> get_list_with_page(String sno, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Map map = new HashMap<String, String>();
        map.put("SNO", sno);
        PageInfo re = new PageInfo(applicationMapper.get_application_list(map));
        return re;
    }

    /**
     * 获取申请列表
     *
     * @return
     */
    /*学长的代码（他不让我动 →_→ ）：
     public PageInfo<AppComAppLeaderVO> get_list_with_page_m(int status, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String,String>();
        map.put("status",status);
        PageInfo re=new PageInfo(applicationMapper.get_application_list_m(map));
        return re;
    }*/
    /*这以下是机智的我写的*/
    public JsonMessage get_list_with_page_m(int status, int pageNum, int pageSize) {
//        PageHelper.startPage(pageNum,pageSize);
        Map map = new HashMap<String, String>();
        map.put("STATUS", status);
        map.put("endIndex", pageNum * pageSize);
        map.put("startIndex", (pageNum - 1) * pageSize + 1);
        JsonMessage result = new JsonMessage();
//        PageInfo re=new PageInfo(applicationMapper.get_application_list_m(map));
//        List<AppComAppLeaderVO> list = re.getList();
        List<AppComAppLeaderVO> application_list_m = applicationMapper.get_application_list_m(map);
        result.setCode(0);
        result.setMsg("成功");
        result.setCount(applicationMapper.get_application_list_m_count(map));
        JSONArray jsonArray = new JSONArray((List) application_list_m);
        result.setData(jsonArray);
        return result;
    }
    /*这以上是机智的我写的*/

    public JsonMessage get_list_file(int type, String year, String op) {
        Map map = new HashMap<String, String>();
        JsonMessage result = new JsonMessage();
        result.setCode(0);
        result.setMsg("成功");
        result.setCount(1);
        List list = new ArrayList();
        Map map1 = new HashMap();
        String pacFileName = year + (type == 1 ? "证书" : (type == 2 ? "参赛报告" : "花絮")) + ".zip";
        map1.put("fileName", pacFileName);
        result.setData(new JSONArray(list));
        String certPath= basePath+"/"+year+"/cert/";
        String docPath=basePath+"/"+year+"/doc/";
        String packagePath=basePath+"/"+year+"/package/";
        if ("count".equals(op)) {
            map.put("start",year);
            map.put("end",(Integer.parseInt(year)+1)+"");
            map1.put("fileCount", applicationMapper.get_application_file_count(map));
        } else {
            String url = "";
            List<AppComAppLeaderVO> application_list_m = applicationMapper.get_application_file();
            //TODO 获得打包文件下载地址 url
            List<FileNameVO> files = new ArrayList<>();
            String path = "";
            String realName = "";
            String pacName = "";
            if (type == 1) {
                path = certPath;
//                File testFile=new File(path);
//                if(testFile.list().length==0){
//                    result.setCode(-1);
//                    result.setMsg("该文件夹已被清空");
//                    return result;
//                }
                for (AppComAppLeaderVO acal : application_list_m) {
                    realName = acal.getCertificateImg();
                    FileNameVO temp = new FileNameVO(path + realName);
                    pacName = acal.getCompetion().getCtname() + "-" + acal.getWorkName() + "-" + acal.getAppid() + "." + realName.substring(realName.lastIndexOf(".") + 1);
                    temp.setNewName(pacName);
                    files.add(temp);
                }
            } else if (type == 2) {
                path = docPath;
                for (AppComAppLeaderVO acal : application_list_m) {
                    realName = acal.getGetawardImg();
                    FileNameVO temp = new FileNameVO(path + realName);
                    pacName = acal.getCompetion().getCtname() + "-" + acal.getWorkName() + "-" + acal.getAppid() + "." + realName.substring(realName.lastIndexOf(".") + 1);
                    temp.setNewName(pacName);
                    files.add(temp);
                }
            } else {
                path = packagePath;
                for (AppComAppLeaderVO acal : application_list_m) {
                    realName = acal.getHighLight();
                    FileNameVO temp = new FileNameVO(path + realName);
                    pacName = acal.getCompetion().getCtname() + "-" + acal.getWorkName() + "-" + acal.getAppid() + "." + realName.substring(realName.lastIndexOf(".") + 1);
                    temp.setNewName(pacName);
                    files.add(temp);
                }
            }
            File pacFile = new File(path + pacFileName);
            byte[] buf = new byte[1024];
            try {
                ZipOutputStream out = new ZipOutputStream(new FileOutputStream(pacFile));
                for (int i = 0; i < files.size(); i++) {
                    FileInputStream in = new FileInputStream(files.get(i));
                    out.putNextEntry(new ZipEntry(files.get(i).getNewName()));
                    System.out.println(files.get(i).getNewName());
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                    }
                    out.closeEntry();
                    in.close();
                }
                out.close();
                System.out.println("压缩完成.");
                map1.put("url", "/" + year + "/" + (type == 1 ? "cert" : (type == 2 ? "doc" : "package")) + "/" + pacFileName);
            }catch (FileNotFoundException fileNotFoundException) {
                result.setCode(-1);
                result.setMsg("该文件夹已被清空！");
            } catch (Exception e) {
                e.printStackTrace();
                result.setCode(-1);
                result.setMsg("打包文件出错，请重试");
            }
        }
        list.add(map1);
        result.setData(new JSONArray(list));
        return result;
    }

    public Message get_file_dir() {
        Message message=new Message();
        File dir=new File(basePath);
        String[] list = dir.list();
        message.setMessage("成功");
        message.setCode(0);
        message.setData(list);
        return message;
    }
    public JsonMessage clear_file_dir(int type, String year) {
        JsonMessage message=new JsonMessage();
        String dirPath=basePath+"/"+year+(type==1?"/cert/":(type==2?"/doc/":"/package/"));
        File dir=new File(dirPath);
        try {
            if(!dir.exists()){
                message.setMsg("该文件夹不存在");
                message.setCode(-1);
            }else if(!dir.isDirectory()){
                message.setMsg("此路径非文件夹");
                message.setCode(-1);
            }else{
                String[] tempList = dir.list();
                File temp;
                for (int i = 0; i < tempList.length; i++) {
                    temp = new File(dirPath+tempList[i]);
                    if (temp.isFile()) {
                        temp.delete();
                    }
                }
                message.setMsg("成功");
                message.setCode(0);
            }
        }catch (Exception e){
            message.setMsg("文件操作出现错误，请重试");
            message.setCode(-1);
        }
        return message;
    }
    /**
     * 获取申请列表
     *
     * @return
     */
    public JsonMessage get_list_json(String sno, int pageNum, int pageSize, int type) {
        PageHelper.startPage(pageNum, pageSize);
        Map map = new HashMap<String, String>();
        map.put("SNO", sno);
        map.put("endIndex", pageNum * pageSize);
        map.put("startIndex", (pageNum - 1) * pageSize + 1);
        map.put("TEMPSTATUS", type);
        JsonMessage result = new JsonMessage();
        List<AppComAppLeaderVO> application_list = applicationMapper.get_application_list(map);
        for (int i = 0; i < application_list.size(); i++) {
            if (sno.equals(application_list.get(i).getLeader()))
                application_list.get(i).setRes(1);
        }
        result.setCode(0);
        result.setMsg("成功");
        result.setCount(applicationMapper.get_application_list_count(map));
        JSONArray jsonArray = new JSONArray((List) application_list);
        result.setData(jsonArray);
        return result;
    }

    /**
     * 获取详情
     *
     * @param appid
     * @return
     */
    public AppComDetailVO get_detail(String appid, String sno) {
        Map map = new HashMap<String, String>();
        map.put("APPID", appid);
        AppComDetailVO application_detail = applicationMapper.get_application_detail(map);
        if (sno.equals(application_detail.getApplicantId()))
            application_detail.setRes(1);
        return application_detail;
    }

    public boolean update_state(String appid, int status, String note) {
        Map map = new HashMap();
        map.put("appid", appid);
        map.put("status", status);
        map.put("note", note);
        int i = applicationMapper.update_state(map);
        if (i == 1) {
            return true;
        }
        return false;
    }

    public boolean update_state_pass(String appid, int status, String note) {
        Map map = new HashMap();
        map.put("appid", appid);
        map.put("status", status);
        map.put("note", note);
        int i = applicationMapper.update_state(map);
        if (i == 1) {
            map.put("status", 2);
            if (applicationMapper.update_am_state(map) != 0) {
                return true;
            }
        }
        return false;
    }

    public int delete(String appid) {
        return applicationMapper.deleteApp(appid) + applicationMapper.deleteAppRes(appid);
    }

    public int selectMoney(Map map) {
        Object o = applicationMapper.selectMoney(map);
        if (o == null)
            return 0;
        else {
            java.math.BigDecimal bigDecimal = (BigDecimal) o;
            return Integer.parseInt(bigDecimal.toString());
        }
    }
}
