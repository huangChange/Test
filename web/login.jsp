<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>登录页面</title>
  <link rel="stylesheet" href="./css/login.css">

  <style>
    .error {
      color: #f00;
      position: relative;
      top: 20px;
    }
  </style>
</head>
<body>

<div class="top"></div>
<div class="container">
  <div class="container-form">
    <form action="${pageContext.request.contextPath}/loginServlet" method="post">
      <p class="user">
        <span><img src="./images/username.png" alt=""></span>
        <input type="text" id="username" name="username" placeholder="输入用户名"  autocomplete="off" >
      </p>
      <p class="pass">
        <span><img src="./images/password.png" alt=""></span>
        <input id="password" type="password" name="password" placeholder="输入密码">
      </p>
      <div align="center" class="error">${pageContext.request.getAttribute("error_msg")}</div>
    </form>
  </div>
  <div class="bottom">
    <div class="bottom-left">
      <span class="forget">忘记密码?</span>
    </div>
    <div class="bottom-right">
      <div class="div-regist">
        <span class="btn_regist">注册</span>
      </div>
      <div class="login">
        <span>登录</span>
      </div>
    </div>
  </div>
</div>
</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script>

  $('.login').click(function() {
    if($('#username').val() === "") {
      $('.error').css('color', '#f00');
      $('.error').html('用户名不能为空!');
      return;
    }
    if($('#password').val() === "") {
      $('.error').css('color', '#f00');
      $('.error').html('密码不能为空!');
      return;
    }
    $('form').submit();
  });

  $('#username').focus(function() {
    $('.user').css("border", "1px solid #00bcd4");
  }).blur(function() {
    $('.user').css("border", "1px solid gray");
  });

  $('#password').focus(function() {
    $('.pass').css("border", "1px solid #00bcd4");
  }).blur(function() {
    $('.pass').css("border", "1px solid gray");
  });

  $('.btn_regist').click(function() {
    location.href = "${pageContext.request.contextPath}/regist.jsp";
  });

</script>
</html>
