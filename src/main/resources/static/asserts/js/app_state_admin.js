var type=1;
layui.use(['table','layer','form','jquery'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    //显示表单状态基本信息
    table.render({
        elem: '#application_state'
        ,height: 380
        ,url: '/app/list_test'//数据接口
        ,toolbar:'#toolbar_topOp'
        ,page: true //开启分页
        ,where:{'type':1}
        ,loading: true
        ,cols: [
            [
                {field: 'appid', title: '申请编号', width:170, sort: true,fixed: 'left'}
                ,{field: 'ctname', title: '比赛名称', minWidth:200,templet: '<div>{{d.competion.ctname}}</div>'}
                ,{field: 'applicantId', title: '申请人', width:100,templet: '<div>{{d.appplicantStu.sname}}</div>'}
                ,{field: 'leader', title: '项目组长', width:100,templet: '<div>{{d.leaderStu.sname}}</div>'}
                ,{field: 'teacherPrice', title: '老师奖金', width: 90,templet: function(d){
                    return d.teacherPrice/100;}}
                ,{field: 'awardDate', title: '申请时间', width: 116, sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#bar'}
            ]
        ]
    });
    listTable=function(listType){
        table.render({
            elem: '#application_state'
            ,height: 380
            ,url: '/app/list_test'//数据接口
            ,toolbar:'#toolbar_topOp'
            ,page: true //开启分页
            ,where:{'type':listType}
            ,loading: true
            ,cols: [
                [   {field: 'appid', title: '申请编号', width:170, sort: true,fixed: 'left'}
                    ,{field: 'ctname', title: '比赛名称', minWidth:200,templet: '<div>{{d.competion.ctname}}</div>'}
                    ,{field: 'applicantId', title: '申请人', width:100,templet: '<div>{{d.appplicantStu.sname}}</div>'}
                    ,{field: 'leader', title: '项目组长', width:100,templet: '<div>{{d.leaderStu.sname}}</div>'}
                    ,{field: 'teacherPrice', title: '老师奖金', width: 90,templet: function(d){
                        return d.teacherPrice/100;}}
                    ,{field: 'awardDate', title: '申请时间', width: 116, sort: true}
                    ,{fixed: 'right', title:'操作', toolbar: '#bar'}
                ]
            ]
        });
    }
    //表格：“表单状态基本信息” 实例的监听工具条
    table.on('tool(application_operate)', function(obj){ //注：tool 是工具条事件名，application_state 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        console.log(obj)
        if(layEvent == 'detail'){ //查看详情
            console.log(data.appid);
            $.ajax(
                {
                    url: '/app/detail?appid=' + data.appid,
                    type: 'GET',   // 请求方式
                    success: function (result) {
                        setTableValue(result);//动态向表格赋值
                        layer.open({
                            type: 1
                            ,title: '查看详细信息'  //弹出层标题
                            ,area: ['800px', '400px']   //设置弹出层大小[宽，高]
                            ,offset: 'auto'
                            ,content:$("#layer_application_detail")
                            ,shade: 0.5 //不显示遮罩
                            ,btn:['确认']
                            ,btnAlign:'c'
                            ,anim: 1
                            ,yes: function () {  //确定按钮回调方法， 需要手动关闭弹出层
                                layer.closeAll();
                            }
                        });
                    },
                    error: function () {
                    }
                }
            );
        }else if(layEvent == 'delete'){
            deleteApp(data.appid);
        }else if(layEvent == 'refuse'){
            refuse(data.appid);
        }else if(layEvent == 'pass'){
            pass(data.appid);
        }else if(layEvent == 'refresh'){
            refreshF(data.appid);
        }
    });
    window.setTableValue=function(result){
        var rText='';
        rText += "<tr><td>申请编号</td><td>"+result.data.appid+"</td>";
        rText += "<tr><td>比赛名称</td><td>"+result.data.com.ctname+"</td>";
        rText += "<tr><td>举办单位</td><td>"+result.data.com.host_unit+"</td>";

        var com_type='',result_type='',level_type='',prize_type='';
        if(result.data.awar.com_type==0)
        {
            com_type="一般竞赛项目";
        }
        else if(result.data.awar.com_type==1)
        {
            com_type="重点竞赛项目";
        }

        if(result.data.awar.result_type==0)
        {
            result_type="作品";
        }
        else if(result.data.awar.result_type==1)
        {
            result_type="考试";
        }

        if(result.data.awar.level_type==0)
        {
            level_type="省级";
        }
        else if(result.data.awar.level_type==1)
        {
            level_type="国家级";
        }
        else if(result.data.awar.level_type==2)
        {
            level_type="校级";
        }
        else if(result.data.awar.level_type==3)
        {
            level_type="院级";
        }

        if(result.data.awar.prize_type==1)
        {
            prize_typez="特等奖";
        }
        else if(result.data.awar.prize_type==2)
        {
            prize_type="一等奖";
        }
        else if(result.data.awar.prize_type==3)
        {
            prize_type="二等奖";
        }
        else if(result.data.awar.prize_type==4)
        {
            prize_type="三等奖";
        }
        else if(result.data.awar.prize_type==5)
        {
            prize_type="优秀奖";
        }
        rText += "<tr><td>竞赛分类</td><td>"+com_type+"</td>";
        rText += "<tr><td>成果类型</td><td>"+result_type+"</td>";
        rText += "<tr><td>获奖级别</td><td>"+level_type+"</td>";
        rText += "<tr><td>所获奖项</td><td>"+prize_type+"</td>";
        if(result.data.workName!=null&&result.data.workName!=''){
            rText += "<tr><td>作品名称</td><td>"+result.data.workName+"</td>";
        }
        if(result.data.workBriefIntro!=null&&result.data.workBriefIntro!=''){
            rText += "<tr><td>作品简介</td><td>"+result.data.workBriefIntro+"</td>";
        }
        var students='';
        var teachers='';
        for(i=0;i<result.data.stus.length;i++)
        {

            if(result.data.stus[i].sno!=null&&result.data.stus[i].sno!=''){
                if(students.length!=0)
                    students+="<br>";
                students +=result.data.stus[i].sname+"("+result.data.stus[i].sno+") 占比"+result.data.stus[i].propertion+"% 预计奖金:"+(result.data.stus[i].money)/100+"元";
            }
        }
        if(students.length!=0)
            rText += "<tr><td>参赛学生</td><td>"+students+"</td>";
        for(i=0;i<result.data.teas.length;i++)
        {
            if(result.data.teas[i].tno!=null&&result.data.teas[i].tno!='') {
                if (teachers.length != 0)
                    teachers += "<br>"
                teachers += result.data.teas[i].tname + " 占比" + result.data.teas[i].propertion + "% 预计奖金:" + (result.data.teas[i].money) / 100 + "元";
            }
        }
        if(teachers.length!=0)
            rText += "<tr><td>指导老师</td><td>"+teachers+"</td>";

        var cert = result.data.com.ctname + "-" + level_type + "-" + prize_type + "-获奖证书";
        var doc = result.data.com.ctname + "-" + level_type + "-" + prize_type + "-参赛报告";
        var pac = result.data.com.ctname + "-" + level_type + "-" + prize_type + "-花絮";
        var certUrl = "/" + (result.data.appid).substring(0, 4) + "/cert/" + result.data.certificateImg;
        var docUrl = "/" + (result.data.appid).substring(0, 4) + "/doc/" + result.data.getawardImg;
        var packageUrl = "/" + (result.data.appid).substring(0, 4) + "/package/" + result.data.highLight;
        rText += "<tr><td>文件</td><td>";
        if (result.data.certificateImg != null && result.data.certificateImg != '')
            rText += "<a  href='" + certUrl + "' download='" + result.data.appid + "-" + cert + "'>" + cert + "</a><br>";
        if (result.data.getawardImg != null && result.data.getawardImg != '')
            rText += "<a  href='" + docUrl + "' download='" + result.data.appid + "-" + doc + "'>" + doc + "</a><br>";
        if (result.data.highLight != null && result.data.highLight != '')
            rText += "<a  href='" + packageUrl + "' download='" + result.data.appid + "-" + pac + "'>" + pac + "</a>";
        rText += "</td><tr></tr>";


        if(result.data.status==0){
            rText += "<tr><td>当前状态</td><td>待指导老师确认</td>";
        }else if(result.data.status==1){
            rText += "<tr><td>当前状态</td><td>待审核</td>";
            rText +="<tr><td>操作</td><td><button type='button' class='layui-btn layui-btn-normal' onclick="+"'pass("+'"'+result.data.appid+'"'+")'>通过</button><button type='button' class='layui-btn layui-btn-danger' onclick="+"'refuse("+'"'+result.data.appid+'"'+")'>拒绝</button></td></tr>";
        }else if(result.data.status==2){
            rText += "<tr><td>当前状态</td><td>未通过</td>";
            if(result.data.note!= null&&result.data.note!= '')
            {
                rText += "<tr><td>审核备注</td><td>"+result.data.note+"</td>";
            }
            rText +="<tr><td>操作</td><td><button type='button' class='layui-btn layui-btn-danger' onclick="+"'deleteApp("+'"'+result.data.appid+'"'+")'>删除</button></td></tr>";
        }else if(result.data.status==3){
            rText += "<tr><td>当前状态</td><td>已通过</td>";
        }else if(result.data.status==-1){
            rText += "<tr><td>当前状态</td><td>已删除</td>";
            rText +="<tr><td>操作</td><td><button type='button' class='layui-btn layui-btn-primary' onclick="+"'refreshF("+'"'+result.data.appid+'"'+")'>恢复</button></td></tr>";
        }

        $("#application_detail_table").children("tbody").empty();
        $("#application_detail_table").children("tbody").append(rText);
    }
    window.pass=function(obj){
        // layer.alert("pass"+obj);
        $.ajax(
            {
                url: '/app/change?appid=' + obj+'&operation=pass',
                type: 'POST',
                success: function (result) {
                    if(result.code==0){
                        layer.msg("已通过",{time:1000},function () {
                            listTable(type);
                            layer.closeAll();
                        })
                    }else{
                        layer.msg(result.message);
                    }
                },
                error: function () {
                    layer.msg("服务器出了点问题，请稍后重试");
                }
            }
        );
    }
    window.refuse=function(obj){
        layer.prompt({
            formType: 2,
            value: '无',
            title: '审核理由',
            area: ['250px', '120px'],
            maxlength:50
        }, function(value, index, elem){
            var url='/app/change?appid=' + obj+'&operation=refuse'+'&note='+value;
            $.ajax(
                {
                    url: url,
                    type: 'POST',
                    success: function (result) {
                        if(result.code==0){
                            layer.msg("已拒绝",{time:1000},function () {
                                listTable(type);
                                layer.closeAll();
                            })
                        }else{
                            layer.msg(result.message);
                        }
                    },
                    error: function () {
                        layer.msg("服务器出了点问题，请稍后重试");
                    }
                }
            );
        });

    }
    window.deleteApp=function(obj){
        layer.confirm('确认删除?', {icon: 2, title:'提示'}, function(index){
            $.ajax(
                {
                    url: '/app/delete?appid=' + obj,     // 请求地址,访问controller中的ccc方法, 就是你的控制器, 如 test.com/home/index/index
                    type: 'POST',   // 请求方式
                    success: function (result) {
                        if(result.code == 0){
                            layer.msg("已删除！",{time:1000},function () {
                                listTable(type);
                                layer.closeAll();
                            })
                        }else {
                            layer.msg(result.message);
                        }
                    },
                    error: function () {
                        // console.log('Send Request Fail..'); // 请求失败时的回调函数
                    }
                }
            );
        });
    }
    window.refreshF=function(obj){
        layer.confirm('确认恢复?', {icon: 1, title:'提示'}, function(index){
            $.ajax(
                {
                    url: '/app/change?appid=' + obj+'&operation=refresh',     // 请求地址,访问controller中的ccc方法, 就是你的控制器, 如 test.com/home/index/index
                    type: 'POST',   // 请求方式
                    success: function (result) {
                        if(result.code == 0){
                            layer.msg("已恢复！",{time:1000},function () {
                                listTable(type);
                                layer.closeAll();
                            })
                        }else {
                            layer.msg(result.message);
                        }
                    },
                    error: function () {
                        // console.log('Send Request Fail..'); // 请求失败时的回调函数
                    }
                }
            );
        });
    }
    window.delBtn=function(obj){
        // layer.msg(obj)
        layer.confirm('确认删除?', {icon: 2, title:'提示'}, function(index){
            $.ajax(
                {
                    url: '/app/delete?appid=' + obj,     // 请求地址,访问controller中的ccc方法, 就是你的控制器, 如 test.com/home/index/index
                    type: 'POST',   // 请求方式
                    success: function (result) {
                        if(result.code == 0){
                            layer.msg("已删除！",{time:1000},function () {
                                listTable(type);
                                layer.closeAll();
                            })
                        }else {
                            layer.msg(result.message);
                        }
                    },
                    error: function () {
                        // console.log('Send Request Fail..'); // 请求失败时的回调函数
                    }
                }
            );
        });

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