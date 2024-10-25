package service.custom.impl;

import dto.Customer;
import javafx.collections.ObservableList;
import repository.custom.CustomerDao;
import repository.custom.impl.CustomerDaoImpl;
import service.custom.CustomerService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public boolean add(Customer customer) {
       return customerDao.save(customer);
    }

    @Override
    public boolean delete(String customerId) {
        return customerDao.delete(customerId);
    }

    @Override
    public Customer search(String customerId) {
        return customerDao.search(customerId);
    }

    @Override
    public boolean update(Customer customer) {
        customerDao.update(customer);
        return true;
    }

    @Override
    public String generateCustomerId() {
        String lastId = customerDao.findLastId();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastId);
        return (matcher.find()) ? matcher.group() : null;
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return customerDao.getAll();
    }
}
