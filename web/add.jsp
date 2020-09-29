<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生信息</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        .error {
            height: 35px;
            line-height: 35px;
            text-align: left;
            color: #f00;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <h3 align="center">添加学生信息</h3>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/addStudentServlet" method="post" id="addStudent_form">
        <div class="form-group">
            <label for="num" class="col-sm-4 control-label">学号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="num" autocomplete="off" name="num" placeholder="学号">
            </div>
            <div class="error col-sm-3">
                <span class="error_msg text-center"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" placeholder="姓名">
            </div>
        </div>
        <div class="form-group">
            <label for="class_num" class="col-sm-4 control-label">班级</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="class_num" name="class_num" placeholder="班级" autocomplete="off">
            </div>
        </div>
        <div class="form-group">
            <label for="department" class="col-sm-4 control-label">院系</label>
            <div class="col-sm-4">
                <select class="form-control" name="department" id="department">
                    <option value="0">请选择院系</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="province" class="col-sm-4 control-label">籍贯</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="province" name="province" placeholder="籍贯" autocomplete="off">
            </div>
        </div>
        <div class="form-group">
            <label for="bir" class="col-sm-4 control-label">出生日期</label>
            <div class="col-sm-4">
                <input type="date"  class="form-control" id="bir" name="bir" placeholder="日期" autocomplete="off">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">性别</label>
            <div class="col-lg-4">
                <label class="radio-inline">
                    <input type="radio" name="sex" checked id="man" value="男"> 男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="woman" value="女"> 女
                </label>
            </div>
        </div>
        <div>
            <div class="col-sm-offset-5 col-sm-12">
                <button type="button" id="add" class="btn btn-primary">添加</button>
                <a href="${pageContext.request.contextPath}/findStudentsByPageServlet" class="btn btn-primary">返回</a>
            </div>
        </div>
    </form>
</div>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
    $.get('${pageContext.request.contextPath}/findAllDepartmentServlet', {}, function(data) {
        // 判断data是否为空
        if(data != "" && data.length != 0) {
            $(data).each(function() {
                // 遍历data
                var option = "<option value='" + this.name + "'>" + this.name + "</option>";
                // 给select添加option
                $('#department').append(option);
            });
        }
    });
    $('#num').blur(function() {
        let num = $('#num').val();
        $.get("${pageContext.request.contextPath}/findStudentByNumServlet", {num: num}, function(data){
            if(data != null) {
                $('.error_msg').html('该学号已经存在');
            }else {
                $('.error_msg').html('');
            }
        });
    });

    $('#add').click(function() {
        if($('#num').val() === '') {
            $('.error_msg').html('学号不能为空!');
            return;
        }
        if($('.error_msg').html() != '') {
            return;
        }
        if($('#name').val() != '' && $('#class_num').val() != '' && $('#province').val() != '' && $('#department').val() != '0') {
            $('#addStudent_form').submit();
        }else {
            alert('请检查是否填写完整!');
        }
    });
</script>
</html>
