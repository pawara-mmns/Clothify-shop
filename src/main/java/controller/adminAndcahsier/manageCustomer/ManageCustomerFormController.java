package controller.adminAndcahsier.manageCustomer;

import dto.Customer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.impl.CustomerServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCustomerFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoenNumber;

    @FXML
    private TableView<Customer> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private Label txtAdminName;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearch;

    private final CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtPhoneNumber.getText(),
                txtAddress.getText()
        );
        if(isValidCustomerInputDetails(customer)){
            if(customerService.add(customer)){
                new Alert(Alert.AlertType.INFORMATION, "Customer added successful !").show();
            }else{
                new Alert(Alert.AlertType.ERROR, "Customer not added :(").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR,"Please fill out all fields correctly !").show();
        }
        clearInputFields();
        loadTable();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if(customerService.delete(txtCustomerID.getText())){
            new Alert(Alert.AlertType.INFORMATION, "Customer remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
        }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setTextToValue(customerService.search(txtSearch.getText()));

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Customer customer = new Customer(
                txtCustomerID.getText(),
                txtCustomerName.getText(),
                txtPhoneNumber.getText(),
                txtAddress.getText()
        );
        if (isValidCustomerInputDetails(customer)) {
            if (customerService.update(customer)) {
                new Alert(Alert.AlertType.INFORMATION, "Customer updated successful !").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Customer not updated :(").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Please fill out all fields correctly !").show();
        }
        loadTable();
        clearInputFields();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colID.setCellValueFactory(new PropertyValueFactory<>("cus_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("cus_name"));
        colPhoenNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });
        loadTable();
        txtCustomerID.setText(generateCustomerId());

    }
    private void loadTable() {
        ObservableList<Customer> customerObservableList = customerService.getAllCustomers();
        tblCustomer.setItems(customerObservableList);
    }
    private void setTextToValue(Customer newValue) {
        txtCustomerID.setText(newValue.getCus_id());
        txtCustomerName.setText(newValue.getCus_name());
        txtPhoneNumber.setText(String.valueOf(newValue.getPhonenumber()));
        txtAddress.setText(newValue.getAddress());
    }
    private String generateCustomerId() {
        String base = "C";
        int integer = Integer.parseInt(customerService.generateCustomerId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base+(integer + 1);
    }
    private void clearInputFields() {
        txtCustomerID.setText(generateCustomerId());
        txtCustomerName.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
    }
    private boolean isValidCustomerInputDetails (Customer customer){
        if (customer.getCus_id().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (customer.getCus_name().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
        if (customer.getAddress().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is required.").show();
            return false;
        }
        return true;
    }
}
