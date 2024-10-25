package service;

import service.custom.impl.CustomerServiceImpl;
import service.custom.impl.EmployeeServiceImpl;
import service.custom.impl.ProductServiceImpl;
import service.custom.impl.SupplierServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance==null ? instance = new ServiceFactory() : instance;
    }

    public <T extends SuperService> T getServiceType(ServiceType serviceType) {
        switch (serviceType) {
            case CUSTOMER:return (T) new CustomerServiceImpl();
            case PRODUCT:return (T) new ProductServiceImpl();
            case SUPPLIER:return (T) new SupplierServiceImpl();
            case EMPLOYEE:return (T) new EmployeeServiceImpl();
        }
        return null;
    }

}
