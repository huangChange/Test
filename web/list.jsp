<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>学生信息</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <style>

        th, td {
            text-align: center;
            vertical-align: center;
        }

    </style>
</head>
<body>

    <div class="container">
        <h2 align="center">学生信息管理系统</h2>

        <div style="float: left;" class="container col-sm-12">
            <form class="form-inline" action="${pageContext.request.contextPath}/findStudentsByPageServlet" method="post">
                <div class="form-group">
                    <label for="exampleInputName1">姓名</label>
                    <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="exampleInputName1" >
                </div>
                <div class="form-group">
                    <label for="exampleInputName2">学号</label>
                    <input type="text" name="num" value="${condition.num[0]}" class="form-control" id="exampleInputName2"  >
                </div>
                <div class="form-group">
                    <label for="exampleInputName3">班级</label>
                    <input type="text" name="class_num" value="${condition.class_num[0]}" class="form-control" id="exampleInputName3" >
                </div>

                <div class="form-group">
                    <label for="exampleInputEmail4">学院</label>
                    <input type="text" name="department" value="${condition.department[0]}" class="form-control" id="exampleInputEmail4"  >
                </div>
                <button type="submit" class="btn btn-default">查询</button>
            </form>
        </div>
        <div class="col-sm-12" align="right" >
            <a href="add.jsp" class="btn btn-primary" >添加学生</a>
            <a href="javascript:void(0);" class="btn btn-primary" id="del_all">删除选中</a>
        </div>
        <div class="col-sm-12">
            <table class="table table-hover table-bordered" >
                <tr class="success">
                    <th><input id="all" type="checkbox"></th>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>班级</th>
                    <th>学院</th>
                    <th>籍贯</th>
                    <th>出生日期</th>
                    <th>操作</th>
                </tr>
                <form id="del_select_form" action="${pageContext.request.contextPath}/deleteSelectedServlet" method="get" >
                <c:forEach items="${pb.stus}" var="student">
                    <tr>
                        <td><input class="_check" type="checkbox" name="num" value="${student.num}"></td>
                        <td>${student.num}</td>
                        <td>${student.name}</td>
                        <td>${student.sex}</td>
                        <td>${student.class_num}</td>
                        <td>${student.department}</td>
                        <td>${student.province}</td>
                        <td>${student.bir}</td>
                        <td>
                            <a href="update.jsp?num=${student.num}" class="btn btn-default">修改</a>
                            <a href="javascript:void(0);" onclick="delStudent(${student.num})" id="delStudent" class="btn btn-default">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </form>
            </table>
        </div>
        <%-- 分页栏 --%>
        <div >
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <%-- 上一页 --%>
                    <c:if test="${pb.currentPage == 1}">
                        <li class="disabled">
                            <a aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != 1}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findStudentsByPageServlet?currentPage=${pb.currentPage - 1}&pageCount=5&name=${condition.name[0]}&num=${condition.num[0]}&class_num=${condition.class_num[0]}&department=${condition.department[0]}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <%-- 上一页 --%>
                    <%-- 数字 --%>
                    <c:forEach begin="1" end="${pb.totalPage}" var="i">

                        <c:if test="${pb.currentPage == i}">
                            <li class="active">
                                <a href="${pageContext.request.contextPath}/findStudentsByPageServlet?currentPage=${i}&pageCount=5&name=${condition.name[0]}&num=${condition.num[0]}&class_num=${condition.class_num[0]}&department=${condition.department[0]}">
                                        ${i}
                                </a>
                            </li>
                        </c:if>
                        <c:if test="${pb.currentPage != i}">
                            <li>
                                <a href="${pageContext.request.contextPath}/findStudentsByPageServlet?currentPage=${i}&pageCount=5&name=${condition.name[0]}&num=${condition.num[0]}&class_num=${condition.class_num[0]}&department=${condition.department[0]}">
                                    ${i}
                                </a>
                            </li>
                        </c:if>
                    </c:forEach>
                    <%-- 数字 --%>
                    <%--下一页 --%>
                    <c:if test="${pb.currentPage == pb.totalPage}">
                        <li class="disabled">
                            <a aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <c:if test="${pb.currentPage != pb.totalPage}">
                        <li>
                            <a href="${pageContext.request.contextPath}/findStudentsByPageServlet?currentPage=${pb.currentPage + 1}&pageCount=5&name=${condition.name[0]}&num=${condition.num[0]}&class_num=${condition.class_num[0]}&department=${condition.department[0]}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <%--下一页 --%>
                    <span style="font-size: 24px;margin-left: 5px">共${pb.totalCount}条纪录,共${pb.totalPage}页</span>
                </ul>
            </nav>

        </div>

    </div>

</body>
<script src="js/jquery-3.3.1.min.js"></script>
<script>
    var all = document.getElementsByClassName('_check');
    $('#all').click(function() {
        for(let i = 0; i < all.length; i++) {
            all[i].checked = this.checked;
        }
    });

    function delStudent(num) {
        if(confirm("您确定要删除吗?")) {
            location.href = "${pageContext.request.contextPath}/deleteStudentServlet?num=" + num;
            confirm("删除成功!");
        }
    }

    $('#del_all').click(function() {
        let flag = false;
        for(let i = 0; i < all.length; i++) {
            if(all[i].checked) {
                flag = true;
                break;
            }
        }
        if(!flag) {
            confirm("您还未选择要删除的信息!");
            return;
        }
        if(confirm("是否删除选中?")) {
            $('#del_select_form').submit();
            confirm("删除成功!");
        }
    });
</script>
</html>
