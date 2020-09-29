package Ricard.code.dao;

import Ricard.code.domain.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao {
    /**
     * 查询总的记录数
     * @return
     */
    int findAllStudnets(Map<String,String[]> param);

    /**
     * 根据学号删除学生信息
     * @param num
     */
    void delStudentByNum(String num);

    /**
     * 通过学号判断学生是否存在
     * @param num
     * @return
     */
    Student findSudent(String num);

    /**
     * 添加学生信息
     * @param stuList
     * @return
     */
    boolean addStudent(List<Object> stuList);

    /**
     * 修改学生信息
     * @param paramMap
     * @return
     */
    boolean updateStudent(Map<String, String[]> paramMap);

    List<Student> findAllStudnetsByPage(int start, int pageCount, Map<String, String[]> parameterMap);
}
