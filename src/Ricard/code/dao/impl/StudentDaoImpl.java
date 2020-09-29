package Ricard.code.dao.impl;


import Ricard.code.dao.StudentDao;
import Ricard.code.domain.Student;
import Ricard.code.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

    @Override
    public int findAllStudnets(Map<String,String[]> param) {
        // 定义sql语句
        String sql = "select count(*) from student where 1=1 ";

        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        List<Object> paramList = new ArrayList<Object>();

        if(param != null && param.size() > 0) {
            Set<Map.Entry<String, String[]>> entries = param.entrySet();
            for(Map.Entry<String,String[]> entry : entries) {
                String key = entry.getKey();
                if(!key.equals("currentPage") && !key.equals("pageCount") && !entry.getValue()[0].equals("") && entry.getValue()[0].length() > 0) {
                    sb.append(" and " + key + " like ?");
                    paramList.add("%" + entry.getValue()[0] + "%");
                }
            }
        }

        sql = sb.toString();
        System.out.println("sql: " + sql);
        System.out.println("param: " + paramList);

        // 执行sql语句
        return template.queryForObject(sql, Integer.class, paramList.toArray());
    }

    @Override
    public void delStudentByNum(String num) {
        // 定义sql语句
        String sql = "delete from student where num = ?";
        // 执行sql
        template.update(sql, num);
    }

    @Override
    public Student findSudent(String num) {
        // 定义sql
        String sql = "select * from student where num = ?";

        try {
            Student stu = template.queryForObject(sql, new BeanPropertyRowMapper<Student>(Student.class), num);
            return stu;
        }catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public boolean addStudent(List<Object> stuList) {
        String sql = "insert into student(num,name,class_num,department,province,bir,sex) values(?,?,?,?,?,?,?)";

        try {
            template.update(sql, stuList.toArray());
            return true;
        }catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public boolean updateStudent(Map<String, String[]> paramMap) {
        // 定义sql语句
        String sql = "update student set class_num = ?, department = ?, province = ?, bir = ?, sex = ? where num = ?";

        // 定义List集合,将map集合中的value值添加到list集合中
        List<Object> paramList = new ArrayList<Object>();

        Set<Map.Entry<String, String[]>> entries =  paramMap.entrySet();
        for(Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            if(!key.equals("num") && !key.equals("name")) {
                paramList.add(entry.getValue()[0]);
            }
        }
        paramList.add(paramMap.get("num")[0]);

        try {
            template.update(sql, paramList.toArray());
            return true;
        }catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public List<Student> findAllStudnetsByPage(int start, int pageCount, Map<String, String[]> parameterMap) {
        String sql = "select * from student where 1=1";
        // 拼接sql
        StringBuilder sb = new StringBuilder();
        sb.append(sql);
        // 保存查询的参数
        List<Object> paramList = new ArrayList<Object>();

        if(parameterMap != null) {
            Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
            for(Map.Entry<String,String[]> entry : entries) {
                String key = entry.getKey();
                if(!key.equals("currentPage") && !key.equals("pageCount") && !entry.getValue()[0].equals("") && entry.getValue()[0].length() > 0) {
                    sb.append(" and " + key + " like ?");
                    paramList.add("%" + entry.getValue()[0] + "%");
                }
            }
        }
        sb.append(" limit ?,? ");
        paramList.add(start);
        paramList.add(pageCount);

        sql = sb.toString();
        System.out.println("sql: " + sql);
        System.out.println("param: " + paramList);

        return template.query(sql, new BeanPropertyRowMapper<Student>(Student.class), paramList.toArray());
    }
}
