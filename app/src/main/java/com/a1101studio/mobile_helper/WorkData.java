package com.a1101studio.mobile_helper;

/**
 * Created by andruy94 on 12/18/2016.
 */
public class WorkData {
    private static WorkData ourInstance = new WorkData();

    public static WorkData getInstance() {
        return ourInstance;
    }

    private WorkData() {
    }
}
