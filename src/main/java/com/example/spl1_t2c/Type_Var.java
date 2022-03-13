package com.example.spl1_t2c;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public ComboBox <String> comb1 ;
    public ComboBox <String> comb2 ;
    public ComboBox <String> comb3;
    public ComboBox <String> comb4 ;
    public ComboBox <String> comb5 ;

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
        ObservableList<String> list5 = FXCollections.observableArrayList("for", "while", "do-while");
        comb5.setItems(list5);
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

    File file1 = new File("If-else.txt");

    public void Enter() throws Exception {
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
        String loop = comb5.getSelectionModel().getSelectedItem();
        //System.out.println(vaLue);
        String cond = condition.getText(); // condition of ifelse
        String inCond = inCondition.getText();  // condition er vitor ja thakbe
        out.setText("#include<iostream> " +
                "\nusing namespace std;" +
                "\nint main(){");

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

        if(loop != null && !loop.isEmpty()){
            Loop(loop);
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

    public void Loop(String loop) throws IOException {
        File file1 = new File("Loop.txt");
        file1.createNewFile();
        StringBuffer sb = null;
        try (FileWriter fw = new FileWriter("Loop.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.append(comb5.getValue()).append("\n");

            FileReader fr = new FileReader(file1);   //reads the file
            BufferedReader br = new BufferedReader(fr);  //creates a buffering character input stream
            sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            //  System.out.println(sb.toString()); file output
        } catch (IOException e) {
            System.out.println(e);
        }

//            try {
//                for (int i = 0; i < sb.length(); i++) {
//                    //System.out.println(sb.charAt(i));
//                    if (sb.subSequence(i, (i + 2)) == "for") {
//                        out.appendText(" \n for( int i=0; i<n; i++){\n");
//                    }
//
//                    if (sb.subSequence(i, (i + 4)) == "while") {
//                        out.appendText(" \n while(i<n){\n\t  i++; \n\t}");
//                    }
//
//                    if (sb.subSequence(i, (i + 7)) == "do-while") {
//                        out.appendText("\n do{ \n\n  }while(i<n);");
//                    }
//
//                }
//            }catch (StringIndexOutOfBoundsException e){
//                System.out.println(e);
//            }

        if(loop != null && !loop.isEmpty()){
            if(loop == "for"){
                out.appendText(" \n for( int i=0; i<n; i++){\n\n}");
            }

            else if(loop == "while"){
                out.appendText(" \n while(i<n){\n\t  i++; \n\t}");
            }
            else{
                out.appendText("\n do{ \n\ni++;  }while(i<n);");
            }
        }
    }

}

