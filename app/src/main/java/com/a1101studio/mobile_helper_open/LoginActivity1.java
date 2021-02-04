package com.a1101studio.mobile_helper_open;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.core.util.Pair;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.a1101studio.mobile_helper_open.models.Detail;
import com.a1101studio.mobile_helper_open.models.DocumentModel;
import com.a1101studio.mobile_helper_open.models.TopListModel;
import com.a1101studio.mobile_helper_open.singleton.WorkData;
import com.a1101studio.mobile_helper_open.utils.FileHelper;
import com.google.gson.Gson;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.*;
import java.util.concurrent.Callable;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.a1101studio.mobile_helper_open.BuildConfig.DEBUG;


public class LoginActivity1 extends AppCompatActivity {
    java.util.List<Pair<String, String>> loginAndUser;
    TextView password;
    TextView login;
    Button aut;
    TextView tvVersion;
    boolean isServices = true;
    private int tapeCount = 0;
    private final int NEED_TAPE = 5;
    private CheckBox cbLoadLastSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        login = (TextView) findViewById(R.id.login);
        password = (TextView) findViewById(R.id.password);
        aut = (Button) findViewById(R.id.aut);
        tvVersion = (TextView) findViewById(R.id.textViewVersion);
        tvVersion.setText(getString(R.string.version_number, BuildConfig.VERSION_NAME));
        cbLoadLastSession = (CheckBox) findViewById(R.id.checkBox);

        tvVersion.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity1.this);
            builder.setTitle(R.string.About);
            builder.setMessage(getString(R.string.about_main_text, BuildConfig.VERSION_NAME, String.valueOf(BuildConfig.VERSION_CODE)));
            builder.setPositiveButton(R.string.ok, (d, i) -> d.cancel());
            builder.show();


        });
        aut.setEnabled(false);
        Observable.fromCallable(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                loginAndUser = FileHelper.loadUserList(LoginActivity1.this);
                return new Object();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
            @Override
            public void onCompleted() {
                iniLoginAndPasswordObservable();
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        });

        View.OnClickListener oclaut = v -> {
            Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
            WorkData.getInstance();
            String login1 = "user0";
            loadLastData(login1);
            finish();
            startActivity(intent);
            WorkData.getInstance().setUserName(login1);
        };

        aut.setOnClickListener(oclaut);

        init();


        if (DEBUG && false) {
            Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
            WorkData.getInstance();
            finish();
            startActivity(intent);
        }
        aut.callOnClick();

    }

    void iniLoginAndPasswordObservable() {
        Observable.combineLatest(
                RxTextView.textChanges(login),
                RxTextView.textChanges(password),
                (login, password) -> login.length() > 0 && password.length() > 0 && isServices
        ).subscribe(aut::setEnabled);
    }

    void init() {

        /*Resources res = getResources();
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
        */


    }

    private void initVoidData() {
        ArrayList<Detail[]> checkListItems2 = new ArrayList<>();
        WorkData.getInstance().setDetails(checkListItems2);//пишем всё в озу
        ArrayList<TopListModel> topListModel = new ArrayList<>();
        WorkData.getInstance().setTopListModels(topListModel);// создадим список неисправностей
        DocumentModel documentModel = new DocumentModel();
        WorkData.getInstance().setDocumentModel(documentModel);
    }

    private void loadLastData(String userName) {
        SharedPreferences mPrefs = this.getSharedPreferences(getApplicationInfo().name, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String user = mPrefs.getString("USER", "");
        String myObjectKey = mPrefs.getString(WorkData.class.getSimpleName(), "");
        if (user.equals(userName) && !myObjectKey.equals("") && cbLoadLastSession.isChecked()) {//если последний юезр Вас\ Пупин и у него что-то было, то грузим, иначе пустотой забиваем всё
            WorkData workData = gson.fromJson(myObjectKey, WorkData.class);
            WorkData.getInstance().setDetails(workData.getDetails());//пишем всё в озу
            WorkData.getInstance().setTopListModels(workData.getTopListModels());// создадим список неисправностей
            WorkData.getInstance().setDocumentModel(workData.getDocumentModel());
        } else {
            initVoidData();
            SharedPreferences.Editor ed = mPrefs.edit();
            ed.putString("USER", userName);
            ed.apply();
        }

    }

    static Detail[] addCheckListItem(String title,//Тайтл плитки
                                     String[] defectTitles,//список дефектов
                                     ArrayList<String[]> lowModelsCheckBoxesListTitles,//список заголовка блоков чекбоксов
                                     ArrayList<String[][]> lowLowCheckBoxesTitles,//список массива заголовока чекбоксов
                                     ArrayList<String[]> lowModelsCommentListTitles) {//список тайтлов к комментам){

        Detail[] details = {Detail.
                CreateDetail(title, defectTitles, lowModelsCheckBoxesListTitles, lowLowCheckBoxesTitles, lowModelsCommentListTitles)};

        return details;
    }


}
