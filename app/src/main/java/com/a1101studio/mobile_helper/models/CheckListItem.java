package com.a1101studio.mobile_helper.models;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */
/**
* Это класс описатель плитки он состоит из:
 * самой плитки-checkBoxItem
 * массив дефектов - defectCheckListItems, который в свою очередь состоит из :
 * чекбокса верхнего уровня - checkBoxItem
 * массив элементов нижнего уровня - lowItemsModels, которые в свою очередь состоят из :
 * массив списков checkBoxItems и его тайтла - lowCheckListItems
 * массив комментариев commentsModels
 *
*
* */
public class CheckListItem {

    public static  CheckListItem[] CreateCheckListitem(String[] title,ArrayList<String[]> desc){
        CheckListItem[] checkListItems=new CheckListItem[title.length];//оглавления
        for(int i=0;i<checkListItems.length;i++){
            CheckListItem[] innerCheckListItems=new CheckListItem[desc.get(i).length];
            for(int j=0;j<innerCheckListItems.length;j++){
                innerCheckListItems[j]=new CheckListItem(desc.get(i)[j],false, checkBoxItem);
            }
            checkListItems[i]=new CheckListItem(title[i],false,innerCheckListItems);
        }
        return checkListItems;

    }
    public static  CheckListItem CreateCheckListitem(String title,
                                                     String[] defectTitles,
                                                     ArrayList<String[]> lowModelsCheckBoxesListTitles,
                                                     ArrayList<String[][]> lowLowCheckBoxesTitles,
                                                     ArrayList<String[]> lowModelsCommentListTitles){
        CheckListItem checkListItem=new CheckListItem();
        CheckBoxItem checkBoxItem=new CheckBoxItem(false,title);
        checkListItem.setCheckBoxItem(checkBoxItem);

        //осталось самое сложное
        DefectCheckListItem[] defectCheckListItems=new DefectCheckListItem[defectTitles.length];
        for (int i=0;i<defectTitles.length;i++){
            defectCheckListItems[i]=new DefectCheckListItem();
            //ставим тайтл
            CheckBoxItem checkBoxItem1=new CheckBoxItem(false,defectTitles[i]);
            defectCheckListItems[i].setCheckBoxItem(checkBoxItem1);
            //идём глубже
            LowItemsModel lowItemsModel=new LowItemsModel();

            LowCheckListItem[] lowCheckListItems=new LowCheckListItem[lowModelsCheckBoxesListTitles.get(i).length];
            for(int j=0;j<lowModelsCheckBoxesListTitles.get(i).length;j++){
                lowCheckListItems[j]=new LowCheckListItem();
                lowCheckListItems[j].setCheckBoxesTitle(lowModelsCheckBoxesListTitles.get(i)[j]);

                CheckBoxItem[] checkBoxItems=new CheckBoxItem[lowLowCheckBoxesTitles.get(i)[j].length];
                for(int k=0;k<lowLowCheckBoxesTitles.get(i)[j].length;k++){
                    checkBoxItems[k]=new CheckBoxItem(false,lowLowCheckBoxesTitles.get(i)[j][k])
                }
                lowCheckListItems[j].setCheckBoxItems(checkBoxItems);
            }
            lowItemsModel.setLowCheckListItems(lowCheckListItems);

            CommentsModel[] commentsModels=new CommentsModel[lowModelsCommentListTitles.get(i).length];
            for(int j=0;j<lowModelsCommentListTitles.get(i).length;i++){
                commentsModels[j[=new CommentsModel(lowModelsCommentListTitles.get(i)[j],"");
            }
            lowItemsModel.setCommentsModels(commentsModels);
            defectCheckListItems[i].setLowItemsModels(lowItemsModel);
        }
        checkListItem.setDefectCheckListItems(defectCheckListItems);
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

    private CheckBoxItem checkBoxItem;
    private DefectCheckListItem[] defectCheckListItems;

    public CheckListItem(CheckBoxItem checkBoxItem, DefectCheckListItem[] defectCheckListItems) {
        this.checkBoxItem = checkBoxItem;
        this.defectCheckListItems = defectCheckListItems;
    }

    public CheckListItem(){

    }
    public CheckBoxItem getCheckBoxItem() {
        return checkBoxItem;
    }

    public void setCheckBoxItem(CheckBoxItem checkBoxItem) {
        this.checkBoxItem = checkBoxItem;
    }

    public DefectCheckListItem[] getDefectCheckListItems() {
        return defectCheckListItems;
    }

    public void setDefectCheckListItems(DefectCheckListItem[] defectCheckListItems) {
        this.defectCheckListItems = defectCheckListItems;
    }
}
