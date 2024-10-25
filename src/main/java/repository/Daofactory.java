package repository;

import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.DaoType;

import static util.DaoType.PRODUCT;

public class Daofactory {
    private static Daofactory instance;
    private Daofactory(){}

    public static Daofactory getInstance() {
        return instance == null ? instance = new Daofactory() : instance;
    }

    public <T extends SuperDao> T getDaoType(DaoType type) {
        return switch (type) {
            case PRODUCT -> (T) new ProductServiceImpl();
            case EMPLOYEE -> (T) new EmployeeServiceImpl();
            case SUPPLIER -> (T) new SupplierServiceImpl();
            case CUSTOMER -> (T) new CustomerServiceImpl();

        };
    }
}
