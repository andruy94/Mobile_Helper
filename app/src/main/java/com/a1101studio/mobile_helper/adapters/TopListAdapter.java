package com.a1101studio.mobile_helper.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.TilesActivity;
import com.a1101studio.mobile_helper.models.Detail;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;

import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class TopListAdapter extends ArrayAdapter<TopListModel> {
    public static int REQUEST_IMAGE_CAPTURE = 1;
    public static String jakers;
    private ArrayList<TopListModel> topListModels;
    private Context context;

    public TopListAdapter(Context context, ArrayList<TopListModel> topListModels) {
        super(context, R.layout.top_list_item, topListModels);
        this.context = context;
        this.topListModels = WorkData.getInstance().getTopListModels();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.top_list_item, parent, false);
        EditText etSeatNubmer = (EditText) rowView.findViewById(R.id.etSeatNumber);

        TextView tvDefect = (TextView) rowView.findViewById(R.id.tvDefect);

        StringBuilder stringBuilder = new StringBuilder();
        Detail[] details = WorkData.getInstance().getDetails().get(position);
        for (Detail detail : details) {
            stringBuilder.append(detail.getCheckedItems());
        }
        topListModels.get(position).setDefect(stringBuilder.toString());

        ImageButton ibPhoto = (ImageButton) rowView.findViewById(R.id.ibPhoto);
        ibPhoto.setEnabled(true);
        ibPhoto.setOnClickListener(v -> dispatchTakePictureIntent(topListModels.get(position).getSeatNumber()));
        etSeatNubmer.setText(topListModels.get(position).getSeatNumber() + ';' + topListModels.get(position).getType());
        etSeatNubmer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setNegativeButton(context.getString(R.string.cancel),null).setItems(context.getResources().getStringArray(R.array.extra_long_click_menu), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                new AlertDialog.Builder(context).setItems(context.getResources().getStringArray(R.array.types), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        topListModels.get(position).setType(context.getResources().getStringArray(R.array.types)[which]);
                                        dialog.cancel();
                                    }
                                }).show();
                                TopListAdapter.this.notifyDataSetChanged();
                                dialog.cancel();
                                break;
                            case 1:
                                topListModels.remove(position);
                                WorkData.getInstance().getDetails().remove(position);
                                TopListAdapter.this.notifyDataSetChanged();
                                dialog.cancel();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        tvDefect.setText(topListModels.get(position).getType());
        tvDefect.setOnClickListener(v -> Toast.makeText(context, "ololo", Toast.LENGTH_LONG).show());

        tvDefect.setText(topListModels.get(position).getDefect());
        tvDefect.setOnClickListener(v -> {
            if (!etSeatNubmer.getText().toString().trim().equals("")) {
                String s=etSeatNubmer.getText().toString();
                boolean flag=false;
                int k=0;
                String[] stringArray = context.getResources().getStringArray(R.array.types);
                for (int i = 0; i < stringArray.length; i++) {
                    String str = stringArray[i];

                    if (s.contains(str)) {
                        flag = true;
                        k=i;
                        break;
                    }

                }
                if(flag)
                    s=s.substring(0,s.length()-context.getResources().getStringArray(R.array.types)[k].length()-1);

                topListModels.get(position).setSeatNumber(s);

                //topListModels.get(position).setDefect("...");
                Intent intent = new Intent(context, TilesActivity.class);
                intent.putExtra("k", position);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, R.string.fill, Toast.LENGTH_SHORT).show();
            }
        });

        if(position==0)
            etSeatNubmer.requestFocus();
        return rowView;
    }

    private void dispatchTakePictureIntent(String filename) {

        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
       /* if(new File(context.getCacheDir()+"/"+filename).exists()){
            AlertDialog.Builder builder=new AlertDialog.Builder(context);
            View view=((Activity)context).getLayoutInflater().inflate(R.layout.dialog_view, null);
            ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
            Glide.with(context).load(context.getCacheDir()+"/"+filename).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(imageView);
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.setNegativeButton(R.string.re_take_photo, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    jakers=filename;
                    ((Activity)context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            });
            builder.show();
        }
        else if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {*/
        jakers = filename;
        ((Activity) context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        //}
    }

}
