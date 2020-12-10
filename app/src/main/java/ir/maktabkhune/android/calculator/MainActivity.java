package ir.maktabkhune.android.calculator;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView displayTextView;
    Button oneButton;
    Button twoButton;
    Button threeButton;
    Button fourButton;
    Button fiveButton;
    Button sixButton;
    Button sevenButton;
    Button eightButton;
    Button nineButton;
    Button zeroButton;
    Button multiplicationButton;
    Button divisionButton;
    Button plusButton;
    Button subtractButton;
    Button equalButton;
    Button clearButton;
    float operandOne = 0;
    float operandTwo = 0;
    float operandTwoTemp = 0;
    float result = 0;
    int operandPointer = 1;
    String operator = "null";
    String operatorTemp = "null";
    boolean operandTwoIsEntered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFinder();
        setOnClick();
    }

    void viewFinder() {
        displayTextView = findViewById(R.id.display);
        oneButton = findViewById(R.id.one);
        twoButton = findViewById(R.id.two);
        threeButton = findViewById(R.id.three);
        fourButton = findViewById(R.id.four);
        fiveButton = findViewById(R.id.five);
        sixButton = findViewById(R.id.six);
        sevenButton = findViewById(R.id.seven);
        eightButton = findViewById(R.id.eight);
        nineButton = findViewById(R.id.nine);
        zeroButton = findViewById(R.id.zero);
        multiplicationButton = findViewById(R.id.multiplication);
        divisionButton = findViewById(R.id.division);
        plusButton = findViewById(R.id.plus);
        subtractButton = findViewById(R.id.subtract);
        equalButton = findViewById(R.id.equal);
        clearButton = findViewById(R.id.clear);
    }

    void setOnClick() {
        oneButton.setOnClickListener(this);
        twoButton.setOnClickListener(this);
        threeButton.setOnClickListener(this);
        fourButton.setOnClickListener(this);
        fiveButton.setOnClickListener(this);
        sixButton.setOnClickListener(this);
        sevenButton.setOnClickListener(this);
        eightButton.setOnClickListener(this);
        nineButton.setOnClickListener(this);
        zeroButton.setOnClickListener(this);
        multiplicationButton.setOnClickListener(this);
        divisionButton.setOnClickListener(this);
        plusButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        equalButton.setOnClickListener(this);
        clearButton.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                actionInInputNumber(1);
                break;
            case R.id.two:
                actionInInputNumber(2);
                break;
            case R.id.three:
                actionInInputNumber(3);
                break;
            case R.id.four:
                actionInInputNumber(4);
                break;
            case R.id.five:
                actionInInputNumber(5);
                break;
            case R.id.six:
                actionInInputNumber(6);
                break;
            case R.id.seven:
                actionInInputNumber(7);
                break;
            case R.id.eight:
                actionInInputNumber(8);
                break;
            case R.id.nine:
                actionInInputNumber(9);
                break;
            case R.id.zero:
                actionInInputNumber(0);
                break;
            case R.id.multiplication:
                actionInInputOperator();
                operator = "*";
                break;
            case R.id.division:
                actionInInputOperator();
                operator = "/";
                break;
            case R.id.plus:
                actionInInputOperator();
                operator = "+";
                break;
            case R.id.subtract:
                actionInInputOperator();
                operator = "-";
                break;
            case R.id.equal:
                actionInInputEqual();
                break;
            case R.id.clear:
                displayTextView.setText(" ");
                clearAllData();
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    void actionInInputNumber(int inputInt) {
        if (operator.equals("=")) {
            clearAllData();
            operandOne = inputInt;
            displayTextView.setText(Float.toString(operandOne));
            return;
        }
        switch (operandPointer) {
            case 1:
                operandOne *= 10;
                operandOne += inputInt;
                displayTextView.setText(Float.toString(operandOne));
                break;
            case 2:
                operandTwoIsEntered = true;
                operandTwo *= 10;
                operandTwo += inputInt;
                displayTextView.setText(Float.toString(operandTwo));
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    void actionInInputOperator() {
        switch (operandPointer) {
            case 1:
                operandPointer = 2;
                break;
            case 2://when user enter multi operator case
                if (operandTwoIsEntered) {
                    switch (operator) {
                        case "*":
                            operandOne = operandOne * operandTwo;
                            break;
                        case "/":
                            if (operandTwo == 0) {
                                displayTextView.setText("NaN");
                                clearAllData();
                                return;
                            }
                            operandOne = operandOne / operandTwo;
                            return;
                        case "+":
                            operandOne = operandOne + operandTwo;
                            break;
                        case "-":
                            operandOne = operandOne - operandTwo;
                            break;
                    }
                    operandTwo = 0;
                    displayTextView.setText(Float.toString(operandOne));
                    return;
                }
        }
        displayTextView.setText(" ");
    }

    @SuppressLint("SetTextI18n")
    void actionInInputEqual() {
        switch (operator) {
            case "*":
                result = operandOne * operandTwo;
                break;
            case "/":
                if (operandTwo == 0) {
                    displayTextView.setText("NaN");
                    clearAllData();
                    return;
                }
                result = operandOne / operandTwo;
                break;
            case "+":
                result = operandOne + operandTwo;
                break;
            case "-":
                result = operandOne - operandTwo;
                break;
            case "=": {//when user enter multi = is for repeat last operator and operand
                multiEqualCase();
            }
        }
        if (!operator.equals("=")) {//save last operator and operand for multi = case
            operatorTemp = operator;
            operandTwoTemp = operandTwo;
        }
        operator = "=";
        displayTextView.setText(Float.toString(result));
        operandOne = result;
        operandTwo = 0;
        operandTwoIsEntered = false;
    }

    void clearAllData() {
        operandOne = 0;
        operandTwo = 0;
        operandTwoTemp = 0;
        result = 0;
        operandPointer = 1;
        operator = "null";
        operatorTemp = "null";
        operandTwoIsEntered = false;
    }

    @SuppressLint("SetTextI18n")
    void multiEqualCase() {
        switch (operatorTemp) {
            case "*":
                result = operandOne * operandTwoTemp;
                break;
            case "/":
                if (operandTwoTemp == 0) {
                    displayTextView.setText("NaN");
                    return;
                }
                result = operandOne / operandTwoTemp;
                break;
            case "+":
                result = operandOne + operandTwoTemp;
                break;
            case "-":
                result = operandOne - operandTwoTemp;
                break;
        }
    }
}
