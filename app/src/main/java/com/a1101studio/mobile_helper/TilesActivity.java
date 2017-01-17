package com.a1101studio.mobile_helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

public class TilesActivity extends AppCompatActivity {
    private  CheckListItem[] checkListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tiles);
        Intent intent=getIntent();
        int k=intent.getIntExtra("k",0);
        checkListItem= WorkData.getInstance().getCheckListItemList().get(k);
        Button btn1=(Button) findViewById(R.id.button1);
        Button btn2=(Button) findViewById(R.id.button2);
        Button btn3=(Button) findViewById(R.id.button3);
        Button btn4=(Button) findViewById(R.id.button4);
        Button btn5=(Button) findViewById(R.id.button5);
        Button btn6=(Button) findViewById(R.id.button6);
        btn6.setVisibility(View.INVISIBLE);
        btn1.setText(checkListItem[0].getDescription());
        btn1.setOnClickListener(v->showDefects(checkListItem[0].getCheckListItems(),this));
        btn2.setText(checkListItem[1].getDescription());
        btn2.setOnClickListener(v->showDefects(checkListItem[1].getCheckListItems(),this));
        btn3.setText(checkListItem[2].getDescription());
        btn3.setOnClickListener(v->showDefects(checkListItem[2].getCheckListItems(),this));
        btn4.setText(checkListItem[3].getDescription());
        btn4.setOnClickListener(v->showDefects(checkListItem[3].getCheckListItems(),this));
        btn5.setText(R.string.pidr);
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
