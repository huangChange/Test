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

@WebServlet("/deleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取参数值
        String num = request.getParameter("num");
        request.removeAttribute("num");

        StudentService service = new StudentServiceImpl();

        boolean flag = service.deleteStudent(num);
        if(flag) {
            response.sendRedirect(request.getContextPath() + "/findStudentsByPageServlet?");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
