package com.a1101studio.mobile_helper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView=(ListView) findViewById(R.id.lv1);

        ArrayList<HashMap<String, String>> myArrList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map;

        // Досье на первого кота
        map = new HashMap<String, String>();
        map.put("Name", "Мурзик");
        map.put("Tel", "495 501-3545");
        myArrList.add(map);

        // Досье на второго кота
        map = new HashMap<String, String>();
        map.put("Name", "Барсик");
        map.put("Tel", "495 241-6845");
        myArrList.add(map);

        // Досье на третьего кота
        map = new HashMap<String, String>();
        map.put("Name", "Васька");
        map.put("Tel", "495 431-5468");
        myArrList.add(map);

        SimpleAdapter adapter = new SimpleAdapter(this, myArrList, R.layout.top_list_item,
                new String[] {"Name", "Tel"},
                new int[] {R.id.text1, R.id.text2});
        listView.setAdapter(adapter);
        listView.addFooterView(new Button(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
