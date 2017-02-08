package com.a1101studio.mobile_helper.models;

/**
 * Created by andruy94 on 2/8/2017.
 */

public class CheckBoxItem {
    private boolean isChecked;
    private String title;

    public CheckBoxItem(boolean isChecked, String title) {
        this.isChecked = isChecked;
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
