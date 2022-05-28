package com.example.spl1_t2c;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public Label sout = new Label("");;
    public Label error = new Label("");;
    public Label error1 = new Label("");
    public TextArea out = new TextArea("");
    public ComboBox <String> comb1 ;
    public ComboBox <String> comb2 ;
    public ComboBox <String> comb3;
    public ComboBox <String> comb4 ;
    public ComboBox <String> comb5 ;
    Stack<Integer> stack = new Stack<>();
    ArrayList<String> cond = new ArrayList<>(Arrays.asList("if","else","for","while","do"));

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
        ObservableList<String> list4 = FXCollections.observableArrayList("for", "while", "do while");
        comb5.setItems(list4);
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

    public long numOfLines(String fileName){
        readFile(fileName);

        long lines = 0;

        try (InputStream is = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] c = new byte[1024];
            int count = 0;
            int rc = 0;
            boolean nLine = false;
            File file = new File(fileName);
            Scanner sc = new Scanner(file);
           // System.out.println("***************************");
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
    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
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

    public void deleteDuplicated(String filePath) throws IOException {
        String input = null;
        //Instantiating the Scanner class
        Scanner sc = new Scanner(new File(filePath));
        //Instantiating the FileWriter class
        FileWriter writer = new FileWriter(filePath);
        //Instantiating the Set class
        Set set = new HashSet();
        while (sc.hasNextLine()) {
            input = sc.nextLine();
            if(set.add(input)) {
                writer.append(input+"\n");
            }
        }
        writer.flush();
        System.out.println("Contents added............");
    }

    public static long filesCompareByLine(Path path1, Path path2) throws IOException {
        try (BufferedReader bf1 = Files.newBufferedReader(path1);
             BufferedReader bf2 = Files.newBufferedReader(path2)) {

            long lineNumber = 1;
            String line1 = "", line2 = "";
            while ((line1 = bf1.readLine()) != null) {
                line2 = bf2.readLine();
                if (line2 == null || !line1.equals(line2)) {
                    return lineNumber;
                }
                lineNumber++;
            }
            if (bf2.readLine() == null) {
                return -1;
            }
            else {
                return lineNumber;
            }
        }
    }

    public static void copyData(File file1, File file2) throws Exception
    {
        FileInputStream iS = new FileInputStream(file1);
        FileOutputStream oS = new FileOutputStream(file2);
        try {

            int i;
            while ((i = iS.read()) != -1) {
                oS.write(i);
            }
        }

        catch(Exception e) {
            System.out.println("Error Found: "+e.getMessage());
        }

        finally {
            if (iS != null) {
                iS.close();
            }

            if (oS != null) {
                oS.close();
            }
        }
    }

    public void Enter() throws Exception {
        File f1 = new File("test.txt");
        if(!f1.exists()){
            f1.createNewFile();
        }

        File f2 = new File("ActualLoop.txt");
        if(!f2.exists()){
            f2.createNewFile();
        }

        File f3 = new File("IfElse.txt");
        if(!f3.exists()){
            f3.createNewFile();
        }

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
        String algo = print1.getText();
        String loop = comb5.getSelectionModel().getSelectedItem();
        String comm = loop_command.getText();
        ArrayList<String> int1 = new ArrayList<>();
        ArrayList<String> double1 = new ArrayList<>();
        ArrayList<String> float1 = new ArrayList<>();
        ArrayList<String> long1 = new ArrayList<>();
        ArrayList<String> char1 = new ArrayList<>();
        ArrayList<String> actual = new ArrayList<>();
        AlgoCommand ac = new AlgoCommand();


        out.setText("#include<iostream> " +
                "\nusing namespace std;");

        Algo_Conversion(algo);

        if(algo.isEmpty()){
            out.appendText("\nint main(){");
        }
        else{
            out.appendText("\n}\nint main(){\n");
        }

        int n=0;

        if( (s != null && varName != null)){
            if(varName == null || varName.isEmpty()){
                System.out.println("nothing");
            }
            else{
                appendStrToFile("test.txt",s+" "+varName + "\n");
                appendStrToFile("Loop.txt",s+" "+varName + "\n");
            }
        }

        if(varNum != null && vaLue != null){
            if(s == "char"){
                appendStrToFile("test.txt", "\n"+varNum+" = ' "+vaLue + " ' var\n" );
                appendStrToFile("Loop.txt", "\n"+varNum+" = ' "+vaLue + " ' var\n" );
            }
            else{
                appendStrToFile("test.txt", "\n"+varNum+" = "+vaLue + " var\n" );
                appendStrToFile("Loop.txt", "\n"+varNum+" = "+vaLue + " var\n" );
            }

        }

        if( opera != null && !opera.isEmpty()){
            if(all != null && opera != null){
                appendStrToFile("test.txt", all + " = " + opera + " op\n" );
                appendStrToFile("Loop.txt", all + " = " + opera + " op\n" );
            }

            else {
                appendStrToFile("test.txt", opera + " op\n" );
                appendStrToFile("Loop.txt", opera + " op\n" );
            }

        }

        CommandCheck cc = new CommandCheck();
        cc.OpCheck(out,sout);

        if(if1 != null && cond != null && inCond != null){
            appendStrToFile("test.txt", if1 +"\n" );
            if(if1 == "if"){
                appendStrToFile("IfElse.txt", if1 + " ifif " + cond + "\t@@ "+inCond + "\n");
            }

            else if(if1 == "else if"){
                appendStrToFile("IfElse.txt", if1 + " elseif1 " + cond + "\t@@ "+inCond + "\n");
            }

            else if(if1 == "else"){
                appendStrToFile("IfElse.txt", if1 + " else1 " + cond + "\t@@ "+inCond + "\n");
            }

        }

        if(loop != null && comm != null){
            appendStrToFile("test.txt",  loop + "\n" );
            appendStrToFile("ActualLoop.txt",  loop + "\t" + comm +"\n" );

        }

        if (!varName.isEmpty() && s == null) {
            //comb1.setOnAction(e -> error.setText(" Variable type is not declared "));
             error.setText(" Variable type is not declared ");
        }

        else if (s != null && varName == null)
        {
            error.setText(" Variable name is not declared ");
        }

        else if (varName == null && s == null)
        {
            //out.setText("\n");
            out.appendText("");
        }

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


            long count = numOfLines("test.txt");
            FileReader fr=new FileReader("test.txt");
            BufferedReader br = new BufferedReader(fr);

            boolean loopFlag = false;
            String ss;

        for (int i = 1; i <= count; i++) {
                try {
                    while (i < count + 1) {
                       String str = br.readLine();
                        String str1 ,prev = null;
                           if(str.contains("int") || str.contains("float") ||
                                    str.contains("double") || str.contains("long") || str.contains("char")){

                                VarNameCheck vr = new VarNameCheck();
                                vr.checkVarName(varName,sout);
                                if(str.contains("int")){
                                    ss = ac.after(str,"int");
                                    int1.add(ss);
                                }
                               else if(str.contains("float")){
                                   ss = ac.after(str,"float");
                                   float1.add(ss);
                               }
                               else if(str.contains("long")){
                                   ss = ac.after(str,"long");
                                   long1.add(ss);
                               }
                               else if(str.contains("char")){
                                   ss = ac.after(str,"char");
                                   char1.add(ss);
                               }

                                    if(varName == null || varName.isEmpty()){
                                        error.setText(" Variable name is not declared ");
                                    }
                                    else{
                                        ss = ac.after(str,"char");
                                        if(str.contains("char") && char1.equals(ss)){

                                            out.appendText("\n" + str + " ;");
                                        }
                                        else{
                                        error.setText(" ");
                                        out.appendText("\n" + str + " ;");}
                                    }

//                           if(str.contains(varName) ){
//                               error.setText("Variable Name is already declared ");
//                               break;
//                           }
                                for(int j=0;j<str.length();j++){
                                    if(str.charAt(j)==' '){
                                        prev = str.substring(j+1,str.length());
                                        break;
                                    }
                                }

                            }

                            else if(str.contains(" op")){
                                str = str.replace(" op"," ");

                                Operand op = new Operand();
                                out.appendText("\n" + str + ";\n");

                                if(var_All.toString() != null){
                                    op.Operand_Checking(opera,var_All.toString(),error,out);
                                }
                            }

                            else if(str.contains("=") && str.contains("var")){
                                str = str.replace("var"," ");
                                //System.out.println("lalalala");
                               String s1 = ac.after(str,"= ");
                                out.appendText("\n" + str + " ;");
                                  if (varName != null && s != null )
                               {
                                   //comb2.getItems().add(var_name.getText());
//            if(varNum == null){
//                out.appendText("");
//            }
                                   if (value != null && !val) {
                                       if (s == "int") {
                                           try {
                                               int check = Integer.parseInt(vaLue);
                                           } catch (NumberFormatException nfe) {
                                               sout.setText("It's not an Integer Value.\nUse values like '5' not " + vaLue );
                                           }
                                       }
                                       else if (s == "float") {
                                           try {
                                               float check = Float.parseFloat(vaLue);
                                           } catch (NumberFormatException nfe) {
                                               sout.setText("It's not a Float Value.\nUse values like '5.0' not " + vaLue);
                                           }
                                       }
                                       else if (s == "double") {
                                           try {
                                               double d = Double.parseDouble(vaLue);
                                           } catch (NumberFormatException nfe) {
                                               sout.setText("It's not a Double Value.\nUse values like '5.000' not " + vaLue);
                                           }
                                       }
                                       else if (s == "char" ) {
                                           for(int j=0; j < s.length(); j++) {
                                               Boolean flag = Character.isDigit(s.charAt(i));
                                               if(flag) {
                                                   //System.out.println("'"+ s.charAt(i)+"' is a number");
                                                   sout.setText("It's not a character \n It's a number");
                                               }
                                           }

                                       }
                                       else {
                                           System.out.println("void ");
                                       }
                                   }
                               }
                            }

                            else if ((str.contains("if") || str.contains("else if") || str.contains("else")) && loopFlag==false){
                               loopFlag = true;
                                If_ElseCommand ie = new If_ElseCommand();
                                ie.If_else(if1,cond,inCond,error,out,stack);
                            }

                            else if((str.contains("for") || str.contains("while") || str.contains("do while")) && loopFlag==false){
                                //System.out.println("lalala))))))");
                               loopFlag = true;
                                Loop lp = new Loop();
                                 lp.nestedLoop(out,stack);
                            }
                            else{
                                break;
                            }
                       }
                }catch(Exception e){
                    System.out.println(e);
                }
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



    public void Algo_Conversion(String algo)throws IOException{
        int dhur = 0;
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

        long count = numOfLines("comment.txt");
        FileReader fr=new FileReader("comment.txt");
        BufferedReader br = new BufferedReader(fr);
            for (int i = 1; i <= count; i++) {
                if (i == 1) {
                    out.appendText("\nvoid "+ br.readLine() + "{");
                }
                else {
                    try {
                        String prev = null;
                        while (i < count + 1) {
                            //stack.push(0);
                            AlgoCommand al = new AlgoCommand();
                            dhur = al.AllCommand(br.readLine(),out,stack);
                            System.out.println(dhur + " lalalalala ");
                        }


                    }catch(Exception e){
                        System.out.println(e);
                    }
                }
            }

            System.out.println(stack);
            while(!stack.empty()){
                     stack.pop();
                    out.appendText("\n  }");
            }

    }

    public void Start(ActionEvent event) {
        out.appendText("#include<iostream>\n" +
                "using namespace std;\n" +
                "int main()\n" +
                "{\n" );
    }
}
