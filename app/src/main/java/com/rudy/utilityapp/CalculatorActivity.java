package com.rudy.utilityapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mariuszgromada.math.mxparser.*;

public class CalculatorActivity extends AppCompatActivity {
    private char symbols [] ={'+','.','-','^','√','*','/','\u0000'};
    private TextView result;
    private String strAll ="";
    private String userExp = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        result = (TextView) findViewById(R.id.result);

        Button btnHome = (Button) findViewById(R.id.btn_Home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void updateText(String strToAdd){

        strAll += strToAdd;

        result.setText(strAll);



    }
    //------------------------------------------buttons methods start here --------------------------------------------------
    public void btn_0(View v){
        updateText("0");

    }
    public void btn_1(View v){
        updateText("1");

    }
    public void btn_2(View v){
        updateText("2");

    }
    public void btn_3(View v){
        updateText("3");

    }
    public void btn_4(View v){
        updateText("4");

    }
    public void btn_5(View v){
        updateText("5");

    }
    public void btn_6(View v){
        updateText("6");

    }
    public void btn_7(View v){
        updateText("7");

    }
    public void btn_8(View v){
        updateText("8");

    }
    public void btn_9(View v){
        updateText("9");

    }
    public void btnMinus(View v){
        updateText("-");

    }
    public void btnPlus(View v){
        updateText("+");

    }
    public void btnTimes(View v){
        updateText("×");

    }
    public void btnDivide(View v){
        updateText("÷");

    }
    public void btnPower(View v){
        updateText("^");

    }
    public void btnEquals(View v){

//        if (result.getText().charAt(0) == '='){
//            result.setText("Error");
//            strAll ="";
//            return;
//        }
        //put the inputted formula to string expression to chage the division and multiplication symbol to a format mXParser understand
        userExp = result.getText().toString();
        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");

        //if Error message has been shown and equals button press again show Eroor
        if (result.getText().toString().equals("Error") ){
            result.setText("Error");
            strAll ="";
            return;
        }

        //will make it so the las digit enter is not a *, / , ^ ,√ if it is show error
        if (userExp.charAt(userExp.length() - 1) == '*' || userExp.charAt(userExp.length() - 1) == '√' || userExp.charAt(userExp.length() - 1) == '^' || userExp.charAt(userExp.length() - 1) == '/'){
            result.setText("Error");
            strAll ="";
            return;
        }

        Log.i("algo", String.valueOf(userExp.length()));
        //check for symbols in length if two symbols are one after the other show error
        for (int i = 0; i < userExp.length() -1; i++){
            char temp = userExp.charAt(i);
            char temp2 = userExp.charAt(i +1);

            for (int j = 0; j < symbols.length; j++){
                if ( (temp == temp2  && temp == symbols[j]) ){

                    result.setText("Error");
                    strAll ="";
                    return;
                }
                //check for duplicate operator if two are together show error
                for (int k = 0; k < symbols.length; k++){
                    for (int l =0; l < symbols.length; l++){
                        if (temp == symbols[k] && temp2 == symbols[l]){
                            result.setText("Error");
                            strAll ="";
                            return;
                        }
                    }

                }
            }
        }
        //if result starts with one this symbols and btnEquals press show error message
        if ( (userExp.charAt(0) == '√' || userExp.charAt(0) == '+'|| userExp.charAt(0) == '-' ||userExp.charAt(0) == '^'||userExp.charAt(0) == '/'||
                userExp.charAt(0) == '+' ||  userExp.charAt(0) == '*' || userExp.charAt(0) == '.' || userExp.charAt(0) == '=' ) && (userExp.length() == 1)){

            result.setText("Error");
            strAll ="";
            return;
        }

        // operation start with * , ^ , / , show error
        if ( (userExp.charAt(0) == '*' || userExp.charAt(0) == '^'|| userExp.charAt(0) == '/' ) && (userExp.length() > 0) )  {
            result.setText("Error");
            strAll ="";
            return;
        }

        Expression  mXparserExpresion  = new Expression(userExp);
        String calculate = String.valueOf(mXparserExpresion.calculate());
        int periodPos =0;

        //gets the position of the (.) in the string calculate
        for (int i = 0;  i < calculate.length(); i++){
            if (calculate.charAt(i) == '.'){
                periodPos = i;
            }
        }
        //Log.i("before if statement", String.valueOf(periodPos));

        //check if the character after the period is 0 if it is omit whatever is after it
        if (Integer.parseInt(String.valueOf(calculate.charAt(periodPos +1))) < 1 ){
            //Log.i("max", calculate.substring(0,periodPos));
            calculate.substring(0,periodPos);
            result.setText(calculate.substring(0,periodPos));
            strAll ="";
            return;
        }

        //set the cut off to 10 decimals
        if (calculate.length() > 10){
            result.setText(calculate.substring(0,10));
            strAll ="";
            return;
        }

        result.setText(calculate);
        clear(v);
        //Log.i("max", "Else got executed");






    }// end of equal function

    public void btnSquareRoot(View v){

        updateText("√");

    }
    public void btnDot(View v){
        updateText(".");

    }
    public void clear(View v){
        result.setText("0");
        strAll ="";

    }// end clear




}