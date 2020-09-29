package Ricard.code.dao;


import Ricard.code.domain.Teacher;

import java.util.Map;

public interface TeacherDao {

    Teacher findTeacher(String username);

    boolean addTeacher(Map<String, String[]> teacher);

    Teacher findByUsernamePassword(String username, String password);
}
