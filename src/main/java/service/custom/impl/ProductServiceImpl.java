package service.custom.impl;

import dto.Product;
import javafx.collections.ObservableList;
import repository.custom.ProductDao;
import repository.custom.SupplierDao;
import repository.custom.impl.ProductDaoImpl;
import repository.custom.impl.SupplierDaoImpl;
import service.custom.ProducrService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductServiceImpl implements ProducrService {


    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public boolean add(Product product) {
        return productDao.save(product);
    }

    @Override
    public boolean delete(String productId) {
        return productDao.delete(productId);
    }

    @Override
    public Product search(String supplierId) {
        return productDao.search(supplierId);
    }

    @Override
    public boolean update(Product product) {
        productDao.update(product);
        return true;
    }

    @Override
    public String generateProductId() {
        String lastId = productDao.findLastId();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Product> getAllProducts() {
        return productDao.getAll();
    }
}
