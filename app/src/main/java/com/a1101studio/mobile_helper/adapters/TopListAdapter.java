package com.a1101studio.mobile_helper.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1101studio.mobile_helper.List;
import com.a1101studio.mobile_helper.LoginActivity1;
import com.a1101studio.mobile_helper.MainActivity;
import com.a1101studio.mobile_helper.R;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by andruy94 on 12/18/2016.
 */

public class TopListAdapter  extends ArrayAdapter<TopListModel> {
    public static int REQUEST_IMAGE_CAPTURE=1;
    public static String jakers;
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
        ImageButton ibPhoto=(ImageButton) rowView.findViewById(R.id.ibPhoto);
        ibPhoto.setEnabled(true);
        ibPhoto.setOnClickListener(v -> dispatchTakePictureIntent(topListModels.get(position).getSeatNumber()));
        etSeatNubmer.setText(topListModels.get(position).getSeatNumber());

        
        tvDefect.setText(topListModels.get(position).getDefect());
        tvDefect.setOnClickListener(v->{
            if(!etSeatNubmer.getText().toString().trim().equals("")){
            topListModels.get(position).setSeatNumber(etSeatNubmer.getText().toString());
            topListModels.get(position).setDefect("...");
            Intent intent=new Intent(context, List.class);

            context.startActivity(intent);}
            else {
                Toast.makeText(context, R.string.fill,Toast.LENGTH_SHORT).show();
            }
        });


        return rowView;
    }

    private void dispatchTakePictureIntent(String filename) {

        final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(new File(context.getCacheDir()+"/"+filename).exists()){
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
        else if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            jakers=filename;
            ((Activity)context).startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

}
