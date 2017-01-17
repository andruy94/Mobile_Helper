package com.a1101studio.mobile_helper;


import android.content.Intent;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a1101studio.mobile_helper.models.DocumentModel;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.a1101studio.mobile_helper.utils.HtmlHelper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.ByteArray;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.Encoder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.R.attr.type;


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
        btnShowListActivity.setOnClickListener(v->startActivity(new Intent(this,MainListActivity.class)));
        Button btnSend=(Button) findViewById(R.id.btnSend);



        View.OnClickListener oclBtnOk = v -> createPDF();
        btnSend.setOnClickListener(oclBtnOk);
    }

    void createPDF(){
        File htmlFolder = new File(getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS),"Sofon");
        if (!htmlFolder.exists()) {
            htmlFolder.mkdir();

        }
        try{
        File myFile = new File(htmlFolder+"n" + ".html");

            ArrayList<String> checkedItems=new ArrayList<>();
            ArrayList<String> seatNames=new ArrayList<>();

            for(int i=0;i< WorkData.getInstance().getTopListModels().size();i++) {//масиив верхних штук
                seatNames.add(WorkData.getInstance().getTopListModels().get(i).getSeatNumber());
                StringBuilder stringBuilder=new StringBuilder();
                for (int j = 0; j < WorkData.getInstance().getCheckListItemList().get(i).length; j++) {//массив вложенных
                    String s= WorkData.getInstance().getCheckListItemList().get(i)[j].getCheckedItems();
                    if(!s.equals("")){
                        stringBuilder.append(s);
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
                    seatNames.toArray( new String[0]),
                    checkedItems.toArray( new String[0])

            );


            HtmlHelper htmlHelper=new HtmlHelper(myFile.getPath(),documentModel);
            saveFile(htmlHelper.getHtmlString(),myFile);

            Intent intent = new Intent(Intent.ACTION_VIEW);
           intent.setDataAndType(Uri.fromFile(myFile), "text/html");
            //intent.addCategory(Intent.CATEGORY_BROWSABLE);
           // intent.setData(Uri.fromFile(myFile));
            //intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);


        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TAG",e.toString());
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
