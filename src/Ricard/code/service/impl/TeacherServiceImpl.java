package Ricard.code.service.impl;


import Ricard.code.dao.TeacherDao;
import Ricard.code.dao.impl.TeacherDaoImpl;
import Ricard.code.domain.Teacher;
import Ricard.code.service.TeacherService;

import java.util.Map;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDao dao = new TeacherDaoImpl();

    @Override
    public Teacher findTeacherByUsername(String username) {
        return dao.findTeacher(username);
    }

    @Override
    public boolean addTeacher(Map<String, String[]> teacher) {
        return dao.addTeacher(teacher);
    }

    @Override
    public Teacher login(String username, String password) {

        return dao.findByUsernamePassword(username, password);
    }
}
