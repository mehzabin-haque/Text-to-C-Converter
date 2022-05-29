package com.example.spl1_t2c;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Loop {
    Type_Var vr = new Type_Var();
    AlgoCommand ac = new AlgoCommand();
    public void nestedLoop(TextArea out,Stack<Integer> stack) throws IOException {
        long count1 = vr.numOfLines("ActualLoop.txt");
        FileReader fr1 = new FileReader("ActualLoop.txt");
        BufferedReader br1 = new BufferedReader(fr1);
        ArrayList<String> ar = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        int fc = 0,fd=0,ff=0,fw=0;
        out.appendText("\n  int n; \n  cin >> n; \n");
        for (int j = 1; j <= count1; j++) {
            while (j < count1 + 1) {
                String str = br1.readLine();
                //System.out.println(str+ "0234953u46934");
                if (str == null) {
                    break;
                }
                if (str.contains("for") || str.contains("while") || str.contains("do while")) {
                    fc++;
                    ar.add(str);
                    if(str.contains("do while")){
                        fd++;
                    }
                    else if(str.contains("for")){;
                        ff++;
                    }
                    else{
                        fw++;
                    }
                }
            }
            System.out.println(fc + "----------------");

                if(fc == 1 && count1 == 1){
                   for(int i=0;i<ar.size();i++) {
                       if(ar.get(i).contains("for")) {
                           out.appendText(" for(int itr_1=0" + " ; itr_1" + " <n ; itr_1" + "++){");
                           String ss = ac.after(ar.get(0), "for");
                           if (ss != null) {
                               ac.AllCommand(ss, out, stack);
                           }
                           out.appendText("\n  }\n");
                       }

                       else if(ar.get(i).contains("do while")) {
                           out.appendText("\n  int i = 0; \n  do while{");
                          // out.appendText("\n\t i++;\n\t}");
                           String ss = ac.after(ar.get(0), "do while");
                           if (ss != null) {
                               ac.AllCommand(ss, out, stack);
                           }
                           out.appendText("\n\t i++;\n\t}while( i<n )");
                       }
                       else if(ar.get(i).contains("while ")){
                           out.appendText("\n int i = 0; \n  while( i<n ){");
                           out.appendText("\t");
                           String ss = ac.after(ar.get(0), "while ");
                           if (ss != null) {
                               ac.AllCommand(ss, out, stack);
                           }

                           out.appendText("\n\t i++;\n\t}");
                       }
                   }
                }

                   else  if (fc > 1) {
                        for (int m = 0; m < fc; m++) {
                            System.out.println(ar.get(m) + "00000000000    " + m);
                                if (ar.get(m).contains("for")) {
                                    out.appendText(" \nfor(int itr_0" + m+ "=0" + " ; itr_0" + m + " <n ; itr_0" + m+ "++){");
                                    String ss = ac.after(ar.get(m), "for");
                                    if (ss != null) {
                                        ac.AllCommand(ss, out, stack);
                                    }

                                } else if (ar.get(m).contains("do while")) {
                                    out.appendText("\n  int i" + m+ "= 0; \n  do while{");
                                    // out.appendText("\n\t i++;\n\t}");
                                    String ss = ac.after(ar.get(m), "do while");
                                    if (ss != null) {
                                        ac.AllCommand(ss, out, stack);
                                    }
                                    out.appendText("\n\t i"+m+"++;");
                                    for (int k = 0; k < fd; k++) {
                                        out.appendText("\n  }while( i" + m +" <n );");
                                    }
                                   // break;
                                } else if (ar.get(m).contains("while")) {
                                    out.appendText("\n int i" + m+ "= 0; \n  while( i" + m +" <n ){");
                                    out.appendText("\t");
                                    String ss = ac.after(ar.get(m), "while");
                                    if (ss != null) {
                                        ac.AllCommand(ss, out, stack);
                                    }
                                    out.appendText("\n\t i"+j+"++;");
                                    //break;
                                }
                            }
                    for (int k = 0; k < fc-fd; k++) {
                        out.appendText("\n  }");
                    }
                    break;
                        }

                   }
        br1.close();
        }
    public void loopCheck(String loop, String comm, TextArea out, Stack<Integer> stack) throws IOException {
        if(loop.contains("for") ){
            out.appendText("\n  int n; \n  cin >> n; \n  for(int i=0; i<n; i++){");
            System.out.println(comm+")))))))))))");
                ac.AllCommand(comm,out,stack);

            out.appendText("\n\t}");
        }
        else if(loop.contains("while") ){
            out.appendText("\n  int n; \n  cin >> n; \n  int i = 0; \n  while( i<n ){");
            out.appendText("\t");
            ac.AllCommand(comm,out,stack);

            out.appendText("\n\t i++;\n\t}");
        }
        else if(loop.contains("do while")){
            out.appendText("\n  int n; \n  cin >> n; \n  do while{");

            ac.AllCommand(comm,out,stack);
            out.appendText("\n\t i++;\n\t}while( i<n )");
        }

    }
}
