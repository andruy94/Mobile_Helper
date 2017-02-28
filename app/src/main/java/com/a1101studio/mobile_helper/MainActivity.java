package com.a1101studio.mobile_helper;


import android.app.AlertDialog;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a1101studio.mobile_helper.models.DocumentModel;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.a1101studio.mobile_helper.utils.HtmlHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {
    EditText Pred;
    EditText Sector;
    EditText Unom;
    EditText Name;
    EditText VID;
    EditText OT;
    EditText Do;
    EditText Names;
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
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId()==IDM_OPEN){
            Intent intent=new Intent(MainActivity.this,reportList.class);
            startActivity(intent);
            return true;
        }else if(item.getItemId()==IDM_LOGOUT){
            finish();
        }

        return false;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Pred   = (EditText)findViewById(R.id.Predpriytie);
        Sector   = (EditText)findViewById(R.id.Sector);
        Unom   = (EditText)findViewById(R.id.Unom);
        Name   = (EditText)findViewById(R.id.Nazvanie);
        VID   = (EditText)findViewById(R.id.VID);
        OT   = (EditText)findViewById(R.id.OT);
        Do   = (EditText)findViewById(R.id.DO);
        Names   = (EditText)findViewById(R.id.Nameosmotr);
        Prinal   = (EditText)findViewById(R.id.Prinal);
        Button btnShowListActivity=(Button) findViewById(R.id.btnShowList);

            btnShowListActivity.setOnClickListener(v -> startActivity(new Intent(this, MainListActivity.class)));

        Button btnSend=(Button) findViewById(R.id.btnSend);



        View.OnClickListener oclBtnOk = v -> createPDF();
        btnSend.setOnClickListener(oclBtnOk);
    }

    void createPDF() {
        if (Pred.getText().toString().trim().length() > 0 && Sector.getText().toString().trim().length() > 0 && Unom.getText().toString().trim().length() > 0 && Name.getText().toString().trim().length() > 0 &&
                VID.getText().toString().trim().length() > 0 && OT.getText().toString().trim().length() > 0 && Do.getText().toString().trim().length() > 0 && Names.getText().toString().trim().length() > 0 && Prinal.getText().toString().trim().length() > 0) {
            File htmlFolder = new File(Environment.getExternalStorageDirectory().getPath() + "/mobile_helper/");
            if (!htmlFolder.exists()) {
                htmlFolder.mkdir();

            }
            try {
                String namerepot;
                namerepot = Name.getText().toString();
                if (namerepot == " ") {
                    namerepot = "" + new Date().getTime();
                }
                namerepot = namerepot + ".html";
                File myFile = new File(Environment.getExternalStorageDirectory().getPath() + "/mobile_helper/" + namerepot);

                ArrayList<String> checkedItems = new ArrayList<>();
                ArrayList<String> seatNames = new ArrayList<>();

                for (int i = 0; i < WorkData.getInstance().getTopListModels().size(); i++) {//Ð¼Ð°ÑÐ¸Ð¸Ð² Ð²ÐµÑ€Ñ…Ð½Ð¸Ñ… ÑˆÑ‚ÑƒÐº
                    String seatNumber=WorkData.getInstance().getTopListModels().get(i).getSeatNumber();
                    seatNumber=(WorkData.getInstance().getTopListModels().get(i).isSeat() ) ? seatNumber+";"+WorkData.getInstance().getTopListModels().get(i).getType():seatNumber;
                    seatNames.add(seatNumber);
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int j = 0; j < WorkData.getInstance().getDetails().get(i).length; j++) {//Ð¼Ð°ÑÑÐ¸Ð² Ð²Ð»Ð¾Ð¶ÐµÐ½Ð½Ñ‹Ñ…
                        String s = WorkData.getInstance().getDetails().get(i)[j].getCheckedItems();
                        if (!s.equals("")) {
                            stringBuilder.append(s);
                            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                            stringBuilder.append(".");
                        }

                    }
                    checkedItems.add(stringBuilder.toString());
                }

                documentModel = new DocumentModel(
                        Pred.getText().toString(),
                        Sector.getText().toString(),
                        Unom.getText().toString(),
                        Name.getText().toString(),
                        VID.getText().toString(),
                        OT.getText().toString(),
                        Do.getText().toString(),
                        Names.getText().toString(),
                        Prinal.getText().toString(),
                        seatNames.toArray(new String[0]),
                        checkedItems.toArray(new String[0])

                );

                HtmlHelper htmlHelper = new HtmlHelper(myFile.getPath(), documentModel);
                saveFile(htmlHelper.getHtmlString(), myFile);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(myFile), "text/html");
                //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                // intent.setData(Uri.fromFile(myFile));
                //intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);


            } catch (IOException e) {
                e.printStackTrace();
                Log.e("TAG", e.toString());
            }
        }
        else {
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Âû äîëæíû çàïîëíèòü âñå ïîëÿ ôîðìû.");
            dlgAlert.setTitle("Îøèáêà");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    void saveFile(String s, File file) throws IOException{

            if(file.exists())
                file.delete();
            Log.e("TAG",file.getPath());
            FileOutputStream ostream = new FileOutputStream(file);
            ostream.write(s.getBytes());
            ostream.flush();
            ostream.close();

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
        message.setSubject("ÐžÑ‚Ð·Ñ‹Ð²");
        message.setText(mailText);

        MimeBodyPart messageBodyPart ;

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = getActivity().getCacheDir().getPath()+"/asd.jpg";
        String fileName = "Ñ…ÑƒÐ¹ Ð¿Ð¸Ð·Ð´Ð°";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }*/




}
