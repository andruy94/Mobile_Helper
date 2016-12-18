package com.a1101studio.mobile_helper.singleton;

import com.a1101studio.mobile_helper.List;
import com.a1101studio.mobile_helper.models.CheckListItem;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */
public class WorkData {
    private ArrayList<CheckListItem> checkListItemList;
    private static WorkData ourInstance = new WorkData();

    public static WorkData getInstance() {
        return ourInstance;
    }

    private WorkData() {
    }
}
