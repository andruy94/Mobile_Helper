package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.ArrayList;

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
        Button button=new Button(this);
        button.setText(R.string.send);
        button.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(MainListActivity.this);
            builder.setMessage(R.string.sending_is_ok).setPositiveButton(R.string.cancel, (dialog, which) -> dialog.cancel());
        });
        //LinearLayout view=(LinearLayout) getLayoutInflater().inflate(R.layout.top_list_item,listView);
        View rowView =  ((LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.top_list_item, null, false);
        EditText etSeatNubmer=(EditText) rowView.findViewById(R.id.etSeatNumber);

        TextView tvDefect=(TextView) rowView.findViewById(R.id.tvDefect);
        ImageButton imageView=(ImageButton) rowView.findViewById(R.id.ibPhoto);
        imageView.setOnClickListener(v->new AlertDialog.Builder(this).setMessage(getString(R.string.fill)).setPositiveButton("OK", (dialog, which) ->dialog.cancel()).show());

        Resources res = getResources();
        String[] title=res.getStringArray(R.array.title);//оглавление
        ArrayList<String[]> descs=new ArrayList<String[]>();
        String[] descs1=res.getStringArray(R.array.Des_1_1);
        String[] descs2=res.getStringArray(R.array.Des_1_2);
        String[] descs3=res.getStringArray(R.array.Des_1_3);
        String[] descs4=res.getStringArray(R.array.Des_1_4);

        descs.add(descs1);
        descs.add(descs2);
        descs.add(descs3);
        descs.add(descs4);



        tvDefect.setOnClickListener(v->{
            if(!etSeatNubmer.getText().toString().trim().equals("")){
                WorkData.getInstance().getTopListModels().add(new TopListModel("...",etSeatNubmer.getText().toString()));
                Intent intent=new Intent(this, List.class);
                WorkData.getInstance().getCheckListItemList().add(LoginActivity1.addCheckListItem(title,descs));//тут можешь мподпихивать данные нужные
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

}
