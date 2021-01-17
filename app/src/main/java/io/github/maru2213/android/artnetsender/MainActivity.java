package io.github.maru2213.android.artnetsender;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView command_view;
    private Button button_0;
    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    private Button button_plus;
    private Button button_minus;
    private Button button_at;
    private Button button_thru;
    private Button button_full;
    private Button button_zero;
    private Button button_by;
    private Button button_please;
    private Button button_esc;
    private Button button_reset;

    private List<String> commandList = new ArrayList<>();

    private final String PLUS = "PLUS";
    private final String MINUS = "MINUS";
    private final String AT = "AT";
    private final String THRU = "THRU";
    private final String FULL = "FULL";
    private final String ZERO = "ZERO";
    private final String BY = "BY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        command_view = findViewById(R.id.command_view);
        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_at = findViewById(R.id.button_at);
        button_thru = findViewById(R.id.button_thru);
        button_full = findViewById(R.id.button_full);
        button_zero = findViewById(R.id.button_zero);
        button_by = findViewById(R.id.button_by);
        button_please = findViewById(R.id.button_please);
        button_esc = findViewById(R.id.button_esc);
        button_reset = findViewById(R.id.button_reset);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_0:
                commandList.add("0");
                break;
            case R.id.button_1:
                commandList.add("1");
                break;
            case R.id.button_2:
                commandList.add("2");
                break;
            case R.id.button_3:
                commandList.add("3");
                break;
            case R.id.button_4:
                commandList.add("4");
                break;
            case R.id.button_5:
                commandList.add("5");
                break;
            case R.id.button_6:
                commandList.add("6");
                break;
            case R.id.button_7:
                commandList.add("7");
                break;
            case R.id.button_8:
                commandList.add("8");
                break;
            case R.id.button_9:
                commandList.add("9");
                break;
            case R.id.button_plus:
                commandList.add(PLUS);
                break;
            case R.id.button_minus:
                commandList.add(MINUS);
                break;
            case R.id.button_at:
                commandList.add(AT);
                break;
            case R.id.button_thru:
                commandList.add(THRU);
                break;
            case R.id.button_full:
                commandList.add(FULL);
                break;
            case R.id.button_zero:
                commandList.add(ZERO);
                break;
            case R.id.button_by:
                commandList.add(BY);
                break;
            case R.id.button_please:
                please();
                commandList.clear();
                break;
            case R.id.button_esc:
                commandList.clear();
                break;
            case R.id.button_reset:
                reset();
                commandList.clear();
                break;
        }
        updateTextView();
    }

    private void updateTextView() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < commandList.size(); i++) {
            String s = commandList.get(i);
            if (isSign(s)) {
                if (i > 0) {
                    if (!isSign(commandList.get(i - 1))) {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append(s).append(" ");
            } else {
                stringBuilder.append(s);
            }
        }
        command_view.setText(stringBuilder.toString());
    }

    private boolean isSign(String s) {
        return s.equals(PLUS) || s.equals(MINUS) || s.equals(AT) || s.equals(THRU) || s.equals(FULL) || s.equals(ZERO) || s.equals(BY);
    }

    private void please() {
        //TODO
    }

    private void reset() {
        //TODO
    }
}