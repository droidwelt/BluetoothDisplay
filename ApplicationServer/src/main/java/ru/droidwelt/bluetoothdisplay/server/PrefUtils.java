package ru.droidwelt.bluetoothdisplay.server;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;

import java.util.Locale;


public class PrefUtils {

    private static final String SAVED_PREF_KEY_COLOR = "saved_state_key_color";
    private static final int INITIAL_COLOR = 0xFFFF8000;

    public  String colorHex(int color) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
    }


    static int getPrefColor() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Appl.getContext());
        return sp.getInt(SAVED_PREF_KEY_COLOR, INITIAL_COLOR);
    }

    static void setPrefColor(int c) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(Appl.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(SAVED_PREF_KEY_COLOR, c);
        editor.apply();
    }





}
