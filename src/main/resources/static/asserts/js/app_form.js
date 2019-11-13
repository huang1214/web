//选择竞赛弹框
function chooseJSBox() {
    layer.open({
        type: 1  //1为页面层
        , title: '选择竞赛'  //弹出层标题
        , area: ['879px', '434px']    //设置弹出层大小[宽，高]
        , offset: 'auto'   //设置弹出层位置 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        , id: 'layerDemo_ctname1' //防止重复弹出，点击这个input只能有一个弹出层
        , content: $("#layer_ctname")   //弹出层内容为layer_ctname块，不能直接是表格id
        , btnAlign: 'c' //按钮居中
        , shade: 0 //不显示遮罩
        , yes: function () {  //确定按钮回调方法， 需要手动关闭弹出层
            layer.closeAll();
        }
    });
}

//选择老师弹框
function chooseTeacherBox() {
    layer.open({
        type: 1  //1为页面层
        , title: '指导老师编号'  //弹出层标题
        , area: ['800px', '400px']   //设置弹出层大小[宽，高]
        , offset: 'auto'   //设置弹出层位置 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        , id: 'layerDemo_teacher2Id' //防止重复弹出，点击这个input只能有一个弹出层
        , content: $("#layer_teacher2Id")   //弹出层内容为layer_ctname块，不能直接是表格id
        , btnAlign: 'c' //按钮居中
        , shade: 0 //不显示遮罩
        , yes: function () {  //确定按钮回调方法， 需要手动关闭弹出层
            layer.closeAll();
        }
    });
}

// 选择学生弹框
function chooseStudentBox(othis) {
    layer.open({
        type: 1  //1为页面层
        , title: '学生信息'  //弹出层标题
        , area: ['800px', '400px']   //设置弹出层大小[宽，高]
        , offset: 'auto'   //设置弹出层位置 //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
        , id: 'layerDemo_studentId' //防止重复弹出，点击这个input只能有一个弹出层
        , content: $("#layer_studentId")   //弹出层内容为layer_ctname块，不能直接是表格id
        , btnAlign: 'c' //按钮居中
        , shade: 0 //不显示遮罩
        , yes: function () {  //确定按钮回调方法， 需要手动关闭弹出层
            layer.closeAll();
        }
    });
}

//删除弹框
function deleteMySelf(othis) {
    //alert(othis.id);
    if (othis.id == "leaderNum") {
    }
    $(othis).remove();

    document.getElementById('ts').value = "";
    $(".tsBox").each(function (index, item) {
        var val1 = item.value;
        var aa = val1.split(":")[0];
        document.getElementById('ts').value += "," + aa;
    });
    document.getElementById('tms').value = "";

    $(".tmsBox").each(function (index, item) {
        // alert(item.value);
        var val1 = item.value;
        var aa = val1.split(":")[0];
        // alert(aa)
        document.getElementById('tms').value += "," + aa;
    });
};
layui.use(['laydate', 'upload', 'table', 'layer', 'form'], function () {
    // alert(1)
    var form = layui.form;
    var laydate = layui.laydate;
    var upload = layui.upload;
    var table = layui.table;
    var layer = layui.layer;
    var $ = layui.jquery;
    var img_url;
    //执行一个laydate实例
    /*日期与时间选择*/
    laydate.render({
        elem: '#awardDate' //指定元素
        , type: 'date'
        , trigger: 'click'//呼出事件改成click
    });
    upload.render({
        elem: '#test2' //绑定元素
        , url: '/add/'//上传接口
        , auto: false
        , multiple: true
        , bindAction: '#application_form_btn'
        , choose: function (obj) {
            //将每次选择的文件追加到文件队列
            files = obj.pushFile();
            //layer.load(); //上传loading
            obj.preview(function (index, file, result) {
                $(".layui-upload-list").append('<img src="' + result + '" id="remove_' + index + '" title="双击删除该图片" style="width:280px;height:auto;margin-right: 20px;">')
                $('#remove_' + index).bind('dblclick', function () {//双击删除指定预上传图片
                    delete files[index];//删除指定图片
                    $(this).remove();
                })
            })

        }
        , done: function (res) {
            //上传完毕回调。
        }
        , error: function () {
            //请求异常回调
        }
    });
    //执行实例
    var uploadInst = upload.render({
        elem: '#test1' //绑定元素
        , url: '/common/upload' //上传接口
        , done: function (res) {
            //上传完毕回调
            img_url = "http://localhost:8083/" + res.data;

            alert("上传完毕");
            // alert(img_url);
            document.getElementById("img_demo").src = img_url;
        }
        , error: function () {
            //请求异常回调
            alert("请求异常");
        }
    });
    table.render({
        elem: '#teacherListTable'
        , toolbar: '#toolbar_teacherAdd'
        , text: '暂无数据'
        , title: '指导老师'
        , loading: false
        , cols: [
            [ //表头
                {field: 'tid', title: '教师编号', width: 103,align:'center'}
                , {field: 'tname', title: '教师姓名', width: 150,align:'center'}
                , {field: 'proprotion', title: '奖金占比(百分制)', width: 200,align:'center'}
                , {fixed: 'right', align: 'center', toolbar: '#toolbar_teacherDel'}
            ]
        ]
    });
    table.render({
        elem: '#stuListTable'
        , toolbar: '#toolbar_stuAdd'
        , text: '暂无数据'
        , title: '参赛学生'
        , loading: false
        , cols: [
            [ //表头
                {field: 'sid', title: '学号', width: 130,align:'center'}
                , {field: 'sname', title: '姓名', width: 150,align:'center'}
                , {field: 'proprotion', title: '奖金占比(百分制)', width: 200,align:'center'}
                , {fixed: 'right', align: 'center', toolbar: '#toolbar_strDel'}
            ]
        ]
    });
    //比赛信息获取
    table.on('toolbar(layer_com_table)', function (obj) {//注：tool 是工具条事件名，layer_com_table 是 table 原始容器的属性 lay-filter="对应的值"
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        switch (obj.event) {
            case 'getCheckData_ctname':
                var data = checkStatus.data;  //获取选中行数据
                document.getElementById("get_tname").value = data[0].ctname;  //将获取的数据中的dno值给input
                document.getElementById("ctId").value = data[0].ctid;
                /*'1:考试' '0:作品' */
                /*                    如果匹配到的成果类型为考试
                                    【参赛作品名称】【作品简介】消失 */
                if (data[0].result_type == 1) {
                    $("#workName,#workBriefIntro,#tms").removeAttr("lay-verify");
                    $("#div_workName,#div_workBriefIntro,#studentArea").addClass("disappear");
                } else {
                    $("#tms").attr("lay-verify","verify_tms");
                    $("#workName").attr("lay-verify","verify_workName");
                    $("#workBriefIntro").attr("lay-verify","verify_workBriefIntro");
                    $("#div_workName,#div_workBriefIntro,#studentArea").removeClass("disappear");
                }
                layer.closeAll(); //疯狂模式，关闭所有层
                break;
        }
        ;
    });
    // 指导老师信息获取
    table.on('toolbar(layer_teacher2Id_table)', function (obj) {//注：tool 是工具条事件名，layer_com_table 是 table 原始容器的属性 lay-filter="对应的值"
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        var num = checkStatus.data.length;
        if (num == 0) {
            layer.msg("未选择指导老师");
            return;
        }
        switch (obj.event) {
            case 'getCheckData_teacher2Id':
                var data = checkStatus.data;  //获取选中行数据
                // var inpT = "<div class='layui-inline deleteTips' onclick='deleteMySelf(this)' ><div class='layui-input-inline'>" +
                //     "<input type='text' class='layui-input layui-btn layui-btn-primary tsBox ' value='" +
                //     data[0].tno + ":" + data[0].tname +
                //     "'></div></div>";
                // $("#teacherArea").append(inpT);
                var tableData = [];
                var tableBak = table.cache['teacherListTable'];
                if (typeof (tableBak) != "undefined") {
                    for (var i = 0; i < tableBak.length; i++) {
                        if (tableBak[i].length != 0)
                            tableData.push(tableBak[i]);
                    }
                }
                if (tableData.length > 0) {
                    tableData.push({tid: data[0].tno, tname: data[0].tname, proprotion: 0});
                } else {
                    tableData.push({tid: data[0].tno, tname: data[0].tname, proprotion: 100});
                }
                table.reload('teacherListTable', {data: tableData});
                layer.closeAll(); //疯狂模式，关闭所有层
                break;
        };
        renewTeacher();
    });
    // 学生信息的获取
    table.on('toolbar(layer_studentId_table)', function (obj) {//注：tool 是工具条事件名，layer_com_table 是 table 原始容器的属性 lay-filter="对应的值"
        var checkStatus = table.checkStatus(obj.config.id); //获取选中行状态
        var num = checkStatus.data.length;
        if (num == 0) {
            layer.msg("未选择参赛学生");
            return;
        }
        switch (obj.event) {
            case 'getCheckData_studentId':
                var data = checkStatus.data;  //获取选中行数据
                var tableData = [];
                tableData.push({sid: data[0].sno, sname: data[0].sname, proprotion: 100});
                var tableBak = table.cache['stuListTable'];
                if (typeof (tableBak) != "undefined") {
                    for (var i = 0; i < tableBak.length; i++) {
                        if (tableBak[i].length != 0)
                            tableData.push(tableBak[i]);
                    }
                }
                table.reload('stuListTable', {data: tableData});
                layer.closeAll(); //疯狂模式，关闭所有层
                break;
            case 'getCheckData_teamId':
                var data = checkStatus.data;  //获取选中行数据
                var tableData = [];
                var tableBak = table.cache['stuListTable'];
                if (typeof (tableBak) != "undefined") {
                    for (var i = 0; i < tableBak.length; i++) {
                        if (tableBak[i].length != 0)
                            tableData.push(tableBak[i]);
                    }
                }
                tableData.push({sid: data[0].sno, sname: data[0].sname, proprotion: 0});
                table.reload('stuListTable', {data: tableData});
                layer.closeAll(); //疯狂模式，关闭所有层
                break;
        };
        renewStu();
    });
    //老师操作
    table.on('tool(teacherListTableFilter)', function (obj) {
        if (obj.event == 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                renewTeacher();
                layer.close(index);
            });
        } else if (obj.event == 'changeP') {
            layer.prompt({
                formType: 0,
                value: obj.data.proprotion,
                title: '请输入占比(介于0-100)'
            }, function (value, index, elem) {
                if (value >= 0 && value <= 100) {
                    obj.update({
                        proprotion: value
                    });
                    renewTeacher();
                    layer.close(index);
                } else {
                    layer.msg("非法输入")
                }
            });
        }
    })
    //学生操作
    table.on('tool(stuListTableFilter)', function (obj) {
        if (obj.event == 'del') {
            layer.confirm('真的删除行么', function (index) {
                obj.del();
                renewStu();
                layer.close(index);
            });
        } else if (obj.event == 'changeP') {
            layer.prompt({
                formType: 0,
                value: obj.data.proprotion,
                title: '请输入占比(介于0-100)'
            }, function (value, index, elem) {
                if (value >= 0 && value <= 100) {
                    obj.update({
                        proprotion: value
                    });
                    renewStu();
                    layer.close(index);
                } else {
                    layer.msg("非法输入")
                }
            });
        }
    })
    $(document).on('click', '#btn_search_student', function () {
        var search_sno = $('#search_sno').val();
        if (search_sno == "") {
            layer.msg('输入不能为空');
        } else {
            table.reload('layer_studentId_table', {
                method: 'POST'
                , page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    sname: search_sno,
                    pageNum: 1,
                    pageSize: 1
                }
                , url: '/queryBySname'    //访问UserController中的方法
            }, 'data');
        }
    });
    $(document).on('click', '#btn_search_ctname', function () {
        var search_ctname = $('#search_ctname').val();
        //执行重载
        table.reload('layer_com_table', {   //layer_com_table为表格的id名
            method: 'POST'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                ctname: search_ctname
            }
            , url: '/competition/keyword_search_ctname'    //访问controller中的方法
        }, 'data');
    });
    $(document).on('click', '#btn_search_teacher2Id', function () {
        var search_tno = $('#search_tno2').val();
        var search_tname = $('#search_tname2').val();
        var search_dcollege = $('#search_dcollege2').val();
        var search_dname = $('#search_dname2').val();
        var search_ttitle = $('#search_ttitle2').val();
        //执行重载
        table.reload('layer_teacher2Id_table', {   //layer_teacher2Id_table为表格的id名
            method: 'POST'
            , page: {
                curr: 1 //重新从第 1 页开始
            }
            , where: {
                tno: search_tno,//注意用，隔开
                tname: search_tname,
                dcollege: search_dcollege,
                dname: search_dname,
                ttitle: search_ttitle
            }
            , url: '/teacher1/keyword_search'    //访问UserController中的方法
        }, 'data');
    })
    //比赛
    $(document).on('click', '#get_tname', function () {
        chooseJSBox();
        // 比赛名称弹出层>>表格
        table.render({
            elem: '#layer_com_table'
            , height: 312
            , url: '/getAllCom' //数据接口
            , method: "get"
            , page: true //开启分页
            , toolbar: '#toolbar_ctname'
            , cols: [
                [ //表头
                    {type: 'radio'}
                    , {field: 'ctid', title: '竞赛编号', width: 103, sort: true}
                    , {field: 'ctname', title: '学科和科技竞赛名称', width: 350}
                    , {field: 'host_unit', title: '主办单位', width: 350}
                ]
            ]
        });

    });
    // 教师
    $(document).on('click', '#addteacher', function () {
        chooseTeacherBox();
        // 指导老师弹出层>>表格
        table.render({
            elem: '#layer_teacher2Id_table'
            , height: 312
            , url: '/getAllTeacher' //数据接口
            , method: "get"
            , page: true //开启分页
            , toolbar: '#toolbar_teacher2Id'
            , cols: [
                [ //表头
                    {type: 'radio'}
                    , {field: 'tno', title: '教师编号', width: 103, sort: true}
                    , {field: 'tname', title: '教师名称', width: 120}
                    , {field: 'tsex', title: '教师性别', width: 120}
                    , {field: 'dcollege', title: '所属学院', width: 120}
                    , {field: 'dname', title: '所属部门', width: 120}
                    , {field: 'ttitle', title: '教师职称', width: 120}
                ]
            ]
        });
    });
    //学生
    $(document).on('click', '#addStudent', function () {
        chooseStudentBox(null);
        //学生弹出层
        table.render({
            elem: '#layer_studentId_table'
            , height: 312
            , method: "get"   // TODO 应该默认就存在表单
            , page: true //开启分页
            , toolbar: '#toolbar_studentId'
            , cols: [
                [ //表头
                    //TODO 也不是所有信息都要显示出来，这里到时候改改
                    {type: 'radio'}
                    , {field: 'sno', title: '学号', sort: true}
                    , {field: 'sname', title: '姓名'}
                    , {field: 'ssex', title: '性别'}
                    , {field: 'sbirthday', title: '出生年月',}  // 待转换数据
                    , {field: 'sdomitory', title: '寝室'}
                    , {field: 'cno', title: '班级ID'}
                    , {field: 'stel', title: '电话'}
                    , {field: 'state', title: '在校状态'}
                ]
            ]
        });
    });
    //提示
    {
        var tip_index = 0;
        $(document).on('mouseenter', '#addteacher', function () {
            tip_index = layer.tips('点击添加教师', '#addteacher', {time: 0});
        }).on('mouseleave', '#addteacher', function () {
            layer.close(tip_index);
        });
        $(document).on('mouseenter', '#addStudent', function () {
            tip_index = layer.tips('点击添加队员', '#addStudent', {time: 0});
        }).on('mouseleave', '#addStudent', function () {
            layer.close(tip_index);
        });
        $(document).on('mouseenter', '.deleteTips', function () {
            // tip_index = layer.tips('点击删除这条记录', '.deleteTips', {time: 0});
            // layer.msg('点击按钮可以删除',{time: 1800});
        });
    }
    //自定义验证规则
    form.verify({
        verify_ctname: function (value) {
            if (value == null || value == "") {
                return '比赛名称不能为空';
            }
        }
        , verify_workName: function (value) {
            if (value == null || value == "") {
                return '作品名称不能为空';
            }
        }
        , verify_awardDate: function (value) {
            if (value == null || value == "") {
                return '获奖时间不能为空';
            }
        }
        , verify_unit: function (value) {
            if (value == null || value == "") {
                return '第一单位不能为空！';
            }
            if (value != '南昌大学软件学院') {
                return '第一单位必须为南昌大学软件学院！';
            }
        }
        , verify_level_type: function (value) {
            if (value == null || value == "") {
                return '请选择您的获奖类型(省级/国家级)';
            }
        }
        , verify_prize_type: function (value) {
            if (value == null || value == "") {
                return '请选择您的获奖类型(特/一等/二等/三等/优秀)';
            }
        }
        , verify_applicantId: function (value) {
            if (value == null || value == "") {
                return '申请人的ID不能为空';
            }
        }
        , verify_BankCard: function (value) {
            if (value == null || value == "") {
                return '申请人的银行卡号不能为空';
            }
            if (value.length > 20) {
                return '请检查银行卡号是否有误';
            }
        }
        , verify_tms: function (value) {
            if (value == null || value == "") {
                layer.tips('请点击该项', '#addStudent');
                return '必须添加成员';
            }
        }
        , verify_workBriefIntro: function (value) {
            if (value == null || value == "") {
                return '作品简介不能为空';
            }
        }
    });
    form.render();//菜单渲染 把内容加载进去
    form.on('submit(application_form_submit)', function (data) {
        // console.log(data)
        $("#application_form_btn").removeClass("layui-btn-normal");
        $("#application_form_btn").addClass("layui-btn-disabled");
        var params = {
            "ctId": data.field.ctId,
            "workName": data.field.workName,
            "awardDate": data.field.awardDate,
            "unit": data.field.unit,
            "level_type": data.field.level_type,
            "prize_type": data.field.prize_type,
            "applicantId": data.field.applicantId,
            "applicantBankCard": data.field.applicantBankCard,
            "leader": data.field.leader,
            "workBriefIntro": data.field.workBriefIntro,
            "tms": data.field.tms,
            "ts": data.field.ts,
        };
        // alert(params);
        $.ajax({
            type: "POST",
            //contentType: "application/json",//post请求的信息格式
            url: "/app/add",
            data: params,
            dataType: 'json',
            success: function (result) {
                if (result.code == 0) {
                    layer.msg(result.message);
                    document.getElementById("form_application").reset();
                    $("#application_form_btn").removeClass("layui-btn-disabled");
                    $("#application_form_btn").addClass("layui-btn-normal");
                } else {
                    layer.msg(result.message);
                }
            },
            error: function () {
                alert("异常！");
            }
        });
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    window.renewTeacher = function () {
        var newParams = "";
        var tableBak = table.cache['teacherListTable'];
        if (typeof (tableBak) != "undefined") {
            for (var i = 0; i < tableBak.length; i++) {
                if (typeof(tableBak[i].tid) != "undefined")
                    newParams += tableBak[i].tid + ":" + tableBak[i].proprotion + ",";
            }
        }
        document.getElementById('ts').value = newParams;
        // alert(document.getElementById('ts').value);
    }
    window.renewStu = function () {
        var newParams = "";
        var tableBak = table.cache['stuListTable'];
        if (typeof (tableBak) != "undefined") {
            for (var i = 0; i < tableBak.length; i++) {
                if (typeof(tableBak[i].sid) != "undefined")
                    newParams += tableBak[i].sid + ":" + tableBak[i].proprotion + ",";
            }
        }
        document.getElementById('tms').value = newParams;
        if (typeof (tableBak) != "undefined") {
            for (var i = 0; i < tableBak.length; i++) {
                if (typeof(tableBak[i].sid) != "undefined"){
                    document.getElementById('leader').value=tableBak[i].sid;
                    break;
                }
            }
        }
    }
});
