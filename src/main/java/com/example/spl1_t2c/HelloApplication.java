package com.example.spl1_t2c;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 550);
            Image myImage = new Image(getClass().getResourceAsStream("Welcome.jpg"));
            stage.setTitle("T2C++");
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}