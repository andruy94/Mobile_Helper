package com.a1101studio.mobile_helper.models;

/**
 * Created by andruy94 on 2/8/2017.
 */

public class LowItemsModel {
    private LowCheckListItem[] lowCheckListItems;
    private CommentsModel[] commentsModels;

    public LowItemsModel(LowCheckListItem[] lowCheckListItems, CommentsModel[] commentsModels) {
        this.lowCheckListItems = lowCheckListItems;
        this.commentsModels = commentsModels;
    }

    public LowItemsModel() {

    }

    public CommentsModel[] getCommentsModels() {
        return commentsModels;
    }

    public void setCommentsModels(CommentsModel[] commentsModels) {
        this.commentsModels = commentsModels;
    }


    public LowCheckListItem[] getLowCheckListItems() {
        return lowCheckListItems;
    }

    public void setLowCheckListItems(LowCheckListItem[] lowCheckListItems) {
        this.lowCheckListItems = lowCheckListItems;
    }
}
