package com.a1101studio.mobile_helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private int checkBoxCount=3;
    private final Context context;

    private final String[] values;
    public ListAdapter(Context context,  String[] values) {
        super(context,R.layout.list_item, values);
        this.context=context;
        this.values=values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CheckBox[] checkBoxes=new CheckBox[checkBoxCount];
        for(int i=0;i<checkBoxes.length;i++){
            checkBoxes[i] = new CheckBox(context);
            checkBoxes[i].setText("ololol"+i);
        }

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        textView.setText(values[position]);
        final boolean[] flag = {true};
        textView.setOnClickListener(v -> {
            ((CheckBox) rowView.findViewById(R.id.chbHeader)).setChecked(true);
            if(flag[0]) {
                LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);
                // View child=inflater.inflate(R.layout.inner_item,linearLayout);
                for(CheckBox checkBox:checkBoxes)
                    linearLayout.addView(checkBox);
                flag[0] =!flag[0];
            }else {
                LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);
                linearLayout.removeAllViews();
                flag[0] =!flag[0];
            }
        });

        return rowView;
    }
}
