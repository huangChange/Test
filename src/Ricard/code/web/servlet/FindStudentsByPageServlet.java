package Ricard.code.web.servlet;

import Ricard.code.domain.PageBean;
import Ricard.code.domain.Student;
import Ricard.code.service.StudentService;
import Ricard.code.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@WebServlet("/findStudentsByPageServlet")
public class FindStudentsByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // 获取条件查询参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String,String[]>> entries = parameterMap.entrySet();

        String currentPageStr = request.getParameter("currentPage");
        String pageCountStr = request.getParameter("pageCount");

        int currentPage = 0;
        if(currentPageStr == null || "".equals(currentPageStr)) {
            currentPage = 1;
        }else{
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageCount = 0;
        if(pageCountStr == null || "".equals(pageCountStr)) {
            pageCount = 5;
        }else {
            pageCount = Integer.parseInt(pageCountStr);
        }

        StudentService service = new StudentServiceImpl();

        PageBean<Student> pageBean = service.listStudentByPage(currentPage, pageCount, parameterMap);

        request.setAttribute("pb", pageBean);
        // 回显数据
        request.setAttribute("condition", parameterMap);

        request.getRequestDispatcher("list.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
