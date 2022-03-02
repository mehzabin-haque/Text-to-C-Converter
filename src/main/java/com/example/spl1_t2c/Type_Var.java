package com.example.spl1_t2c;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Type_Var extends ToDo1 implements Initializable {

    @FXML
    public TextField print;
    public TextField var_name;
    public TextField value;
    public TextField condition;
    public TextField inCondition;
    public Button start, enter;
    @FXML
    public Label sout;
    public Label error;
    public TextArea out;
    public ComboBox comb1;
    public ComboBox comb2;
    public ComboBox comb3;

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("int", "long", "float", "double", "void");
        comb1.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("if", "else if", "else");
        comb3.setItems(list2);
    }

    public void comBox(ActionEvent event) {
        String s = comb1.getSelectionModel().getSelectedItem().toString();
        //out.setText(s);
    }

    public void Enter() throws IOException {
        String comment = print.getText(); // print part
        //System.out.println(comment);
        String varName = var_name.getText(); // variable name
        String vaLue = value.getText();
        System.out.println(vaLue);
        String cond = condition.getText(); // condition of ifelse
        String inCond = inCondition.getText();  // condition er vitor ja thakbe
        comb2.getItems().add(var_name.getText());
        var_name.clear();
//        comb3.getItems().add(condition.getText());
//        var_name.clear();
        String s = comb1.getSelectionModel().getSelectedItem().toString();
        System.out.println(s); // 1st combo er value
        String varNum = comb2.getSelectionModel().getSelectedItem().toString(); // 2nd combo er value
        String if1 = comb3.getSelectionModel().getSelectedItem().toString();
        System.out.println(comb2.getValue()); // combox varibale print

        out.setText("#include<iostream> " +
                "\nusing namespace std;" +
                "\nint main(){");
//        \n " + s + " " + varName + ";\n" + if1 + "( " + cond + " )" +
//        "{ \n \t" + inCond + ";\n   }" +
//                "\ncout << \"" + comment + "\" << endl;\nreturn 0;" + "\n } \n "

        // varname and vartype

        if (varName != null && s == null) {
            error.setText(" Varibale type is not declared ");
        } else if (s != null && varName == null) {
            error.setText(" Varibale name is not declared ");
        } else if (varName == null && s == null) {
            //out.setText("\n");
            out.appendText(" ");
        } else if (varName != null && s != null) {
            out.appendText("\n" + s + " " + varName + " ;");
            comb2.getItems().add(var_name.getText());
            if (vaLue == null) {
                out.appendText(" ");
            } else if (value != null) {
                if (s == "int") {
                    try {
                        int check1 = Integer.parseInt(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Integer Value");
                    }
                } else if (s == "float") {
                    try {
                        float check = Float.parseFloat(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Float Value");
                    }
                } else if (s == "double") {
                    try {
                        double check = Double.parseDouble(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Double Value");
                    }
                } else if (s == "long") {
                    try {
                        long check = Long.parseLong(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Long Value");
                    }
                } else {
                    System.out.println("void ");
                }

                out.appendText("\n" + varNum + " = " + vaLue + " ;");
            }
        }

        if (if1 == null) {
            out.appendText(" ");
        }
        if (if1 != null) {
            if (if1 != "else") {
                if (cond == null) {
                    error.setText(" Condition of if and else-if statement is needed ");
                } else if (cond != null) {
                    out.appendText(" \n " + if1 + " ( " + cond + " ) {");
                }
            }
            else if (if1 == "else") {
                out.appendText(" else { ");
            }

            if (inCond == null) {
                out.appendText("\n");
            }
            else if (inCond != null) {
                for(int i=0;i<20;i++){
                    if (inCond.subSequence(i,i+4) == "print") {
                        out.appendText("\n \t cout << \" ");
                        if (inCond.substring(i) == varName) {
                            out.appendText(varName + " ;" + "\n\t}");
                        }
                    }
                }
            }
        }

        if(comment ==null) {
        out.appendText(" ");
        }
        if(comment !=null) {
        out.appendText("\n cout << \" " + comment + " \" ;");
        }

        if(comment == null && varName == null && s == null && if1 == null){
            out.appendText("");
        }

        out.appendText("\n return 0; \n}");
    }
}

