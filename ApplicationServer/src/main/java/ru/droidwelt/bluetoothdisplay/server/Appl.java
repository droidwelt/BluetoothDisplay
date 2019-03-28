package ru.droidwelt.bluetoothdisplay.server;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;


public class Appl extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context context;
    static final String TRANSFER_CODE = "ZZZZZZZZZZZZZZZZZZZZZZZZ";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getBaseContext();
    }


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Appl.context = context;
    }


}

