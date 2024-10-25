package repository.custom.impl;

import dto.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.CustomerDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer customer) {
        String SQL = "INSERT INTO customer VALUES (?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    customer.getCus_id(),
                    customer.getCus_name(),
                    customer.getPhonenumber(),
                    customer.getAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Customer> getAll() {
        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM customer";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                customerObservableList.add(new Customer(
                        resultSet.getString("cus_id"),
                        resultSet.getString("cus_name"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address")
                ));
            }
            return customerObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM customer WHERE cus_id = ?";
        try {
            return CrudUtil.execute(SQL, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Customer search(String id) {
        String SQL = "SELECT * FROM customer WHERE cus_id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL, id);
            if (resultSet.next()) {
                return new Customer(
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
    public boolean update(Customer customer) {
        String SQL = "UPDATE customer SET cus_name = ?, phonenumber = ?, address = ? WHERE cus_id=?";
        try {
            return CrudUtil.execute(SQL,
                    customer.getCus_name(),
                    customer.getPhonenumber(),
                    customer.getAddress(),
                    customer.getCus_id()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findLastId() {
        String SQL = "SELECT MAX(cus_id) FROM customer";
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
