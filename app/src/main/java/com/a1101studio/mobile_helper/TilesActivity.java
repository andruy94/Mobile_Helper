package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

public class TilesActivity extends AppCompatActivity {
    private  CheckListItem[] checkListItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        btn2.setText(checkListItem[1].getDescription());
        btn3.setText(checkListItem[2].getDescription());
        btn4.setText(checkListItem[3].getDescription());
        //btn5.setText(checkListItem[4].getDescription());
    }
}
