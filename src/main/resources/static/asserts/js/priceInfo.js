var type=1,year=2019,years;
layui.use(['table','layer','form','jquery'], function(){
    var table = layui.table;
    var layer = layui.layer;
    var form = layui.form;
    var $ = layui.jquery;
    //显示表单状态基本信息
    listTable=function(){
        console.log(type,year);
        table.render({
            elem: '#application_state'
            ,height: 380
            ,url: '/app/list_prize'//数据接口
            ,toolbar:'#toolbar_topOp'
            ,page: true //开启分页
            ,where:{'page':'1','limit':'1000'}
            ,loading: true
            ,cols: [
                [
                    {field:'index',title: '序号', width:170,templet: '<div>{{d.index}}</div>',fixed: 'left'}
                    ,{field:'teachers',title: '指导老师', minWidth:200,templet: '<div>{{d.teachers}}</div>'}
                    ,{field:'students',title: '参赛学生', minWidth:200,templet: '<div>{{d.students}}</div>'}
                    ,{field:'workName',title: '作品名称', minWidth:200,templet: '<div>{{d.workName}}</div>'}
                    ,{field:'prizeName',title: '获奖名称', minWidth:200,templet: '<div>{{d.prizeName}}</div>'}
                    ,{field:'date',title: '获奖时间', minWidth:200,templet: '<div>{{(d.date)}}</div>'}
                ]
            ]
            ,done:function (res, curr, count) {
                console.log(res)
                var tText='';
                for(var a=0;a<years.length;a++){
                    tText+= "<option value='"+years[a]+"'>"+years[a]+"</option>"
                }
                $("#chooseYearBtn").empty();
                $("#chooseYearBtn").append(tText);
                form.val('formTest',{
                    chooseYearBox:year
                });
                form.render();
            }
        });
    }
    //表格：“表单状态基本信息” 实例的监听工具条
    table.on('tool(application_operate)', function(obj){ //注：tool 是工具条事件名，application_state 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        // console.log(obj)
        if(layEvent == 'download'){
            downloadB();
        }else if(layEvent=='clean'){
            cleanB();
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
    window.downloadB=function(){
        layer.load(1, {time: 2*1000});
        $.ajax(
            {
                url: '/app/list_file?op=download&type=' + type+'&year='+year,
                type: 'POST',
                success: function (result) {
                    console.log(result)
                    layer.closeAll();
                    if(result.code==0){
                        // console.log(result)
                        var elt = document.createElement('a');
                        elt.setAttribute('href', result.data[0].url);
                        elt.setAttribute('download',result.data[0].fileName);
                        elt.style.display = 'none';
                        document.body.appendChild(elt);
                        elt.click();
                        document.body.removeChild(elt);
                    }else{
                        layer.alert(result.msg);
                    }
                },
                error: function () {
                    layer.msg("服务器出了点问题，请稍后重试");
                }
            }
        );
    }
    window.cleanB=function(){
        layer.load(1, {time: 5*1000});
        $.ajax(
            {
                url: '/app/list_file?op=delete&type=' + type+'&year='+year,
                type: 'POST',
                success: function (result) {
                    if(result.code==0){
                        layer.closeAll();
                        layer.msg("该文件夹已清空！")
                    }else{
                        layer.msg(result.msg);
                    }
                },
                error: function () {
                    layer.msg("服务器出了点问题，请稍后重试");
                }
            }
        );
    }
    form.on('select(myselect)', function (data) {
        type=data.value;
        listTable();
        form.val('formTest',{
            chooseBox:type
        })
    })
    form.on('select(myselectyear)', function (data) {
        year=data.value;
        listTable();
        form.val('formTest',{
            chooseYearBox:year
        })
    })
    window.setVal=function(d){
        year=d.data[0];
        years=d.data;
        listTable();
        // var tText='';
        // for(var a=0;a<d.data.length;a++){
        //     tText+= "<option value='"+d.data[a]+"'>"+d.data[a]+"</option>"
        // }
        // $("#chooseYearBtn").empty();
        // $("#chooseYearBtn").append(tText);
        form.render();
    };
    var index = layer.load(2, {time: 10*1000});
    {
        $.ajax(
            {
                url: '/app/list_file_dir',
                type: 'POST',
                success: function (result) {
                    if(result.code==0){
                        layer.closeAll();
                        setVal(result);
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
});