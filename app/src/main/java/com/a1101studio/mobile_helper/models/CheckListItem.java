package com.a1101studio.mobile_helper.models;

import com.a1101studio.mobile_helper.List;

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
    public String getCheckedItems(){
        StringBuilder stringBuilder=new StringBuilder();

        if (isChecked){
            stringBuilder.append(description+": ");
                for (CheckListItem checkListItemLow:checkListItems){
                    if(checkListItemLow.isChecked())
                        stringBuilder.append(checkListItemLow.description+",");
                }
        }


        return stringBuilder.toString();
    }

    private String description;
    private boolean isChecked;
    private DefectCheckListItem[] defectCheckListItems;

    public CheckListItem(String description, boolean isChecked, DefectCheckListItem[] defectCheckListItems) {
        this.description = description;
        this.isChecked = isChecked;
        this.defectCheckListItems = defectCheckListItems;
    }

    public CheckListItem(String description, boolean isChecked) {
        this.description = description;
        this.isChecked = isChecked;
        this.defectCheckListItems = null;
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


    public DefectCheckListItem[] getDefectCheckListItems() {
        return defectCheckListItems;
    }

    public void setDefectCheckListItems(DefectCheckListItem[] defectCheckListItems) {
        this.defectCheckListItems = defectCheckListItems;
    }
}
