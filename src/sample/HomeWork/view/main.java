package sample.HomeWork.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.HomeWork.controller.Controller;


public class main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("portailLoginscreen.fxml")) ;
        loader.setController(new Controller());
        Parent root=loader.load();
        Scene scene=new Scene(root);
        primaryStage.setTitle("my app");
      
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch();
    }

    
}

