package Ricard.code.web.servlet;

import Ricard.code.domain.Teacher;
import Ricard.code.service.TeacherService;
import Ricard.code.service.impl.TeacherServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/findTeacherServlet")
public class FindTeacherServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.设置字符编码
        response.setContentType("text/html;charset=utf-8");
        // 2.获取用户名
        String username = request.getParameter("username");

        TeacherService service = new TeacherServiceImpl();

        // 3.通过用户名查找用户
        Teacher teacher = service.findTeacherByUsername(username);
        // 定义map集合存储响应信息
        Map<String, Object> map = new HashMap<>();
        // 4.判断Teacer对象是否为空
        if(teacher != null) {
            map.put("userExit", true);
            map.put("msg", "该用户名太受欢迎了,请换一个!");
        }else {
            map.put("userExit", false);
            map.put("msg", "恭喜您,该用户可以使用!");
        }
        ObjectMapper mapper = new ObjectMapper();
        // 5.响应信息 将map转为json, 传递给客户端
        mapper.writeValue(response.getWriter(), map);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
