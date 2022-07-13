package com.example.fbr_semester_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.sql.*;

public class SignupController implements Initializable {


    @FXML
    private ChoiceBox<String> tax_city;
    private String[] city = {"Lahore","Islamabad","Multan","Karachi","Peshawar","Faisalabad"};
    public void initialize(URL arg0, ResourceBundle arg1) {
        tax_city.getItems().addAll(city);
    }
    @FXML
    private TextField tax_cnic;

    @FXML
    private TextField tax_contact;

    @FXML
    private DatePicker tax_dob;

    @FXML
    private TextField tax_id;

    @FXML
    private TextField tax_name;

    @FXML
    private PasswordField tax_password;

    @FXML
    private Button tax_signup;

    @FXML
    void onbuttonClicked(MouseEvent event) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String usercnic = tax_cnic.getText();
        String userpassword = tax_password.getText();
        String name = tax_name.getText();
        String contact = tax_contact.getText();
        String city = String.valueOf(tax_city.getItems());




        String url = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("insert into sign_in (cnic, password) values (?,?)");
            pst.setString(1, usercnic);
            pst.setString(2, userpassword);
            int rsvar = pst.executeUpdate();
           if (rsvar == 1) {
               Stage stage;
               Scene scene;
               Parent root;
               root = FXMLLoader.load(getClass().getResource("new.fxml"));
               stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               scene = new Scene(root);
               stage.setScene(scene);
               stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed!");
                alert.setHeaderText(null);
                alert.setContentText("Try Again!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

