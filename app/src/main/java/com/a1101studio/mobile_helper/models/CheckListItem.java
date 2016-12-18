package com.a1101studio.mobile_helper.models;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class CheckListItem {

    public static  CheckListItem[] CreateCheckListitem(String[] title,ArrayList<String[]> desc){
        CheckListItem[] checkListItems=new CheckListItem[title.length];//оглавления
        for(int i=0;i<checkListItems.length;i++){
            CheckListItem[] innerCheckListItems=new CheckListItem[desc.get(i).length];
            for(int j=0;j<innerCheckListItems.length;j++){
                innerCheckListItems[j]=new CheckListItem(desc.get(i)[j],false);
            }
            checkListItems[i]=new CheckListItem(title[i],false,innerCheckListItems);
        }
        return checkListItems;

    }

    private String description;
    private boolean isChecked;
    private CheckListItem[] checkListItems;

    public CheckListItem(String description, boolean isChecked, CheckListItem[] checkListItems) {
        this.description = description;
        this.isChecked = isChecked;
        this.checkListItems = checkListItems;
    }

    public CheckListItem(String description, boolean isChecked) {
        this.description = description;
        this.isChecked = isChecked;
        this.checkListItems = null;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CheckListItem[] getCheckListItems() {
        return checkListItems;
    }

    public void setCheckListItems(CheckListItem[] checkListItems) {
        this.checkListItems = checkListItems;
    }
}
