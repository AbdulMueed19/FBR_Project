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

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class PayNowController implements Initializable {
    @FXML
    private ChoiceBox<String> department;
    private String[] departments = {"Education","Health","Defence","Infrastructure","Subsidies","Relief Packages"};
    public void initialize(URL arg0, ResourceBundle arg1) {department.getItems().addAll(departments);
    }

    @FXML
    private TextField calculated_Tax;

    @FXML
    private TextField paid_Tax;


    @FXML
    private DatePicker period_End;

    @FXML
    private DatePicker period_Start;


    int temp = taxpayerController.userid;

    @FXML
    void onPayTaxClicked(MouseEvent event) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        double calculated_tax = Double.parseDouble(calculated_Tax.getText());
        double paid_tax = Double.parseDouble(paid_Tax.getText());
        String period_start = String.valueOf(period_Start.getValue());
        String period_end = String.valueOf(period_End.getValue());
        String Department = department.getValue();


        String url = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, Password);
            pst = ((Connection) conn).prepareStatement("insert into Tax_data (tax_id, calculated_tax, paid_tax, Period_start, Period_end, Department) values (?,?,?,?,?,?)");
            pst.setInt(1, temp);
            pst.setDouble(2, calculated_tax);
            pst.setDouble(3, paid_tax);
            pst.setString(4, period_start);
            pst.setString(5, period_end);
            pst.setString(6,Department);


            int rsasset = pst.executeUpdate();
            if (rsasset == 1) {
                pst = ((Connection) conn).prepareStatement("DELETE FROM Asset WHERE tax_id = ?");
                pst.setInt(1,temp);
                int rsupdate = pst.executeUpdate();
                while (rsupdate == 1)
                {
                    Stage stage;
                    Scene scene;
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("TaxPayer_window.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();}}
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Failed!");
                alert.setHeaderText(null);
                alert.setContentText("Try Again!");

                alert.showAndWait();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}



