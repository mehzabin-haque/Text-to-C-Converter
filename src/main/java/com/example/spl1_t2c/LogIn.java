package com.example.spl1_t2c;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class LogIn extends SwitchScene{

    @FXML
    private Stage stage;
    @FXML
    private Scene scene;
    @FXML
    private Parent root;

    public void switchToRegistration(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("Register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("Page1.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public Label username1,password,Sign_in,login_success;
    @FXML
    public Button log_in;
    @FXML
    public TextField user_name,pass_word;
    @FXML
    String user, pass;

    public void onClick_btn_login(ActionEvent e) {

        user = user_name.getText();
        pass = pass_word.getText();
        // System.out.println(user + " " + pass);
        boolean grantAccess = false;

        File f = new File("login.txt");
        try {
            Scanner read = new Scanner(f);

            while (read.hasNextLine()) {
                String S = read.nextLine();
                if (S.equals(user)) {
                    // System.out.println("user name match");
                    if (read.nextLine().equals(pass)) {
                        //System.out.println("password match");
                        grantAccess = true;
                        break;
                    }
                }
            }
            if (grantAccess) {
                login_success.setText("Login Successful.");
                switchToPage1(e);

            } else {
                login_success.setText("Invalid information. Try again.");
            }
            read.close();

        } catch (FileNotFoundException m) {

            m.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
