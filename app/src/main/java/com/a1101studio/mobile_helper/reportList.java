package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.a1101studio.mobile_helper.utils.FileHelper;

import java.io.File;

import static com.a1101studio.mobile_helper.utils.FileHelper.CreateOrGetFileDir;

public class reportList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getResources().getLayout(R.layout.activity_report_list);
        ListView listView = (ListView) findViewById(R.id.listView);
        String[] theNamesOfFiles;
        File dir= CreateOrGetFileDir(this);
        File[] filelist = dir.listFiles((dir1, name) -> {
            return name.contains(".html");
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
                intent.setDataAndType(Uri.fromFile(new File(FileHelper.CreateOrGetFileDir(reportList.this) + "/" + adapter.getItem(position).toString())), "text/html");
                //intent.addCategory(Intent.CATEGORY_BROWSABLE);
                // intent.setData(Uri.fromFile(myFile));
                //intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }
}
