package com.example.fbr_semester_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class welcomecontroller {

    @FXML
    private Button fbrofficialform;

    @FXML
    private Button govtviewform;

    @FXML
    private Button taxpayerform;

    @FXML
    void ongettingClicked(MouseEvent event) throws IOException {
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("new.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ongettingClickedfbr(MouseEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("FBRLoginForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ongettingClickedgovt(MouseEvent event) throws IOException{
        Stage stage;
        Scene scene;
        Parent root;
        root = FXMLLoader.load(getClass().getResource("GovtLoginForm.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
