/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calc_Socket;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage; 

/**
 *
 * @author samue
 */
public class MainApp extends Application{
    private Stage primaryStage;
    private MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Calc_Socket");

        inizializza();
        
    }
    
     public static void main(String[] args) {
        launch(args);
    }

    private void inizializza() {
        
        try {
            
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("GUI.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            
            mainController=loader.getController();
            mainController.setMainApp(this);
            
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
