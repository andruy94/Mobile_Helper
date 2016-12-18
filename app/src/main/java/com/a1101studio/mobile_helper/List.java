package com.a1101studio.mobile_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a1101studio.mobile_helper.adapters.ListAdapter;

public class List extends AppCompatActivity {
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.lv1);
        String[] strings={"1","2","3"};//тут пишешь что надо
        ListAdapter listAdapter=new ListAdapter(this,strings);
        listView.setAdapter(listAdapter);
    }
}
