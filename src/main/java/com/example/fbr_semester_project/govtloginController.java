package com.example.fbr_semester_project;

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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class govtloginController {

    @FXML
    private TextField govtcnic;

    @FXML
    private Button govtlogin;

    @FXML
    private PasswordField govtpassword;

    @FXML
    void onlogingotclicked(MouseEvent event) { String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String user = govtcnic.getText();
        String pass = govtpassword.getText();
        String url = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("select * from GOV_data where cnic = ? and password = ?");
            pst.setString(1, user);
            pst.setString(2, pass);

            rs = pst.executeQuery();
            if (rs.next()) {
                Stage stage = new Stage();
                Scene scene;
                Parent root;
                root = FXMLLoader.load(getClass().getResource("GovtFormFinal.fxml"));
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

    }

