var type=0;
layui.use(['table','layer','form','jquery'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form
    var $ = layui.jquery;
    //显示表单状态基本信息
    table.render({
        elem: '#application_state'
        ,height: 380
        ,url: '/app/list'//数据接口
        ,toolbar:'#toolbar_topOp'
        ,page: true //开启分页
        ,where:{'type':0}
        ,loading: true
        ,cols: [
            [   //表头
                //详情中含有 指导老师，团队具体人员，第一单位，
                //layui中显示一个对象中另一个对象的属性值
                // { field: 'dname', title : '部门', width:80,templet: '<div>{{d.dept.dname}}</div>'}
                {field: 'appid', title: '申请编号', minWidth:109, sort: true,fixed: 'left'}
                ,{field: 'ctname', title: '比赛名称', minWidth:200,templet: '<div>{{d.competion.ctname}}</div>'}
                ,{field: 'applicantId', title: '申请人', minWidth:105,templet: '<div>{{d.appplicantStu.sname}}</div>'}
                ,{field: 'leader', title: '项目组长', minWidth:105,templet: '<div>{{d.leaderStu.sname}}</div>'}
                // ,{field: 'studentPrice', title: '学生奖励金额', minWidth: 50,templet: function(d){
                //     return d.studentPrice/100;}}
                ,{field: 'teacherPrice', title: '老师奖金', minWidth: 50,templet: function(d){
                    return d.teacherPrice/100;}}
                ,{field: 'awardDate', title: '申请时间', minWidth: 150, sort: true}
                ,{field: 'status', title: '申请状态', minWidth: 135,templet: function(d){
                    if (d.status==0) {  // 自定义内容
                        return "未审核";
                    } else if (d.status==1) {
                        return "审核未通过";
                    }
                    else if (d.status==2) {
                        return "审核通过";
                    }
                    else if (d.status==3) {
                        return "项目组长已确认";
                    }
                }}
                ,{fixed: 'right', title:'操作', toolbar: '#bar',Width:100}
            ]
        ]
    });
    listTable=function(listType){
        table.render({
            elem: '#application_state'
            ,height: 380
            ,url: '/app/list'//数据接口
            ,toolbar:'#toolbar_topOp'
            ,page: true //开启分页
            ,where:{'type':listType}
            ,loading: true
            ,cols: [
                [   //表头
                    //详情中含有 指导老师，团队具体人员，第一单位，
                    //layui中显示一个对象中另一个对象的属性值
                    // { field: 'dname', title : '部门', width:80,templet: '<div>{{d.dept.dname}}</div>'}
                    {field: 'appid', title: '申请编号', minWidth:109, sort: true,fixed: 'left'}
                    ,{field: 'ctname', title: '比赛名称', minWidth:200,templet: '<div>{{d.competion.ctname}}</div>'}
                    ,{field: 'applicantId', title: '申请人', minWidth:105,templet: '<div>{{d.appplicantStu.sname}}</div>'}
                    ,{field: 'leader', title: '项目组长', minWidth:105,templet: '<div>{{d.leaderStu.sname}}</div>'}
                    // ,{field: 'studentPrice', title: '学生奖励金额', minWidth: 50,templet: function(d){
                    //     return d.studentPrice/100;}}
                    ,{field: 'teacherPrice', title: '老师奖金', minWidth: 50,templet: function(d){
                        return d.teacherPrice/100;}}
                    ,{field: 'awardDate', title: '申请时间', minWidth: 150, sort: true}
                    ,{field: 'status', title: '申请状态', minWidth: 135,templet: function(d){
                        if (d.status==0) {  // 自定义内容
                            return "未审核";
                        } else if (d.status==1) {
                            return "审核未通过";
                        }
                        else if (d.status==2) {
                            return "审核通过";
                        }
                        else if (d.status==3) {
                            return "项目组长已确认";
                        }
                    }}
                    ,{fixed: 'right', title:'操作', toolbar: '#bar',Width:100}
                ]
            ]
        });
    }
    //表格：“表单状态基本信息” 实例的监听工具条
    table.on('tool(application_operate)', function(obj){ //注：tool 是工具条事件名，application_state 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        // console.log(obj)
        if(layEvent === 'detail'){ //查看详情
            console.log(data.appid);
            $.ajax(
                {
                    url: '/app/detail?appid=' + data.appid,
                    type: 'GET',   // 请求方式
                    success: function (result) {
                        layer.open({
                            type: 1
                            ,title: '查看详细信息'  //弹出层标题
                            ,area: ['800px', '400px']   //设置弹出层大小[宽，高]
                            ,offset: 'auto'   //设置弹出层位置 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                            ,content:$("#layer_application_detail")
                            ,shade: 0 //不显示遮罩
                            ,yes: function () {  //确定按钮回调方法， 需要手动关闭弹出层
                                layer.closeAll();
                            }
                            ,end:function(){
                                // layer.closeAll();
                            }
                        });
                        setTableValue(result);//动态向表格赋值
                    },
                    error: function () {
                    }
                }
            );
        }else if(layEvent === 'ensure'){ //确定该条申请结果
            $.ajax(
                {
                    url: '/app/change?appid=' + data.appid+'&operation=assure',     // 请求地址,访问controller中的ccc方法, 就是你的控制器, 如 test.com/home/index/index
                    type: 'POST',   // 请求方式
                    success: function (result) {
                        console.log('success...'); // 请求成功后的回调函数, result 为响应内容
                        if(result.code == 0){
                            layer.alert('确认成功！');
                        }else {
                            layer.alert('确认失败！');
                        }
                        table_app.reload();   //表格重载
                    },
                    error: function () {
                        console.log('Send Request Fail..'); // 请求失败时的回调函数
                    }
                }
            );

        } else if(layEvent === 'LAYTABLE_TIPS'){
            layer.alert('Hi，头部工具栏扩展的右侧图标。');
        }
    });
    function setTableValue(result){
        // console.log(data.appid+"这里执行了应该显示的申请号");
        /*比赛信息*/
        var appid=document.getElementById('appid');
        var ctname=document.getElementById('ctname');
        var workName=document.getElementById('workName');
        var awardDate=document.getElementById('awardDate');
        var workBriefIntro=document.getElementById('workBriefIntro');
        var unit=document.getElementById('unit');

        var com_type=document.getElementById('com_type');
        var result_type=document.getElementById('result_type');
        var level_type=document.getElementById('level_type');
        var prize_type=document.getElementById('prize_type');

        /*申请人信息*/
        var aStu_sno=document.getElementById('aStu_sno');
        var aStu_sname=document.getElementById('aStu_sname');
        var aStu_cno=document.getElementById('aStu_cno');

        /*项目组长信息*/
        var lStu_sno=document.getElementById('lStu_sno');
        var lStu_sname=document.getElementById('lStu_sname');
        var lStu_cno=document.getElementById('lStu_cno');

        appid.innerHTML=result.data.appid;               // 使用 DOM
        ctname.innerHTML=result.data.com.ctname;
        workName.innerHTML=result.data.workName;
        awardDate.innerHTML=result.data.awardDate;
        workBriefIntro.innerHTML=result.data.workBriefIntro;
        unit.innerHTML=result.data.unit;

        if(result.data.awar.com_type==0)
        {
            com_type.innerHTML="一般竞赛项目";
        }
        if(result.data.awar.com_type==1)
        {
            com_type.innerHTML="重点竞赛项目";
        }

        if(result.data.awar.result_type==0)
        {
            result_type.innerHTML="考试";
        }
        if(result.data.awar.result_type==1)
        {
            result_type.innerHTML="作品";
        }

        if(result.data.awar.level_type==0)
        {
            level_type.innerHTML="省级";
        }
        if(result.data.awar.level_type==1)
        {
            level_type.innerHTML="国家级";
        }
        if(result.data.awar.level_type==2)
        {
            level_type.innerHTML="校级";
        }
        if(result.data.awar.level_type==3)
        {
            level_type.innerHTML="院级";
        }

        if(result.data.awar.prize_type==1)
        {
            prize_type.innerHTML="特等奖";
        }
        if(result.data.awar.prize_type==2)
        {
            prize_type.innerHTML="一等奖";
        }
        if(result.data.awar.prize_type==3)
        {
            prize_type.innerHTML="二等奖";
        }
        if(result.data.awar.prize_type==4)
        {
            prize_type.innerHTML="三等奖";
        }
        if(result.data.awar.prize_type==5)
        {
            prize_type.innerHTML="优秀奖";
        }
        for(i=0;i<result.data.stus.length;i++)
        {
            if(result.data.stus[i].sno!=null){
                $("<tr></tr>").append("<td>"+"项目成员"+(i+1)+"编号</td>")
                    .append("<td>"+result.data.stus[i].sno+"</td>")
                    .appendTo("#application_detail_table");
            }
            console.log(result.data.stus[i].sno)
            if(result.data.stus[i].sname!=null){
                $("<tr></tr>").append("<td>"+"项目成员"+(i+1)+"姓名</td>")
                    .append("<td>"+result.data.stus[i].sname+"</td>")
                    .appendTo("#application_detail_table");
            }
            if(result.data.stus[i].cno!=null){
                $("<tr></tr>").append("<td>"+"项目成员"+(i+1)+"班级编号</td>")
                    .append("<td>"+result.data.stus[i].cno+"</td>")
                    .appendTo("#application_detail_table");
            }

        }
        for(i=0;i<result.data.teas.length;i++)
        {
            /*                if(result.data.teas[i].tno!=null) {
                                $("<tr></tr>").append("<td>" + "指导老师" + (i + 1) + "编号</td>")
                                    .append("<td>" + result.data.teas[i].tno + "</td>")
                                    .appendTo("#application_detail_table");
                            }*/
            if(result.data.teas[i].tname!=null){
                $("<tr></tr>").append("<td>"+"指导老师"+(i+1)+"姓名</td>")
                    .append("<td>"+result.data.teas[i].tname+"</td>")
                    .appendTo("#application_detail_table");
            }
            /*                if(result.data.teas[i].dname!=null){
                                $("<tr></tr>").append("<td>"+"指导老师"+(i+1)+"部门名称</td>")
                                    .append("<td>"+result.data.teas[i].dname+"</td>")
                                    .appendTo("#application_detail_table");
                            }*/

        }

        if(result.data.note!= null)
        {
            $("<tr></tr>").append("<td>"+"审核不通过原因"+"</td>")
                .append("<td>"+result.data.note+"</td>")
                .appendTo("#application_detail_table");
        }
        /* 这里没找到数据*/
        /*            aStu_sno.innerHTML=result.appplicantStu.sno;
                    aStu_sname.innerHTML=result.appplicantStu.sname;
                    aStu_cno.innerHTML=result.appplicantStu.cno;

                    lStu_sno.innerHTML=data.leaderStu.sno;
                    lStu_sname.innerHTML=data.leaderStu.sname;
                    lStu_cno.innerHTML=data.leaderStu.cno;*/
    }
    form.on('select(myselect)', function (data) {
        type=data.value;
        listTable(type);
        form.val('formTest',{
            chooseBox:type
        })
    })
    form.render();
});