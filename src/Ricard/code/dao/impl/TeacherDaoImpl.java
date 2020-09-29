package Ricard.code.dao.impl;


import Ricard.code.dao.TeacherDao;
import Ricard.code.domain.Teacher;
import Ricard.code.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;


public class TeacherDaoImpl implements TeacherDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

    @Override
    public Teacher findTeacher(String username) {
        // 定义sql语句
        String sql = "select * from teacher where username = ?";

        try {
            Teacher teacher = template.queryForObject(sql, new BeanPropertyRowMapper<Teacher>(Teacher.class), username);
            return teacher;
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public boolean addTeacher(Map<String, String[]> teacher) {
        // 获取属性值
        String username = teacher.get("username")[0];
        String password = teacher.get("password")[0];
        String name = teacher.get("name")[0];
        String department = teacher.get("department")[0];
        String email = teacher.get("email")[0];
        String tel = teacher.get("tel")[0];

        // 定义sql语句
        String sql = "insert into teacher values(null, ?, ?, ?, ?, ?, ?)";

        // 执行sql语句
        try {
            template.update(sql, username, password, name, department, email, tel);
        }catch (DataAccessException e) {
            return false;
        }

        return true;
    }

    @Override
    public Teacher findByUsernamePassword(String username, String password) {
        // 定义sql语句
        String sql = "select * from teacher where username = ? and password = ?";

        // 执行sql语句
        try {
            Teacher teacher = template.queryForObject(sql, new BeanPropertyRowMapper<Teacher>(Teacher.class),
                    username, password);

            return teacher;
        } catch (DataAccessException e) {
            return null;
        }

    }
}
