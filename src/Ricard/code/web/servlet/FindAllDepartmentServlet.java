package Ricard.code.web.servlet;

import Ricard.code.domain.Department;
import Ricard.code.service.DepartmentService;
import Ricard.code.service.impl.DepartmentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/findAllDepartmentServlet")
public class FindAllDepartmentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置response响应格式
        response.setContentType("application/json;charset=utf-8");

        // 定义service对象,操作数据库
        DepartmentService service = new DepartmentServiceImpl();
        List<Department> departments = service.findAllDepartment();

        // 序列化list集合
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(departments);
        // 响应数据
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
