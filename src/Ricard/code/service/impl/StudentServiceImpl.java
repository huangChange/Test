package Ricard.code.service.impl;

import Ricard.code.dao.StudentDao;
import Ricard.code.dao.impl.StudentDaoImpl;
import Ricard.code.domain.PageBean;
import Ricard.code.domain.Student;
import Ricard.code.service.StudentService;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    StudentDao dao = new StudentDaoImpl();

    @Override
    public boolean deleteStudent(String num) {
        dao.delStudentByNum(num);
        return true;
    }

    /**
     * 删除已选中的学生信息
     * @param nums
     * @return
     */
    @Override
    public boolean deleteSelected(String[] nums) {
        for(String num : nums) {
            deleteStudent(num);
        }
        return true;
    }

    @Override
    public Student findStudentByNum(String num) {
        Student stu = dao.findSudent(num);

        return stu;
    }

    @Override
    public boolean addStudent(List<Object> stuList) {
        return dao.addStudent(stuList);
    }

    /**
     * 修改学生信息
     * @param paramMap
     * @return
     */
    @Override
    public boolean updateStudent(Map<String, String[]> paramMap) {
        return dao.updateStudent(paramMap);
    }

    @Override
    public PageBean<Student> listStudentByPage(int currentPage, int pageCount, Map<String, String[]> parameterMap) {
        PageBean<Student> pageBean = new PageBean<Student>();
        // 封装PageBean对象
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageCount(pageCount);
        int start = (currentPage - 1) * pageCount;

        int totalCount = dao.findAllStudnets(parameterMap);
        pageBean.setTotalCount(totalCount);

        List<Student> stus = dao.findAllStudnetsByPage(start, pageCount, parameterMap);
        pageBean.setStus(stus);

        int totalPage = totalCount % pageCount == 0? totalCount / pageCount : totalCount / pageCount + 1;
        pageBean.setTotalPage(totalPage);

        return pageBean;
    }
}
