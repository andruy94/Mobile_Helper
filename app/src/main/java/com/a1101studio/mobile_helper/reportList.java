package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a1101studio.mobile_helper.utils.FileHelper;

import java.io.File;
import java.io.FilenameFilter;

public class reportList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getResources().getLayout(R.layout.activity_report_list);
        ListView listView = (ListView) findViewById(R.id.listView);
        String[] theNamesOfFiles;
        File dir= FileHelper.createOrGetFileDir(this);
        File[] filelist = dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir1, String name) {
                return name.contains(".html");
            }
        });
        if(filelist!=null) {

            theNamesOfFiles = new String[filelist.length];
            for (int i = 0; i < theNamesOfFiles.length; i++) {
                theNamesOfFiles[i] = filelist[i].getName();
            }
        }else {
            theNamesOfFiles=new String[0];
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theNamesOfFiles);

// используем адаптер данных
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uriForFile = FileProvider.getUriForFile(reportList.this, reportList.this.getString(R.string.file_provider_authority), new File(FileHelper.createOrGetFileDir(reportList.this) + "/" + adapter.getItem(position).toString()));
                intent.setData(uriForFile);//, "application/html");
                // set flag to give temporary permission to external app to use your FileProvider
                reportList.this.grantUriPermission(reportList.this.getString(R.string.file_provider_authority),uriForFile,Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(intent);
            }
        });

    }
}
