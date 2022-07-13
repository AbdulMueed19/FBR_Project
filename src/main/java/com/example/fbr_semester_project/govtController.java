package com.example.fbr_semester_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class govtController implements Initializable {
    @FXML
    private Label change;
    static double total;

    @FXML
    private TableColumn<TaxPayersData, Double> tablecalculated;

    @FXML
    private TableView<TaxPayersData> tablegovt;

    @FXML
    private TableColumn<TaxPayersData, Integer> tableid;

    @FXML
    private TableColumn<TaxPayersData, Double> tablepaid;

    @FXML
    private TableColumn<TaxPayersData, Date> tableperiod;

    @FXML
    private TableColumn<TaxPayersData, Date> tableperiodend;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String urlt = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";
        ObservableList<TaxPayersData> GovtTableList = FXCollections.observableArrayList();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(urlt, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("select * from Tax_data");
            rs = pst.executeQuery();
            while (rs.next()) {
                GovtTableList.add(new TaxPayersData(rs.getInt("tax_id"), rs.getDouble("calculated_tax"), rs.getDouble("paid_tax"), rs.getString("Period_start"), rs.getString("Period_end"), rs.getDouble("calculated_tax")));
            }
            pst = ((java.sql.Connection) conn).prepareStatement("select SUM(paid_tax) as TotalTax from Tax_data");
            rs = pst.executeQuery();
            if (rs.next()) {
                String sum = rs.getString("TotalTax");
                change.setText(sum);
                total = Double.parseDouble(sum);
                System.out.println(total);
            }
            }
        catch (Exception e) {
            e.printStackTrace();
        }
        tableid.setCellValueFactory(new PropertyValueFactory<>("tax_id"));
        tablecalculated.setCellValueFactory(new PropertyValueFactory<>("calculated_tax"));
        tablepaid.setCellValueFactory(new PropertyValueFactory<>("paid_tax"));
        tableperiod.setCellValueFactory(new PropertyValueFactory<>("period_start"));
        tableperiodend.setCellValueFactory(new PropertyValueFactory<>("period_end"));
        //outstanding_balance.setCellValueFactory(new PropertyValueFactory<>("outstanding_balance"));
        tablegovt.setItems(GovtTableList);
    }

    @FXML
    void onGovtClicked(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("TaxAllocationform.fxml"));
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
