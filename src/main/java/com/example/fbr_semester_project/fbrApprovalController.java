package com.example.fbr_semester_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class fbrApprovalController implements Initializable {

    @FXML
    private TextField comments;

    @FXML
    private TextField fbr_taxid;

    @FXML
    private ChoiceBox<String> status;
    private String[] Status = {"Approved","UnApproved"};
    public void initialize(URL arg0, ResourceBundle arg1) {
        status.getItems().addAll(Status);
    }

    @FXML
    void onUpdateClick(MouseEvent event) {
        String userName = "vision";
        String Password = "mueed";
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        int id = Integer.parseInt(fbr_taxid.getText());
        String FBRstatus = status.getValue();
        String Comments = comments.getText();

        String url = "jdbc:sqlserver://VISION\\SQLEXPRESS:1433;DatabaseName=dbsem_final;encrypt=true;trustServerCertificate=true;";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userName, Password);
            pst = ((Connection) conn).prepareStatement("insert into FBR_approval (tax_id, comments, fbrstatus) values (?,?,?)");
            pst.setInt(1, id);
            pst.setString(2, Comments);
            pst.setString(3, FBRstatus);

            int rsvar = pst.executeUpdate();
            if (rsvar == 1) {
                Stage stage;
                Scene scene;
                Parent root;
                root = FXMLLoader.load(getClass().getResource("FBROfficialform.fxml"));
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
