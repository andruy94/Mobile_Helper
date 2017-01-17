package com.a1101studio.mobile_helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

public class TilesActivity extends AppCompatActivity {
    private  CheckListItem[] checkListItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tiles);
        Intent intent=getIntent();
        int k=intent.getIntExtra("k",0);
        checkListItems = WorkData.getInstance().getCheckListItemList().get(k);
        Button[] buttons=new Button[6];
        buttons[0]=(Button) findViewById(R.id.button1);
        buttons[1]=(Button) findViewById(R.id.button2);
        buttons[2]=(Button) findViewById(R.id.button3);
        buttons[3]=(Button) findViewById(R.id.button4);
        buttons[4]=(Button) findViewById(R.id.button5);
        buttons[5]=(Button) findViewById(R.id.button6);
        for(int i=0;i< checkListItems.length;i++){

            buttons[i].setText(checkListItems[0].getDescription());
            int finalI = i;
            buttons[i].setOnClickListener(v->showDefects(checkListItems[finalI].getCheckListItems(),this));
        }



        buttons[5].setText(R.string.pidr);
    }

    private void showDefects(CheckListItem[] checkListItems, Context context) {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        String[] Items=new String[checkListItems.length];
        boolean[] chedList=new boolean[checkListItems.length];
        for(int i=0;i<checkListItems.length;i++){
            Items[i]=checkListItems[i].getDescription();
            chedList[i]=checkListItems[i].isChecked();
        }
        builder.setMultiChoiceItems(Items, chedList, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //Toast.makeText(context,String.format("item # %d = %s",which,checkListItems[which].getDescription()),Toast.LENGTH_SHORT).show();
                checkListItems[which].setChecked(isChecked);
            }
        });
        builder.setPositiveButton("OK", (dialog, which) -> dialog.cancel());
        builder.show();

    }
}
