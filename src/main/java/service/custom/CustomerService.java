package service.custom;

import dto.Customer;
import javafx.collections.ObservableList;
import service.SuperService;

public interface CustomerService extends SuperService {
    boolean add(Customer customer);

    boolean delete(String customerId);

    Customer search(String search);

    boolean update(Customer customer);

    String generateCustomerId();

    ObservableList<Customer> getAllCustomers();
}
