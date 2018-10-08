package com.theah64.da_vinci.pojos;

import android.support.annotation.StringRes;

public class Action {

    private final String icon;

    @StringRes private final int title;

    public Action(String icon, int title) {
        this.icon = icon;
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public int getTitle() {
        return title;
    }
}
