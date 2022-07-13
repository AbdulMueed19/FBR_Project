package com.example.fbr_semester_project;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;


public class HelloController {

    @FXML
    private Button signin_button;

    @FXML
    private TextField tax_cnic = new TextField();


    @FXML
    private PasswordField tax_password;

    public TextField getTax_cnic() {
        return tax_cnic;
    }
    public static String user;
    @FXML
    public void onClicked(MouseEvent event) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        user = tax_cnic.getText();
        String pass = tax_password.getText();
        String url = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("select * from sign_in where cnic = ? and password = ?");
            pst.setString(1, user);
            pst.setString(2, pass);

            rs = pst.executeQuery();
            if (rs.next()) {
                Stage stage = new Stage();
                Scene scene;
                Parent root;

        /*Parent root = FXMLLoader.load(HelloApplication.class.getResource("Signup_form.fxml"));
        Stage stage2 = new Stage();
        Scene scene = new Scene(root, 1280, 720);
        stage2.setScene(scene);
        stage2.setTitle("Sign up!");
        stage2.setScene(scene);
        stage2.show();*/
                root = FXMLLoader.load(getClass().getResource("TaxPayer_window.fxml"));
                stage.setTitle(user);
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();


            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Credential Authentication");
                alert.setHeaderText(null);
                alert.setContentText("Credentials not matched!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onSignUp(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Signup_form.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
