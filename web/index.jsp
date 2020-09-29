<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body>
    <div align="center">
        <h3>欢迎您,${pageContext.session.getAttribute("name")}</h3>
    </div>
    <div align="center">
        <h3><a href="${pageContext.request.contextPath}/findStudentsByPageServlet">查看所有学生信息</a></h3>
    </div>
</body>
</html>
