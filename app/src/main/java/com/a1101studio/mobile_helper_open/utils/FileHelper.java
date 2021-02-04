package com.a1101studio.mobile_helper_open.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import androidx.core.content.FileProvider;
import androidx.core.util.Pair;
import android.util.Log;

import com.a1101studio.mobile_helper_open.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static File createImageFile(Context context, String imageName) {//1
        String path = File.separator + imageName + File.separator;
        File file = FileHelper.createOrGetFileDir(path, context);
        return createImageFile(file, imageName);
    }

    public static File createImageFile(File file, String imageName) {//2
        String realImageName = String.format("%s_%s.jpg", imageName, String.valueOf(new Date().getTime()));
        File file1 = new File(file.getPath(), realImageName);
        try {
            file1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            file1 = null;
        }
        return file1;
    }


    public static void saveFileWithColision(Bitmap bitmap, String dirName, String nameImg, Context context) {//4
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

    public static void saveFile(String s, File file) throws IOException {//5

        if (file.exists())
            file.delete();
        Log.e("TAG", file.getPath());
        FileOutputStream ostream = new FileOutputStream(file);
        ostream.write(s.getBytes());
        ostream.flush();
        ostream.close();

    }


    public static List<Pair<String, String>> loadUserList(Context context) {
        List<Pair<String, String>> pairs = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("userList"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                String[] split = mLine.split(",");
                Pair<String, String> pair = new Pair<>(split[0], split[1]);
                pairs.add(pair);
            }
        } catch (Exception e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    //log the exception
                }
            }
        }

        return pairs;
    }

    public static void dispatchTakePictureIntent(Activity activity, File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        Uri uriForFile = FileProvider.getUriForFile(activity, activity.getString(R.string.file_provider_authority), file);


        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriForFile);
        activity.startActivityForResult(takePictureIntent, ACTION_TAKE_PHOTO);
    }
}
