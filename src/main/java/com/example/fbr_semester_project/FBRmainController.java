package com.example.fbr_semester_project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

public class FBRmainController implements Initializable {
    @FXML
    private TableView<TaxPayersData> table_records;
    @FXML
    private TableColumn<TaxPayersData, Double> calculated_tax;

    @FXML
    private TableColumn<TaxPayersData, Double> outstanding_balance;

    @FXML
    private TableColumn<TaxPayersData, Double> paid_tax;

    @FXML
    private TableColumn<TaxPayersData, Date> period_end;

    @FXML
    private TableColumn<TaxPayersData, Date> period_start;

    @FXML
    private TableColumn<TaxPayersData, Integer> tax_id;
    @FXML
    private TableView<Approval> tableapproval;

    @FXML
    private TableColumn<Approval, String> tableapprovalstatus;

    @FXML
    private TableColumn<Approval, String> tablecomments;

    @FXML
    private TableColumn<Approval, Integer> tabletaxid;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String urlt = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";
        ObservableList<TaxPayersData> TableList = FXCollections.observableArrayList();
        ObservableList<Approval> ApprovalList = FXCollections.observableArrayList();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(urlt, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("select * from Tax_data");
            rs = pst.executeQuery();
            while (rs.next()) {
                TableList.add(new TaxPayersData(rs.getInt("tax_id"), rs.getDouble("calculated_tax"), rs.getDouble("paid_tax"), rs.getString("Period_start"), rs.getString("Period_end"), rs.getDouble("calculated_tax")));
            }}
            catch (Exception e) {
                e.printStackTrace();
            }
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(urlt, userName, Password);
            pst = ((java.sql.Connection) conn).prepareStatement("select * from FBR_approval");
            rs = pst.executeQuery();
            while (rs.next()) {
                ApprovalList.add(new Approval(rs.getInt("tax_id"), rs.getString("comments"), rs.getString("fbrstatus")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        tabletaxid.setCellValueFactory(new PropertyValueFactory<>("FBRid"));
        tableapprovalstatus.setCellValueFactory(new PropertyValueFactory<>("FBRstatus"));
        tablecomments.setCellValueFactory(new PropertyValueFactory<>("FBRComments"));
        tableapproval.setItems(ApprovalList);

        tax_id.setCellValueFactory(new PropertyValueFactory<>("tax_id"));
        calculated_tax.setCellValueFactory(new PropertyValueFactory<>("calculated_tax"));
        paid_tax.setCellValueFactory(new PropertyValueFactory<>("paid_tax"));
        period_start.setCellValueFactory(new PropertyValueFactory<>("period_start"));
        period_end.setCellValueFactory(new PropertyValueFactory<>("period_end"));
        outstanding_balance.setCellValueFactory(new PropertyValueFactory<>("outstanding_balance"));
        table_records.setItems(TableList);
    }

    @FXML
    void onApprovalButtonClicked(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FBRapprovalForm.fxml"));
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