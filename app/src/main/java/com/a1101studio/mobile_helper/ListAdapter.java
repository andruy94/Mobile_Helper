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
    private final Context context;

    private final String[] values;
    public ListAdapter(Context context,  String[] values) {
        super(context,R.layout.list_item, values);
        this.context=context;
        this.values=values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        textView.setText(values[position]);
        final boolean[] flag = {true};
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CheckBox) rowView.findViewById(R.id.chbHeader)).setChecked(true);
                if(flag[0]) {
                    LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);
                    // View child=inflater.inflate(R.layout.inner_item,linearLayout);
                    View child = new CheckBox(context);
                    ((CheckBox) child).setText("ololol");
                    linearLayout.addView(child);
                    View child1 = new CheckBox(context);
                    ((CheckBox) child1).setText("ololol");
                    linearLayout.addView(child1);
                    View child2 = new CheckBox(context);
                    ((CheckBox) child2).setText("ololol");
                    linearLayout.addView(child2);
                    flag[0] =!flag[0];
                }else {
                    Toast.makeText(context, "asd", Toast.LENGTH_LONG).show();
                    LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);
                    linearLayout.removeAllViews();
                    flag[0] =!flag[0];
                }
            }
        });

        return rowView;
    }
}
