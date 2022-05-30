package com.example.spl1_t2c;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.Scanner;
import java.util.Stack;

public class Operand {

    public String replace(String op){

        for(int i=0;i<op.length();i++){
            if(i+2<op.length()){
                System.out.println(op);
                if(op.equals("E")){
                    break;
                }
                if(op.charAt(i) == 'E' && op.charAt(i+1) == '+' && op.charAt(i+2) == 'E'){
                    op = op.replace("E+E","E");
                }
                else if(op.charAt(i) == 'E' && op.charAt(i+1) == '-' && op.charAt(i+2) == 'E'){
                    op = op.replace("E-E","E");
                }
                else if(op.charAt(i) == 'E' && op.charAt(i+1) == '*' && op.charAt(i+2) == 'E'){
                    op = op.replace("E*E","E");
                }
                else if(op.charAt(i) == 'E' && op.charAt(i+1) == '/' && op.charAt(i+2) == 'E'){
                    op = op.replace("E/E","E");
                }
                else if(op.charAt(i) == 'E' && op.charAt(i+1) == '%' && op.charAt(i+2) == 'E'){
                    op =  op.replace("E%E","E");
                }
                else if(op.charAt(i) == '(' && op.charAt(i+1) == 'E' && op.charAt(i+2) == ')'){
                    op =  op.replace("(E)","E");
                }
            }
        }

        return op;
    }

    public void Operand_Checking(String op, String var_all, Label error, TextArea out){
        //s1 = cfg(s);
        // E = a+b,a-b,a*b,a/b,a%b,s
        String s = op;
        String s1 = "E";
        System.out.println(var_all);
        for(int i=0;i<op.length();i++){
            for(int j=0;j<var_all.length();j++){
                if(op.charAt(i) != var_all.charAt(j) && ( op.charAt(j) != '+' || op.charAt(j) != '-'
                        || op.charAt(j) != '*' || op.charAt(j) != '/' || op.charAt(j) != '%'
                        || op.charAt(j) != '('|| op.charAt(j) != ')')){
                   // System.out.println(op.charAt(i));
                   // System.out.println(var_all.charAt(j));
                   // error.setText(" Undeclared variable has been used");
                   // System.out.println("-----------");
                    break;
                }
                //System.out.println("00000000");
            }
        }

        for(int i=0;i<op.length();i++){
            if(op.charAt(i)=='+' || op.charAt(i)=='-' || op.charAt(i)=='*' || op.charAt(i)=='/' ||
                    op.charAt(i)=='%' || op.charAt(i)=='(' || op.charAt(i)==')'){
            }
            else{
                op = op.replace(op.charAt(i),'E');
            }
            //System.out.println("----------");
//            for(int j=0;j<var_all.length();j++){
//                if(op.charAt(i) == var_all.charAt(j)){
//                    //System.out.println(op.replace(op.charAt(i),'E'));
//                   op = op.replace(op.charAt(i),'E');
//                }
//            }
        }
        //System.out.println("++++++++++++++++");

        while(op.length() > 1){
            String prev = op;
            op = replace(op);
            if(prev == op){
                break;
            }
        }

        if(op.equals("E")){
            //out.appendText(" "+ s + " ;");

           // System.out.println("Alright");
        }

        else if(op.isEmpty()){
            System.out.println("Nothing to do");
        }

        else
        {
            error.setText("Invalid Operation !!!");

        }

            System.out.println(op);
        }
}
