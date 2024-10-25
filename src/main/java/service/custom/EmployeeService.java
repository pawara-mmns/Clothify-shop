package service.custom;

import dto.Customer;
import dto.Employee;
import javafx.collections.ObservableList;
import service.SuperService;

public interface EmployeeService extends SuperService {
    boolean add(Employee employee);

    boolean delete(String employeeId);

    Employee search(String search);

    boolean update(Employee employee);

    String generateEmployeeId();

    ObservableList<Employee> getAllEmployees();
}
