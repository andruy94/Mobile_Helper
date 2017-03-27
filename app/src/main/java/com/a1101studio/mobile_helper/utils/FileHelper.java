package com.a1101studio.mobile_helper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by andruy94 on 3/8/2017.
 */

public class FileHelper {
    public static final int ACTION_TAKE_PHOTO = 0;

    public static File createOrGetFileDir(String dirName, Context context) {

        File dir = new File(context.getExternalFilesDir(null).getPath() + dirName);
        dir.mkdir();
        return dir;
    }

    public static File createOrGetFileDir(Context context) {
        return context.getExternalFilesDir(null);
    }

    public static File createImageFile(Context context,String imageName){
        String path = File.separator + imageName + File.separator;
        File file = FileHelper.createOrGetFileDir(path, context);
        return createImageFile(file,imageName);
    }

    public static File createImageFile(File file, String imageName){
        String realImageName= String.format("%s_%s.jpg", imageName, String.valueOf(new Date().getTime()));
        File file1=new File(file.getPath(),realImageName);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            file1=null;
        }
        return file1;
    }


    public static void saveFileWithColision(Bitmap bitmap, String dirName, String nameImg, Context context) {
        //File outputDir = getCacheDir();
        try {
            File dir = createOrGetFileDir(dirName, context);
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
            Log.e("TAG", "ex=" + e.toString());
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


    public static void dispatchTakePictureIntent(Activity activity, File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        activity.startActivityForResult(takePictureIntent, ACTION_TAKE_PHOTO);
    }
}
