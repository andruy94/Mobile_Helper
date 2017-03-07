package com.a1101studio.mobile_helper.singleton;

import com.a1101studio.mobile_helper.models.Detail;
import com.a1101studio.mobile_helper.models.DocumentModel;
import com.a1101studio.mobile_helper.models.TopListModel;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */
public class WorkData {
    private static final int KILL_TIMER = 5000;
    private  ArrayList<Detail[]> details;
    private ArrayList<TopListModel> topListModels;
    private DocumentModel documentModel;
    private static WorkData ourInstance = new WorkData();
    public static WorkData getInstance() {
        return ourInstance;
    }

    private WorkData() {

    }

    public  ArrayList<Detail[]> getDetails() {
        return details;
    }

    public DocumentModel getDocumentModel() {
        return documentModel;
    }

    public void setDocumentModel(DocumentModel documentModel) {
        this.documentModel = documentModel;
    }

    public static WorkData getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(WorkData ourInstance) {
        WorkData.ourInstance = ourInstance;
    }

    public  void setDetails(ArrayList<Detail[]> details) {
        this.details = details;
    }

    public ArrayList<TopListModel> getTopListModels() {
        return topListModels;
    }

    public void setTopListModels(ArrayList<TopListModel> topListModels) {
        this.topListModels = topListModels;
    }




}
