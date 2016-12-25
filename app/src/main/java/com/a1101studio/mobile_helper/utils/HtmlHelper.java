package com.a1101studio.mobile_helper.utils;

import com.a1101studio.mobile_helper.models.DocumentModel;

import java.util.Calendar;

/**
 * Created by andru on 20.12.2016.
 */

public class HtmlHelper {
    private String fileName;//имя файла/адресс
    private String[] SeatNames;//номера опор/пролётов
    private String[] Defects;//набор дефекто соотвествующие опорам
    private String HtmlString;//набор дефекто соотвествующие опорам
    private DocumentModel documentModel;

    public HtmlHelper(String fileName, DocumentModel documentModel) {
        this.fileName = fileName;
        SeatNames = documentModel.getSeatNames();
        Defects = documentModel.getDefectNames();
        this.documentModel=documentModel;
    }

    public String getHtmlString() {
        HtmlString = HtmlString+ "<p>"+"Предприятие:"+documentModel.getCompanyName()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Район(участок):"+documentModel.getArea()+"</p>";
        HtmlString = HtmlString+ "<h2 style=\"text-align: center;\">&nbsp;Листок осмотра</h2>";
        HtmlString = HtmlString+ "<p>"+"Воздушная линия Uном="+documentModel.getElectricLine()+"кВ наименование:"+documentModel.getElectricLine()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Вид осмотра:"+documentModel.getTypeOfInspection()+"</p>";

                HtmlString = HtmlString+ "<table>\n" +
                        "        <tbody>\n" +
                        "        <tr>\n" +
                        "        <td style=\"text-align: center;\">Номер опоры,пролета</td>\n" +
                        "        <td style=\"text-align: center;\">Зачеченные неисправности</td></tr>";
        for (int x = 0; x < SeatNames.length; x = x + 2) {

                HtmlString = HtmlString+ "<tr><td>"+documentModel.getSeatNames()[x]+"</td>";
                HtmlString = HtmlString+ "<td>"+documentModel.getSeatNames()[x+1]+"</td></tr>";

        }
        Calendar c = Calendar.getInstance();
        HtmlString = HtmlString+ "<p>"+"Осмотр проведен от опоры №"+documentModel.getNumberStartInspectionSeat()+" до опоры №"+documentModel.getNumberEndInspectioSeat()+"</p>";
        HtmlString = HtmlString+ "<p>"+c.get(Calendar.DAY_OF_MONTH)+"."+c.get(Calendar.MONTH)+"."+c.get(Calendar.YEAR)+"</p>";
        HtmlString = HtmlString+ "<p>"+"Осмотр выполнил:"+"wtf???"+"/________________/"+"</p>";
        HtmlString = HtmlString+ "<p>"+"Листок осмотра принял:"+documentModel.getInspectorName()+"/________________/"+"</p>";
        return HtmlString;
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
