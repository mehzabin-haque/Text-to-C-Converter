package com.example.spl1_t2c;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
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
    public TextField operation = new TextField("");
    public TextField loop_command = new TextField("");
    public Button start, enter;
    @FXML
    public Label sout;
    public Label error;
    public TextArea out;
    public Button loop;
    public ComboBox <String> comb1 ;
    public ComboBox <String> comb2 ;
    public ComboBox <String> comb3;
    public ComboBox <String> comb4 ;

    boolean substring1(String line) {
        String s="print";
            if(s==line)
                return true;
        return true;
    }

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("int", "char", "float", "double", "void");
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

    public void Enter() throws Exception {
        File f1 = new File("main.txt");
        f1.createNewFile();

        String comment = print.getText(); // print part

        String varName = var_name.getText(); // variable name
        String s = comb1.getSelectionModel().getSelectedItem();

        ObservableList<String> list = FXCollections.observableArrayList(comb2.getItems());
        list.add(var_name.getText());
        boolean b = varName.isEmpty();
        if(var_name != null && !b){
           // removeDuplicates(varName);
            comb2.setItems(list);
        }

        ObservableList<String> list2 = FXCollections.observableArrayList(comb4.getItems());
        list2.add(var_name.getText());
        if(var_name != null && !b){
            // removeDuplicates(varName);
            comb4.setItems(list);
        }

        String vaLue = value.getText();
        boolean val = vaLue.isEmpty();
        String varNum = comb2.getSelectionModel().getSelectedItem(); // 2nd combo er value
        String if1 = comb3.getSelectionModel().getSelectedItem();
        String all = comb4.getSelectionModel().getSelectedItem();
        String cond = condition.getText(); // condition of ifelse
        String inCond = inCondition.getText();  // condition er vitor ja thakbe
        String opera = operation.getText();
        String comm = loop_command.getText();
        out.setText("#include<iostream> " +
                "\nusing namespace std;" +
                "\nint main(){");

        FileWriter myWriter = new FileWriter("main.txt");

        myWriter.write(s + " " + varName);
        myWriter.append("\n" + varNum + " " + vaLue);
        myWriter.append("\n" + all + " " + opera );
        myWriter.append("\n" + if1 + " " + cond + "\n\t"+inCond);
        myWriter.append("\n" + loop + " " + comm);

        myWriter.close();

        if (varName != null && s == null) {
            //comb1.setOnAction(e -> error.setText(" Variable type is not declared "));
           // error.setText(" Variable type is not declared ");
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
            if (value != null && !val) {
                if (s == "int") {
                    try {
                         int check = Integer.parseInt(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not an Integer Value.\nUse values like '5' not " + vaLue );
                    }
                }
                else if (s == "float") {
                    try {
                       float check = Float.parseFloat(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Float Value.\nUse values like '5.0' not " + vaLue);
                    }
                }
                else if (s == "double") {
                    try {
                          double d = Double.parseDouble(vaLue);
                    } catch (NumberFormatException nfe) {
                        error.setText("It's not a Double Value.\nUse values like '5.000' not " + vaLue);
                    }
                }
                else if (s == "char") {

                    for(int i=0; i < s.length(); i++) {
                        Boolean flag = Character.isDigit(s.charAt(i));
                        if(flag) {
                            //System.out.println("'"+ s.charAt(i)+"' is a number");
                            error.setText("It's not a character \n It's a number");
                        }
                    }

                }
                else {
                    System.out.println("void ");
                }

                if(s == "char"){
                    out.appendText("\n" + varNum + " = '" + vaLue + "' ;");
                }
                else{
                    out.appendText("\n" + varNum + " = " + vaLue + " ;");
                }
            }
        }

        If_else(if1,cond,inCond, varName);
        AllCommand(comment);

        boolean c = comment.isEmpty();
        if(comment == null && c) {
            out.appendText("");
        }
//        if(comment != null && !c) {
//            out.appendText("\n cout << \" " + comment + " \" ;");
//        }

        if(comment == null && varName == null && s == null && if1 == null){
            out.appendText("");
        }

        try {
            File myObj = new File("main.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        out.appendText("\n return 0; \n}");
    }

    public void If_else(String if1,String cond,String inCond,String varName) throws Exception{
        if (if1 != null) {

            if (if1 != "else") {

                if (cond.isEmpty()) {
                    error.setText(" Condition of if and else-if \n statement is needed ");

                } else if (cond != null && !cond.isEmpty()) {
                    error.setText("");
                    if (if1 == "if") {
                        out.appendText(" \n " + if1 + " ( " + cond + " ) {");
                    }
                    if (if1 == "else if") {
                        out.appendText(" \n " + if1 + " ( " + cond + " ) {");
                    }

                }
            } else if (if1 == "else") {
                out.appendText(" \nelse { ");
            }

            if (inCond == null) {
                out.appendText("\n");
            }
            if (inCond != null) {
                if (substring1(inCond)) {
                    out.appendText("\n \t cout <<  " + varName + " ;" + "\n\t}");
//                        if (substring2(varName)) {
//                            out.appendText(inCond.substring(6) + " ;" + "\n\t}");
//                        }
                } else {
                    out.appendText("\n \t " + inCond + ";" + "\n\t} ");
                }
            }
        }
    }

    public void AllLoop(ActionEvent event) throws IOException {
        String inComm = loop_command.getText();
        File file1 = new File("Loop.txt");
        file1.createNewFile();
        FileWriter myWriter = new FileWriter("loop.txt");

        myWriter.write("for");
        myWriter.append("\n"+inComm);
    }

    public void AllCommand(String cmd)throws IOException{

        try {
                FileReader fr = new FileReader("Loop.txt");
                FileWriter fw = new FileWriter("main.txt");
                String str = "";

                int i;
                while ((i = fr.read()) != -1) {
                    
                    str += (char)i;
                }

                System.out.println(str);

                fw.append("\n"+str);
                fr.close();
                fw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

}

