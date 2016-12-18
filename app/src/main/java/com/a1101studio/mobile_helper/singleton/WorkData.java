package com.a1101studio.mobile_helper.singleton;

import com.a1101studio.mobile_helper.List;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.TopListModel;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */
public class WorkData {
    private  ArrayList<CheckListItem[]> checkListItemList;
    private ArrayList<TopListModel> topListModels;
    private static WorkData ourInstance = new WorkData();
    public static WorkData getInstance() {
        return ourInstance;
    }

    private WorkData() {
    }

    public  ArrayList<CheckListItem[]> getCheckListItemList() {
        return checkListItemList;
    }

    public  void setCheckListItemList(ArrayList<CheckListItem[]> checkListItemList) {
        this.checkListItemList = checkListItemList;
    }

    public ArrayList<TopListModel> getTopListModels() {
        return topListModels;
    }

    public void setTopListModels(ArrayList<TopListModel> topListModels) {
        this.topListModels = topListModels;
    }
}
