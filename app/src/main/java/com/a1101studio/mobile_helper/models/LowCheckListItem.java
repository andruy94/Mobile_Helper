package com.a1101studio.mobile_helper.models;

import java.util.ArrayList;

/**
 * Created by andruy94 on 2/8/2017.
 */

public class LowCheckListItem {
    private String checkBoxesTitle;
    private CheckBoxItem[] checkBoxItems;

    public LowCheckListItem(String checkBoxesTitle, CheckBoxItem[] checkBoxItems) {
        this.checkBoxesTitle = checkBoxesTitle;
        this.checkBoxItems = checkBoxItems;
    }

    public String getCheckBoxesTitle() {
        return checkBoxesTitle;
    }

    public void setCheckBoxesTitle(String checkBoxesTitle) {
        checkBoxesTitle = checkBoxesTitle;
    }


    public CheckBoxItem[] getCheckBoxItems() {
        return checkBoxItems;
    }

    public void setCheckBoxItems(CheckBoxItem[] checkBoxItems) {
        this.checkBoxItems = checkBoxItems;
    }
}
