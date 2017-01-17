package com.a1101studio.mobile_helper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

public class TilesActivity extends AppCompatActivity {
    private  CheckListItem[] checkListItemsTop;
    Button[] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tiles);
        Intent intent=getIntent();
        int k=intent.getIntExtra("k",0);
        checkListItemsTop = WorkData.getInstance().getCheckListItemList().get(k);

        buttons = new Button[6];
        buttons[0]=(Button) findViewById(R.id.button1);
        buttons[1]=(Button) findViewById(R.id.button2);
        buttons[2]=(Button) findViewById(R.id.button3);
        buttons[3]=(Button) findViewById(R.id.button4);
        buttons[4]=(Button) findViewById(R.id.button5);
        buttons[5]=(Button) findViewById(R.id.button6);
        Button button=(Button) findViewById(R.id.btnExit);
        button.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        button.setOnClickListener(v->finish());
        for(int i=0;i< checkListItemsTop.length;i++){
            buttons[i].setVisibility(View.VISIBLE);
            if(checkListItemsTop[i].isChecked())
                buttons[i].setBackgroundColor(Color.GREEN);
            buttons[i].setText(checkListItemsTop[i].getDescription());
            int finalI = i;
            buttons[i].setOnClickListener(v->showDefects( finalI,checkListItemsTop[finalI].getCheckListItems(),this));
        }



       // buttons[5].setText(R.string.pidr);
    }

    private void showDefects(int k,CheckListItem[] checkListItems, Context context) {
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
        builder.setPositiveButton(R.string.confirm_changes, (dialog, which) -> {
            dialog.cancel();
            buttons[k].setBackgroundColor(Color.GREEN);
            checkListItemsTop[k].setChecked(true);
        });
        builder.setNegativeButton(R.string.cancel,(dialog, which) -> {dialog.cancel(); buttons[k].setBackgroundColor(Color.YELLOW);checkListItemsTop[k].setChecked(false);});
        builder.setTitle(checkListItemsTop[k].getDescription());
        builder.show();

    }
}
