package com.a1101studio.mobile_helper.models;

import java.io.Serializable;
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
 */
public class Detail implements Serializable {


    public static Detail CreateDetail(String title,//Тайтл плитки
                                      String[] defectTitles,//список дефектов
                                      ArrayList<String[]> lowModelsCheckBoxesListTitles,//список заголовка блоков чекбоксов
                                      ArrayList<String[][]> lowLowCheckBoxesTitles,//список массива заголовока чекбоксов
                                      ArrayList<String[]> lowModelsCommentListTitles) {//список тайтлов к комментам
        Detail detail = new Detail();
        CheckBoxItem checkBoxItem = new CheckBoxItem(false, title);
        detail.setCheckBoxItem(checkBoxItem);

        //осталось самое сложное
        DefectCheckListItem[] defectCheckListItems = new DefectCheckListItem[defectTitles.length];
        for (int i = 0; i < defectTitles.length; i++) {
            defectCheckListItems[i] = new DefectCheckListItem();
            //ставим тайтл
            CheckBoxItem checkBoxItem1 = new CheckBoxItem(false, defectTitles[i]);
            defectCheckListItems[i].setCheckBoxItem(checkBoxItem1);
            //идём глубже
            LowItemsModel lowItemsModel = new LowItemsModel();
            LowCheckListItem[] lowCheckListItems = null;
            if (lowModelsCheckBoxesListTitles != null) {
                if (i < lowModelsCheckBoxesListTitles.size()) {
                    lowCheckListItems = new LowCheckListItem[lowModelsCheckBoxesListTitles.get(i).length];
                    for (int j = 0; j < lowModelsCheckBoxesListTitles.get(i).length; j++) {
                        lowCheckListItems[j] = new LowCheckListItem();
                        lowCheckListItems[j].setCheckBoxesTitle(lowModelsCheckBoxesListTitles.get(i)[j]);
                        String[] s = lowModelsCheckBoxesListTitles.get(i);
                        CheckBoxItem[] checkBoxItems = new CheckBoxItem[lowLowCheckBoxesTitles.get(i)[j].length];
                        for (int k = 0; k < lowLowCheckBoxesTitles.get(i)[j].length; k++) {
                            checkBoxItems[k] = new CheckBoxItem(false, lowLowCheckBoxesTitles.get(i)[j][k]);
                        }
                        lowCheckListItems[j].setCheckBoxItems(checkBoxItems);
                    }
                }
            }
            lowItemsModel.setLowCheckListItems(lowCheckListItems);

            CommentsModel[] commentsModels = null;
            if (lowModelsCommentListTitles != null) {
                if (i < lowModelsCommentListTitles.size()) {
                    commentsModels = new CommentsModel[lowModelsCommentListTitles.get(i).length];
                    for (int j = 0; j < lowModelsCommentListTitles.get(i).length; j++) {
                        commentsModels[j] = new CommentsModel(lowModelsCommentListTitles.get(i)[j], "");
                    }
                }
            }
            lowItemsModel.setCommentsModels(commentsModels);
            defectCheckListItems[i].setLowItemsModels(lowItemsModel);
        }
        detail.setDefectCheckListItems(defectCheckListItems);
        return detail;
    }


    public String getCheckedItems() {
        StringBuilder stringBuilder0 = new StringBuilder();
        StringBuilder stringBuilder = new StringBuilder();
        if (checkBoxItem.isChecked()) {
            stringBuilder0.append(checkBoxItem.getTitle());
            stringBuilder0.append(": ");
        }
        for (int i = 0; i < defectCheckListItems.length; i++) {
            if(defectCheckListItems[i].getCheckBoxItem().isChecked()) {
                StringBuilder stringBuilder2 = new StringBuilder();
                if (defectCheckListItems[i].getLowItemsModels().getLowCheckListItems() != null)
                    for (int j = 0; j < defectCheckListItems[i].getLowItemsModels().getLowCheckListItems().length; j++) {
                        boolean flag = false;
                        for (int k = 0; k < defectCheckListItems[i].getLowItemsModels().getLowCheckListItems()[j].getCheckBoxItems().length; k++) {
                            if (defectCheckListItems[i].getLowItemsModels().getLowCheckListItems()[j].getCheckBoxItems()[k].isChecked()) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            stringBuilder2.append(defectCheckListItems[i].getLowItemsModels().getLowCheckListItems()[j].getCheckBoxesTitle());
                            stringBuilder2.append(" ");
                            for (int k = 0; k < defectCheckListItems[i].getLowItemsModels().getLowCheckListItems()[j].getCheckBoxItems().length; k++) {
                                if (defectCheckListItems[i].getLowItemsModels().getLowCheckListItems()[j].getCheckBoxItems()[k].isChecked()) {
                                    stringBuilder2.append(defectCheckListItems[i].getLowItemsModels().getLowCheckListItems()[j].getCheckBoxItems()[k].getTitle());
                                    stringBuilder2.append(" , ");
                                }

                            }
                            stringBuilder2.delete(stringBuilder2.length() - 2, stringBuilder2.length() - 1);
                            stringBuilder2.append(";");
                        }
                    }
                CommentsModel[] commentsModels = defectCheckListItems[i].getLowItemsModels().getCommentsModels();
                commentsModels = commentsModels;
                if (defectCheckListItems[i].getLowItemsModels().getCommentsModels() != null)
                    for (int j = 0; j < defectCheckListItems[i].getLowItemsModels().getCommentsModels().length; j++) {
                        try {
                            if (!defectCheckListItems[i].getLowItemsModels().getCommentsModels()[j].getComment().equals("")) {
                                stringBuilder2.append(defectCheckListItems[i].getLowItemsModels().getCommentsModels()[j].getCommentTitle());
                                stringBuilder2.append(" ");
                                stringBuilder2.append(defectCheckListItems[i].getLowItemsModels().getCommentsModels()[j].getComment());
                                stringBuilder2.append(";");
                            }
                        } catch (ArrayIndexOutOfBoundsException ex) {

                        }
                    }
                if (defectCheckListItems[i].getCheckBoxItem().isChecked() && !stringBuilder2.toString().equals("")) {
                    stringBuilder.append(defectCheckListItems[i].getCheckBoxItem().getTitle());
                    stringBuilder.append(":");
                    stringBuilder.append(stringBuilder2.toString());
                } else if (defectCheckListItems[i].getCheckBoxItem().isChecked() && defectCheckListItems[i].getLowItemsModels().getCommentsModels().length == 0 && defectCheckListItems[i].getLowItemsModels().getLowCheckListItems().length == 0) {
                    stringBuilder.append(defectCheckListItems[i].getCheckBoxItem().getTitle());
                    stringBuilder.append(';');
                } else if (defectCheckListItems[i].getCheckBoxItem().isChecked()) {
                    stringBuilder.append(defectCheckListItems[i].getCheckBoxItem().getTitle());
                    stringBuilder.append(';');
                }
            }
        }
        if(stringBuilder.toString().trim().equals("")) {//если второго контента не набёрется, то всё пусто
            checkBoxItem.setChecked(false);
            return "";
        }
        else
            return stringBuilder0.append(stringBuilder).toString();
    }


    private CheckBoxItem checkBoxItem;
    private DefectCheckListItem[] defectCheckListItems;

    public Detail(CheckBoxItem checkBoxItem, DefectCheckListItem[] defectCheckListItems) {
        this.checkBoxItem = checkBoxItem;
        this.defectCheckListItems = defectCheckListItems;
    }

    public Detail() {

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
