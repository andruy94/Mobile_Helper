package com.a1101studio.mobile_helper.models;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class TopListModel {
    private String defect;
    private String SeatNumber;

    public TopListModel(String defect, String seatNumber) {
        this.defect = defect;
        SeatNumber = seatNumber;
    }

    public String getDefect() {
        return defect;
    }

    public void setDefect(String defect) {
        this.defect = defect;
    }

    public String getSeatNumber() {
        return SeatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        SeatNumber = seatNumber;
    }
}
