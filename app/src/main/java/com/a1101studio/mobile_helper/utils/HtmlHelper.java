package com.a1101studio.mobile_helper.utils;

/**
 * Created by andru on 20.12.2016.
 */

public class HtmlHelper {
    private String fileName;//имя файла/адресс
    private String[] SeatNames;//номера опор/пролётов
    private String[] Defects;//набор дефекто соотвествующие опорам


    public HtmlHelper(String fileName, String[] seatNames, String[] defects) {
        this.fileName = fileName;
        SeatNames = seatNames;
        Defects = defects;
    }

    public String[] getDefects() {
        return Defects;
    }

    public void setDefects(String[] defects) {
        Defects = defects;
    }

    public String[] getSeatNames() {
        return SeatNames;
    }

    public void setSeatNames(String[] seatNames) {
        SeatNames = seatNames;
    }
}
