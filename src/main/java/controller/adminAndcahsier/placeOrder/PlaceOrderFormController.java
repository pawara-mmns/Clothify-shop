package controller.adminAndcahsier.placeOrder;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PlaceOrderFormController {

    @FXML
    private JFXButton btnAddToCartOnActiion;

    @FXML
    private ComboBox<?> cmbCustomerName;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableView<?> tblProduct;

    @FXML
    private Label txtAdminName;

    @FXML
    private ScrollPane txtListProduct;

    @FXML
    private Label txtOrderID;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label txtTotalPrice;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
