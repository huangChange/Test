<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改学生信息</title>

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
    <h3 align="center">修改学生信息</h3>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/updateStudentServlet" method="post" id="updateStudent_form">
        <div class="form-group">
            <label for="num" class="col-sm-4 control-label">学号</label>
            <div class="col-sm-4">
                <input type="text" value="${stu.num}" readonly class="form-control" id="num" autocomplete="off" name="num" placeholder="学号">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">姓名</label>
            <div class="col-sm-4">
                <input type="text" value="${stu.name}" readonly class="form-control" id="name" name="name" placeholder="姓名">
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
            <div class="col-lg-4" id="sex">

            </div>
        </div>
        <div>
            <div class="col-sm-offset-5 col-sm-12">
                <button type="button" id="update" class="btn btn-primary">提交修改</button>
                <a href="${pageContext.request.contextPath}/findStudentsByPageServlet" class="btn btn-primary">返回</a>
            </div>
        </div>
    </form>
</div>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/getParameter.js"></script>
<script>
    var num = getParameter('num');
    $.get('${pageContext.request.contextPath}/findAllDepartmentServlet', {}, function(data) {
        // 判断data是否为空
        if(data != "" && data.length != 0) {
            $.get('${pageContext.request.contextPath}/findStudentByNumServlet', {num: num}, function(stu) {
                for (let i = 0; i < data.length; i++) {
                    // 遍历data
                    if(stu.department == data[i].name) {
                        var option = "<option selected value='" + data[i].name + "'>" + data[i].name + "</option>";
                    }else {
                        var option = "<option value='" + data[i].name + "'>" + data[i].name + "</option>";
                    }
                    // 给select添加option
                    $('#department').append(option);
                }
                $('#num').val(stu.num);
                $('#name').val(stu.name);
                $('#class_num').val(stu.class_num);
                $('#province').val(stu.province);
                $('#bir').val(stu.bir);

                var sex = '';
                if(stu.sex == '男') {
                    sex = '<label class="radio-inline">\n' +
                        '                        <input type="radio" name="sex" checked value="男"> 男\n' +
                        '                    </label>\n' +
                        '                    <label class="radio-inline">\n' +
                        '                        <input type="radio" name="sex" value="女"> 女\n' +
                        '                    </label>';
                }else {
                    sex = ' <label class="radio-inline">\n' +
                        '                        <input type="radio" name="sex" value="男"> 男\n' +
                        '                    </label>\n' +
                        '                    <label class="radio-inline">\n' +
                        '                        <input type="radio"  checked name="sex" value="女"> 女\n' +
                        '                    </label>';
                }
                $('#sex').append(sex);
            });
        }
    });

    $('#update').click(function() {
        console.log($('#bir').val());
        if($('#class_num').val() != '' && $('#province').val() != '' && $('#department').val() != '0' && $('#bir').val() != '') {
            $('#updateStudent_form').submit();
        }else {
            alert('请检查是否填写完整!');
        }

    });
</script>
</html>
