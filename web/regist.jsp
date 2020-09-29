<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>教师注册页面</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background-image: url("images/test.jpeg");
            text-align: center;
        }
        .container {
            margin-top: 10%;
        }
        .error {
            height: 35px;
            line-height: 35px;
            text-align: left;
        }
        .code>img {
            width: 80px;
            height: 40px;
        }
    </style>
</head>
<body>
<div class="container">
    <h3>教师注册页面</h3>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/registServlet" method="post" id="regist_teacher_form">
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username" autocomplete="off" name="username" placeholder="用户名">
            </div>
            <div class="error col-sm-4">
                <span class="error_msg"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="password1" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password1" name="password" placeholder="密码">
            </div>
            <div class="error col-sm-4">
                <span id="pass_error"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="password2" class="col-sm-4 control-label">再次输入密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password2" placeholder="密码">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">真实姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" placeholder="真实姓名" autocomplete="off">
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
            <label for="email" class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="email" name="email" placeholder="邮箱" autocomplete="off">
            </div>
        </div>
        <div class="form-group">
            <label for="tel" class="col-sm-4 control-label">电话</label>
            <div class="col-sm-4">
                <input type="number" maxlength="11" class="form-control" id="tel" name="tel" placeholder="电话" autocomplete="off">
            </div>
        </div>
        <div class="form-group">
            <label for="code" class="col-sm-4 control-label">验证码</label>
            <div class="col-sm-3 code">
                <input type="text" class="form-control" id="code" name="code" placeholder="输入验证码" autocomplete="off">
            </div>
            <div class="col-sm-1 code">
                <img id="img" src="${pageContext.request.contextPath}/checkCodeServlet"  alt="">
            </div>
            <div class="error col-sm-4 ">
                <span class="code_error" style="color: #f00">${pageContext.request.getAttribute("code_error")}</span>
            </div>
        </div>
        <div>
            <div class="col-sm-offset-2 col-sm-8">
                <button type="submit" class="btn btn-primary">Regist</button>
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

    function change(id, color, msg) {
        $(id).css("color", color);
        $(id).html(msg);
    }

    $('#username').blur(function() {
        var username = $('#username').val();
        if(username === "") {
            change('.error_msg', '#f00', '用户名不能为空!');
            return false;
        }
        $.get('${pageContext.request.contextPath}/findTeacherServlet', {username:username}, function(data) {
            if(data.userExit) {
                console.log(data.userExit);
                change('.error_msg', '#f00', data.msg);
            }else {
                change('.error_msg', '#0f0', data.msg);
            }
        }, "json");
    });

    $('#password2').blur(function() {
       var pass1 = $('#password1').val();
       var pass2 = $('#password2').val();

       if(pass1 == "" || pass2 == "") {
           change('#pass_error', '#f00', '密码不能为空!');
           return;
       }

       if(pass1 != pass2) {
           change('#pass_error', '#f00', '两次密码不一样!');
           return;
       }
       change('#pass_error', '#0f0', '密码可以使用!');
    });

    $('form').on('submit', function() {

        console.log($('.error_msg').val());
        console.log($('#pass_error').val());
        if($('.error_msg').html() === "恭喜您,该用户可以使用!"
            && $('#pass_error').html() === '密码可以使用!'
            && $('#name').val() != ""
            && $('#tel').val() != "") {
            return true;
        }else {
            alert('请检查是否有未填写的内容')
            return false;
        }
    });

    document.getElementById('img').addEventListener('click', function() {
        this.src = "${pageContext.request.contextPath}/checkCodeServlet?time" + new Date().getTime();
    })

</script>
</html>
