package controller.loginform;

import db.DBConnection;
import dto.UserAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAdminFormController {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnBackButtonOnAction(MouseEvent event) {

    }

    @FXML
    void btnLoginAdminOnAction(ActionEvent event) {
        String SQL = "SELECT * FROM admin_login WHERE email = ?";

        try {
            Connection connection = DBConnection.getInstance().getConnection();

            PreparedStatement psTm = connection.prepareStatement(SQL);

                psTm.setString(1, txtEmail.getText());
                ResultSet rs = psTm.executeQuery();

                UserAdmin userAdmin = null;
                if (rs.next()) {
                    userAdmin = new UserAdmin(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4)
                    );
                }

                System.out.println(userAdmin);
                if (userAdmin != null &&
                        txtEmail.getText().equals(userAdmin.getEmail()) &&
                        txtPassword.getText().equals(userAdmin.getPassword())) {

                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_dash_board_form.fxml"))));
                    stage.show();

                } else {
                    new Alert(Alert.AlertType.ERROR, "Login Error: Invalid email or password").showAndWait();
                }

            } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Database Error: " + e.getMessage()).showAndWait();
        }
    }
    @FXML
    void btnPasswordResetOnAction(ActionEvent event) {









    }
}
