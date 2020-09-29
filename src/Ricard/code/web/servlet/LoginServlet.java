package Ricard.code.web.servlet;


import Ricard.code.domain.Teacher;
import Ricard.code.service.TeacherService;
import Ricard.code.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 获取表单中的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 获取session对象
        HttpSession session = request.getSession();

        // 定义service对象
        TeacherService service = new TeacherServiceImpl();

        Teacher teacher =  service.login(username, password);

        if(teacher != null) {
            // 将得到的用户名存入到session
            session.setAttribute("name", teacher.getName());
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }else {
            request.setAttribute("error_msg", "用户名或密码输入错误!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
