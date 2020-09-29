package Ricard.code.dao.impl;

import Ricard.code.dao.DepartmentDao;
import Ricard.code.domain.Department;
import Ricard.code.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());
    
    @Override
    public List<Department> findAllDepartment() {
        // 定义sql语句
        String sql = "select * from department";

        // 执行sql语句
        try {
            List<Department> list = template.query(sql, new BeanPropertyRowMapper<Department>(Department.class));

            return list;
        }catch (DataAccessException e) {
            return null;
        }
    }
}
