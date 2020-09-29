package Ricard.code.web.servlet;


import Ricard.code.service.StudentService;
import Ricard.code.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/updateStudentServlet")
public class UpdateStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 获取参数
        Map<String, String[]> parameterMap = request.getParameterMap();

        StudentService service = new StudentServiceImpl();
        boolean flag = service.updateStudent(parameterMap);

        if(flag) {
            response.setContentType("text/html;charset=utf-8");
            response.sendRedirect(request.getContextPath() + "/findStudentsByPageServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
