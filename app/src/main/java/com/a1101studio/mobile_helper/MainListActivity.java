package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1101studio.mobile_helper.adapters.TopListAdapter;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import static com.a1101studio.mobile_helper.adapters.TopListAdapter.REQUEST_IMAGE_CAPTURE;
import static com.a1101studio.mobile_helper.adapters.TopListAdapter.jakers;

public class MainListActivity extends AppCompatActivity {

    private ArrayList<TopListModel> topListModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.topListModels= WorkData.getInstance().getTopListModels();
        ListView listView=(ListView) findViewById(R.id.lv1);

        TopListAdapter adapter = new TopListAdapter(this, WorkData.getInstance().getTopListModels());
        listView.setAdapter(adapter);

        View rowView =  ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.top_list_item, null, false);
        EditText etSeatNubmer=(EditText) rowView.findViewById(R.id.etSeatNumber);

        TextView tvDefect=(TextView) rowView.findViewById(R.id.tvDefect);
        ImageButton imageView=(ImageButton) rowView.findViewById(R.id.ibPhoto);
        imageView.setOnClickListener(v->new AlertDialog.Builder(this).setMessage(getString(R.string.fill)).setPositiveButton("OK", (dialog, which) ->dialog.cancel()).show());

        /*Resources res = getResources();
        String[] title=res.getStringArray(R.array.title);//оглавление
        ArrayList<String[]> descs=new ArrayList<String[]>();
        String[] descs1=res.getStringArray(R.array.Des_1_1);
        String[] descs2=res.getStringArray(R.array.Des_1_2);
        String[] descs3=res.getStringArray(R.array.Des_1_3);
        String[] descs4=res.getStringArray(R.array.Des_1_4);
        String[] descs5=res.getStringArray(R.array.Des_1_5);
        descs.add(descs1);
        descs.add(descs2);
        descs.add(descs3);
        descs.add(descs4);
        descs.add(descs5);*/

        String detailName="detail1";

        String[] defectsName={"defect1","defect228","defect3"};

        String[] s1={"ololo1","ololo12","ololo13"};
        String[] s2={"olol2"};
        String[] s3={"olol3","okki2"};
        ArrayList<String[]> LowModelsCHeckboxesTitles =new ArrayList<>();
        LowModelsCHeckboxesTitles.add(s1);
        LowModelsCHeckboxesTitles.add(s2);
        LowModelsCHeckboxesTitles.add(s3);

        String[][] ss1=new String[3][];
        for (int i=0;i<s1.length;i++){
            ss1[i]=new String[3];
            for(int j=0;j<3;j++){
                ss1[i][j]="olol2"+i;
            }
        }
        String[][] ss2=new String[3][];
        for (int i=0;i<s2.length;i++){
            ss2[i]=new String[1];
            for(int j=0;j<1;j++){
                ss2[i][j]="olol"+j;
            }
        }
        String[][] ss3=new String[3][];
        for (int i=0;i<s3.length;i++){
            ss3[i]=new String[1];
            for(int j=0;j<2;j++){
                ss3[i][j]="olol"+j;
            }
        }
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles =new ArrayList<>();
        lowlowModelsCHeckboxesTitles.add(ss1);
        lowlowModelsCHeckboxesTitles.add(ss2);
        lowlowModelsCHeckboxesTitles.add(ss3);


        String[] s4={"Title1"};
        String[] s5={"Title2"};
        String[] s6={"Title3"};
        ArrayList<String[]> LowModelsCommentsTitles =new ArrayList<>();
        LowModelsCommentsTitles.add(s4);
        LowModelsCommentsTitles.add(s5);
        LowModelsCommentsTitles.add(s6);





        tvDefect.setOnClickListener(v->{
            if(!etSeatNubmer.getText().toString().trim().equals("")){
                WorkData.getInstance().getTopListModels().add(new TopListModel("...",etSeatNubmer.getText().toString()));
                Intent intent=new Intent(this, TilesActivity.class);
                WorkData.getInstance().getCheckListItemList().add(LoginActivity1.addCheckListItem(detailName,defectsName,LowModelsCHeckboxesTitles,lowlowModelsCHeckboxesTitles,LowModelsCommentsTitles));//тут можешь мподпихивать данные нужные
                intent.putExtra("k",WorkData.getInstance().getTopListModels().size()-1);

                etSeatNubmer.setText("");
                tvDefect.setText("");
                startActivity(intent);}
            else {
                Toast.makeText(this, R.string.fill,Toast.LENGTH_SHORT).show();
            }
        });

        listView.addFooterView(rowView);
        View viewHeader =  ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header_top_list, null, false);
        listView.addHeaderView(viewHeader);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.e("TAG","k="+data.getIntExtra("lol",0));
            final String fileName = jakers;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveFileWithColision(imageBitmap,fileName);
                }
            }).start();
        }
    }
    void saveFileWithColision(Bitmap bitmap, String nameImg){
        File outputDir = getCacheDir();
        try {
            File namefile = new File(outputDir+"/"+nameImg);
            if(namefile.exists())
                namefile.delete();
            Log.e("TAG",namefile.getPath());
            FileOutputStream ostream = new FileOutputStream(namefile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.flush();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
