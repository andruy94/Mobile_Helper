package com.a1101studio.mobile_helper.utils;

import com.a1101studio.mobile_helper.models.DocumentModel;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;


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

    public String getHtmlString() throws UnsupportedEncodingException {
        HtmlString = "";
                HtmlString = HtmlString+ "<!DOCTYPE html>\n" +
                "<html>\n" +
                "    <head>\n" +
                "<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />" +
                "        <title>Отчет</title>\n" +
                "    </head>  <body>";
        HtmlString = HtmlString+ "<p>"+"Предприятие:"+documentModel.getCompanyName()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Район(участок):"+documentModel.getArea()+"</p>";
        HtmlString = HtmlString+ "<h2 style=\"text-align: center;\">&nbsp;Листок осмотра</h2>";
        HtmlString = HtmlString+ "<p>"+"Воздушная линия Uном="+documentModel.getElectricLine()+"кВ наименование:"+documentModel.getElectricLine()+"</p>";
        HtmlString = HtmlString+ "<p>"+"Вид осмотра:"+documentModel.getTypeOfInspection()+"</p>";

        HtmlString = HtmlString+ "<table border=\"1\" cellspacing=\"0\">\n" +
                "        <tbody>\n" +
                "        <tr>\n" +
                "        <td style=\"text-align: center;\">Номер опоры,пролета</td>\n" +
                "        <td style=\"text-align: center;\">Замеченные неисправности</td></tr>";
        for (int x = 0; x < SeatNames.length; x++) {

            HtmlString = HtmlString+ "<tr><td  style=\"text-align: center;\">"+documentModel.getSeatNames()[x]+"</td>";
            HtmlString = HtmlString+ "<td>"+documentModel.getDefectNames()[x]+"</td></tr>";

        }
        Calendar c = Calendar.getInstance();
        HtmlString = HtmlString+ "</tbody>\n" +
                "</table><p>"+"Осмотр проведен от опоры №"+documentModel.getNumberStartInspectionSeat()+" до опоры №"+documentModel.getNumberEndInspectioSeat()+"</p>";
        HtmlString = HtmlString+ "<p>"+c.get(Calendar.DAY_OF_MONTH)+"."+c.get(Calendar.MONTH)+"."+c.get(Calendar.YEAR)+"</p>";
        HtmlString = HtmlString+ "<p>"+"Осмотр выполнил:"+"  /________________/"+"</p>";
        HtmlString = HtmlString+ "<p>"+"Листок осмотра принял:"+documentModel.getInspectorName()+"/________________/"+"</p>";
        HtmlString = HtmlString+ "  </body>\n" +
                "</html>";
        byte ptext[] = HtmlString.getBytes("UTF-8");
        String value = new String(ptext, "UTF-8");
        return value;
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
