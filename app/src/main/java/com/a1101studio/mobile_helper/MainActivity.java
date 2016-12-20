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

import com.a1101studio.mobile_helper.models.TopListModel;
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
        File pdfFolder = new File(getExternalFilesDir(
                Environment.DIRECTORY_DOCUMENTS),"Sofon");
        if (!pdfFolder.exists()) {
            pdfFolder.mkdir();

        }

        File myFile = new File(pdfFolder+"n" + ".pdf");
        Document document = new Document(PageSize.A4);
        try {
            OutputStream output = new FileOutputStream(myFile);
            Rectangle pagesize = new Rectangle(216f, 720f);
            document = new Document(pagesize, 36f, 72f, 108f, 180f);
            document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, output);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.fs;
            Uri pathurl = Uri.parse(path);
            Log.e("TAG",pathurl.toString());
            document.open();

            //  BaseFont bfComic = BaseFont.createFont(pathurl.toString(),"Cp1250",true);

                   /*BaseFont bf = BaseFont.createFont("/system/fonts/Comic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
                    Font font = new Font(bf);
                    document.add(new Paragraph("буковки", font));*/
                     /*Font font = FontFactory.getFont("/system/fonts/Comic.ttf", "CP1251",  BaseFont.EMBEDDED);
                    Chunk c3 = new Chunk("INVOICE",font );
                    c3.setBackground(BaseColor.WHITE);*/


            BaseFont font1 = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1250,BaseFont.EMBEDDED);
            Font font=new Font(font1);
            document.add(new Paragraph("\u041e\u0442\u043a\u0443\u0434\u0430 \u0442\u044b?",font));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph(getString(R.string.sector) + Sector.getText().toString(),font));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph(getString(R.string.Unom) + Unom.getText().toString()));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("Район (Учатсок):" + Name.getText().toString(),font));
            document.add( Chunk.NEWLINE );
            String str=getString(R.string.Unom);
            String stid = new String(str.getBytes("UTF-8"),
                    "cp1251");
            document.add(new Paragraph(stid+ VID.getText().toString(),font));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("Осмотр проведен от опоры №" + OT.getText().toString()));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("До опоры №" + Do.getText().toString()));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("Осмотр выполнил:" + Names.getText().toString()));
            document.add( Chunk.NEWLINE );
            document.add(new Paragraph("Листок осмотра принял:" + Prinal.getText().toString()));
            document.add( Chunk.NEWLINE );
            document.close();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(myFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.e("TAG",e.toString());
        } catch (DocumentException e) {
            Log.e("TAG",e.toString());
        } catch (IOException e) {
            Log.e("TAG",e.toString());
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}
