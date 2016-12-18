package com.a1101studio.mobile_helper.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a1101studio.mobile_helper.List;
import com.a1101studio.mobile_helper.LoginActivity1;
import com.a1101studio.mobile_helper.MainActivity;
import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class TopListAdapter  extends ArrayAdapter<TopListModel> {
    ArrayList<TopListModel> topListModels;
    Context context;
    public TopListAdapter(Context context, ArrayList<TopListModel> topListModels) {
        super(context, R.layout.top_list_item,topListModels);
        this.context=context;
        this.topListModels= WorkData.getInstance().getTopListModels();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.top_list_item, parent, false);
        EditText etSeatNubmer=(EditText) rowView.findViewById(R.id.etSeatNumber);
        
        TextView tvDefect=(TextView) rowView.findViewById(R.id.tvDefect);

        etSeatNubmer.setText(topListModels.get(position).getSeatNumber());

        
        tvDefect.setText(topListModels.get(position).getDefect());
        tvDefect.setOnClickListener(v->{
            if(!etSeatNubmer.getText().toString().trim().equals("")){
            topListModels.get(position).setSeatNumber(etSeatNubmer.getText().toString());
            topListModels.get(position).setDefect("...");
            Intent intent=new Intent(context, List.class);
            intent.putExtra("k",position);
            context.startActivity(intent);}
            else {
                Toast.makeText(context, R.string.fill,Toast.LENGTH_SHORT).show();
            }
        });


        return rowView;
    }
}
