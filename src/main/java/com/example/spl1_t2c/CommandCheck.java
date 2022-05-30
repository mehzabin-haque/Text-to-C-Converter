package com.example.spl1_t2c;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

import static java.util.stream.Collectors.toMap;

public class CommandCheck {
    Type_Var vr = new Type_Var();
    AlgoCommand ac = new AlgoCommand();

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
        return newList;
    }

    public static boolean checkDuplicateUsingSet(ArrayList<String> input){
       // List inputList = Arrays.asList(input);
        Set inputSet = new HashSet(input);
        if(inputSet.size() != input.size()){
            return true;
        }
        return false;

}

    public boolean check(ArrayList<String> a, ArrayList<String> b){
        boolean c = false;
        for(int i=0;i<b.size();i++){
            if(a.contains(b.get(i))){
                c = true;
            }
            else{
                c = false;
            }
        }

        return c;
    }
    public ArrayList<String> Token(String str){

        StringTokenizer st = new StringTokenizer(str, " ");


        ArrayList<String> elements = new ArrayList<String>();

        while(st.hasMoreTokens()) {

                elements.add(st.nextToken());

        }

        System.out.println("\nPrinting elements in ArrayList ...");
        for(String element : elements) {
            System.out.println(element);
        }

        elements = removeDuplicates(elements);
        return elements;
    }

    public void OpCheck(TextArea out, Label error) throws IOException {

        long count = vr.numOfLines("Loop.txt");
        FileReader fr=new FileReader("Loop.txt");
        BufferedReader br = new BufferedReader(fr);
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> int1 = new ArrayList<>();
        ArrayList<String> double1 = new ArrayList<>();
        ArrayList<String> float1 = new ArrayList<>();
        ArrayList<String> long1 = new ArrayList<>();
        ArrayList<String> char1 = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();

        Trie tr = new Trie();

        for (int i = 1; i <= count; i++) {

                while (i < count + 1) {
                    String str = br.readLine(),prev=null,prev1=null,prev2=null;

                    if(str != null ) {
                        if (str.contains(" = ") || str.contains(" op") || str.contains(" var")) {
                            str = str.replace("=", "");
                            str = str.replace(" op", "");
                            str = str.replace(" var", "");
                            str = str.replaceAll("[^a-zA-Z0-9]", " ");
                        }
                    }
                        String s = ac.before(str, " ");
                        String s1 = ac.after(str, " ");

                        if(s.contains("int")){
                            int1.add(s1);
                            actual.add(s1);
                        }

                       else if(s.contains("float")){
                            float1.add(s1);
                            actual.add(s1);
                        }
                        else if(s.contains("double")){
                            double1.add(s1);
                            actual.add(s1);
                        }
                        else if(s.contains("char")){
                            char1.add(s1);
                            actual.add(s1);
                        }
                        else if(s.contains("long")){
                            long1.add(s1);
                            actual.add(s1);
                        }

                        else{
                            list2 = Token(str);
                        }

                    System.out.println(list2 + " ========= " + int1 + " ========= " + float1 + " ========= " + double1
                            + " ========= " + char1 + " ========= " + long1);

                        int c = 0;
                       if( ! Collections.disjoint(list2, int1)){
                           //boolean noElementsInCommon = Collections.disjoint(list2, int1);
                           c ++;
                       }

                       if( !Collections.disjoint(list2, float1)){
                           c ++;
                       }
                       if( !Collections.disjoint(list2, double1)){
                            c ++;
                        }

                       if( !Collections.disjoint(list2, long1)){
                            c ++;
                       }

                        if( !Collections.disjoint(list2, char1)){
                            c ++;
                        }
                    System.out.println(c);
                       if(c>1){
                           error.setText("Invalid operation between two\n different data types");
                       }

                        map.put(s, s1);
                       // tm.put(s,s1);


                        if(str != null && !str.isEmpty()){
                            list.add(s );

                            tr.insert(s + s1);
                        }

                    break;
                }
        }
       // System.out.println(actual + "###@@@");
        boolean bd = checkDuplicateUsingSet(actual);
        if(bd ){
          error.setText("Duplicate Variable name");
        }

       // System.out.println("trie content (*** indicates the end of a word): ");
        tr.print();
        System.out.println();

        System.out.println("list of words in trie: ");
        System.out.println(tr.listWords());
        System.out.println();

        System.out.println(list);

    }
}
