package Ricard.code.service;


import Ricard.code.domain.PageBean;
import Ricard.code.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentService {

    /**
     * 根据学号删除学生信息
     * @param num
     * @return
     */
    boolean deleteStudent(String num);

    /**
     * 删除已选中的学生信息
     * @param nums
     * @return
     */
    boolean deleteSelected(String[] nums);

    /**
     * 通过学号查找学生是否存在
     * @return
     */
    Student findStudentByNum(String num);

    /**
     * 添加学生信息
     * @param stuList
     * @return
     */
    boolean addStudent(List<Object> stuList);

    boolean updateStudent(Map<String, String[]> paramMap);

    /**
     * 分页查询
     * @param currentPage
     * @param pageCount
     * @param parameterMap
     * @return
     */
    PageBean<Student> listStudentByPage(int currentPage, int pageCount, Map<String, String[]> parameterMap);
}
