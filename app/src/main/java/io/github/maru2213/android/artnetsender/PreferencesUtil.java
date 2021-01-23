/*
 * ArtNetSender - An Android app sending artnet signals to local network
 *  Copyright (C) 2021  maru2213
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
