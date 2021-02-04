package com.a1101studio.mobile_helper_open.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1101studio.mobile_helper_open.R;
import com.a1101studio.mobile_helper_open.models.Detail;
import com.a1101studio.mobile_helper_open.models.CommentsModel;
import com.a1101studio.mobile_helper_open.models.LowCheckListItem;
import com.a1101studio.mobile_helper_open.singleton.WorkData;

import java.util.ArrayList;

import static com.a1101studio.mobile_helper_open.adapters.TopListAdapter.REQUEST_IMAGE_CAPTURE;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class ListAdapter extends ArrayAdapter<Detail> {
    private int lastFocussedPosition = -1;
    private Handler handler = new Handler();
    public ArrayList<ViewHolderModel> getViewHolderModelArrayList() {
        return viewHolderModelArrayList;
    }

    private ArrayList<ViewHolderModel> viewHolderModelArrayList = new ArrayList<>();
    private final Context context;
    private String currentTag;
    private final Detail detail;

    public String getCurrentTag() {
        return currentTag;
    }

    static public class ViewHolderModel {
        private static final int TEXT_SIZE = 34;
        private static final int TEXT_SIZE_SMALL = 20;
        private CheckBox defectCheckBox;
        private TextView[] titleCheckBoxesBlocks;
        private CheckBox[][] checkBoxes;
        private EditText[] commentsEditTexts;
        private TextView[] commentsTextViews;
        private ImageView takePhoto;

        ViewHolderModel(CheckBox defectCheckBox, TextView[] titleCheckBoxesBlocks, CheckBox[][] checkBoxes, EditText[] commentsEditTexts, TextView[] commentsTextViews) {
            this.defectCheckBox = defectCheckBox;
            this.titleCheckBoxesBlocks = titleCheckBoxesBlocks;
            this.checkBoxes = checkBoxes;
            this.commentsEditTexts = commentsEditTexts;
            this.commentsTextViews = commentsTextViews;
        }

        public ViewHolderModel() {

        }

        public CheckBox getDefectCheckBox() {
            return defectCheckBox;
        }

        public void setDefectCheckBox(CheckBox defectCheckBox) {
            this.defectCheckBox = defectCheckBox;
            this.defectCheckBox.setTextSize(TEXT_SIZE);
        }

        public TextView[] getTitleCheckBoxesBlocks() {
            return titleCheckBoxesBlocks;
        }

        public void setTitleCheckBoxesBlocks(TextView[] titleCheckBoxesBlocks) {
            this.titleCheckBoxesBlocks = titleCheckBoxesBlocks;
        }

        public CheckBox[][] getCheckBoxes() {
            return checkBoxes;
        }

        public void setCheckBoxes(CheckBox[][] checkBoxes) {
            this.checkBoxes = checkBoxes;
        }

        public TextView[] getCommentsTextViews() {
            return commentsTextViews;
        }

        public void setCommentsTextViews(TextView[] commentsTextViews) {
            this.commentsTextViews = commentsTextViews;
        }

        public EditText[] getCommentsEditTexts() {
            return commentsEditTexts;
        }

        public void setCommentsEditTexts(EditText[] commentsEditTexts) {
            this.commentsEditTexts = commentsEditTexts;
        }

    }

    @Override
    public int getCount() {
        return detail.getDefectCheckListItems().length;
    }

    public ListAdapter(Context context, Detail[] detail, int k, int m) {
        super(context, R.layout.list_item, detail);
        this.context = context;
        this.detail = WorkData.getInstance().getDetails().get(k)[m];//detail;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        final LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);


        //final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolderModel v = new ViewHolderModel();
        viewHolderModelArrayList.add(v);
        //фото кнопка
        ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.iBtnPhotoDefect);
        imageButton.setOnClickListener(view ->
        {
            currentTag= detail.getDefectCheckListItems()[position].getCheckBoxItem().getTitle();
            final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ((Activity)context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        });

        //чекбокс дефекта
        CheckBox defectCheckBox = (CheckBox) rowView.findViewById(R.id.chbHeader);
        v.setDefectCheckBox(defectCheckBox);
        defectCheckBox.setEnabled(true);
        defectCheckBox.setText(detail.getDefectCheckListItems()[position].getCheckBoxItem().getTitle());
        defectCheckBox.setChecked(detail.getDefectCheckListItems()[position].getCheckBoxItem().isChecked());
        if (detail.getDefectCheckListItems()[position].getCheckBoxItem().isChecked()) {
            linearLayout.setVisibility(View.VISIBLE);
            defectCheckBox.setChecked(true);
        } else {
            linearLayout.setVisibility(View.GONE);
        }
        defectCheckBox.setOnClickListener(view -> {
            if (detail.getDefectCheckListItems()[position].getCheckBoxItem().isChecked()) {
                linearLayout.setVisibility(View.GONE);
                defectCheckBox.setChecked(false);
                detail.getDefectCheckListItems()[position].getCheckBoxItem().setChecked(false);
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                defectCheckBox.setChecked(true);
                detail.getDefectCheckListItems()[position].getCheckBoxItem().setChecked(true);
            }
        });
        //блок наборов чекьбоксов(один набор - оглавление и N-1 чекбос)
        LowCheckListItem[] lowCheckListItems = detail.getDefectCheckListItems()[position].getLowItemsModels().getLowCheckListItems();

        if (lowCheckListItems != null) {
            TextView[] textViews = new TextView[lowCheckListItems.length];
            v.setTitleCheckBoxesBlocks(textViews);
            CheckBox[][] checkBoxes = new CheckBox[lowCheckListItems.length][];
            v.setCheckBoxes(checkBoxes);
            for (int i = 0; i < lowCheckListItems.length; i++) {
                if (lowCheckListItems[i] != null) {
                    textViews[i] = new TextView(context);
                    textViews[i].setText(lowCheckListItems[i].getCheckBoxesTitle());
                    linearLayout.addView(textViews[i]);
                    checkBoxes[i] = new CheckBox[lowCheckListItems[i].getCheckBoxItems().length];
                    for (int j = 0; j < lowCheckListItems[i].getCheckBoxItems().length; j++) {
                        checkBoxes[i][j] = new CheckBox(context);
                        CheckBox checkBox = checkBoxes[i][j];
                        checkBox.setText(lowCheckListItems[i].getCheckBoxItems()[j].getTitle());
                        checkBox.setChecked(lowCheckListItems[i].getCheckBoxItems()[j].isChecked());
                        int finalI = i;
                        int finalJ = j;
                        checkBox.setOnClickListener(view -> {
                            checkBox.setChecked(!lowCheckListItems[finalI].getCheckBoxItems()[finalJ].isChecked());
                            lowCheckListItems[finalI].getCheckBoxItems()[finalJ].setChecked(!lowCheckListItems[finalI].getCheckBoxItems()[finalJ].isChecked());
                        });
                        linearLayout.addView(checkBox);
                    }
                }
            }
        }
        //коментарии к дефетк

        CommentsModel[] commentsModels = detail.getDefectCheckListItems()[position].getLowItemsModels().getCommentsModels();

        TextView[] commentsTextViews = new TextView[commentsModels.length];
        v.setCommentsTextViews(commentsTextViews);
        EditText[] commentsEditTexts = new EditText[commentsModels.length];
        v.setCommentsEditTexts(commentsEditTexts);
        if (commentsModels != null)
            for (int i = 0; i < commentsModels.length; i++) {

                commentsTextViews[i] = new TextView(context);
                commentsEditTexts[i] = new EditText(context);

                commentsEditTexts[i].setFocusable(true);
                commentsEditTexts[i].setEnabled(true);
                int finalI1 = i;
                commentsEditTexts[i].setOnFocusChangeListener(new View.OnFocusChangeListener() {

                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            handler.postDelayed(new Runnable() {

                                @Override
                                public void run() {
                                    if (lastFocussedPosition == -1 || lastFocussedPosition == position) {
                                        lastFocussedPosition = position;
                                        commentsEditTexts[finalI1].requestFocus();
                                    }
                                }
                            }, 100);

                        } else {
                            lastFocussedPosition = -1;
                        }
                    }
                });

                // textView1.setTag(i,"eT"+i);
                int finalI = i;
                commentsEditTexts[i].addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start,
                                                  int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start,
                                              int before, int count) {
                        if (s.toString().trim().length() != 0)
                            commentsModels[finalI].setComment(s.toString());
                        else
                            commentsModels[finalI].setComment("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                if (commentsModels[i].getCommentTitle() != null)
                    commentsTextViews[i].setText(commentsModels[i].getCommentTitle());
                if (commentsModels[i].getComment() != null)
                    commentsEditTexts[i].setText(commentsModels[i].getComment());
                linearLayout.addView(commentsTextViews[i]);
                linearLayout.addView(commentsEditTexts[i], new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }







        /*final boolean[] flag = {true};
        titleCheckBox.setOnClickListener(v -> {
            if(flag[0]) {

                //View child=inflater.inflate(R.layout.inner_item,linearLayout);
                for(int i=0;i<detail.getDefectCheckListItems().length;i++) {
                    linearLayout.addView(checkBoxes[i]);
                    linearLayout.addView(checkBoxes[i]);
                    checkBoxes[i].setChecked(detail.getDefectCheckListItems()[i].getCheckBoxItem().isChecked());
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





   /* private View getChecboxBlock(Context context,int i,Detail detail ){
        LowItemsModel lowItemsModel=detail.getDefectCheckListItems()[i].getLowItemsModels();
        final TextView[] CheckBoxesTitle=new TextView[lowItemsModel.getLowCheckListItems().length];
        for (int j=0;j<lowItemsModel.getLowCheckListItems().length;j++){
            CheckBoxesTitle[j]=new TextView(context);
            CheckBoxesTitle[j].setText(lowItemsModel.getLowCheckListItems()[j].getCheckBoxesTitle());
        }


        final CheckBox[] checkBoxes=new CheckBox[lowItemsModel.getLowCheckListItems().length];
        for(int j=0;j<detail.getDefectCheckListItems().length;i++){
            checkBoxes[j] = new CheckBox(context);
            checkBoxes[j].setText(detail.getDefectCheckListItems()[i].getCheckBoxItem().getTitle());
            checkBoxes[j].setChecked(detail.getDefectCheckListItems()[i].getCheckBoxItem().isChecked());
            int[] k={j};
            checkBoxes[j].setOnClickListener(v -> {
                DefectCheckListItem defectCheckListItem=detail.getDefectCheckListItems()[k[0]];
                defectCheckListItem.getCheckBoxItem().setChecked( !defectCheckListItem.getCheckBoxItem().isChecked());
                detail.setChecked(defectCheckListItem.getCheckBoxItem().isChecked());
                titleCheckBox.setChecked(true);

            });
        }
    }*/

}
