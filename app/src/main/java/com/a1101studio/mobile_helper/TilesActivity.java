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
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.DefectCheckListItem;
import com.a1101studio.mobile_helper.models.LowCheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

public class TilesActivity extends AppCompatActivity {
    private  CheckListItem[] checkListItemsTop;
    Button[] buttons;
     int k;
    @Override
    protected void onResume() {
        super.onResume();
        for(int i=0;i< checkListItemsTop.length;i++){
            buttons[i].setVisibility(View.VISIBLE);
            checkListItemsTop[i].getCheckBoxItem().setChecked(!checkListItemsTop[i].getCheckedItems().equals(""));
            if(checkListItemsTop[i].getCheckBoxItem().isChecked())
                buttons[i].setBackgroundColor(Color.YELLOW);
            buttons[i].setText(checkListItemsTop[i].getCheckBoxItem().getTitle());
            int finalI = i;
            buttons[i].setOnClickListener(v->showDefects(TilesActivity.this,finalI,k));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_tiles);
        Intent intent=getIntent();
        k=intent.getIntExtra("k",-1);
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
            checkListItemsTop[i].getCheckBoxItem().setChecked(!checkListItemsTop[i].getCheckedItems().equals(""));
            if(checkListItemsTop[i].getCheckBoxItem().isChecked())
                buttons[i].setBackgroundColor(Color.YELLOW);
            buttons[i].setText(checkListItemsTop[i].getCheckBoxItem().getTitle());
            int finalI = i;
            buttons[i].setOnClickListener(v->showDefects(TilesActivity.this,finalI,k));
        }



       // buttons[5].setText(R.string.pidr);
    }

    private void showDefects(Context context,int m,int k) {
        Intent intent=new Intent(context,List.class);
        intent.putExtra("m",m);
        intent.putExtra("k",k);
        startActivity(intent);
        /*AlertDialog.Builder builder=new AlertDialog.Builder(context);
        String[] Items=new String[checkListItems.length];
        boolean[] chedList=new boolean[checkListItems.length];
        for(int i=0;i<checkListItems.length;i++){
            Items[i]=checkListItems[i].getCheckBoxItem().getTitle();
            chedList[i]=checkListItems[i].getCheckBoxItem().isChecked();
        }
        builder.setMultiChoiceItems(Items, chedList, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //Toast.makeText(context,String.format("item # %d = %s",which,checkListItems[which].getDescription()),Toast.LENGTH_SHORT).show();
                checkListItems[which].getCheckBoxItem().setChecked(isChecked);
            }
        });
        builder.setPositiveButton(R.string.confirm_changes, (dialog, which) -> {
            dialog.cancel();
            buttons[k].setBackgroundColor(Color.GREEN);
            checkListItemsTop[k].getCheckBoxItem().setChecked(true);
        });
        builder.setNegativeButton(R.string.cancel,(dialog, which) -> {dialog.cancel(); buttons[k].setBackgroundColor(Color.YELLOW);checkListItemsTop[k].getCheckBoxItem().setChecked(true);});
        builder.setTitle(checkListItemsTop[k].getCheckBoxItem().getTitle());
        builder.show();*/

    }


    private void getChecboxBlock(Context context,int i,int k,CheckListItem checkListItem ,LinearLayout linearLayout){
        LowCheckListItem lowCheckListItem=checkListItem.getDefectCheckListItems()[i].getLowItemsModels().getLowCheckListItems()[k];
        final TextView checboxesTitle=new TextView(context);
        checboxesTitle.setText(lowCheckListItem.getCheckBoxesTitle());
        linearLayout.addView(checboxesTitle);

        final CheckBox[] checkBoxes=new CheckBox[lowCheckListItem.getCheckBoxItems().length];
        for(int j=0;j<checkListItem.getDefectCheckListItems().length;i++){
            checkBoxes[j] = new CheckBox(context);
            checkBoxes[j].setText(lowCheckListItem.getCheckBoxItems()[j].getTitle());
            checkBoxes[j].setChecked(lowCheckListItem.getCheckBoxItems()[j].isChecked());

            int[] l={j};
            checkBoxes[j].setOnClickListener(v -> {
                lowCheckListItem.getCheckBoxItems()[l[0]].setChecked(!lowCheckListItem.getCheckBoxItems()[l[0]].isChecked());
                checkBoxes[j].setChecked(!lowCheckListItem.getCheckBoxItems()[l[0]].isChecked());
            });
            linearLayout.addView(checkBoxes[j]);
        }
        return;
    }
}
