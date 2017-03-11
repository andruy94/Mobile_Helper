package com.a1101studio.mobile_helper.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
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
}
