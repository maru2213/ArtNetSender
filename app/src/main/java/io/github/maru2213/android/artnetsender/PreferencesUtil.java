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

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public class PreferencesUtil {

    public enum Keys {
        Theme;
    }

    private static final String PREF_NAME = "DataSave";

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static void putString(Context context, Keys key, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(key.name(), value);
        editor.apply();
    }

    public static String getString(Context context, Keys key) {
        return getString(context, key, "");
    }

    public static String getString(Context context, Keys key, String defValue) {
        return getSharedPreferences(context).getString(key.name(), defValue);
    }

    public static void putStringSet(Context context, Keys key, Set<String> values) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putStringSet(key.name(), values);
        editor.apply();
    }

    public static Set<String> getStringSet(Context context, Keys key) {
        return getStringSet(context, key, null);
    }

    public static Set<String> getStringSet(Context context, Keys key, Set<String> defValues) {
        return getSharedPreferences(context).getStringSet(key.name(), defValues);
    }

    public static void putInt(Context context, Keys key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key.name(), value);
        editor.apply();
    }

    public static int getInt(Context context, Keys key) {
        return getInt(context, key, 0);
    }

    public static int getInt(Context context, Keys key, int defValue) {
        return getSharedPreferences(context).getInt(key.name(), defValue);
    }

    public static void putLong(Context context, Keys key, long value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putLong(key.name(), value);
        editor.apply();
    }

    public static long getLong(Context context, Keys key) {
        return getLong(context, key, 0);
    }

    public static long getLong(Context context, Keys key, long defValue) {
        return getSharedPreferences(context).getLong(key.name(), defValue);
    }

    public static void putFloat(Context context, Keys key, float value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putFloat(key.name(), value);
        editor.apply();
    }

    public static float getFloat(Context context, Keys key) {
        return getFloat(context, key, 0);
    }

    public static float getFloat(Context context, Keys key, float defValue) {
        return getSharedPreferences(context).getFloat(key.name(), defValue);
    }

    public static void putBoolean(Context context, Keys key, boolean value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putBoolean(key.name(), value);
        editor.apply();
    }

    public static boolean getBoolean(Context context, Keys key) {
        return getBoolean(context, key, false);
    }

    public static boolean getBoolean(Context context, Keys key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key.name(), defValue);
    }

    public static void remove(Context context, Keys key) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.remove(key.name());
        editor.apply();
    }

    public static void removeAll(Context context) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.clear();
        editor.apply();
    }
}
