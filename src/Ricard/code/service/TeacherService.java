package Ricard.code.service;

import Ricard.code.domain.Teacher;

import java.util.Map;

public interface TeacherService {

    Teacher findTeacherByUsername(String username);

    boolean addTeacher(Map<String, String[]> teacher);

    Teacher login(String username, String password);
}
