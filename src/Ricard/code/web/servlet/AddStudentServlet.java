package Ricard.code.web.servlet;



import Ricard.code.domain.Student;
import Ricard.code.service.StudentService;
import Ricard.code.service.impl.StudentServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet("/addStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        Map<String, String[]> parameterMap = request.getParameterMap();
        // 将Map集合转换为list集合
        List<Object> list = new ArrayList<Object>();

        StudentService service = new StudentServiceImpl();
        // 遍历Map集合
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for(Map.Entry<String, String[]> entry: entries) {
            String key = entry.getKey();
            String value = entry.getValue()[0];
            list.add(value);
        }
        boolean flag = service.addStudent(list);

        if(flag) {
            response.setContentType("text/html;charset=utf-8");
            response.sendRedirect(request.getContextPath() + "/findStudentsByPageServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
