package repository.custom.impl;

import dto.Customer;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.SupplierDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDaoImpl implements SupplierDao {

    @Override
    public boolean save(Supplier supplier) {
        String SQL = "INSERT INTO supplier VALUES (?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    supplier.getSup_id(),
                    supplier.getSup_name(),
                    supplier.getPhonenumber(),
                    supplier.getAddress()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Supplier> getAll() {
        ObservableList<Supplier> supplierObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM supplier";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                    supplierObservableList.add(new Supplier(
                        resultSet.getString("sup_id"),
                        resultSet.getString("sup_name"),
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address")
                ));
            }
            return supplierObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM supplier WHERE sup_id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Supplier search(String id) {
        String SQL = "SELECT * FROM supplier WHERE sup_id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);
            if (resultSet.next()) {
                return new Supplier(
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
    public boolean update(Supplier supplier) {
        String SQL = "UPDATE supplier SET sup_name = ?, phonenumber = ?, address = ? WHERE sup_id=?";
        try {
            return CrudUtil.execute(SQL,
                    supplier.getSup_name(),
                    supplier.getPhonenumber(),
                    supplier.getAddress(),
                    supplier.getSup_id()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findLastId() {
        String SQL = "SELECT MAX(sup_id) FROM supplier";
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


