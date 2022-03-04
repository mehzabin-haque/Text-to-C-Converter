package com.example.spl1_t2c;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Type_Var extends ToDo1 implements Initializable {

    @FXML
    public TextField print = new TextField("");
    public TextField var_name = new TextField("");
    public TextField value = new TextField("");
    public TextField condition = new TextField("");
    public TextField inCondition = new TextField("");
    public Button start, enter;
    @FXML
    public Label sout;
    public Label error;
    public TextArea out;
    public ComboBox <String> comb1 ;
    public ComboBox <String> comb2 ;
    public ComboBox <String> comb3;

    boolean substring1(String line) {
        String s="print";
            if(s==line)
                return true;
        return true;
    }

//    boolean substring2(String line) {
//        String s=;
//        if(s==line)
//            return true;
//        return true;
//    }

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("int", "long", "float", "double", "void");
        comb1.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("if", "else if", "else");
        comb3.setItems(list2);
        ObservableList<String> list3 = FXCollections.emptyObservableList();
        comb2.setItems(list3);
    }

    public String removeDuplicates(String input){
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            if(!result.contains(String.valueOf(input.charAt(i)))) {
                result += String.valueOf(input.charAt(i));
            }
        }
        return result;
    }

    public void Enter() throws IOException {
        //System.out.println(comb2.getItems());
        String comment = print.getText(); // print part
        //System.out.println(comment);
        String varName = var_name.getText(); // variable name
        String s = comb1.getSelectionModel().getSelectedItem();
        //System.out.println(s); // 1st combo er value

        ObservableList<String> list = FXCollections.observableArrayList(comb2.getItems());
        list.add(var_name.getText());
        boolean b = varName.isEmpty();
        if(var_name != null && !b){
           // removeDuplicates(varName);
            comb2.setItems(list);
        }

        String vaLue = value.getText();
        String varNum = comb2.getSelectionModel().getSelectedItem(); // 2nd combo er value
        String if1 = comb3.getSelectionModel().getSelectedItem();
        //System.out.println(vaLue);
        String cond = condition.getText(); // condition of ifelse
        String inCond = inCondition.getText();  // condition er vitor ja thakbe
        //var_name.clear();
//        comb3.getItems().add(condition.getText());
//        var_name.clear();

        //System.out.println(comb2.getValue()); // combox varibale print

        out.setText("#include<iostream> " +
                "\nusing namespace std;" +
                "\nint main(){");

        if (varName != null && s == null) {
            error.setText(" Variable type is not declared ");
        }

        else if (s != null && varName == null) {
            error.setText(" Variable name is not declared ");
        }

        else if (varName == null && s == null) {
            //out.setText("\n");
            out.appendText("");
        }

        else if (varName != null && s != null ) {
            out.appendText("\n " + s + " " + varName + " ;");
            //comb2.getItems().add(var_name.getText());
//            if(varNum == null){
//                out.appendText("");
//            }
            if (value != null) {
                if (s == "int") {
                    try {
                         int check = Integer.parseInt(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Integer Value");
                    }
                }
                else if (s == "float") {
                    try {
                       float check = Float.parseFloat(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Float Value");
                    }
                }
                else if (s == "double") {
                    try {
                          double d = Double.parseDouble(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Double Value");
                    }
                }
                else if (s == "long") {
                    try {
                        long l = Long.parseLong(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Long Value");
                    }
                }
                else {
                    System.out.println("void ");
                }

                out.appendText("\n" + varNum + " = " + vaLue + " ;");
            }
        }

        if (if1 != null) {
            if (if1 != "else") {

                if (cond.isEmpty()) {
                    error.setText(" Condition of if and else-if statement is needed ");

                }
                else if (cond != null ) {
                    if(if1 == "if"){
                        out.appendText(" \n " + if1 + " ( " + cond + " ) {");
                    }
                    if(if1 == "else if"){
                        out.appendText(" \n " + if1 + " ( " + cond + " ) {");
                    }

//                    comb3.setOnAction(e -> out.appendText(" \n " + if1 + " ( " + cond + " ) {"));
//                    comb3.setOnAction(e -> out.appendText(" \n " + if1 + " ( " + cond + " ) {"));

                   //out.appendText(" \n " + if1 + " ( " + cond + " ) {" + "\n" );

                }
            }
            else if (if1 == "else") {
                out.appendText(" else { ");
            }

//            if (inCond == null) {
//                out.appendText("\n");
//            }
            if (inCond != null) {
                    if (substring1(inCond)) {
                        out.appendText("\n \t cout << \" " + varName + "\" ;" + "\n\t}");
//                        if (substring2(varName)) {
//                            out.appendText(inCond.substring(6) + " ;" + "\n\t}");
//                        }
                    }
                    else{
                        out.appendText("\n \t " + inCond + ";" + "\n\t} ");
                    }
                }
            }

        boolean c = comment.isEmpty();
        if(comment == null && c) {
            out.appendText("");
        }
        if(comment != null && !c) {
            out.appendText("\n cout << \" " + comment + " \" ;");
        }

        if(comment == null && varName == null && s == null && if1 == null){
            out.appendText("");
        }

        out.appendText("\n return 0; \n}");
    }

}

