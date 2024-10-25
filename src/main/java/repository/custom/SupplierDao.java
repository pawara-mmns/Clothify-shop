package repository.custom;

import dto.Supplier;
import repository.CrudDao;

public interface SupplierDao extends CrudDao<Supplier> {
    String findLastId();
}
