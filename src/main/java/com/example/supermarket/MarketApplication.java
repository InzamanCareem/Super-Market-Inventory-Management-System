package com.example.supermarket;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MarketApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarketApplication.class.getResource("market.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("John's Super Market");
        stage.setWidth(1150);
        stage.setHeight(700);
        stage.setScene(scene);
        stage.show();

        // Sets an event handler when closing the application
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                exit(stage);
            }
        });
    }

    public void exit(Stage stage){
        ButtonType confirmExit = AlertManager.showConfirmation("Exit", "You're about to exit the application", "Do you really want to exit?");
        if (confirmExit == ButtonType.OK){
            stage.close();
        }
    }
}