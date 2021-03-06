package com.a1101studio.mobile_helper_open;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.a1101studio.mobile_helper_open.adapters.NewListAdapter;
import com.a1101studio.mobile_helper_open.models.DefectCheckListItem;
import com.a1101studio.mobile_helper_open.models.Detail;
import com.a1101studio.mobile_helper_open.singleton.WorkData;

import java.util.Date;

import static com.a1101studio.mobile_helper_open.adapters.TopListAdapter.REQUEST_IMAGE_CAPTURE;
import static com.a1101studio.mobile_helper_open.utils.FileHelper.saveFileWithColision;

public class List extends AppCompatActivity {

    NewListAdapter listAdapter;
    private String headName;
    Detail detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Intent intent = getIntent();
        int k = intent.getIntExtra("k", -1);
        int m = intent.getIntExtra("m", -1);


        detail = WorkData.getInstance().getDetails().get(k)[m];
        listAdapter = new NewListAdapter(this, WorkData.getInstance().getDetails().get(k), k, m);
        headName = WorkData.getInstance().getTopListModels().get(k).getSeatNumber();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv1);
        recyclerView.setAdapter(listAdapter);
        Button button = (Button) findViewById(R.id.btnConfrim);
        button.setTextSize(20);
        button.setText(R.string.confirm);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (checkResult())
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addView(button);
        //TopListAdapter adapter = new TopListAdapter(this, WorkData.getInstance().getTopListModels());
        //listView.setAdapter(adapter);

        /*AlertDialog.Builder builder=new AlertDialog.Builder(this);
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
        button.setOnClickListener(v->finish());*/


        //listView.addHeaderView(button);
    }

    private boolean checkResult() {
        boolean flag = true;
        DefectCheckListItem[] defectCheckListItems = detail.getDefectCheckListItems();

        for (int i = 0; i < defectCheckListItems.length; i++) {
            if (defectCheckListItems[i].getCheckBoxItem().isChecked()) {
                if (defectCheckListItems[i].getLowItemsModels().getCommentsModels().length > 0) {
                    for (int j = 0; j < defectCheckListItems[i].getLowItemsModels().getCommentsModels().length; j++) {
                        //---
                    }
                }
            }
        }
        return flag;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.e("TAG", "k=" + data.getIntExtra("lol", 0));
            String fileName = listAdapter.getCurrentTag();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveFileWithColision(imageBitmap, "/" + headName + "/" + fileName + "/", fileName + "_" + new Date().getTime() + ".jpg",List.this);
                }
            }).start();
        }
    }


}
