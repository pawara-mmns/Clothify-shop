package controller.admin.manageProduct;

import dto.Customer;
import dto.Product;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.CustomerService;
import service.custom.ProducrService;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageProductFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colqty;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<Product> tblProduct;

    @FXML
    private Label txtAdminName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtProductID;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearch;

    private final ProducrService productService = ServiceFactory.getInstance().getServiceType(ServiceType.PRODUCT);

    @FXML
    void btnAddProductOnAction(ActionEvent event) {
        Product product = new Product(
                txtProductID.getText(),
                txtProductName.getText(),
                txtQty.getText(),
                txtPrice.getText()
        );
        if(isValidCustomerInputDetails(product)){
            if(productService.add(product)){
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
        if(productService.delete(txtProductID.getText())){
            new Alert(Alert.AlertType.INFORMATION, "Customer remove successful !").show();
        } else {
            new Alert(Alert.AlertType.ERROR).show();
        }
        loadTable();
        clearInputFields();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        setTextToValue(productService.search(txtSearch.getText()));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        Product product = new Product(
                txtProductID.getText(),
                txtProductName.getText(),
                txtQty.getText(),
                txtPrice.getText()
        );
        if (isValidCustomerInputDetails(product)) {
            if (productService.update(product)) {
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
        colID.setCellValueFactory(new PropertyValueFactory<>("pro_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("pro_name"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tblProduct.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null) {
                setTextToValue(newValue);
            }
        });
        loadTable();
        txtProductID.setText(generateProductId());
    }

    private void loadTable() {
        ObservableList<Product> customerObservableList = productService.getAllProducts();
        tblProduct.setItems(customerObservableList);
    }

    private void setTextToValue(Product newValue) {
        txtProductID.setText(newValue.getPro_id());
        txtProductName.setText(newValue.getPro_name());
        txtQty.setText(String.valueOf(newValue.getQty()));
        txtPrice.setText(newValue.getPrice());
    }

    private String generateProductId() {
        String base = "P";
        int integer = Integer.parseInt(productService.generateProductId());
        if (integer < 10) {
            base += "00";
        } else if (integer < 100) {
            base += "0";
        }
        return base + (integer + 1);
    }

    private void clearInputFields() {
        txtProductID.setText(generateProductId());
        txtProductName.setText("");
        txtQty.setText("");
        txtPrice.setText("");
    }
    private boolean isValidCustomerInputDetails (Product product){
        if (product.getPro_id().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "CustomerId is required !").show();
            return false;
        }
        if (product.getPro_name().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Name is required.").show();
            return false;
        }
        if (product.getQty().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Qty is required.").show();
            return false;
        }
        if (product.getPrice().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Price is required.").show();
            return false;
        }
        return true;
    }
}
