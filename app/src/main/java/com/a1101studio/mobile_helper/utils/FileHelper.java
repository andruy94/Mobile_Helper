package com.a1101studio.mobile_helper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by andruy94 on 3/8/2017.
 */

public class FileHelper {
    public static File CreateOrGetFileDir(String dirName, Context context) {

        File dir = new File(context.getExternalFilesDir(null).getPath() + dirName);
        dir.mkdir();
        /*if (!dir.canWrite()) {
            dir = new File(context.getFilesDir() + dirName );
            dir.mkdir();
        }*/
        return dir;
    }

    public static File CreateOrGetFileDir(Context context) {

        File dir = context.getExternalFilesDir(null);
        /*if (!dir.canWrite()) {
            dir = new File(context.getFilesDir() + dirName );
            dir.mkdir();
        }*/
        return dir;
    }

    public static void saveFileWithColision(Bitmap bitmap, String dirName, String nameImg, Context context) {
        //File outputDir = getCacheDir();
        try {
            File dir = CreateOrGetFileDir(dirName,context);
            dir.mkdir();
            File namefile = new File(dir.getPath() + "/" + nameImg);
            if (namefile.exists())
                namefile.delete();
            Log.e("TAG", namefile.getPath());
            namefile.createNewFile();
            FileOutputStream ostream = new FileOutputStream(namefile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
            ostream.flush();
            ostream.close();
        } catch (Exception e) {
            Log.e("TAG","ex="+ e.toString());
        }
    }
    public static void saveFile(String s, File file) throws IOException {

        if (file.exists())
            file.delete();
        Log.e("TAG", file.getPath());
        FileOutputStream ostream = new FileOutputStream(file);
        ostream.write(s.getBytes());
        ostream.flush();
        ostream.close();

    }

    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent(Activity activity,File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(activity.getPackageManager()) != null) {
            // Create the File where the photo should go

            // Continue only if the File was successfully created
            if (file != null) {
                //Uri photoURI = getU
                /*takePictureIntent.setDataAndType(Uri.fromFile(new File(dir, adapter.getItem(which))), "image/*");*/
                //takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                activity.startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }
}
