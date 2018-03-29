package sample.HomeWork.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sample.HomeWork.view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void gotoOther(ActionEvent event) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource("/sample/HomeWork/view/otherTab.fxml"));
        Main.stage.setScene(new Scene(pane));

    }
    @FXML
    private void gotoManage(ActionEvent event) throws IOException {

        Parent pane = FXMLLoader.load(getClass().getResource("/sample/HomeWork/view/portailLoginscreen.fxml"));
        Main.stage.getScene().setRoot(pane);
    }
}
