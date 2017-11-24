package com.example.nixo.bilibiliproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0,dian,ac,jia,jian,cheng,chu,del,deng;
    private EditText show;
    private boolean clear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定控件区
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        num0 = findViewById(R.id.num0);
        dian = findViewById(R.id.dian);
        ac   = findViewById(R.id.ac);
        jia  = findViewById(R.id.jia);
        jian = findViewById(R.id.jian);
        cheng = findViewById(R.id.cheng);
        chu  = findViewById(R.id.chu);
        del  = findViewById(R.id.del);
        deng = findViewById(R.id.deng);
        show = findViewById(R.id.show);


        //点击事件区
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        num0.setOnClickListener(this);
        dian.setOnClickListener(this);
        ac.setOnClickListener(this);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        cheng.setOnClickListener(this);
        chu.setOnClickListener(this);
        del.setOnClickListener(this);
        deng.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        //EditText上显示的数值
        String str =  show.getText().toString();
        switch (v.getId()){
            //1~0
            case R.id.num1:
            case R.id.num2:
            case R.id.num3:
            case R.id.num4:
            case R.id.num5:
            case R.id.num6:
            case R.id.num7:
            case R.id.num8:
            case R.id.num9:
            case R.id.num0:


            //点
            case R.id.dian:
                if (clear){
                    clear = false;
                    str = "";
                    show.setText("");
                }
                show.setText(str+((Button)v).getText());
                break;

            //加减乘除
            case R.id.jia:
            case R.id.jian:
            case R.id.chu:
            case R.id.cheng:

                show.setText(str+" "+((Button)v).getText()+" ");
                break;

            //del
            case R.id.del:
                if(str != null && !str.equals("")){
                    show.setText(str.substring(0,str.length()-1));
                }
                break;
            //AC
            case R.id.ac:
                show.setText("");
                break;
            //等于
            case R.id.deng:
                getResult();
                break;



        }

    }

    private void getResult() {

        String str2 = show.getText().toString();

        if (str2 != null && str2.equals("")) {
            return;
        }
        //如果不包含空格 返回
        if (!str2.contains(" ")) {
            return;
        }

        clear = true;
        double result = 0;

        // 运算符前的数字
        String n1 = str2.substring(0, str2.indexOf(" "));

        // 运算符
        String op = str2.substring(str2.indexOf(" ") + 1, str2.indexOf(" ") + 2);

        // 第二个数字
        String n2 = str2.substring(str2.indexOf(" ") + 3);

        //按断数字1和数字2都不为空，然后转为double型进行运算
        if (!n1.equals("") && !n2.equals("")) {

            double number1 = Double.parseDouble(n1);
            double number2 = Double.parseDouble(n2);

            if (op.equals("+")) {
                result = number1 + number2;
            } else if (op.equals("-")) {
                result = number1 - number2;
            } else if (op.equals("X")) {
                result = number1 * number2;
            } else if (op.equals("/")) {
               result = number1 / number2;
            }

            //判断小数点
            if (!n1.contains(".") && n2.contains(".") && !op.contains("÷")) {
                int s = (int) result;
                show.setText(s + "");
            } else {
                show.setText(result + "");
            }
        }else if (!n1.equals("") && n2.equals("")){ //如果1有数字2没有数字，就显示1的数字。
            show.setText(str2);
        }else if(n1.equals("") && !n2.equals("")){ //如果1没有数字，2有运算符和数字，就说明他要在0上操作。
            double number2 = Double.parseDouble(n2);
            if(op.equals("+")){
                result = 0 + number2;
            }else if(op.equals("-")){
                result = 0 - number2;
            }else if(op.equals("X")){
                result = 0;
            }else if(op.equals("/")){
               result = 0;
            }

            if (!str2.contains(".")){
                int s = (int) result;
                show.setText(s+"");
            }else{
                show.setText(result+"");
            }
        }else{
            show.setText("");
        }

 }
}
