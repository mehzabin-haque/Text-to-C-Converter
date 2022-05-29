package com.example.spl1_t2c;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.util.Stack;

public class AlgoCommand {

    //Stack<Integer> stack = new Stack<>();
    public void bracketPrint(TextArea out,int space,Stack<Integer> stack){

        stack.push(space);
        if(stack.size()>=2) {
            if(stack.peek() < stack.elementAt(stack.size()-2)){
                int temp = stack.peek();
                stack.pop();
                while(!stack.empty() && (temp < stack.elementAt(stack.size()-2)) ){
                    stack.pop();
                    out.appendText("\n");
                    out.appendText("}");
                }
            }
        }

        System.out.println(stack);
    }

    static String before(String value, String a) {

        int pos1 = value.indexOf(a);
        if (pos1 == -1) {
            return "";
        }

        return value.substring(0, pos1);
    }

    static String between(String value, String a, String b) {

        int pos1 = value.indexOf(a);
        if (pos1 == -1) {
            return "";
        }

        int pos2 = value.lastIndexOf(b);
        if (pos2 == -1) {
            return "";
        }

        int adjustedPosA = pos1 + a.length();
        if (adjustedPosA >= pos2) {
            return "";
        }

        return value.substring(adjustedPosA, pos2);
    }


    static String after(String value, String a) {

        int pos1 = value.lastIndexOf(a);
        if (pos1 == -1) {
            return "";
        }

        int adjustedPosA = pos1 + a.length();
        if (adjustedPosA >= value.length()) {
            return "";
        }

        return value.substring(adjustedPosA);
    }

    public int countSpace(String line) {
        int c=0;
        for(int i=0; i<line.length(); i++)
        {
            if(line.charAt(i)==' '){
                c++;
            }
            else if(line.charAt(i)=='\t'){
                c += 4 ;
            }
            else{
                return c;
            }
        }

        return c;
    }

    public int AllCommand(String cmd, TextArea out,Stack<Integer> stack) {
        int temp = stack.size();
        int c = countSpace(cmd);
        KMPStringMatching kmp = new KMPStringMatching();
        //stack.push(c);
        System.out.println(c);
        for(int j=0;j<countSpace(cmd);j++){
            out.appendText(" ");
        }

        int fr = 0;
        int ie = 0;
        int a1,a2,a3,a4,a5,a6,a7,a8,a9,a10;
        //System.out.println(cmd.length()+ " =============== ");
        try {
            for (int i = 0; i < cmd.length(); i++) {
                if (i + 2 < cmd.length()) {

                    if (kmp.KMPSearch("for",cmd)) {
                        fr++;
                        String s = before(cmd,"for ");
                        out.appendText("\n" + s);
                        a1 = countSpace(s);
                        bracketPrint(out,a1,stack);
                        String s1 = between(cmd,"for ","=");
                        out.appendText("for (int " + s1);

                        if(kmp.KMPSearch(" to",cmd)){
                            String s2 = before(cmd," to");
                            //System.out.println(s2 + "@@@@@@@@@");
                            String s3 = after(s2,"=");
                            //System.out.println(s3 + "!!!!!!!!!!!");
                            out.appendText(" = " + s3 + " ; ");

                            String s4 = after(cmd," to ");
                            out.appendText(s1 + "<= " +s4 + " ; " + s1+ "++){");
                        }

                        else if(kmp.KMPSearch("downto",cmd)){
                            String s2 = before(cmd,"downto");
                            // System.out.println(s2 + "@@@@@@@@@");
                            String s3 = after(s2,"=");
                            //System.out.println(s3 + "!!!!!!!!!!!");
                            out.appendText(" = " + s3 + " ; ");

                            String s4 = after(cmd," downto ");
                            out.appendText(s1 + " >= " +s4 + " ; " + s1 + "--){");
                            break;
                        }
                        break;
                    }    //String s1 = "to";

                    else if (cmd.contains("if")) {
                        ie++;
                        String s1 = before(cmd,"if ");
                        out.appendText("\n" + s1);
                        a2 = countSpace(s1);
                        bracketPrint(out,a2,stack);
                        String s = after(cmd,"if ");
                        out.appendText("if( " + s);
                        out.appendText(" ){\n\n\t");
                        break;
                    }
                    else if (cmd.contains("else if")) {
                        ie++;
                        String s1 = before(cmd,"else if ");
                        out.appendText("\n" + s1);
                        a3 = countSpace(s1);
                        bracketPrint(out,a3,stack);
                        String s = after(cmd,"else if ");
                        out.appendText("else if( " + s);
                        out.appendText(" ){\n\n\t");
                        break;
                    }
                    else if (cmd.contains("else ")) {
                        ie++;
                        String s1 = before(cmd,"else ");
                        out.appendText("\n" + s1);
                        a4 = countSpace(s1);
                        //stack.push(a4);
                        bracketPrint(out,a4,stack);
                        out.appendText("else{ ");
                        break;
                    }
                    else if (kmp.KMPSearch("exchange ",cmd)) {
                        String s0 = before(cmd,"exchange ");
                        a5 = countSpace(s0);
                        //stack.push(a5);
                        //bracketPrint(out,a5,stack);
                        String s = between(cmd,"exchange "," with ");
                        out.appendText(s0 + "swap( " + s + " , ");
                        String s1 = after(cmd," with ");
                        out.appendText(s1+ " ) ;");
                        break;
                    }
                    else if (kmp.KMPSearch("print",cmd)) {
                        String s1 = before(cmd,"print ");
                        out.appendText("\n" + s1);
                        a6 = countSpace(s1);
                        //stack.push(a6);
                        //bracketPrint(out,a6,stack);
                        String s =after(cmd,"print ");
                        out.appendText("cout << " + s + " ;");
                        break;
                    }
                    else if (kmp.KMPSearch("while",cmd)) {
                        String s0 = before(cmd,"while ");
                        out.appendText("\n" + s0);
                        a7 = countSpace(s0);
                        //stack.push(a7);
                        bracketPrint(out,a7,stack);
                        out.appendText("while( ");
                        //" ){\n\n\t}"

//                         if(cmd.charAt(i+9) ==' '){
//                            out.appendText(" ){\n "+ cmd.charAt(i+6)+"++; \n\t");
//                        }

                        if (cmd.contains(" and ")) {
                            String s = between(cmd,"while","and");
                            out.appendText(s);
                            out.appendText(" && " );
                            String s1 = after(cmd,"and ");
                            out.appendText(s1 + " ){\n\t");
                        }
                        else {
                            String s = after(cmd,"while ");
                            out.appendText(s + " ){\n\t");
                        }

                        break;

                    }

//                    else if(cmd.contains("do while ")){
//
//                    }
                    else if (kmp.KMPSearch("and",cmd)) {
                        out.appendText(" && ");
                        break;
                    }
                    else if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == '/') {
                        out.appendText("\n  " + cmd.substring(i) + "\n");
                        break;
                    }

                    else if(cmd.contains(" or ")){
                        out.appendText(" || ");
                        break;
                    }

                    else if (kmp.KMPSearch("return",cmd)) {
                        String s = after(cmd,"return");
                        out.appendText("\n" +s + " ;");
                        break;
                    }

                    else if(kmp.KMPSearch("=",cmd)){
                        out.appendText("\n" + cmd + " ;");
                        break;
                    }
                    else{
                        out.appendText("\n" + cmd + " ;");
                        break;
                    }
                }

            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }

        //System.out.println("Stack Start +++++++++++");
       // System.out.println(stack);
        return stack.size();
        //System.out.println(stack.peek() + " _---_-_---_-_--_-" + stack.elementAt(stack.size()-2));
        //int up = stack.peek();
        //int up2 = stack.pop();
        //System.out.println(up + " \t" + up2);
//        for (int i = 0; i < stack.lastElement(); i++) {
//            System.out.println(stack.get(i) + " ==== ");
//            if (stack.get(i) > c) {
//                stack.push(c);
//            }
//            else if(stack.get(i) < c)
//                stack.pop();
//                if (cmd.charAt(1) == '/' && cmd.charAt(2) == '/') {
//                    break;
//                } else {
//                    //stack.pop();
//                    out.appendText("\n\t}");
//                }
//            } return c;

    }

}
