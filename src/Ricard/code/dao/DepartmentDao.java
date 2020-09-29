package Ricard.code.dao;


import Ricard.code.domain.Department;

import java.util.List;

public interface DepartmentDao {

    List<Department> findAllDepartment();
}
