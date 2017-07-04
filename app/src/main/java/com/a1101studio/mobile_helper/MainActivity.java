package com.a1101studio.mobile_helper;


import android.app.AlertDialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.a1101studio.mobile_helper.models.DocumentModel;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.a1101studio.mobile_helper.utils.FileHelper;
import com.a1101studio.mobile_helper.utils.HtmlHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.a1101studio.mobile_helper.utils.FileHelper.saveFile;


public class MainActivity extends AppCompatActivity {
    EditText ETCompanyName;
    Spinner ETArea;
    Spinner ETElectricLine;
    EditText ETNomination;
    EditText ETTypeOfInspection;
    EditText ETNumberStartInspectionSeat;
    EditText ETNumberEndInspectioSeat;
    EditText ETInspectorName;
    EditText Prinal;
    private ArrayList<TopListModel> topListModels;
    DocumentModel documentModel;
    private static final int IDM_OPEN = 101;
    private static final int IDM_LOGOUT = 102;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, IDM_OPEN, Menu.NONE, R.string.report_list);
        menu.add(Menu.NONE, IDM_LOGOUT, Menu.NONE, R.string.logout);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == IDM_OPEN) {
            Intent intent = new Intent(MainActivity.this, reportList.class);
            startActivity(intent);
            return true;
        } else if (item.getItemId() == IDM_LOGOUT) {
            finish();
        }

        return false;
    }




    @Override
    protected void onStop() {
        super.onStop();
        documentModel.setCompanyName(ETCompanyName.getText().toString());
        documentModel.setArea(ETArea.getSelectedItem().toString());
        documentModel.setElectricLine(ETElectricLine.getSelectedItem().toString());
        documentModel.setNomination(ETNomination.getText().toString());
        documentModel.setTypeOfInspection(ETTypeOfInspection.getText().toString());
        documentModel.setNumberStartInspectionSeat(ETNumberStartInspectionSeat.getText().toString());
        documentModel.setNumberEndInspectioSeat(ETNumberEndInspectioSeat.getText().toString());
        documentModel.setInspectorName(ETInspectorName.getText().toString());
        documentModel.setInspectionSheet(Prinal.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        documentModel=WorkData.getInstance().getDocumentModel();

        ETCompanyName = (EditText) findViewById(R.id.Predpriytie);
        ETArea = (Spinner) findViewById(R.id.Sector);

        ETElectricLine = (Spinner) findViewById(R.id.Unom);
        ETNomination = (EditText) findViewById(R.id.Nazvanie);
        ETTypeOfInspection = (EditText) findViewById(R.id.VID);
        ETNumberStartInspectionSeat = (EditText) findViewById(R.id.OT);
        ETNumberEndInspectioSeat = (EditText) findViewById(R.id.DO);
        ETInspectorName = (EditText) findViewById(R.id.Nameosmotr);
        Prinal = (EditText) findViewById(R.id.Prinal);
        Button btnShowListActivity = (Button) findViewById(R.id.btnShowList);


        ETCompanyName.setText("Московские высоковольтные сети");
        setSpinText(ETArea,documentModel.getArea());
        setSpinText(ETArea,documentModel.getElectricLine());
        ETNomination.setText(documentModel.getNomination());
        ETTypeOfInspection.setText("Плановый");
        ETNumberStartInspectionSeat.setText(documentModel.getNumberStartInspectionSeat());
        ETNumberEndInspectioSeat.setText(documentModel.getNumberEndInspectioSeat());
        ETInspectorName.setText(documentModel.getInspectorName());
        Prinal.setText(documentModel.getInspectionSheet());

        btnShowListActivity.setOnClickListener(v -> startActivity(new Intent(this, MainListActivity.class)));

        Button btnSend = (Button) findViewById(R.id.btnSend);


        View.OnClickListener oclBtnOk = v -> createPDF();
        btnSend.setOnClickListener(oclBtnOk);
    }

    void createPDF() {
        if (ETCompanyName.getText().toString().trim().length() > 0 && ETArea.getSelectedItem().toString().trim().length() > 0 && ETElectricLine.getSelectedItem().toString().trim().length() > 0 && ETNomination.getText().toString().trim().length() > 0 &&
                ETTypeOfInspection.getText().toString().trim().length() > 0 && ETNumberStartInspectionSeat.getText().toString().trim().length() > 0 && ETNumberEndInspectioSeat.getText().toString().trim().length() > 0 && ETInspectorName.getText().toString().trim().length() > 0 && Prinal.getText().toString().trim().length() > 0) {
            File htmlFolder = FileHelper.createOrGetFileDir(this);
            if (!htmlFolder.exists()) {
                htmlFolder.mkdir();
            }
            try {
                String namerepot;
                namerepot = ETNomination.getText().toString();
                if (namerepot == " ") {
                    namerepot = "" + new Date().getTime();
                }
                namerepot = namerepot + ".html";
                File myFile = new File(FileHelper.createOrGetFileDir(this), namerepot);

                ArrayList<String> checkedItems = new ArrayList<>();
                ArrayList<String> seatNames = new ArrayList<>();

                for (int i = 0; i < WorkData.getInstance().getTopListModels().size(); i++) {//масиив верхних штук
                    String seatNumber = WorkData.getInstance().getTopListModels().get(i).getSeatNumber();
                    seatNumber = (WorkData.getInstance().getTopListModels().get(i).isSeat()) ? seatNumber + ";" + WorkData.getInstance().getTopListModels().get(i).getType() : seatNumber;
                    seatNames.add(seatNumber);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < WorkData.getInstance().getDetails().get(i).length; j++) {//массив вложенных
                        String s = WorkData.getInstance().getDetails().get(i)[j].getCheckedItems();
                        if (!s.equals("")) {
                            stringBuilder.append(s);
                            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                            stringBuilder.append(".");
                        }

                    }
                    checkedItems.add(stringBuilder.toString());
                }

                /*documentModel = new DocumentModel(
                        ETCompanyName.getText().toString(),
                        ETArea.getText().toString(),
                        ETElectricLine.getText().toString(),
                        ETNomination.getText().toString(),
                        ETTypeOfInspection.getText().toString(),
                        ETNumberStartInspectionSeat.getText().toString(),
                        ETNumberEndInspectioSeat.getText().toString(),
                        ETInspectorName.getText().toString(),
                        Prinal.getText().toString(),
                        seatNames.toArray(new String[0]),
                        checkedItems.toArray(new String[0])

                );*/
                documentModel.setCompanyName(ETCompanyName.getText().toString());
                documentModel.setArea(ETArea.getSelectedItem().toString());
                documentModel.setElectricLine(ETElectricLine.getSelectedItem().toString());
                documentModel.setNomination(ETNomination.getText().toString());
                documentModel.setTypeOfInspection(ETTypeOfInspection.getText().toString());
                documentModel.setNumberStartInspectionSeat(ETNumberStartInspectionSeat.getText().toString());
                documentModel.setNumberEndInspectioSeat(ETNumberEndInspectioSeat.getText().toString());
                documentModel.setInspectorName(ETInspectorName.getText().toString());
                documentModel.setInspectionSheet(Prinal.getText().toString());
                documentModel.setSeatNames(seatNames.toArray(new String[0]));
                documentModel.setDefectNames(checkedItems.toArray(new String[0]));


                HtmlHelper htmlHelper = new HtmlHelper(myFile.getPath(), documentModel,this);
                saveFile(htmlHelper.getHtmlString(), myFile);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setDataAndType(Uri.fromFile(myFile), "text/html");
                /*if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                    intent.setDataAndType(FileProvider.getUriForFile(MainActivity.this,
                            BuildConfig.APPLICATION_ID + ".provider",
                            myFile), "text/html");
                else*/
                    intent.setDataAndType(Uri.fromFile(myFile), "text/html");
                startActivity(intent);


            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAG", "ex="+e.toString());
            }
        } else {
            AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
            dlgAlert.setMessage(R.string.fill_all_field);
            dlgAlert.setTitle(R.string.error);
            dlgAlert.setPositiveButton(R.string.ok, null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    public void setSpinText(Spinner spin, String text)
    {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }

    }

    /*void SendMail(String mailText) throws MessagingException {
        final String username = "";
        final String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        javax.mail.Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("andruy94@gmail.com"));
        message.setRecipients(javax.mail.Message.RecipientType.TO,
                InternetAddress.parse("andruy94@ya.ru"));
        message.setSubject("Отзыв");
        message.setText(mailText);

        MimeBodyPart messageBodyPart ;

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = getActivity().getCacheDir().getPath()+"/asd.jpg";
        String fileName = "хуй пизда";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }*/


}
