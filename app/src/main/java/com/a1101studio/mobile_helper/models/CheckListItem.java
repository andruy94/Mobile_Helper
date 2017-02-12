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


    public static  CheckListItem CreateCheckListitem(String title,//Тайтл плитки
                                                     String[] defectTitles,//список дефектов
                                                     ArrayList<String[]> lowModelsCheckBoxesListTitles,//список заголовка блоков чекбоксов
                                                     ArrayList<String[][]> lowLowCheckBoxesTitles,//список массива заголовока чекбоксов
                                                     ArrayList<String[]> lowModelsCommentListTitles){//список тайтлов к комментам
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
            LowCheckListItem[] lowCheckListItems=null;
            if(lowModelsCheckBoxesListTitles!=null) {
                lowCheckListItems = new LowCheckListItem[lowModelsCheckBoxesListTitles.get(i).length];
                for (int j = 0; j < lowModelsCheckBoxesListTitles.get(i).length; j++) {
                    lowCheckListItems[j] = new LowCheckListItem();
                    lowCheckListItems[j].setCheckBoxesTitle(lowModelsCheckBoxesListTitles.get(i)[j]);
                    String[] s=lowModelsCheckBoxesListTitles.get(i);
                    String s1=s[j];
                    String lowCheckListItemsstr=lowCheckListItems[j].getCheckBoxesTitle();
                    CheckBoxItem[] checkBoxItems = new CheckBoxItem[lowLowCheckBoxesTitles.get(i)[j].length];
                    for (int k = 0; k < lowLowCheckBoxesTitles.get(i)[j].length; k++) {
                        checkBoxItems[k] = new CheckBoxItem(false, lowLowCheckBoxesTitles.get(i)[j][k]);
                    }
                    lowCheckListItems[j].setCheckBoxItems(checkBoxItems);
                }
            }
            lowItemsModel.setLowCheckListItems(lowCheckListItems);

            CommentsModel[] commentsModels=null;
            if(lowModelsCommentListTitles!=null) {
                commentsModels = new CommentsModel[lowModelsCommentListTitles.get(i).length];
                for (int j = 0; j < lowModelsCommentListTitles.get(i).length; j++) {
                    commentsModels[j] = new CommentsModel(lowModelsCommentListTitles.get(i)[j], "");
                }
            }
            lowItemsModel.setCommentsModels(commentsModels);
            defectCheckListItems[i].setLowItemsModels(lowItemsModel);
        }
        checkListItem.setDefectCheckListItems(defectCheckListItems);
        return checkListItem;
    }




    public String getCheckedItems(){
        StringBuilder stringBuilder=new StringBuilder();
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
