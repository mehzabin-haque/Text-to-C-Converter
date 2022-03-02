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
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Register extends SwitchScene{
    @FXML
    public Button btn_register,btn_switch1,btn_switch2,go_to_next;
    @FXML
    public TextField username;
    public TextField email_id,contact_no;
    @FXML
    public Label label;
    @FXML
    public PasswordField passwordField;
    @FXML
    public Stage stage;
    public Scene scene;
    public Parent root;
    @FXML
    public void onClick_btn_register(ActionEvent e) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(username.getText().toString()+"\n");
        sb.append(passwordField.getText().toString());
        label.setText("Registration successful.");
        File file = new File("login.txt");
        //file.createNewFile();
        try {
            FileWriter w = new FileWriter(file);
            w.write(sb.toString());
            w.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        File f = new File("information.txt");
        try{
            FileWriter w= new FileWriter(f);
            w.write(email_id.getText()+"\n");
            // w.append(contact_no.getText()+"\n");
            w.close();
        }
        catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
}