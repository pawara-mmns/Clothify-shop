package repository.custom.impl;

import dto.Customer;
import dto.Employee;
import dto.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.custom.ProductDao;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDaoImpl implements ProductDao {

    @Override
    public boolean save(Product product) {
        String SQL = "INSERT INTO product VALUES (?, ?, ?, ?)";
        try {
            return CrudUtil.execute(SQL,
                    product.getPro_id(),
                    product.getPro_name(),
                    product.getQty(),
                    product.getPrice()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Product> getAll() {
        ObservableList<Product> productObservableList = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM product";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL);
            while (resultSet.next()) {
                productObservableList.add(new Product(
                        resultSet.getString("pro_id"),
                        resultSet.getString("pro_name"),
                        resultSet.getString("qty"),
                        resultSet.getString("price")
                ));
            }
            return productObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        String SQL = "DELETE FROM product WHERE pro_id = ?";
        try {
            return CrudUtil.execute(SQL,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product search(String id) {
        String SQL = "SELECT * FROM product WHERE pro_id = ?";
        try {
            ResultSet resultSet = CrudUtil.execute(SQL,id);
            if (resultSet.next()) {
                return new Product(
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
    public boolean update(Product product) {
        String SQL = "UPDATE product SET pro_name = ?, phonenumber = ?, address = ? WHERE pro_id=?";
        try {
            return CrudUtil.execute(SQL,
                    product.getPro_name(),
                    product.getQty(),
                    product.getPrice(),
                    product.getPro_id()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String findLastId() {
        String SQL = "SELECT MAX(pro_id) FROM product";
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


