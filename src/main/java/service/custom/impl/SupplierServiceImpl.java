package service.custom.impl;

import dto.Supplier;
import javafx.collections.ObservableList;
import repository.custom.SupplierDao;
import repository.custom.impl.SupplierDaoImpl;
import service.custom.SupplierService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierServiceImpl implements SupplierService {

    private final SupplierDao supplierDao = new SupplierDaoImpl();

    @Override
    public boolean add(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    @Override
    public boolean delete(String supplierId) {
        return supplierDao.delete(supplierId);
    }

    @Override
    public Supplier search(String supplierId) {
        return supplierDao.search(supplierId);
    }

    @Override
    public boolean update(Supplier supplier) {
        supplierDao.update(supplier);
        return true;
    }

    @Override
    public String generateSupplierId() {
        String lastId = supplierDao.findLastId();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Supplier> getAllSupplier() {
        return supplierDao.getAll();
    }
}
