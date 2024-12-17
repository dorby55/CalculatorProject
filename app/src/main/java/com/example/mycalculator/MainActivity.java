package com.example.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView sum;
    double firstOperand = 0;
    double secondOperand = 0;
    String currentOperator = "";
    boolean isOperatorPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sum = findViewById(R.id.total_sum);
    }

    public void numFunc(View view) {
        Button button = (Button) view;
        if (isOperatorPressed) {
            sum.setText(button.getText().toString());
            isOperatorPressed = false;
        }
        else{
            if(sum.getText().toString().equals("0") || sum.getText().toString().equals("0.0") || sum.getText().toString().equals("-0.0")){
                sum.setText(button.getText().toString());
            }
            else
                sum.append(button.getText().toString());
        }
    }

    public void operatorFunc(View view) {
        Button button = (Button) view;
        firstOperand = Double.parseDouble(sum.getText().toString());
        currentOperator = button.getText().toString();
        isOperatorPressed = true;
    }

    public void equalsFunc(View view) {
        secondOperand = Double.parseDouble(sum.getText().toString());

        double result = 0;
        switch (currentOperator) {
            case "+":
                result = firstOperand + secondOperand;
                break;
            case "-":
                result = firstOperand - secondOperand;
                break;
            case "X":
                result = firstOperand * secondOperand;
                break;
            case ":":
                if (secondOperand == 0) {
                    sum.setText("Error");
                    return;
                }
                result = firstOperand / secondOperand;
                break;
            case "%":
                result = firstOperand % secondOperand;
                break;
            default:
                break;
            }

        sum.setText(String.valueOf(result));
        firstOperand = result;
        currentOperator = "";
        isOperatorPressed = false;
    }

    public void clearFunc(View view) {
        sum.setText("0");
        firstOperand = 0;
        secondOperand = 0;
        currentOperator = "";
        isOperatorPressed = false;
    }

    public void toggleSignFunc(View view) {
        String currentText = sum.getText().toString();
        if (currentText.isEmpty()) return;

        double currentNumber = Double.parseDouble(currentText);
        if(currentNumber != 0)
            currentNumber = -currentNumber;

        sum.setText(String.valueOf(currentNumber));
    }

    public void pressEffect(View view) {}
}