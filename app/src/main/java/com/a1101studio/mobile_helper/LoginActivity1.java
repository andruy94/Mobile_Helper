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
import java.util.Arrays;

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
        String detailName="detail1";

        String[] defectsName={"defect1","defect2","defect3"};

        String[] s1={"olol1"};
        String[] s2={"olol2"};
        String[] s3={"olol3"};
        ArrayList<String[]> LowModelsCHeckboxesTitles =new ArrayList<>();
        LowModelsCHeckboxesTitles.add(s1);
        LowModelsCHeckboxesTitles.add(s2);
        LowModelsCHeckboxesTitles.add(s3);

        String[][] ss1=new String[3][];
        for (int i=0;i<3;i++){
            ss1[i]=new String[1];
            for(int j=0;j<1;j++){
                ss1[i][j]="olol"+j;
            }
        }
        String[][] ss2=new String[3][];
        for (int i=0;i<3;i++){
            ss2[i]=new String[1];
            for(int j=0;j<1;j++){
                ss2[i][j]="olol"+j;
            }
        }
        String[][] ss3=new String[3][];
        for (int i=0;i<3;i++){
            ss3[i]=new String[1];
            for(int j=0;j<1;j++){
                ss3[i][j]="olol"+j;
            }
        }
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles =new ArrayList<>();
        lowlowModelsCHeckboxesTitles.add(ss1);
        lowlowModelsCHeckboxesTitles.add(ss2);
        lowlowModelsCHeckboxesTitles.add(ss3);


        String[] s4={"Title1"};
        String[] s5={"Title2"};
        String[] s6={"Title3"};
        ArrayList<String[]> LowModelsCommentsTitles =new ArrayList<>();
        LowModelsCommentsTitles.add(s4);
        LowModelsCommentsTitles.add(s5);
        LowModelsCommentsTitles.add(s6);





        CheckListItem[] checkListItems={CheckListItem.CreateCheckListitem(detailName,defectsName,LowModelsCHeckboxesTitles,lowlowModelsCHeckboxesTitles,LowModelsCommentsTitles)};
        ArrayList<CheckListItem[]> checkListItems2=new ArrayList<>();
        checkListItems2.add(checkListItems);
        WorkData.getInstance().setCheckListItemList(checkListItems2);//пишем всё в озу
    }

    static CheckListItem[] addCheckListItem(String title,//Тайтл плитки
                                            String[] defectTitles,//список дефектов
                                            ArrayList<String[]> lowModelsCheckBoxesListTitles,//список заголовка блоков чекбоксов
                                            ArrayList<String[][]> lowLowCheckBoxesTitles,//список массива заголовока чекбоксов
                                            ArrayList<String[]> lowModelsCommentListTitles){//список тайтлов к комментам){

        CheckListItem[] checkListItems={CheckListItem.CreateCheckListitem(title,defectTitles,lowModelsCheckBoxesListTitles,lowLowCheckBoxesTitles,lowModelsCommentListTitles)};

        return checkListItems;
    }


}
