package repository.custom;

import dto.Employee;
import repository.CrudDao;

public interface EmployeeDao extends CrudDao<Employee> {
    String findLastId();
}
