package com.example.theapache64.da_vinci.utils;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.IoniconsModule;

public class DaVinci extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Iconify.with(new IoniconsModule());
    }
}
