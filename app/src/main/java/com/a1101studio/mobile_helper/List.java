package com.a1101studio.mobile_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;

import com.a1101studio.mobile_helper.adapters.ListAdapter;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class List extends AppCompatActivity {
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.lv1);



        String[] title={"sa1212","asd2","asd2"};//оглавление
        ArrayList<String[]> descs=new ArrayList<String[]>();
        String[] descs1={"eqw21","qe2","ewqw2"};//описание к первому оглавлению
        String[] descs2={"eqw21","qe2","ewqw2"};//описание к 2му оглавлению
        String[] descs3={"eqw21","qe2","ewqw2"};//описание к 3му оглавлению

        descs.add(descs1);
        descs.add(descs2);
        descs.add(descs3);

        CheckListItem[] checkListItems=CheckListItem.CreateCheckListitem(title,descs);

        WorkData.getInstance().setCheckListItemList(checkListItems);//пишем всё в озу

        ListAdapter listAdapter=new ListAdapter(this,checkListItems);
        listView.setAdapter(listAdapter);
    }
}
