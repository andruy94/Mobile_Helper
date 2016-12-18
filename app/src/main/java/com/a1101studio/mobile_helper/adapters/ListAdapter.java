package com.a1101studio.mobile_helper.adapters;

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

import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class ListAdapter extends ArrayAdapter<CheckListItem> {
   // private int checkBoxCount=3;
    private final Context context;

    private final CheckListItem[] checkListItem;
    public ListAdapter(Context context,  CheckListItem[] checkListItem) {
        super(context, R.layout.list_item, checkListItem);
        this.context=context;
        this.checkListItem=WorkData.getInstance().getCheckListItemList();//checkListItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CheckBox[] checkBoxes=new CheckBox[checkListItem[position].getCheckListItems().length];
        for(int i=0;i<checkListItem[position].getCheckListItems().length;i++){
            checkBoxes[i] = new CheckBox(context);
            checkBoxes[i].setText(checkListItem[position].getCheckListItems()[i].getDescription());
        }

        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.text1);
        textView.setText(checkListItem[position].getDescription());
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
