package com.a1101studio.mobile_helper_open.models;

import java.io.Serializable;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class TopListModel implements Serializable {
    private String defect;
    private boolean isSeat;
    private String type;
    private String seatNumber;

    public TopListModel(String defect, boolean isSeat, String type, String seatNumber) {
        this.defect = defect;
        this.isSeat = isSeat;
        this.seatNumber = seatNumber;
        this.type=type;
    }

    public TopListModel(String defect, String seatNumber) {
        isSeat=false;
        this.defect=defect;
        this.seatNumber =seatNumber;
        this.type="";
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSeat() {
        return isSeat;
    }

    public void setIsSeat(boolean isSeat) {
        this.isSeat = isSeat;
    }
}
