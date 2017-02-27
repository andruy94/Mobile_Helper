package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;

public class reportList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getResources().getLayout(R.layout.activity_report_list);
        ListView listView = (ListView) findViewById(R.id.listView);
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/mobile_helper/");
        dir.mkdir();
        File[] filelist = dir.listFiles();
        String[] theNamesOfFiles = new String[filelist.length];
        for (int i = 0; i < theNamesOfFiles.length; i++) {
                    theNamesOfFiles[i] = filelist[i].getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theNamesOfFiles);

// используем адаптер данных
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory().getPath() + "/mobile_helper/" + adapter.getItem(position).toString())), "text/html");
                //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                // intent.setData(Uri.fromFile(myFile));
                //intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }
}
