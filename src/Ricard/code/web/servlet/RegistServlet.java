package Ricard.code.web.servlet;

import Ricard.code.service.TeacherService;
import Ricard.code.service.impl.TeacherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/registServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        // 获取session对象
        HttpSession session = request.getSession();
        // 1.获取到输入的验证码
        String code = (String)session.getAttribute("checkCode_Session");
        // 一旦获取到内存中的验证码就移除该属性。
        session.removeAttribute("checkCode_Session");

        // 获取表单提交中的属性
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 定义服务对象,用来访问数据库
        TeacherService service = new TeacherServiceImpl();

        String check_code = parameterMap.get("code")[0];
        // 判断验证码是否输入正确
        if(code != null && code.length() != 0 && code.equalsIgnoreCase(check_code)) {
            boolean flag = service.addTeacher(parameterMap);
            if(flag) {
                response.sendRedirect(request.getContextPath() + "/success.jsp");
            }else {
                response.sendRedirect(request.getContextPath() + "/fail.jsp");
            }
        }else {
            request.setAttribute("code_error", "验证码错误!");
            // 转发
            request.getRequestDispatcher("/regist.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
