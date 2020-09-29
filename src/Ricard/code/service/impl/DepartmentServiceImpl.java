package Ricard.code.service.impl;

import Ricard.code.dao.DepartmentDao;
import Ricard.code.dao.impl.DepartmentDaoImpl;
import Ricard.code.domain.Department;
import Ricard.code.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentDao dao = new DepartmentDaoImpl();
    @Override
    public List<Department> findAllDepartment() {
        return dao.findAllDepartment();
    }
}
