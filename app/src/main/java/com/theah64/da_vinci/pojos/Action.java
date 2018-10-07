package com.theah64.da_vinci.pojos;

public class Action {

    private final String icon;
    private final String title;

    public Action(String icon, String title) {
        this.icon = icon;
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
