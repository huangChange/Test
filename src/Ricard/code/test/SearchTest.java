package Ricard.code.test;


import Ricard.code.domain.Teacher;
import Ricard.code.util.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.util.List;

public class SearchTest {

    @Test
    public void testSearch() {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDs());

        String sql = "select * from teacher where username = ?";


        Teacher zhangsan = template.queryForObject(sql, new BeanPropertyRowMapper<Teacher>(Teacher.class), "zhangsan");
        System.out.println(zhangsan);
    }

}
