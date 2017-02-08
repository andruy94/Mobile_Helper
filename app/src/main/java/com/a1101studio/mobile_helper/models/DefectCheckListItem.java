package com.a1101studio.mobile_helper.models;

/**
 * Created by andruy94 on 2/8/2017.
 */

public class DefectCheckListItem {
    private CheckBoxItem checkBoxItem;
    private LowItemsModel[] lowItemsModels;

    public DefectCheckListItem(LowItemsModel[] lowItemsModels, CheckBoxItem checkBoxItem) {
        this.checkBoxItem = checkBoxItem;
        this.lowItemsModels = lowItemsModels;
    }



    public LowItemsModel[] getLowItemsModels() {
        return lowItemsModels;
    }

    public void setLowItemsModels(LowItemsModel[] lowItemsModels) {
        this.lowItemsModels = lowItemsModels;
    }

    public CheckBoxItem getCheckBoxItem() {
        return checkBoxItem;
    }

    public void setCheckBoxItem(CheckBoxItem checkBoxItem) {
        this.checkBoxItem = checkBoxItem;
    }
}
