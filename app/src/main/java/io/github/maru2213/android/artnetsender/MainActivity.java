/*
 * Copyright 2021 maru2213
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.maru2213.android.artnetsender;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import androidx.appcompat.app.AlertDialog;
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
    private Button button_period;
    private Button button_plus;
    private Button button_minus;
    private Button button_at;
    private Button button_thru;
    private Button button_full;
    private Button button_zero;
    private Button button_by;
    private Button button_please;
    private Button button_delete;
    private Button button_reset;
    private Button button_setting;
    private Button button_add;
    private Button button_del;
    private Button button_back;
    private Button button_next;

    private String currentMode;

    private byte[] data = new byte[512];

    private List<String> commandList = new ArrayList<>();

    private List<String> IPList = new ArrayList<>();

    private int ipIndex = 0;
    private StringBuilder addingIP = new StringBuilder();

    private ArtNetSenderThread senderThread;

    private final String PLUS = "PLUS";
    private final String MINUS = "MINUS";
    private final String AT = "AT";
    private final String THRU = "THRU";
    private final String FULL = "FULL";
    private final String ZERO = "ZERO";
    private final String BY = "BY";

    private final String NORMAL_MODE = "normal";
    private final String SETTING_MODE = "setting";
    private final String ADDING_MODE = "adding";

    private final String CHANNEL_REGEX = "(([1-9])|([1-9][0-9])|([1-4][0-9]{2})|(50[0-9])|(51[0-2]))";
    private final String VALUE_REGEX = "(([0-9])|([1-9][0-9])|(1[0-9]{2})|(2[0-4][0-9])|(25[0-5]))";
    private final String IP_REGEX = "^(([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([1-9]?[0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";

    public byte[] getData() {
        return data;
    }

    public List<String> getIPList() {
        return IPList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dark);

        currentMode = NORMAL_MODE;

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
        button_period = findViewById(R.id.button_period);
        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_at = findViewById(R.id.button_at);
        button_thru = findViewById(R.id.button_thru);
        button_full = findViewById(R.id.button_full);
        button_zero = findViewById(R.id.button_zero);
        button_by = findViewById(R.id.button_by);
        button_please = findViewById(R.id.button_please);
        button_delete = findViewById(R.id.button_delete);
        button_reset = findViewById(R.id.button_reset);
        button_setting = findViewById(R.id.button_setting);
        button_add = findViewById(R.id.button_add);
        button_del = findViewById(R.id.button_del);
        button_back = findViewById(R.id.button_back);
        button_next = findViewById(R.id.button_next);

        button_period.setEnabled(false);
        button_add.setEnabled(false);
        button_del.setEnabled(false);
        button_back.setEnabled(false);
        button_next.setEnabled(false);

        new AlertDialog.Builder(this)
                .setTitle("Choose the theme")
                .setMessage("Which theme do you want to use?")
                .setPositiveButton("Dark", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.activity_main_dark);
                    }
                })
                .setNegativeButton("Light", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.activity_main_light);
                    }
                })
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        senderThread = new ArtNetSenderThread(this);
        senderThread.start();
        updateTextView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        senderThread.stopThread();
    }

    public void onClick(View view) {
        switch (currentMode) {
            case NORMAL_MODE:
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
                    case R.id.button_period:
                        //nop
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
                        break;
                    case R.id.button_delete:
                        if (commandList.size() > 0) {
                            commandList.remove(commandList.size() - 1);
                        }
                        break;
                    case R.id.button_reset:
                        reset();
                        break;
                    case R.id.button_setting:
                        switchSettingMode();
                        break;
                    case R.id.button_add:
                        //nop
                        break;
                    case R.id.button_del:
                        //nop
                        break;
                    case R.id.button_back:
                        //nop
                        break;
                    case R.id.button_next:
                        //nop
                        break;
                }
                break;

            case SETTING_MODE:
                switch (view.getId()) {
                    case R.id.button_0:
                        //nop
                        break;
                    case R.id.button_1:
                        //nop
                        break;
                    case R.id.button_2:
                        //nop
                        break;
                    case R.id.button_3:
                        //nop
                        break;
                    case R.id.button_4:
                        //nop
                        break;
                    case R.id.button_5:
                        //nop
                        break;
                    case R.id.button_6:
                        //nop
                        break;
                    case R.id.button_7:
                        //nop
                        break;
                    case R.id.button_8:
                        //nop
                        break;
                    case R.id.button_9:
                        //nop
                        break;
                    case R.id.button_period:
                        //nop
                        break;
                    case R.id.button_plus:
                        //nop
                        break;
                    case R.id.button_minus:
                        //nop
                        break;
                    case R.id.button_at:
                        //nop
                        break;
                    case R.id.button_thru:
                        //nop
                        break;
                    case R.id.button_full:
                        //nop
                        break;
                    case R.id.button_zero:
                        //nop
                        break;
                    case R.id.button_by:
                        //nop
                        break;
                    case R.id.button_please:
                        //nop
                        break;
                    case R.id.button_delete:
                        //nop
                        break;
                    case R.id.button_reset:
                        //nop
                        break;
                    case R.id.button_setting:
                        switchSettingMode();
                        break;
                    case R.id.button_add:
                        switchAddingMode();
                        break;
                    case R.id.button_del:
                        if (ipIndex < IPList.size()) {
                            IPList.remove(ipIndex);
                            if (ipIndex > IPList.size() - 1) {
                                ipIndex = IPList.size() - 1;
                            }
                        }
                        break;
                    case R.id.button_back:
                        if (IPList.size() > 0) {
                            ipIndex--;
                            if (ipIndex < 0) {
                                ipIndex = IPList.size() - 1;
                            }
                        }
                        break;
                    case R.id.button_next:
                        if (IPList.size() > 0) {
                            ipIndex++;
                            if (ipIndex > IPList.size() - 1) {
                                ipIndex = 0;
                            }
                        }
                        break;
                }
                break;

            case ADDING_MODE:
                switch (view.getId()) {
                    case R.id.button_0:
                        addingIP.append("0");
                        break;
                    case R.id.button_1:
                        addingIP.append("1");
                        break;
                    case R.id.button_2:
                        addingIP.append("2");
                        break;
                    case R.id.button_3:
                        addingIP.append("3");
                        break;
                    case R.id.button_4:
                        addingIP.append("4");
                        break;
                    case R.id.button_5:
                        addingIP.append("5");
                        break;
                    case R.id.button_6:
                        addingIP.append("6");
                        break;
                    case R.id.button_7:
                        addingIP.append("7");
                        break;
                    case R.id.button_8:
                        addingIP.append("8");
                        break;
                    case R.id.button_9:
                        addingIP.append("9");
                        break;
                    case R.id.button_period:
                        addingIP.append(".");
                        break;
                    case R.id.button_plus:
                        //nop
                        break;
                    case R.id.button_minus:
                        //nop
                        break;
                    case R.id.button_at:
                        //nop
                        break;
                    case R.id.button_thru:
                        //nop
                        break;
                    case R.id.button_full:
                        //nop
                        break;
                    case R.id.button_zero:
                        //nop
                        break;
                    case R.id.button_by:
                        //nop
                        break;
                    case R.id.button_please:
                        String ip = addingIP.toString();
                        if (Pattern.compile(IP_REGEX).matcher(ip).matches()) {
                            IPList.add(addingIP.toString());
                            addingIP = new StringBuilder();
                            switchAddingMode();
                        } else {
                            Toast.makeText(this, "Invalid IP address", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.button_delete:
                        if (addingIP.length() > 0) {
                            addingIP.deleteCharAt(addingIP.length() - 1);
                        }
                        break;
                    case R.id.button_reset:
                        //nop
                        break;
                    case R.id.button_setting:
                        switchSettingMode();
                        break;
                    case R.id.button_add:
                        switchAddingMode();
                        break;
                    case R.id.button_del:
                        //nop
                        break;
                    case R.id.button_back:
                        //nop
                        break;
                    case R.id.button_next:
                        //nop
                        break;
                }
                break;

            default:
                break;
        }
        updateTextView();
    }

    private void switchSettingMode() {
        boolean willBeNormalMode = !currentMode.equals(NORMAL_MODE);

        currentMode = willBeNormalMode ? NORMAL_MODE : SETTING_MODE;
        ipIndex = 0;
        button_0.setEnabled(willBeNormalMode);
        button_1.setEnabled(willBeNormalMode);
        button_2.setEnabled(willBeNormalMode);
        button_3.setEnabled(willBeNormalMode);
        button_4.setEnabled(willBeNormalMode);
        button_5.setEnabled(willBeNormalMode);
        button_6.setEnabled(willBeNormalMode);
        button_7.setEnabled(willBeNormalMode);
        button_8.setEnabled(willBeNormalMode);
        button_9.setEnabled(willBeNormalMode);
        button_period.setEnabled(false);
        button_plus.setEnabled(willBeNormalMode);
        button_minus.setEnabled(willBeNormalMode);
        button_at.setEnabled(willBeNormalMode);
        button_thru.setEnabled(willBeNormalMode);
        button_full.setEnabled(willBeNormalMode);
        button_zero.setEnabled(willBeNormalMode);
        button_by.setEnabled(willBeNormalMode);
        button_please.setEnabled(willBeNormalMode);
        button_delete.setEnabled(willBeNormalMode);
        button_reset.setEnabled(willBeNormalMode);
        button_setting.setEnabled(true);
        button_add.setEnabled(!willBeNormalMode);
        button_del.setEnabled(!willBeNormalMode);
        button_back.setEnabled(!willBeNormalMode);
        button_next.setEnabled(!willBeNormalMode);
    }

    private void switchAddingMode() {
        boolean willBeAddingMode = !currentMode.equals(ADDING_MODE);

        currentMode = willBeAddingMode ? ADDING_MODE : SETTING_MODE;
        ipIndex = 0;
        addingIP = new StringBuilder();
        button_0.setEnabled(willBeAddingMode);
        button_1.setEnabled(willBeAddingMode);
        button_2.setEnabled(willBeAddingMode);
        button_3.setEnabled(willBeAddingMode);
        button_4.setEnabled(willBeAddingMode);
        button_5.setEnabled(willBeAddingMode);
        button_6.setEnabled(willBeAddingMode);
        button_7.setEnabled(willBeAddingMode);
        button_8.setEnabled(willBeAddingMode);
        button_9.setEnabled(willBeAddingMode);
        button_period.setEnabled(willBeAddingMode);
        button_plus.setEnabled(false);
        button_minus.setEnabled(false);
        button_at.setEnabled(false);
        button_thru.setEnabled(false);
        button_full.setEnabled(false);
        button_zero.setEnabled(false);
        button_by.setEnabled(false);
        button_please.setEnabled(willBeAddingMode);
        button_delete.setEnabled(willBeAddingMode);
        button_reset.setEnabled(false);
        button_setting.setEnabled(true);
        button_add.setEnabled(true);
        button_del.setEnabled(!willBeAddingMode);
        button_back.setEnabled(!willBeAddingMode);
        button_next.setEnabled(!willBeAddingMode);
    }

    private void updateTextView() {
        StringBuilder stringBuilder = new StringBuilder();
        switch (currentMode) {
            case NORMAL_MODE:
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
                break;

            case SETTING_MODE:
                if (IPList.size() > 0) {
                    stringBuilder.append(IPList.get(ipIndex));
                } else {
                    stringBuilder.append("No addresses");
                }
                break;

            case ADDING_MODE:
                stringBuilder.append("ADD: ").append(addingIP.toString());
                break;

            default:
                break;
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
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Invalid command", Toast.LENGTH_SHORT).show();
        }
        commandList.clear();
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
                case PLUS:
                    int ratio_p;
                    try {
                        ratio_p = Integer.parseInt(commandArray[2]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        throw new IllegalStateException("There should be a number after PLUS");
                    }
                    int currentValue_p = data[startChannel - 1] & 0xFF;
                    int intValue_p = Math.round(currentValue_p + ((float) currentValue_p * ratio_p / 100));
                    if (intValue_p > 255) {
                        intValue_p = 255;
                    }
                    value = (byte) intValue_p;
                    break;
                case MINUS:
                    int ratio_m;
                    try {
                        ratio_m = Integer.parseInt(commandArray[2]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        throw new IllegalStateException("There should be a number after MINUS");
                    }
                    int currentValue_m = data[startChannel - 1] & 0xFF;
                    int intValue_m = Math.round(currentValue_m - ((float) currentValue_m * ratio_m / 100));
                    if (intValue_m < 0) {
                        intValue_m = 0;
                    }
                    value = (byte) intValue_m;
                    break;
                default:
                    throw new IllegalStateException("The second element must be FULL, ZERO, AT, PLUS, or MINUS");
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

            int startValue = 0;
            int endValue = -1;
            int ratio = 0;
            int isPlusOrMinus = 0;
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

                                if (!Pattern.compile(VALUE_REGEX).matcher(commandArray[i + 3]).matches()) {
                                    throw new IllegalStateException("Invalid value");
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
                case PLUS:
                    isPlusOrMinus = 1;
                    try {
                        ratio = Integer.parseInt(commandArray[i + 1]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        throw new IllegalStateException("There should be a number after PLUS");
                    }
                    break;
                case MINUS:
                    isPlusOrMinus = -1;
                    try {
                        ratio = Integer.parseInt(commandArray[i + 1]);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        throw new IllegalStateException("There should be a number after MINUS");
                    }
                    break;
                default:
                    throw new IllegalStateException("There is no FULL, ZERO, AT, PLUS, or MINUS");
            }

            if (endValue < 0) {
                endValue = startValue;
            }

            int channelRange = endChannel - startChannel + 1;
            int channelCount = (int) Math.ceil((double) channelRange / by);
            int dataRange = endValue - startValue;
            int value;
            for (int j = 0; j < channelCount; j++) {
                switch (isPlusOrMinus) {
                    case 0:
                        value = startValue + (int) Math.round((double) dataRange * j / (channelCount - 1));
                        if (j == channelCount - 1) {
                            value = endValue;
                        }
                        break;
                    case 1:
                        int currentValue_p = data[startChannel + (by * j) - 1] & 0xFF;
                        value = Math.round(currentValue_p + ((float) currentValue_p * ratio / 100));
                        if (value > 255) {
                            value = 255;
                        }
                        break;
                    case -1:
                        int currentValue_m = data[startChannel + (by * j) - 1] & 0xFF;
                        value = Math.round(currentValue_m - ((float) currentValue_m * ratio / 100));
                        if (value < 0) {
                            value = 0;
                        }
                        break;
                    default:
                        throw new IllegalStateException("error!");
                }
                data[startChannel + (by * j) - 1] = (byte) value;
            }
        }
    }
}