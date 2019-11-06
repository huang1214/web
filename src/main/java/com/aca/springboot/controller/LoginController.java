package com.aca.springboot.controller;

import com.aca.springboot.entities.*;
import com.aca.springboot.service.BillService;
import com.aca.springboot.service.StudentService;
import com.aca.springboot.service.UserService;
import com.aca.springboot.service.testService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LoginController {

//    @DeleteMapping
//    @PutMapping
//    @GetMapping

    //@RequestMapping(value = "/user/login",method = RequestMethod.POST)
    private final UserService UserService;
    private final testService testService;
    private final StudentService studentService;
    private final BillService billService;

    @Autowired
    public LoginController(UserService userService,testService testService,StudentService studentService,BillService billService){
        this.UserService=userService;
        this.studentService=studentService;
        this.testService=testService;
        this.billService=billService;
    }

    //用户登录
    /**
     * 修改过，if语句中将其改成存储过程中的2，1，0
     * 2 - 用户不存在
     * 1 - 用户名密码错误
     * 0 - 登陆成功
     * @param username
     * @param password
     * @param session
     * @return
     */

    @PostMapping(value = "/login")
    @ResponseBody
    public JsonMessage login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        Map map= UserService.login(username,password);
        int tname= Integer.parseInt(String.valueOf(map.get("logintype")));
        int usertype = Integer.parseInt(String.valueOf(map.get("usertype")));
        JsonMessage j=new JsonMessage();
        if(tname == 2 |tname == 3 ){
            if(usertype == 1){
                Student student = studentService.selectBySnoReturnObject(username);
                session.setAttribute("loginUser",student);
                session.setAttribute("type",1);   //1为学生
            }else{
                Teacher teacher = UserService.selectByTno(username);
                session.setAttribute("loginUser",teacher);
                session.setAttribute("type",2);   //2为老师
            }
            j.setCode(0);
            j.setMsg("成功");
        }else if(tname == 1){
            //登陆失败
            j.setCode(2);
            j.setMsg("用户名密码错误");
        }else if(tname == 0){
            j.setCode(3);
            j.setMsg("用户不存在");
        }
        return j;
    }
    //管理员登录
    @PostMapping(value = "/adm_login")
    @ResponseBody
    public JsonMessage adm_login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session){
        JsonMessage j=new JsonMessage();
        Map map= UserService.a_login(username,password);
        int returnvalue= Integer.parseInt(String.valueOf(map.get("logintype")));
        int usertype = Integer.parseInt(String.valueOf(map.get("usertype")));
        if(returnvalue==4){
            if(usertype == 3){
                System.out.println("selectBy_ADM_ID_ReturnObject之前这里执行了");
                Administrator administrator=UserService.selectBy_ADM_ID_ReturnObject(username);
                session.setAttribute("loginUser",administrator);
                System.out.println("selectBy_ADM_ID_ReturnObject之后这里执行了");
                session.setAttribute("type",3);   //3为管理员
            }
            j.setCode(0);
            j.setMsg("成功");
        }else if(returnvalue == 1){
            j.setCode(2);
            j.setMsg("用户名或密码错误");
        }else if(returnvalue == 0){
            j.setCode(3);
            j.setMsg("用户不存在");
        }
        return j;
    }
/*    //管理员登录
    @PostMapping(value = "/user/a_login")
    public String admin_login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session){
        int tname = UserService.a_login(username,password);
        if(tname == 2){
            //登陆成功，防止表单重复提交，可以重定向到主页
            session.setAttribute("loginUser",username);
            return "redirect:/admin_index.html";
        }else if(tname == 1){
            //登陆失败
            map.put("msg","用户名密码错误");
            return  "login";
        }else if(tname == 0){
            map.put("msg","用户不存在");
            return  "login";
        }
        return "login";
    }*/

    /**
     * 修改密码
     * @param new_pwd1
     * @param new_pwd2
     * @param session
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/change_user")
    public ModelAndView changepwd(@RequestParam("new_pwd1") String new_pwd1,
                                  @RequestParam("new_pwd2") String new_pwd2,
                                  HttpSession session){
        ModelAndView mv = new ModelAndView("redirect:/application/changepwd_user");
        String userpwd = new_pwd1;
        String userid = session.getAttribute("loginUser").toString();
        if(userpwd.equals( new_pwd2)){
            int result = UserService.changepwd(userid,userpwd);
            System.out.println(result + userid);
            if( result == 0 ){
                System.out.println("修改密码失败" + "   "+result);
            }else{
                return mv;
            }
        }else{
            //跳出弹窗，提示两次密码不正确
        }
        return mv;
    }



    //表格查询部门页面，测试
    @ResponseBody
    @GetMapping(value = "/test")
    public JSONObject aaa(HttpServletRequest request){
        int page = Integer.parseInt(request.getParameter("page"));   //获取第几页
        int limit = Integer.parseInt(request.getParameter("limit")); //获取每页的最大条数

        return testService.list_tset(page,limit);
    }

    //点击删除后访问controller中的此方法
    @ResponseBody
    @RequestMapping(value = "/test/dele")
    public int ccc(HttpServletRequest request){
        String id = request.getParameter("param");
        return testService.delete_dept(id);
    }

    //点击编辑后访问controller中的此方法
    @ResponseBody
    @RequestMapping(value = "/test/edit")
    public boolean bbb(HttpServletRequest request,@RequestBody test t){
        //int id = Integer.parseInt(request.getParameter("param"));

        return true;
    }

    //点击搜索后，执行此方法
    @ResponseBody
    @RequestMapping(value = "/test/search")
    public JSONObject bbb(HttpServletRequest request,
                          @RequestParam("dname") String dname,
                          @RequestParam("dadmin") String dadmin){
        int page = Integer.parseInt(request.getParameter("page"));   //获取第几页
        int limit = Integer.parseInt(request.getParameter("limit")); //获取每页的最大条数

        return testService.list_tset_search(page,limit,dname,dadmin);

    }

    @ResponseBody
    @PostMapping("/getInfo")
    public Message getInfo(@RequestParam(value = "StartTime",defaultValue = "20190901",required = false)String ST,
                           @RequestParam(value = "EndTime",defaultValue = "20200630",required = false)String ET,
                           @RequestParam(value = "PersonNum",defaultValue = "5",required = false)int num,
                           HttpSession session){
        Message m=new Message();
        int type= (int) session.getAttribute("type");
        //学生老师需要获取 未审核+待确认+已完成(已确认+已拒绝)
        //管理员需要 待审核+已完成(已确认+已拒绝)
        Map map=new HashMap();
        if(type==1){
            String sno=((Student)session.getAttribute("loginUser")).getSno();
            map.put("unReadApp",testService.getApplicationCount(sno,0));
            map.put("refusedApp",testService.getApplicationCount(sno,1));
            map.put("passedApp",testService.getApplicationCount(sno,2));
            map.put("finishedApp",testService.getApplicationCount(sno,3));
            map.put("unReadBill",billService.getBillCount(sno,0));
            map.put("passedBill",billService.getBillCount(sno,2));
            map.put("refusedBill",billService.getBillCount(sno,1));
            map.put("role","1");
        }else if(type==2){
            String tno=((Teacher)session.getAttribute("loginUser")).getTno();
            map.put("unReadApp",testService.getApplicationCount(tno,0));
            map.put("refusedApp",testService.getApplicationCount(tno,1));
            map.put("passedApp",testService.getApplicationCount(tno,2));
            map.put("finishedApp",testService.getApplicationCount(tno,3));
            map.put("role","2");
        }else if(type==3){
            String tno="%";
            map.put("unReadApp",testService.getApplicationCount(tno,0));
            map.put("finishedApp",testService.getApplicationCount(tno,3)+testService.getApplicationCount(tno,1));
            map.put("unReadBill",billService.getBillCountAdmin(0));
            map.put("passedBill",billService.getBillCountAdmin(2));
            map.put("refusedBill",billService.getBillCountAdmin(1));
            map.put("AppPrizeInfo",testService.getAppPrizeInfo());
            map.put("StudentCount",testService.getTopStudent(num,ST,ET));
            map.put("TeacherCount",testService.getTopTeacher(num,ST,ET));
            map.put("role","3");
        }else{
            m.setCode(-1);
            m.setMessage("内部错误");
            return m;
        }
        m.setCode(0);
        m.setData(map);
        return m;
    }
}
