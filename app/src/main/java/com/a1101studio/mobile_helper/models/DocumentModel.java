package com.a1101studio.mobile_helper.models;

/**
 * Created by andru on 22.12.2016.
 */

public class DocumentModel {
    private String[] seatNames;//номера опор/пролётов
    private String[] defectNames;
    private String companyName;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getElectricLine() {
        return electricLine;
    }

    public void setElectricLine(String electricLine) {
        this.electricLine = electricLine;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public String getTypeOfInspection() {
        return typeOfInspection;
    }

    public void setTypeOfInspection(String typeOfInspection) {
        this.typeOfInspection = typeOfInspection;
    }

    public String getNumberStartInspectionSeat() {
        return NumberStartInspectionSeat;
    }

    public void setNumberStartInspectionSeat(String numberStartInspectionSeat) {
        NumberStartInspectionSeat = numberStartInspectionSeat;
    }

    public String getNumberEndInspectioSeat() {
        return NumberEndInspectioSeat;
    }

    public void setNumberEndInspectioSeat(String numberEndInspectioSeat) {
        NumberEndInspectioSeat = numberEndInspectioSeat;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public String getInspectionSheet() {
        return inspectionSheet;
    }

    public void setInspectionSheet(String inspectionSheet) {
        this.inspectionSheet = inspectionSheet;
    }

    private String area;
    private String electricLine;
    private String nomination;
    private String typeOfInspection;
    private String NumberStartInspectionSeat;
    private String NumberEndInspectioSeat;
    private String inspectorName;
    private String inspectionSheet;

    public DocumentModel( String company, String area,
                         String electricLine, String nomination,
                         String typeOfInspection, String numberStartInspectionSeat,
                         String numberEndInspectioSeat, String inspectorName, String inspectionSheet,String[] seatNames,String[] defectNames) {
        this.seatNames = seatNames;
        this.defectNames=defectNames;
        this.companyName = company;
        this.area = area;
        this.electricLine = electricLine;
        this.nomination = nomination;
        this.typeOfInspection = typeOfInspection;
        NumberStartInspectionSeat = numberStartInspectionSeat;
        NumberEndInspectioSeat = numberEndInspectioSeat;
        this.inspectorName = inspectorName;
        this.inspectionSheet = inspectionSheet;
    }

    public String[] getSeatNames() {
        return seatNames;
    }

    public void setSeatNames(String[] seatNames) {
        this.seatNames = seatNames;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String[] getDefectNames() {
        return defectNames;
    }

    public void setDefectNames(String[] defectNames) {
        this.defectNames = defectNames;
    }
}
