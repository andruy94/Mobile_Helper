package com.a1101studio.mobile_helper;


import android.content.Intent;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.R.attr.type;


public class MainActivity extends AppCompatActivity {

    private ArrayList<TopListModel> topListModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText Pred;
        EditText Sector;
        EditText Unom;
        EditText Name;
        EditText VID;
        EditText OT;
        EditText Do;
        EditText Names;
        EditText Prinal;
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
        View.OnClickListener oclBtnOk = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File pdfFolder = new File(getExternalFilesDir(
                        Environment.DIRECTORY_DOCUMENTS),"Sofon");
                if (!pdfFolder.exists()) {
                    pdfFolder.mkdir();

                }

                File myFile = new File(pdfFolder + ".pdf");
                Document document = new Document(PageSize.A4);
                try {
                    OutputStream output = new FileOutputStream(myFile);
                    Rectangle pagesize = new Rectangle(216f, 720f);
                    document = new Document(pagesize, 36f, 72f, 108f, 180f);
                    document = new Document(PageSize.A4);
                    PdfWriter.getInstance(document, output);
                    String path = "android.resource://" + getPackageName() + "/" + R.raw.ft;
                    Uri pathurl = Uri.parse(path);
                    document.open();

                  //  BaseFont bfComic = BaseFont.createFont(pathurl.toString(),"Cp1250",true);

                   /*BaseFont bf = BaseFont.createFont("/system/fonts/Comic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED); //подключаем файл шрифта, который поддерживает кириллицу
                    Font font = new Font(bf);
                    document.add(new Paragraph("буковки", font));*/
                     /*Font font = FontFactory.getFont("/system/fonts/Comic.ttf", "CP1251",  BaseFont.EMBEDDED);
                    Chunk c3 = new Chunk("INVOICE",font );
                    c3.setBackground(BaseColor.WHITE);*/

                    Font font = FontFactory.getFont(path, "Cp1251", BaseFont.EMBEDDED);

                    document.add(new Paragraph("\u041e\u0442\u043a\u0443\u0434\u0430 \u0442\u044b?",font));
                    document.add( Chunk.NEWLINE );
                    document.add(new Paragraph(getString(R.string.sector) + Sector.getText().toString(),font));
                    document.add( Chunk.NEWLINE );
                    document.add(new Paragraph(getString(R.string.Unom) + Unom.getText().toString()));
                    document.add( Chunk.NEWLINE );
                    document.add(new Paragraph("Number12:" + Name.getText().toString()));
                    document.add( Chunk.NEWLINE );
                    String str=getString(R.string.Unom);
                    String stid = new String(str.getBytes("UTF-8"),
                            "cp1251");
                    document.add(new Paragraph(stid+ VID.getText().toString()));
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
        };
        btnSend.setOnClickListener(oclBtnOk);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
