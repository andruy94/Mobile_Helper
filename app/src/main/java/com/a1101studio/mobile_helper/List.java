package com.a1101studio.mobile_helper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
        Intent intent=getIntent();
        int k=intent.getIntExtra("k",0);


        AlertDialog.Builder builder=new AlertDialog.Builder(this);


        ListAdapter listAdapter=new ListAdapter(this,WorkData.getInstance().getCheckListItemList().get(k),k);
        /*builder.setMultiChoiceItems(new String[]{"A", "B", "C"}, new boolean[]{true, false, true}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {

            }
        });*/
        builder.setAdapter(listAdapter,null);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
        listView.setAdapter(listAdapter);
        Button button=new Button(this);
        button.setText(R.string.confirm);
        button.setOnClickListener(v->finish());


        listView.addHeaderView(button);
    }
}
