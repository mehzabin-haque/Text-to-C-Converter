package com.example.spl1_t2c;

import javafx.scene.control.Label;

public class VarNameCheck {
    AlgoCommand ac = new AlgoCommand();
    public static boolean isAlphaNumeric(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (!(c >= 'A' && c <= 'Z') &&
                    !(c >= 'a' && c <= 'z') &&
                    !(c >= '0' && c <= '9')) {
                return false;
            }
        }
        return true;
    }

    public void checkVarName(String var, Label error){

        for(int i=0;i<var.length();i++){
            boolean flag = Character.isDigit(var.charAt(0));
            boolean flag1 = var.contains(" ");
            boolean test = isAlphaNumeric(var);

            if(flag)
            {
                error.setText(" Variable name can't start with a number ");
                break;
            }
            else if(flag1)
            {
                error.setText(" Variable name can't contain a space ");
                break;
            }
            if(var.contains("_")){
                break;
            }

            else if(test){
                break;
            }

            else
            {
                error.setText(" Variable name can't contain any special character ");
                break;
            }

        }
    }
}
