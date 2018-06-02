package com.a1101studio.mobile_helper.models;

import java.io.Serializable;

/**
 * Created by andruy94 on 2/8/2017.
 */

public class DefectCheckListItem implements Serializable {
    private CheckBoxItem checkBoxItem;
    private LowItemsModel lowItemsModel;

    public DefectCheckListItem(LowItemsModel lowItemsModel, CheckBoxItem checkBoxItem) {
        this.checkBoxItem = checkBoxItem;
        this.lowItemsModel = lowItemsModel;
    }

    public DefectCheckListItem() {

    }


    public LowItemsModel getLowItemsModels() {
        return lowItemsModel;
    }

    public void setLowItemsModels(LowItemsModel lowItemsModel) {
        this.lowItemsModel = lowItemsModel;
    }

    public CheckBoxItem getCheckBoxItem() {
        return checkBoxItem;
    }

    public void setCheckBoxItem(CheckBoxItem checkBoxItem) {
        this.checkBoxItem = checkBoxItem;
    }
}
