package service.custom;

import dto.Employee;
import dto.Product;
import javafx.collections.ObservableList;
import service.SuperService;

public interface ProducrService extends SuperService {
    boolean add(Product product);

    boolean delete(String productId);

    Product search(String search);

    boolean update(Product product);

    String generateProductId();

    ObservableList<Product> getAllProducts();
}
