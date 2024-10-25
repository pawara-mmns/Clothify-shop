package service;


import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

public class Servicefactory {
    private static Servicefactory instance;
    private Servicefactory(){}

    public static Servicefactory getInstance() {
        return instance == null ? instance = new Servicefactory() : instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType type) {
        return switch (type) {
            case PRODUCT -> (T) new ProductServiceImpl();
            case EMPLOYEE -> (T) new EmployeeServiceImpl();
            case SUPPLIER -> (T) new SupplierServiceImpl();
            case CUSTOMER -> (T) new CustomerServiceImpl();
        };
    }

}