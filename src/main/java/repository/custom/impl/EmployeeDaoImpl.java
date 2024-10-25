package repository.custom.impl;

import dto.Customer;
import dto.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.EmployeeDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public boolean save(Employee employee) {
        String SQL = "INSERT INTO employee VALUES (?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    employee.getEmp_id(),
                    employee.getEmp_name(),
                    employee.getPhonenumber(),
                    employee.getAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Employee> getAll() {
        ObservableList<Employee> emplObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM employee";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                emplObservableList.add(new Employee(
                        resultSet.getString("emp_id"),
                        resultSet.getString("emp_name"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address")
                ));
            }
            return emplObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM employee WHERE emp_id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee search(String id) {
        String SQL = "SELECT * FROM employee WHERE emp_id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);
            if (resultSet.next()) {
                return new Employee(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(Employee employee) {
        String SQL = "UPDATE employee SET emp_name = ?, phonenumber = ?, address = ? WHERE emp-id=?";
        try {
            return CrudUtil.execute(SQL,
                    employee.getEmp_name(),
                    employee.getPhonenumber(),
                    employee.getAddress(),
                    employee.getEmp_id()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findLastId() {
        String SQL = "SELECT MAX(emp_id) FROM employee";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}


