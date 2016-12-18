package com.a1101studio.mobile_helper;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.layout.simple_list_item_1;

public class List extends AppCompatActivity {
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.lv1);
        String[] strings={"1","2","3"};//тут пишешь что надо
        ListAdapter listAdapter=new ListAdapter(this,strings);
        String[] strings1={"1","2","3"};//тут выпадающий список
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(List.this,"asd",Toast.LENGTH_LONG).show();
                LinearLayout linearLayout=(LinearLayout) findViewById(R.id.ll_second_list);
                View child=getLayoutInflater().inflate(R.layout.inner_item,linearLayout);

                linearLayout.addView(child);
            }
        });
    }
}
