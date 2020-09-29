package Ricard.code.web.servlet;

import Ricard.code.domain.Student;
import Ricard.code.service.StudentService;
import Ricard.code.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listAllStudentServlet")
public class ListAllStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*request.setCharacterEncoding("utf-8");

        // 定义一个service对象,用来操作数据库
        StudentService service = new StudentServiceImpl();

        List<Student> students = service.listAllStudent();

        if(students != null) {
            request.setAttribute("list", students);

            // 转发到list.jsp中
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }*/

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
