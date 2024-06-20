package com.example.proectdistant;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proectdistant.R;

public class MainActivity extends AppCompatActivity {

    private Button One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Zero;
    private Button Minus, Plus, Division, Multiply, Result, Clear, SquareRoot, Square, Percentage;
    private TextView Formula, EndResult;
    private char Action;
    private double ResultValue = Double.NaN;
    private double Value = Double.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();

        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };

        One.setOnClickListener(numberClickListener);
        Two.setOnClickListener(numberClickListener);
        Three.setOnClickListener(numberClickListener);
        Four.setOnClickListener(numberClickListener);
        Five.setOnClickListener(numberClickListener);
        Six.setOnClickListener(numberClickListener);
        Seven.setOnClickListener(numberClickListener);
        Eight.setOnClickListener(numberClickListener);
        Nine.setOnClickListener(numberClickListener);
        Zero.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(ResultValue) + Action);
                EndResult.setText(null);
            }
        };

        Plus.setOnClickListener(actionClickListener);
        Minus.setOnClickListener(actionClickListener);
        Division.setOnClickListener(actionClickListener);
        Multiply.setOnClickListener(actionClickListener);
        Square.setOnClickListener(actionClickListener);
        Percentage.setOnClickListener(actionClickListener);

        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(ResultValue));
                Formula.setText(null);
            }
        });

        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultValue = Double.NaN;
                Value = Double.NaN;
                Formula.setText("");
                EndResult.setText("");
            }
        });

        SquareRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Formula.setText("√");
            }
        });
    }
    private void setupView() {
        One = findViewById(R.id.One);
        Two = findViewById(R.id.Two);
        Three = findViewById(R.id.Three);
        Four = findViewById(R.id.Four);
        Five = findViewById(R.id.Five);
        Six = findViewById(R.id.Six);
        Seven = findViewById(R.id.Seven);
        Eight = findViewById(R.id.Eight);
        Nine = findViewById(R.id.Nine);
        Zero = findViewById(R.id.Zero);
        Minus = findViewById(R.id.Minus);
        Plus = findViewById(R.id.Plus);
        Result = findViewById(R.id.Result);
        Division = findViewById(R.id.Division);
        Multiply = findViewById(R.id.Multiply);
        EndResult = findViewById(R.id.EndResult);
        Formula = findViewById(R.id.Formula);
        Clear = findViewById(R.id.Clear);
        SquareRoot = findViewById(R.id.Koren);
        Square = findViewById(R.id.Kvadrad);
        Percentage = findViewById(R.id.Proz);
    }

    private void calculate() {
        String textFormula = Formula.getText().toString();
        if (textFormula.isEmpty()) {
            return;
        }

        try {
            if (textFormula.charAt(0) == '√') {
                Value = Double.parseDouble(textFormula.substring(1));
                ResultValue = Math.sqrt(Value);
            } else {
                int index = textFormula.indexOf(Action);
                if (index != -1) {
                    String numberValue = textFormula.substring(index + 1);
                    if (!numberValue.isEmpty()) {
                        Value = Double.parseDouble(numberValue);
                    }

                    switch (Action) {
                        case '/':
                            if (Value == 0) {
                                Toast.makeText(MainActivity.this, "На ноль делить нельзя", Toast.LENGTH_SHORT).show();
                                ResultValue = Double.POSITIVE_INFINITY;
                            } else {
                                ResultValue /= Value;
                            }
                            break;
                        case '*':
                            ResultValue *= Value;
                            break;
                        case '+':
                            ResultValue += Value;
                            break;
                        case '-':
                            ResultValue -= Value;
                            break;
                        case '=':
                            ResultValue = Value;
                            break;
                        case '^':
                            ResultValue = Math.pow(ResultValue, 2);
                            break;
                        case '%':
                            ResultValue = ResultValue * 0.01;
                            break;
                    }
                } else {
                    ResultValue = Double.parseDouble(textFormula);
                }
            }
        } catch (NumberFormatException e) {
            ResultValue = Double.NaN;
        }

        EndResult.setText(String.valueOf(ResultValue));
        Formula.setText("");
    }
}