package service.custom.impl;

import dto.Employee;
import javafx.collections.ObservableList;
import repository.custom.EmployeeDao;
import repository.custom.impl.EmployeeDaoImpl;
import service.custom.EmployeeService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public boolean add(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public boolean delete(String employeeId) {
        return employeeDao.delete(employeeId);
    }

    @Override
    public Employee search(String employeeId) {
        return employeeDao.search(employeeId);
    }

    @Override
    public boolean update(Employee employee) {
        employeeDao.update(employee);
        return true;
    }

    @Override
    public String generateEmployeeId() {
        String lastId = employeeDao.findLastId();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        return employeeDao.getAll();
    }
}
