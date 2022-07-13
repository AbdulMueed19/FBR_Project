package com.example.fbr_semester_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class allocationcontroller implements Initializable {

    @FXML
    private PieChart chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String urlt = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(urlt, userName, Password);
            pst = ((Connection) conn).prepareStatement("Select SUM(paid_tax) as 'paid_tax',Department from Tax_data GROUP BY Department");
            rs = pst.executeQuery();
            while (rs.next()) {
                chartData.add(new PieChart.Data(rs.getString("Department"), rs.getInt("paid_tax")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        chart.setData(chartData);
    }
    @FXML
    void Allocate(MouseEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Allocation Done!");
        alert.setHeaderText(null);
        alert.setContentText("Allocations is made as per the preferences, welcome to Naya Pakistan!");
        alert.showAndWait();
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("GovtFormFinal.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
