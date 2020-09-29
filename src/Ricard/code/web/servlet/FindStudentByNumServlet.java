package Ricard.code.web.servlet;

import Ricard.code.domain.Student;
import Ricard.code.service.StudentService;
import Ricard.code.service.impl.StudentServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findStudentByNumServlet")
public class FindStudentByNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String num = request.getParameter("num");

        StudentService service = new StudentServiceImpl();

        Student stu = service.findStudentByNum(num);

        request.setAttribute("stu", stu);

        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getWriter(), stu);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
