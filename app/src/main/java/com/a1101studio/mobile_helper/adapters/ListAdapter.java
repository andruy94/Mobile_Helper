package com.a1101studio.mobile_helper.adapters;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.CommentsModel;
import com.a1101studio.mobile_helper.models.DefectCheckListItem;
import com.a1101studio.mobile_helper.models.LowCheckListItem;
import com.a1101studio.mobile_helper.models.LowItemsModel;
import com.a1101studio.mobile_helper.singleton.WorkData;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class ListAdapter extends ArrayAdapter<CheckListItem> {

    private final Context context;

    private final CheckListItem checkListItem;

    @Override
    public int getCount() {
        return checkListItem.getDefectCheckListItems().length;
    }

    public ListAdapter(Context context, CheckListItem[] checkListItem, int k, int m) {
        super(context, R.layout.list_item, checkListItem);
        this.context=context;
        this.checkListItem =WorkData.getInstance().getCheckListItemList().get(k)[m];//checkListItem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        final LinearLayout linearLayout=(LinearLayout) rowView.findViewById(R.id.ll_second_list);


        //final View rowView = inflater.inflate(R.layout.list_item, parent, false);

        CheckBox titleCheckBox=(CheckBox) rowView.findViewById(R.id.chbHeader);
        titleCheckBox.setText(checkListItem.getDefectCheckListItems()[position].getCheckBoxItem().getTitle());
        titleCheckBox.setChecked(checkListItem.getCheckBoxItem().isChecked());


        LowCheckListItem[] lowCheckListItems=checkListItem.getDefectCheckListItems()[position].getLowItemsModels().getLowCheckListItems();
        if(lowCheckListItems!=null){
        TextView[] textViews=new TextView[lowCheckListItems.length];
        for(int i=0;i<lowCheckListItems.length;i++) {
            if(lowCheckListItems[i]!=null) {
                textViews[i] = new TextView(context);
                textViews[i].setText(lowCheckListItems[i].getCheckBoxesTitle());
                linearLayout.addView(textViews[i]);
                for (int j = 0; j < lowCheckListItems[i].getCheckBoxItems().length; j++) {
                    CheckBox checkBox = new CheckBox(context);
                    checkBox.setText(lowCheckListItems[i].getCheckBoxItems()[j].getTitle());
                    checkBox.setChecked(lowCheckListItems[i].getCheckBoxItems()[j].isChecked());
                    int finalI = i;
                    int finalJ = j;
                    checkBox.setOnClickListener(v -> {

                        checkBox.setChecked(!lowCheckListItems[finalI].getCheckBoxItems()[finalJ].isChecked());
                        lowCheckListItems[finalI].getCheckBoxItems()[finalJ].setChecked(!lowCheckListItems[finalI].getCheckBoxItems()[finalJ].isChecked());

                    });
                    linearLayout.addView(checkBox);
                }
            }
        }
        }

        CommentsModel[] commentsModels=checkListItem.getDefectCheckListItems()[position].getLowItemsModels().getCommentsModels();
        if(commentsModels!=null)
        for(int i=0;i<commentsModels.length;i++){

            TextView textView=new TextView(context);
            EditText editText=new EditText(context);

            editText.setFocusable(true);
            editText.setEnabled(true);
            //editText.setFocusableInTouchMode(true);
            editText.setId(i+228);
            //editText.setBackgroundColor(Color.WHITE);
            editText.setWidth(180);
            if(i==0 && position==0)
                editText.requestFocus();
            // textView1.setTag(i,"eT"+i);
            int finalI = i;
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                    if(s.length() != 0)
                        commentsModels[finalI].setComment(s.toString());
                }

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            if(commentsModels[i].getCommentTitle()!=null)
                textView.setText(commentsModels[i].getCommentTitle());
            if(commentsModels[i].getComment()!=null)
                editText.setText(commentsModels[i].getComment());
            linearLayout.addView(textView);
            linearLayout.addView(editText,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }







        /*final boolean[] flag = {true};
        titleCheckBox.setOnClickListener(v -> {
            if(flag[0]) {

                //View child=inflater.inflate(R.layout.inner_item,linearLayout);
                for(int i=0;i<checkListItem.getDefectCheckListItems().length;i++) {
                    linearLayout.addView(checkBoxes[i]);
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
