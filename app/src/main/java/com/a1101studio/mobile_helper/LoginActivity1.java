package com.a1101studio.mobile_helper;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import rx.Observable;

import static com.a1101studio.mobile_helper.adapters.TopListAdapter.REQUEST_IMAGE_CAPTURE;


public class LoginActivity1 extends AppCompatActivity {

    TextView password;
    TextView login;
    Button aut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        login = (TextView) findViewById(R.id.login);
        password = (TextView) findViewById(R.id.password);
        aut = (Button) findViewById(R.id.aut);
        loginAndPassword();
        View.OnClickListener oclaut = v -> {
            if(login.getText().toString().trim().equals("1") && password.getText().toString().trim().equals("1")) {
                Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
                WorkData.getInstance();
                startActivity(intent);
            }else{
                AlertDialog.Builder alert=new AlertDialog.Builder(LoginActivity1.this);
                alert.setMessage(R.string.auth_error).setPositiveButton(R.string.Confirm, (dialog, which) -> dialog.cancel()).show();
            }
        };

        aut.setOnClickListener(oclaut);
        ArrayList<TopListModel> topListModel= new ArrayList<>();

        WorkData.getInstance().setTopListModels(topListModel);// создадим список неисправностей
        init();

    }

    void loginAndPassword(){
        Observable.combineLatest(
                RxTextView.textChanges(login),
                RxTextView.textChanges(password),
                (login,password)->login.length()>0 && password.length()>0
        ).subscribe(aut::setEnabled);
    }

    void  init(){
        Resources res = getResources();
        String[] title=res.getStringArray(R.array.title);//оглавление
        ArrayList<String[]> descs=new ArrayList<String[]>();
        String[] descs1=res.getStringArray(R.array.Des_1_1);
        String[] descs2=res.getStringArray(R.array.Des_1_2);
        String[] descs3=res.getStringArray(R.array.Des_1_3);
        String[] descs4=res.getStringArray(R.array.Des_1_4);
        String[] descs5=res.getStringArray(R.array.Des_1_5);
        descs.add(descs1);
        descs.add(descs2);
        descs.add(descs3);
        descs.add(descs4);
        descs.add(descs5);
        CheckListItem[] checkListItems=CheckListItem.CreateCheckListitem(title,descs);
        ArrayList<CheckListItem[]> checkListItems2=new ArrayList<>();
        checkListItems2.add(checkListItems);
        WorkData.getInstance().setCheckListItemList(checkListItems2);//пишем всё в озу
    }

    static CheckListItem[] addCheckListItem(String[] title, ArrayList<String[]> descs){

        CheckListItem[] checkListItems=CheckListItem.CreateCheckListitem(title,descs);

        return checkListItems;
    }


}
