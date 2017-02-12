package com.a1101studio.mobile_helper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.LowCheckListItem;
import com.a1101studio.mobile_helper.models.LowItemsModel;
import com.a1101studio.mobile_helper.singleton.WorkData;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class ListAdapter extends ArrayAdapter<CheckListItem> {

    private final Context context;

    private final CheckListItem[] checkListItems;
    public ListAdapter(Context context, CheckListItem[] checkListItem, int k) {
        super(context, R.layout.list_item, checkListItem);
        this.context=context;
        this.checkListItems =WorkData.getInstance().getCheckListItemList().get(k);//checkListItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckListItem checkListItem= this.checkListItem[position];
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);

        CheckBox titleCheckBox=(CheckBox) rowView.findViewById(R.id.chbHeader);
        titleCheckBox.setText(checkListItem.getDefectCheckListItems()[position].getCheckBoxItem().getTitle());
        titleCheckBox.setChecked(checkListItem.getDefectCheckListItems()[position].getCheckBoxItem().isChecked());






        /*final boolean[] flag = {true};
        titleCheckBox.setOnClickListener(v -> {
            if(flag[0]) {

                //View child=inflater.inflate(R.layout.inner_item,linearLayout);
                for(int i=0;i<checkListItem.getDefectCheckListItems().length;i++) {
                    linearLayout.addView(checkBoxes[i]);
                    checkBoxes[i].setChecked(checkListItem.getDefectCheckListItems()[i].getCheckBoxItem().isChecked());
                }
                flag[0] =!flag[0];
            }else {
                LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);
                linearLayout.removeAllViews();
                flag[0] =!flag[0];
            }
        })*/

        return rowView;
    }



   /* private View getChecboxBlock(Context context,int i,CheckListItem checkListItem ){
        LowItemsModel lowItemsModel=checkListItem.getDefectCheckListItems()[i].getLowItemsModels();
        final TextView[] CheckBoxesTitle=new TextView[lowItemsModel.getLowCheckListItems().length];
        for (int j=0;j<lowItemsModel.getLowCheckListItems().length;j++){
            CheckBoxesTitle[j]=new TextView(context);
            CheckBoxesTitle[j].setText(lowItemsModel.getLowCheckListItems()[j].getCheckBoxesTitle());
        }


        final CheckBox[] checkBoxes=new CheckBox[lowItemsModel.getLowCheckListItems().length];
        for(int j=0;j<checkListItem.getDefectCheckListItems().length;i++){
            checkBoxes[j] = new CheckBox(context);
            checkBoxes[j].setText(checkListItem.getDefectCheckListItems()[i].getCheckBoxItem().getTitle());
            checkBoxes[j].setChecked(checkListItem.getDefectCheckListItems()[i].getCheckBoxItem().isChecked());
            int[] k={j};
            checkBoxes[j].setOnClickListener(v -> {
                DefectCheckListItem defectCheckListItem=checkListItem.getDefectCheckListItems()[k[0]];
                defectCheckListItem.getCheckBoxItem().setChecked( !defectCheckListItem.getCheckBoxItem().isChecked());
                checkListItem.setChecked(defectCheckListItem.getCheckBoxItem().isChecked());
                titleCheckBox.setChecked(true);

            });
        }
    }*/

}
