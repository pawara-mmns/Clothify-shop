package service.custom;

import dto.Customer;
import dto.Supplier;
import javafx.collections.ObservableList;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean add(Supplier supplier);

    boolean delete(String supplierId);

    Supplier search(String search);

    boolean update(Supplier supplier);

    String generateSupplierId();

    ObservableList<Supplier> getAllSupplier();
}
