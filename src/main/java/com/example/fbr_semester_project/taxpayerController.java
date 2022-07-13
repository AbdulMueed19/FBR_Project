package com.example.fbr_semester_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class taxpayerController implements Initializable{
    @FXML
    private Label approvalStatus;


    @FXML
    private Button tax_Asset;
    @FXML
    private TableColumn<Assets, String> tableid;

    @FXML
    TableColumn<Assets, String> tablename;

    @FXML
    private TableColumn<Assets, Integer> tabletax;


    @FXML
    private TableColumn<Assets, Double> tableworth;

    @FXML
    private TableView<Assets> taxpayerTable;

    @FXML
    private TextField totaltax;

    @FXML
    private Label taxpayer_id;

    String hehe = HelloController.user;
    public static int userid;


    @FXML
    void onAssetClicked(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Assetsform.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    ObservableList<Assets> AssetList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String urlt = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(urlt, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("select tax_id from sign_in where cnic = ?");
            pst.setString(1, (hehe));
            rs = pst.executeQuery();
            if (rs.next()) {
                String id = rs.getString("tax_id");
                userid = Integer.parseInt(id);
                taxpayer_id.setText(id);
            }
            pst = ((java.sql.Connection) conn).prepareStatement("select fbrstatus from FBR_approval where tax_id = ?");
            pst.setString(1, String.valueOf((userid)));
            rs = pst.executeQuery();
            if (rs.next()) {
                String status = rs.getString("fbrstatus");
                approvalStatus.setText(status);
            }
            pst = ((java.sql.Connection) conn).prepareStatement("select * from Asset where tax_id = ?");
            pst.setInt(1,userid);
            rs = pst.executeQuery();
            while (rs.next()) {
                AssetList.add(new Assets(rs.getInt("asset_id"), rs.getDouble("asset_worth"), rs.getDouble("tax")));
            }
            pst = ((java.sql.Connection) conn).prepareStatement("select SUM(tax) as TotalSum from Asset where tax_id = ?");
            pst.setInt(1,userid);
            rs = pst.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("TotalSum");
                totaltax.setText(sum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tableworth.setCellValueFactory(new PropertyValueFactory<>("worth"));
        tableid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabletax.setCellValueFactory(new PropertyValueFactory<>("tax"));
        taxpayerTable.setItems(AssetList);
    }
    @FXML
    void onPayClicked(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("PayNowForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void onBackClicked(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("Welcomeform.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}



