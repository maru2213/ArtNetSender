package io.github.maru2213.android.artnetsender;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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

    private byte[] data = new byte[512];

    private List<String> commandList = new ArrayList<>();

    private final String PLUS = "PLUS";
    private final String MINUS = "MINUS";
    private final String AT = "AT";
    private final String THRU = "THRU";
    private final String FULL = "FULL";
    private final String ZERO = "ZERO";
    private final String BY = "BY";

    private final String CHANNEL_REGEX = "(([1-9])|([1-9][0-9])|([1-4][0-9]{2})|(50[0-9])|(51[0-2]))";
    private final String VALUE_REGEX = "(([0-9])|([1-9][0-9])|(1[0-9]{2})|(2[0-4][0-9])|(25[0-5]))";

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

    private void please() {
        if (commandList.size() < 1) {
            return;
        }
        try {
            interpretCommand();
            System.out.println(Arrays.toString(data));
            //TODO dataの送信
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid command", Toast.LENGTH_SHORT).show();
        }
    }

    private void reset() {
        commandList.clear();
        commandList.add("1");
        commandList.add(THRU);
        commandList.add("5");
        commandList.add("1");
        commandList.add("2");
        commandList.add(ZERO);
        please();
    }

    private boolean isSign(String s) {
        return s.equals(PLUS) || s.equals(MINUS) || s.equals(AT) || s.equals(THRU) || s.equals(FULL) || s.equals(ZERO) || s.equals(BY);
    }

    private void interpretCommand() throws Exception {
        List<String> newCommendList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : commandList) {
            if (isSign(s)) {
                if (stringBuilder.length() > 0) {
                    newCommendList.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                }
                newCommendList.add(s);
            } else {
                stringBuilder.append(s);
            }
        }
        if (stringBuilder.length() > 0) {
            newCommendList.add(stringBuilder.toString());
        }

        if (newCommendList.size() < 1) {
            throw new IllegalStateException("The commend is empty");
        }

        String[] commandArray = new String[newCommendList.size()];
        newCommendList.toArray(commandArray);
        System.out.println(Arrays.toString(commandArray));

        if (isSign(commandArray[0])) {
            throw new IllegalStateException("The first element cannot be a sign");
        }

        if (!Pattern.compile(CHANNEL_REGEX).matcher(commandArray[0]).matches()) {
            throw new IllegalStateException("Invalid channel");
        }

        int startChannel;
        try {
            startChannel = Integer.parseInt(commandArray[0]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalStateException("Parse failed");
        }

        if (!commandArray[1].equals(THRU)) {
            //One channel
            byte value;
            switch (commandArray[1]) {
                case FULL:
                    value = (byte) 0xFF;
                    break;
                case ZERO:
                    value = (byte) 0x00;
                    break;
                case AT:
                    switch (commandArray[2]) {
                        case FULL:
                            value = (byte) 0xFF;
                            break;
                        case ZERO:
                            value = (byte) 0x00;
                            break;
                        default:
                            if (!Pattern.compile(VALUE_REGEX).matcher(commandArray[2]).matches()) {
                                throw new IllegalStateException("Invalid value");
                            }
                            int intValue;
                            try {
                                intValue = Integer.parseInt(commandArray[2]);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                throw new IllegalStateException("Parse failed");
                            }
                            value = (byte) intValue;
                    }
                    break;
                default:
                    throw new IllegalStateException("The second element must be FULL, ZERO, or AT");
            }

            data[startChannel - 1] = value;
        } else {
            //THRU
            if (!Pattern.compile(CHANNEL_REGEX).matcher(commandArray[2]).matches()) {
                throw new IllegalStateException("Invalid channel");
            }

            int endChannel;
            try {
                endChannel = Integer.parseInt(commandArray[2]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new IllegalStateException("Parse failed");
            }

            boolean isByUsed = false;
            int by = 1;
            if (commandArray[3].equals(BY)) {
                isByUsed = true;
                try {
                    by = Integer.parseInt(commandArray[4]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    throw new IllegalStateException("Parse failed");
                }
                if (by < 1 || by > 512) {
                    throw new IllegalStateException("Invalid parameter");
                }
            }

            int startValue;
            int endValue = -1;
            int i = isByUsed ? 5 : 3;
            switch (commandArray[i]) {
                case FULL:
                    startValue = 255;
                    break;
                case ZERO:
                    startValue = 0;
                    break;
                case AT:
                    switch (commandArray[i + 1]) {
                        case FULL:
                            startValue = 255;
                            break;
                        case ZERO:
                            startValue = 0;
                            break;
                        default:
                            if (!Pattern.compile(VALUE_REGEX).matcher(commandArray[2]).matches()) {
                                throw new IllegalStateException("Invalid value");
                            }
                            try {
                                startValue = Integer.parseInt(commandArray[i + 1]);
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                throw new IllegalStateException("Parse failed");
                            }

                            if (commandArray.length > i + 2) {
                                if (!commandArray[i + 2].equals(THRU)) {
                                    throw new IllegalStateException("There must be THRU after AT <value>");
                                }

                                try {
                                    endValue = Integer.parseInt(commandArray[i + 3]);
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    throw new IllegalStateException("There should be a number after THRU");
                                }
                            }
                    }
                    break;
                default:
                    throw new IllegalStateException("There is no FULL, ZERO, or AT");
            }

            if (endValue < 0) {
                endValue = startValue;
            }

            int channelRange = endChannel - startChannel + 1;
            int channelCount = (int) Math.ceil((double) channelRange / by);
            int dataRange = endValue - startValue;
            for (int j = 0; j < channelCount; j++) {
                int value = startValue + (int) Math.round((double) dataRange * j / (channelCount - 1));
                if (j == channelCount - 1) {
                    value = endValue;
                }
                data[startChannel + (by * j) - 1] = (byte) value;
            }
        }
    }
}