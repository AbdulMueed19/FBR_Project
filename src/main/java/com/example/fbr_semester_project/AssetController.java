package com.example.fbr_semester_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AssetController implements Initializable {

    @FXML
    private Pane addAsset;

    @FXML
    private DatePicker asset_date;

    @FXML
    private Label asset_id;

    @FXML
    private TextField asset_name;

    @FXML
    private ChoiceBox<String> asset_type;
    private String[] type = {"Residential Asset","Commercial Asset","Agricultural Land","Vehicle","Annual Salary","Avg. Monthly Salary","Others"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {
        asset_type.getItems().addAll(type);
    }

    @FXML
    private TextField asset_worth;

    int temp = taxpayerController.userid;

    @FXML
    void onAddAssetClicked(MouseEvent event) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String name = asset_name.getText();
        String type = (String) asset_type.getValue();
        String date = String.valueOf(asset_date.getValue());
        Double worth = Double.parseDouble(asset_worth.getText());



        String url = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, Password);
            pst = ((Connection) conn).prepareStatement("insert into Asset (asset_name, asset_type, asset_date, asset_worth,tax_id) values (?,?,?,?,?)");
            pst.setString(1, name);
            pst.setString(2, type);
            pst.setString(3, date);
            pst.setDouble(4, worth);
            pst.setInt(5,temp);


            int rsasset = pst.executeUpdate();
            if (rsasset == 1) {
                Stage stage;
                Scene scene;
                Parent root;
                root = FXMLLoader.load(getClass().getResource("TaxPayer_window.fxml"));
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


