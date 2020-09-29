package Ricard.code.web.servlet;

import Ricard.code.service.StudentService;
import Ricard.code.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取参数值
        String[] nums = request.getParameterValues("num");

        // 定义service对象
        StudentService service = new StudentServiceImpl();

        boolean flag = service.deleteSelected(nums);

        if(flag) {
            response.setContentType("text/html;charset=utf-8");
            // 重定向
            response.sendRedirect(request.getContextPath() + "/listAllStudentServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
