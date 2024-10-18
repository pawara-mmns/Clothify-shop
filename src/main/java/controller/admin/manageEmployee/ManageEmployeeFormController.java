package controller.admin.manageEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManageEmployeeFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhoenNumber;

    @FXML
    private TableView<?> tblCustomer;

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

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
