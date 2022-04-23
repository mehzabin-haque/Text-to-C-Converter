package com.example.spl1_t2c;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Type_Var  implements Initializable {

    @FXML
    public TextField print = new TextField("");
    public TextArea print1 = new TextArea("");
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
    private boolean[] dataType;

    public void fileWriter(File savePath, TextArea textArea) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(savePath));
            bf.write(textArea.getText());
            bf.flush();
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void printFunction(String line)
    {
       // while(sc.hasNextLine()) {

//                                       for (int j = 0; j <= sc.nextLine().length(); j++) {
//                                           if(count ==1){
//                                               if (sc.nextLine().charAt(i) == '(') {
//                                                   System.out.println("  ++++++++++++++++++ ");
//                                                   out.appendText("\nvoid " + br.readLine());
//                                                   out.appendText(sc.nextLine().replace(',',';'));
//                                                   break;
//
//                                               }
//                                           }
//                                       }
// break;
// }
//sc.close();
//                               for(int i=0;i<algo.length();i++){
//                                   if(algo.charAt(i) == '('){
//                                       out.appendText(algo);
//                                   }
//                               }
        int algoIndent;
        //int i=7+algoIndent.top();
        //code<<indent.top()<<"cout <<";\\
        int i=0;
        while(i<line.length())
        {
            if(line.charAt(i)=='"')
            {
                i++;
                while(line.charAt(i)!='"')
                    out.appendText(String.valueOf(line.charAt(i++)));
                i++;
            }
            else if(line.charAt(i)==' ' || line.charAt(i)==',' || line.charAt(i)==')')
            {
                if(line.charAt(i)==',')

                i++;
            }
            else
            {
                while((line.charAt(i)>='a' && line.charAt(i)<='z')
                        || (line.charAt(i)>='A' &&line.charAt(i)<='Z') ||line.charAt(i)=='_'
                        || line.charAt(i)=='+' || line.charAt(i)=='-' || line.charAt(i)=='*'
                        || line.charAt(i)=='/' || line.charAt(i)=='%' )
                {
                    out.appendText(String.valueOf(line.charAt(i)));
                }
            }

        }
        out.appendText(";\n");
    }
    void inputFunction(String line)
    {
        String x = null;
        //int i=7+algoIndent.top();
        int i=0;
        while(i<line.length())
        {
            while(i<line.length())
            {
                if(line.charAt(i)==',' || line.charAt(i)==')')
                {
                    i++;
                    break;
                }
                else if(line.charAt(i)==' ')
                    i++;
                else
                {
                   // x.push_back(line[i]);
                    i++;
                }
            }
            if(i-x.length()-1==7)
                out.appendText("cin << ");
            else if(i!=line.length()-2)
                out.appendText(">> " + x);
        }
        out.appendText(";\n");

    }

    void declareVariable(String line)
    {
        String variables;
        int i=0;
        String x;
        while(line.charAt(i)!=' ')
        {
            out.appendText(String.valueOf(line.charAt(i)));
        }
        i++;
        if(true)
        {
            while(i<line.length())
            {
                String var;
                while(i<line.length() && line.charAt(i)!=',')
                    out.appendText(String.valueOf(line.charAt(i)));
                i++;
            }

            int j=0;
            int it = 0;
        }
        return;
    }

    void statement(String line)
    {
        int i=0;

        while(i<line.length())
            out.appendText(String.valueOf(line.charAt(i)));
        out.appendText(String.valueOf(line.charAt(i)));
        return;
    }
    //handles a general statement

    void ifFunction(String line)
    {
        int i=0;
        String c;
        for(; i<line.length()-4; i++)
        {
            if(line.charAt(i)=='a' && line.charAt(i+1)=='n' && line.charAt(i+2)=='d')
            {
                c ="&&";
                i+=2;
            }
            else if(line.charAt(i)=='o' && line.charAt(i+1)=='r')
            {
                c="||";
                i+=1;
            }
        }
        while(i<line.length())
        {
            c = String.valueOf(line.charAt(i+1));
        }

    }

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("int", "char", "float", "double");
        comb1.setItems(list);
        ObservableList<String> list2 = FXCollections.observableArrayList("if", "else if", "else");
        comb3.setItems(list2);
        ObservableList<String> list3 = FXCollections.emptyObservableList();
        comb2.setItems(list3);
    }

    public static ObservableList<String> removeDuplicates(ObservableList<String> list)
    {
        ObservableList<String> newList = FXCollections.observableArrayList();
        for (String element : list) {
            if (!newList.contains(element)) {
                newList.add(element);
            }
        }
        return  newList;
    }

    public static void appendStrToFile(String fileName, String str) {
        try {

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(fileName, true));

            out.write(str);
            out.close();
        }
        catch (IOException e) {
            System.out.println("exception occurred" + e);
        }
    }

    int length(String line) {
        int len=0;
        int i=0;
        while(line.charAt(i)!='\0')
        {
            len++;
            i++;
        }
        return len;
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

    boolean isDelimiter(char x) {
        if(x==' ' || x=='(' || x==')' || x=='{'
                || x=='}' || x=='['|| x==']'|| x=='\n')
        {
            return true;
        }
        return false;
    }

    String extractKeyword(String line) {
        String x;
        int len=0;
        for(int i=countSpace(line); i<line.length(); i++)
        {
            if(isDelimiter(line.charAt(i))==false)
                len++;
            else
                break;
        }
        x=line.substring(countSpace(line), len);
        return x;
    }

    public static void readFile(String filename){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                for(int i=0;i<=data.length();i++){

                }
                System.out.println(data);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void Enter() throws Exception {
        File f1 = new File("main.txt");
        f1.createNewFile();

        String comment = print.getText(); // print part

        String varName = var_name.getText(); // variable name
        String s = comb1.getSelectionModel().getSelectedItem();

        ObservableList<String> list = FXCollections.observableArrayList(comb2.getItems());
        ObservableList<String> var_All = null;
        list.add(var_name.getText());
        boolean b = varName.isEmpty();
        if(var_name != null && !b){
           // removeDuplicates(varName);
            ObservableList<String> rd = removeDuplicates(list);
            var_All = rd;
            comb2.setItems( rd);

        }

        ObservableList<String> list2 = FXCollections.observableArrayList(comb4.getItems());
        list2.add(var_name.getText());
        if(var_name != null && !b){
            ObservableList<String> rd = removeDuplicates(list);
            comb4.setItems( rd);
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
        String algo = print1.getText();


            out.setText("#include<iostream> " +
                    "\nusing namespace std;");

            Algo_Conversion(algo);

            if(algo.isEmpty()){
                out.appendText("\nint main(){");
            }
            else{
                out.appendText("\n}\nint main(){\n");
            }

            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("main.txt"));
                out.write(s + " " + varName);
                out.close();
            }
            catch (IOException e) {
                System.out.println("Exception Occurred" + e);
            }

        appendStrToFile("main.txt", "\n"+varNum+" "+vaLue);
        appendStrToFile("main.txt", "\n" + all + " " + opera);
        appendStrToFile("main.txt", "\n" + if1 + " " + cond + "\n\t"+inCond);
        appendStrToFile("main.txt", "\n" + loop + " " + comm);

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

        Operand op = new Operand();
        String var_all = var_All.toString();

        If_ElseCommand ifCheck = new If_ElseCommand();
        ifCheck.If_else(if1,cond,inCond, varName, error, out);

        if(all != null){
            out.appendText("\n " + all + " = ");
        }

        else{
            out.appendText("\n");
        }
        op.Operand_Checking(opera,var_all,error,out);

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
            BufferedReader in = new BufferedReader(
                    new FileReader("main.txt"));

            String mystring;
            while ((mystring = in.readLine()) != null) {
                System.out.println(mystring);
            }
        }
        catch (IOException e) {
            System.out.println("Exception Occurred" + e);
        }

        out.appendText("\n return 0; \n}");
    }

    public void Algo_Conversion2(String inp) {
        for(int i=0;i<inp.length();i++){
            out.appendText("\nvoid " + Arrays.toString(inp.split(",")));
            if(inp.charAt(i) == ','){
                out.appendText(inp.replace(',',';'));
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

    Stack<Integer> stack = new Stack<>();
    Stack<String> str = new Stack<>();
    ArrayList<String> cond = new ArrayList<>(Arrays.asList("if","else","for","while","do"));

    public int AllCommand(String cmd)throws IOException {
        int c = countSpace(cmd);
        //stack.push(c);
        System.out.println(c);
        for(int j=0;j<countSpace(cmd);j++){
            out.appendText(" ");
        }

        try {
            for (int i = 0; i < cmd.length(); i++) {
                if (i + 2 < cmd.length()) {

                    String s1 = "downto";
                    if (cmd.charAt(i) == 'f' && cmd.charAt(i + 1) == 'o' && cmd.charAt(i + 2) == 'r') {
                        out.appendText("\nfor (int " + cmd.charAt(i + 4));
                        String s = "=";
                        if (s.equals(cmd.charAt(i + 6))) {
                            out.appendText(s);
                        }
                        out.appendText(cmd.substring(i + 5, i + 9));

                        if (cmd.charAt(i + 10) == 'd' && cmd.charAt(i + 11) == 'o' && cmd.charAt(i + 12) == 'w'
                                && cmd.charAt(i + 13) == 'n' && cmd.charAt(i + 14) == 't' && cmd.charAt(i + 15) == 'o') {

                            out.appendText(" ; " + cmd.charAt(i + 4) + "<=" + cmd.charAt(i + 17) + " ; " + cmd.charAt(i + 4) + "--){");
                            break;
                        }

                        if (cmd.charAt(i + 10) == 't' && cmd.charAt(i + 11) == 'o') {
                            out.appendText("; " + cmd.charAt(i + 4) + " <= ");
                            out.appendText(cmd.substring(i + 12, cmd.length()));
                            out.appendText("; " + cmd.charAt(i + 4) + "++){");
                            break;
                        }
                    }    //String s1 = "to";


                    else if ((cmd.charAt(i) == 'i' && cmd.charAt(i + 1) == 'f')) {
                        out.appendText("\n\tif(");
                        out.appendText(cmd.substring(i + 2, cmd.length()));
                        out.appendText(" ){\n\n\t}");
                        break;
                    } else if (cmd.charAt(i) == 'e' && cmd.charAt(i + 1) == 'l' && cmd.charAt(i + 2) == 's' && cmd.charAt(i + 3) == 'e'
                            && cmd.charAt(i + 4) == 'i' && cmd.charAt(i + 5) == 'f') {

                        out.appendText("else if(");
                        out.appendText(cmd.substring(i + 6, cmd.length()));
                        out.appendText(" ){");
                        break;
                    } else if (cmd.charAt(i) == 'e' && cmd.charAt(i + 1) == 'l' && cmd.charAt(i + 2) == 's' && cmd.charAt(i + 3) == 'e') {
                        out.appendText("else(");
                        out.appendText(cmd.substring(i + 4, cmd.length()));
                        out.appendText(" ){");
                        break;
                    } else if (cmd.charAt(i) == 'e' && cmd.charAt(i + 1) == 'x' && cmd.charAt(i + 2) == 'c') {
                        out.appendText("\n\nswap(" + cmd.substring(i + 8, i + 13) + " , " + cmd.substring(i + 19, cmd.length()) + ") ;");
                        break;
                    } else if (cmd.charAt(i) == 'p' && cmd.charAt(i + 1) == 'r' && cmd.charAt(i + 2) == 'i') {
                        out.appendText("cout << " + cmd.charAt(i + 6));
                    } else if (cmd.charAt(i) == 'w' && cmd.charAt(i + 1) == 'h' && cmd.charAt(i + 2) == 'i' && cmd.charAt(i + 3) == 'l' && cmd.charAt(i + 4) == 'e') {
                        out.appendText("\nwhile(" + cmd.substring(i + 5, i + 9));
                        //" ){\n\n\t}"

//                         if(cmd.charAt(i+9) ==' '){
//                            out.appendText(" ){\n "+ cmd.charAt(i+6)+"++; \n\t");
//                        }

                        if (cmd.charAt(i + 10) == 'a' && cmd.charAt(i + 11) == 'n' && cmd.charAt(i + 12) == 'd') {
                            out.appendText(" && " + cmd.substring(i + 14, cmd.length()) + " ){");
                        } else if (cmd.charAt(i + 9) == ' ') {
                            out.appendText(cmd.substring(i + 9, cmd.length()));
                        }

                        break;

                    } else if ((cmd.charAt(i) == 'a' && cmd.charAt(i + 1) == 'n' && cmd.charAt(i + 2) == 'd')) {
                        out.appendText(" && ");
                        break;
                    } else if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == '/') {
                        out.appendText("\n  " + cmd.substring(i));
                        break;
                    }

//                    else if(cmd.charAt(i)=='o' && cmd.charAt(i+1)=='r'){
//                        out.appendText(" || ");
//                        break;
//                    }

                    else if (cmd.charAt(i) == 'r' && cmd.charAt(i + 1) == 'e' && cmd.charAt(i + 2) == 't') {
                        out.appendText("\n" + cmd.substring(i, cmd.length()) + " ;");
                        break;
                    } else if (cmd.charAt(i) != ' ') {
                        out.appendText("\n" + cmd.substring(i, cmd.length()) + " ;");
                        break;
                    }
                }

            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(e);
        }
        if(stack.size()==0){
            stack.push(c);
        }
        else if(stack.peek() < c){
            stack.push(c);
        }
        else if(stack.peek()>c){
            while(!stack.empty() && stack.peek()>c ){
                stack.pop();
                out.appendText("\n");
                for(int j=0;j<countSpace(cmd);j++){
                    out.appendText(" ");
                }
                out.appendText("}");
            }
            out.appendText("\n");
            for(int j=0;j<countSpace(cmd);j++){
                out.appendText(" ");
            }
            out.appendText("}");
        }

        System.out.println(stack);
        if(!stack.empty()){
            System.out.println(c + " " + cmd + " " + stack.peek());
        }

        else{
            System.out.println(c + " " + cmd + " " );
        }

        //(int i=1;i<stack.lastElement();i++){
        int up = stack.peek();
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
//            }
        return c;
    }

       public void Algo_Conversion(String algo)throws IOException{

           try{
                File file = new File("comment.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(algo);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
               fileWriter(new File("comment.txt"),print1);
           readFile("comment.txt");

           long lines = 0;

           try (InputStream is = new BufferedInputStream(new FileInputStream("comment.txt"))) {
               byte[] c = new byte[1024];
               int count = 0;
               int rc = 0;
               boolean nLine = false;
               File file = new File("comment.txt");
               Scanner sc = new Scanner(file);
               System.out.println("***************************");
               FileReader fr=new FileReader(file);
               BufferedReader br=new BufferedReader(fr);
               StringBuffer sb=new StringBuffer();
               while ((rc = is.read(c)) != -1) {
                   for (int i = 0; i < rc; ++i) {
                       if (c[i] == '\n'){
                           ++count;
                       }
                    }
                   nLine = (c[rc - 1] != '\n');
               }
               if (nLine) {
                   ++count;
               }
               lines = count;

               for (int i = 1; i <= count; i++) {
                   if (i == 1) {
                        out.appendText("\nvoid "+ br.readLine() + "{");
                   }
                   else {
                       try {
                           String prev = null;
                           while (i < count + 1) {
                               //stack.push(0);
                               AllCommand(br.readLine());
                              // stack.clear();
                           }
                       }catch(Exception e){
                           System.out.println(e);
                       }
                   }
               }

               System.out.println(stack);
               System.out.println("Lines number " + lines);
           } catch (IOException e) {
               e.printStackTrace();
           }

       }

    public void Start(ActionEvent event) {
        out.appendText("#include<iostream>\n" +
                "using namespace std;\n" +
                "int main()\n" +
                "{\n" );
    }
}

