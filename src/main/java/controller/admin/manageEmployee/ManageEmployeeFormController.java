package controller.admin.manageEmployee;

import dto.Customer;
import dto.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.EmployeeService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageEmployeeFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoenNumber;

    @FXML
    private TableView<Employee> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private Label txtAdminName;

    @FXML
    private TextField txtEmployeeD;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearch;

    private final EmployeeService employeeService = ServiceFactory.getInstance().getServiceType(ServiceType.EMPLOYEE);

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtEmployeeD.getText(),
                txtEmployeeName.getText(),
                txtPhoneNumber.getText(),
                txtAddress.getText()
        );
        if(isValidCustomerInputDetails(employee)){
            if(employeeService.add(employee)){
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
        if(employeeService.delete(txtEmployeeD.getText())){
            new Alert(Alert.AlertType.INFORMATION, "Customer remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
    }
    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setTextToValue(employeeService.search(txtSearch.getText()));

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Employee employee = new Employee(
                txtEmployeeD.getText(),
                txtEmployeeName.getText(),
                txtPhoneNumber.getText(),
                txtAddress.getText()
        );
        if (isValidCustomerInputDetails(employee)) {
            if (employeeService.update(employee)) {
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
        colID.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("emp_name"));
        colPhoenNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });
        loadTable();
        txtEmployeeD.setText(generateEmployeeId());
    }

    private void loadTable() {
        ObservableList<Employee> customerObservableList = employeeService.getAllEmployees();
        tblEmployee.setItems(customerObservableList);
    }

    private void setTextToValue(Employee newValue) {
        txtEmployeeD.setText(newValue.getEmp_id());
        txtEmployeeName.setText(newValue.getEmp_name());
        txtPhoneNumber.setText(String.valueOf(newValue.getPhonenumber()));
        txtAddress.setText(newValue.getAddress());
    }

    private String generateEmployeeId() {
        String base = "C";
        int integer = Integer.parseInt(employeeService.generateEmployeeId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base + (integer + 1);
    }

    private void clearInputFields() {
        txtEmployeeD.setText(generateEmployeeId());
        txtEmployeeName.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
    }
    private boolean isValidCustomerInputDetails (Employee employee){
        if (employee.getEmp_id().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (employee.getEmp_name().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
        if (employee.getAddress().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is required.").show();
            return false;
        }
        return true;
    }
}
