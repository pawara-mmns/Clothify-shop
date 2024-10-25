package repository.custom;

import dto.Product;
import repository.CrudDao;

public interface ProductDao extends CrudDao<Product> {
    String findLastId();
}
