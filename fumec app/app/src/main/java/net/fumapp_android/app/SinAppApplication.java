package net.fumapp_android.app;

import android.app.Application;

/**
 * Created by Rodrigo Lopes Martins on 15/01/17.
 */

public class SinAppApplication extends Application{

    private static SinAppApplication instance;

    public static SinAppApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        instance = this;
    }
}
