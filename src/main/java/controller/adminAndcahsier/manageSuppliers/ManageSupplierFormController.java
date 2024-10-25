package controller.adminAndcahsier.manageSuppliers;

import dto.Customer;
import dto.Supplier;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageSupplierFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoenNumber;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtAddress;

    @FXML
    private Label txtAdminName;

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtSupplierName;

    private final SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.SUPPLIER);

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtSupplierID.getText(),
                txtSupplierName.getText(),
                txtPhoneNumber.getText(),
                txtAddress.getText()
        );
        if(isValidCustomerInputDetails(supplier)){
            if(supplierService.add(supplier)){
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
        if(supplierService.delete(txtSupplierID.getText())){
            new Alert(Alert.AlertType.INFORMATION, "Customer remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setTextToValue(supplierService.search(txtSearch.getText()));

    }
    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Supplier supplier = new Supplier(
                txtSupplierID.getText(),
                txtSupplierName.getText(),
                txtPhoneNumber.getText(),
                txtAddress.getText()
        );
        if (isValidCustomerInputDetails(supplier)) {
            if (supplierService.update(supplier)) {
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
        colID.setCellValueFactory(new PropertyValueFactory<>("sup_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("sup_name"));
        colPhoenNumber.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });
        loadTable();
        txtSupplierID.setText(generateCustomerId());
    }

    private void loadTable() {
        ObservableList<Supplier> supplierObservableList = supplierService.getAllSupplier();
        tblSupplier.setItems(supplierObservableList);
    }

    private void setTextToValue(Supplier newValue) {
        txtSupplierID.setText(newValue.getSup_id());
        txtSupplierName.setText(newValue.getSup_name());
        txtPhoneNumber.setText(String.valueOf(newValue.getPhonenumber()));
        txtAddress.setText(newValue.getAddress());
    }

    private String generateCustomerId() {
        String base = "S";
        int integer = Integer.parseInt(supplierService.generateSupplierId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base + (integer + 1);
    }

    private void clearInputFields() {
        txtSupplierID.setText(generateCustomerId());
        txtSupplierName.setText("");
        txtPhoneNumber.setText("");
        txtAddress.setText("");
    }
    private boolean isValidCustomerInputDetails (Supplier supplier){
        if (supplier.getSup_id().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (supplier.getSup_name().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
        if (supplier.getAddress().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Address is required.").show();
            return false;
        }
        return true;
    }
}

