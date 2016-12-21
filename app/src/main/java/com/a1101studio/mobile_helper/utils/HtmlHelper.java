package com.a1101studio.mobile_helper.utils;

import java.util.Calendar;

/**
 * Created by andru on 20.12.2016.
 */

public class HtmlHelper {
    private String fileName;//имя файла/адресс
    private String[] SeatNames;//номера опор/пролётов
    private String[] Defects;//набор дефекто соотвествующие опорам
    private String HtmlString;//набор дефекто соотвествующие опорам

    public HtmlHelper(String fileName, String[] seatNames, String[] defects) {
        this.fileName = fileName;
        SeatNames = seatNames;
        Defects = defects;
    }

    public String getHtmlString() {
        HtmlString = HtmlString+ "<p>"+"Предприятие:"+SeatNames[0].toString()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Район(участок):"+SeatNames[1].toString()+"</p>";
        HtmlString = HtmlString+ "<h2 style=\"text-align: center;\">&nbsp;Листок осмотра</h2>";
        HtmlString = HtmlString+ "<p>"+"Воздушная линия Uном="+SeatNames[2].toString()+"кВ наименование:"+SeatNames[2].toString()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Воздушная линия Uном="+SeatNames[2].toString()+"кВ наименование:"+SeatNames[2].toString()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Вид осмотра:"+SeatNames[1].toString()+"</p>";

                HtmlString = HtmlString+ "<table>\n" +
                        "        <tbody>\n" +
                        "        <tr>\n" +
                        "        <td style=\"text-align: center;\">Номер опоры,пролета</td>\n" +
                        "        <td style=\"text-align: center;\">Зачеченные неисправности</td></tr>";
        for (int x = 0; x < SeatNames.length; x = x + 2) {

                HtmlString = HtmlString+ "<tr><td>"+SeatNames[x]+"</td>";
                HtmlString = HtmlString+ "<td>"+SeatNames[x+1]+"</td></tr>";

        }

        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int moynt = c.get(Calendar.MONTH);
        int yar = c.get(Calendar.YEAR);
        HtmlString = HtmlString+ "<p>"+"Осмотр проведен от опоры №"+SeatNames[2].toString()+" до опоры №"+SeatNames[2].toString()+"</p>";
        HtmlString = HtmlString+ "<p>"+day+"."+moynt+"."+yar+"</p>";
        HtmlString = HtmlString+ "<p>"+"Осмотр выполнил:"+SeatNames[0].toString()+"/________________/"+"</p>";
        HtmlString = HtmlString+ "<p>"+"Листок осмотра принял:"+SeatNames[0].toString()+"/________________/"+"</p>";
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
