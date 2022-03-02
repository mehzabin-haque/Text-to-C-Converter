package com.example.spl1_t2c;

import java.io.*;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ToDo1{
    @FXML
    public TextField print;
    public Button start,enter;
    public Label sout;

    //String print1;
    @FXML
    public void print1() throws IOException {
        System.out.println("888888888");
        //print1 = print.getText();
        StringBuilder sb = new StringBuilder();
        sb.append(print.getText().toString()+"\n");
        File f = new File("print.txt");
        f.createNewFile();
        try (FileWriter w = new FileWriter(f);){
            w.write(sb.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void Start(){
        sout.setText("#include<conio.h> " +
                "\nusing namespace std;" +
                "\nint main(){ \n ");
    }

}