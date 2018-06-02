package com.a1101studio.mobile_helper.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
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

import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.models.CommentsModel;
import com.a1101studio.mobile_helper.models.Detail;
import com.a1101studio.mobile_helper.models.LowCheckListItem;
import com.a1101studio.mobile_helper.reportList;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.a1101studio.mobile_helper.utils.FileHelper;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.a1101studio.mobile_helper.adapters.TopListAdapter.REQUEST_IMAGE_CAPTURE;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class NewListAdapter extends RecyclerView.Adapter<NewListAdapter.ViewHolderModel> {
    private final int k;
    private final int m;
    private int lastFocussedPosition = -1;
    private Handler handler = new Handler();


    public ArrayList<ViewHolderModel> getViewHolderModelArrayList() {
        return viewHolderModelArrayList;
    }

    private ArrayList<ViewHolderModel> viewHolderModelArrayList = new ArrayList<>();
    private final Context context;
    private String currentTag;
    private final Detail detail;

    static public class ViewHolderModel extends RecyclerView.ViewHolder {
        private static final int TEXT_SIZE = 34;
        private static final int TEXT_SIZE_SMALL = 20;
        private CheckBox defectCheckBox;
        private TextView[] titleCheckBoxesBlocks;
        private CheckBox[][] checkBoxes;
        private EditText[] commentsEditTexts;
        private TextView[] commentsTextViews;
        private ImageButton imageButton;
        private ImageView takePhoto;
        private LinearLayout linearLayout;

        /*ViewHolderModel(CheckBox defectCheckBox, TextView[] titleCheckBoxesBlocks, CheckBox[][] checkBoxes, EditText[] commentsEditTexts, TextView[] commentsTextViews) {
            this.defectCheckBox = defectCheckBox;
            this.titleCheckBoxesBlocks = titleCheckBoxesBlocks;
            this.checkBoxes = checkBoxes;
            this.commentsEditTexts = commentsEditTexts;
            this.commentsTextViews = commentsTextViews;
        }*/

        /*public ViewHolderModel() {

        }*/

        public ViewHolderModel(View itemView) {
            super(itemView);

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


        public LinearLayout getLinearLayout() {
            return linearLayout;
        }

        public void setLinearLayout(LinearLayout linearLayout) {
            this.linearLayout = linearLayout;
        }

        public ImageButton getImageButton() {
            return imageButton;
        }

        public void setImageButton(ImageButton imageButton) {
            this.imageButton = imageButton;
        }
    }

    public String getCurrentTag() {
        return currentTag;
    }

    @Override
    public NewListAdapter.ViewHolderModel onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        final LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);
        //final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolderModel v = new ViewHolderModel(rowView);
        viewHolderModelArrayList.add(v);
        //фото кнопка
        ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.iBtnPhotoDefect);
        v.setImageButton(imageButton);


        //чекбокс дефекта
        CheckBox defectCheckBox = (CheckBox) rowView.findViewById(R.id.chbHeader);
        v.setDefectCheckBox(defectCheckBox);
        v.setLinearLayout(linearLayout);
        return v;
    }

    @Override
    public void onBindViewHolder(ViewHolderModel v, int pos) {
        LinearLayout linearLayout = v.getLinearLayout();
        linearLayout.removeAllViews();
        int position = v.getAdapterPosition();
        //this.postion = position;
        CheckBox defectCheckBox = v.getDefectCheckBox();
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
                        if (before > 0 || (before== 0 && count>0))

                                commentsModels[finalI].setComment(s.toString());
                        //else
                        // commentsModels[finalI].setComment("");
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                String commentTitle = commentsModels[i].getCommentTitle();
                if (commentTitle != null) {
                    commentsTextViews[i].setText(commentTitle);
                    boolean flag = false;
                    boolean flag2 = false;
                    String[] s = context.getResources().getStringArray(R.array.numeric_template);
                    for (String s1 : s) {
                        if ((commentTitle.contains(s1))) {
                            flag = true;
                            break;
                        }
                    }
                    flag2 = commentTitle.toLowerCase().contains(context.getString(R.string.jaker228));

                    if (flag) {
                        commentsEditTexts[finalI].setInputType(InputType.TYPE_CLASS_NUMBER);
                    } else
                        commentsEditTexts[finalI].setInputType(InputType.TYPE_CLASS_TEXT);

                    if (flag2) {
                        commentsEditTexts[finalI].setInputType(InputType.TYPE_NULL);
                        commentsEditTexts[finalI].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder alertD = new AlertDialog.Builder(context);
                                alertD.setTitle(R.string.choise_phasa);
                                alertD.setSingleChoiceItems(R.array.phases, -1, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        commentsEditTexts[finalI].setText(context.getResources().getStringArray(R.array.phases)[which]);
                                        dialog.cancel();
                                    }
                                });
                                alertD.show();
                            }
                        });
                    }


                }
                if (commentsModels[i].getComment() != null)
                    commentsEditTexts[i].setText(commentsModels[i].getComment());
                linearLayout.addView(commentsTextViews[i]);
                linearLayout.addView(commentsEditTexts[i], new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }

        ImageButton imageButton = v.getImageButton();
        imageButton.setOnClickListener(view ->
        {

            String detailName = detail.getDefectCheckListItems()[position].getCheckBoxItem().getTitle();

            File dir = FileHelper.createOrGetFileDir("/" + WorkData.getInstance().getTopListModels().get(k).getSeatNumber() + "/" + detail.getDefectCheckListItems()[position].getCheckBoxItem().getTitle() + "/", context);
            File file = FileHelper.createImageFile(dir, detailName);
            FileHelper.dispatchTakePictureIntent((Activity) context, file);
        });

        imageButton.setOnLongClickListener(v1 -> {
            String[] theNamesOfFiles;
            File dir = FileHelper.createOrGetFileDir("/" + WorkData.getInstance().getTopListModels().get(k).getSeatNumber() + "/" + detail.getDefectCheckListItems()[position].getCheckBoxItem().getTitle() + "/", context);
            File[] filelist = dir.listFiles();
            if (filelist != null) {
                theNamesOfFiles = new String[filelist.length];
                for (int i = 0; i < theNamesOfFiles.length; i++) {
                    theNamesOfFiles[i] = filelist[i].getName();
                }
            } else {
                theNamesOfFiles = new String[0];
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, theNamesOfFiles);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(R.string.img_files);
            builder.setAdapter(adapter, (dialog, which) -> {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uriForFile = FileProvider.getUriForFile(context, context.getString(R.string.file_provider_authority), new File(dir, adapter.getItem(which)));
                // set flag to give temporary permission to external app to use your FileProvider
                context.grantUriPermission(context.getString(R.string.file_provider_authority),uriForFile,Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(uriForFile, "image/*");
                context.startActivity(intent);
                dialog.cancel();
            });
            builder.setPositiveButton(R.string.cancel, (dialog, which) -> dialog.cancel());
            builder.show();

            return false;
        });


    }

    @Override
    public int getItemCount() {
        return detail.getDefectCheckListItems().length;
    }


    public NewListAdapter(Context context, Detail[] detail, int k, int m) {
        //super(context, R.layout.list_item, detail);
        this.k = k;
        this.m = m;
        this.context = context;
        this.detail = WorkData.getInstance().getDetails().get(k)[m];//detail;
    }

    /*@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        final LinearLayout linearLayout = (LinearLayout) rowView.findViewById(R.id.ll_second_list);


        //final View rowView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolderModel v = new ViewHolderModel(null);
        viewHolderModelArrayList.add(v);
        //фото кнопка
        ImageButton imageButton = (ImageButton) rowView.findViewById(R.id.iBtnPhotoDefect);
        imageButton.setOnClickListener(view ->
        {
            currentTag = detail.getDefectCheckListItems()[position].getCheckBoxItem().getTitle();
            final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        });

        //чекбокс дефекта
        CheckBox defectCheckBox = (CheckBox) rowView.findViewById(R.id.chbHeader);
        v.setDefectCheckBox(defectCheckBox);
        defectCheckBox = v.getDefectCheckBox();
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

    //return rowView;
    //}





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
