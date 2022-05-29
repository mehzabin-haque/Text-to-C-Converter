package com.example.spl1_t2c;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class If_ElseCommand {

    AlgoCommand ac = new AlgoCommand();
    Type_Var vr = new Type_Var();

    public void If_else(String if1, String cond, String inCond, Label error, TextArea out, Stack<Integer> stack) throws IOException {
        long count = vr.numOfLines("IfElse.txt");
        FileReader fr = new FileReader("IfElse.txt");
        BufferedReader br = new BufferedReader(fr);
        ArrayList<String> ar = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        int fc = 0;
        // out.appendText("\n  int n; \n  cin >> n; \n");
        for (int j = 1; j <= count; j++) {
            while (j < count + 1) {
                String str = br.readLine();
                //System.out.println(str+ "0234953u46934");
                if (str == null) {
                    break;
                }
                if (str.contains("if") || str.contains("else if") || str.contains("else ")) {
                    fc++;
                    ar.add(str);
                }
//                for(int i=0;i<fc;i++){
//                    String str = ar.get(i);
                if(count ==1 ){
                    if(!str.contains("ifif")){
                        error.setText("There is no 'if' statement before\n 'else if' or 'else' statement ");
                    }
                }
                if (str.contains(" ifif ")) {
                    String s1 = ac.after(str, " ifif ");
                    String s = ac.before(s1, "\t@@");
                    String s2 = ac.after(str, "\t@@");
                   // System.out.println(s2 + " @@@@@");
                    if (s == null || s.isEmpty()) {
                        error.setText(" Condition of if and else-if \n statement is needed ");
                    } else {
                        out.appendText(" \n if ( " + s + " ) {" + " \t ");
                        //System.out.println("11111111111111111111111");
                        ac.AllCommand(s2, out, stack);
                        //System.out.println("9999999999999999999999");
                        out.appendText("\n}");

                    }
                  //
                    //  break;
                }  if (str.contains(" elseif1 ")) {

                    String s1 = ac.after(str, " elseif1 ");
                    String s = ac.before(s1, "@@");
                    String s2 = ac.after(str, "@@");
                   // System.out.println(s2 + " iuwetgbaih");
                    if (s == null || s.isEmpty()) {
                        error.setText(" Condition of if and else-if \n statement is needed ");
                    } else {
                        out.appendText(" \n else if ( " + s + " ) {" + "\t ");
                       // System.out.println(s2 + "else if test ");
                        ac.AllCommand(s2, out, stack);
                        out.appendText("\n}");

                    }

                }  if (str.contains(" else1 ")) {
                    String s1 = ac.after(str, "else1 ");
                    String s = ac.before(s1, "@@");
                    String s2 = ac.after(str, "@@");

                    out.appendText(" \n else {" + " \n\t ");
                    if(s!= null || !s.isEmpty()){
                        error.setText("No need of condition in else statement");
                    }
                    ac.AllCommand(s2, out, stack);
                    out.appendText("\n}");
                   // break;
                }
                //count++;
               // break;
            }
        }
    }
}
