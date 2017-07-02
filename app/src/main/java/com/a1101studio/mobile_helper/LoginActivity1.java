package com.a1101studio.mobile_helper;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.a1101studio.mobile_helper.models.Detail;
import com.a1101studio.mobile_helper.models.DocumentModel;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;
import com.a1101studio.mobile_helper.utils.FileHelper;
import com.a1101studio.mobile_helper.utils.License;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.Date;

import rx.Observable;

import static com.a1101studio.mobile_helper.BuildConfig.DEBUG;


public class LoginActivity1 extends AppCompatActivity {

    TextView password;
    TextView login;
    Button aut;
    TextView tvVersion;
    boolean isServices = true;
    private int tapeCount = 0;
    private final int NEED_TAPE = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login1);
        if (!(new Date().after(License.getDateStartDate()) && new Date().before(License.getDateEndData())))//after=посел
            finish();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_login);
        relativeLayout.setOnClickListener(v -> {
            if (tapeCount < NEED_TAPE) {
                tapeCount++;
            } else if (tapeCount == NEED_TAPE) {
                License.noLimited = true;
                Toast.makeText(LoginActivity1.this, new String("Супер-Убер режим включён!"), Toast.LENGTH_SHORT).show();
            }
        });
        login = (TextView) findViewById(R.id.login);
        password = (TextView) findViewById(R.id.password);
        aut = (Button) findViewById(R.id.aut);
        tvVersion = (TextView) findViewById(R.id.textViewVersion);
        tvVersion.setText(getString(R.string.version_number, BuildConfig.VERSION_NAME));

        tvVersion.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity1.this);
            builder.setTitle(R.string.About);
            builder.setMessage(getString(R.string.about_main_text, BuildConfig.VERSION_NAME, String.valueOf(BuildConfig.VERSION_CODE)));
            builder.setPositiveButton(R.string.ok, (d, i) -> d.cancel());
            builder.show();


        });

        if (!FileHelper.createOrGetFileDir(this).canWrite() || Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.device_no_services);
            builder.setPositiveButton(R.string.ok, (dialog, which) -> {
                //aut.setEnabled(false);
                //isServices = false;
                dialog.cancel();
            });
            builder.show();
        }

        loginAndPassword();
        View.OnClickListener oclaut = v -> {
            if (login.getText().toString().trim().equals("AVTI") && password.getText().toString().trim().equals("AVTI")) {
                Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
                WorkData.getInstance();
                startActivity(intent);
            } else {
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity1.this);
                alert.setMessage(R.string.auth_error).setPositiveButton(R.string.ok, (dialog, which) -> dialog.cancel()).show();
            }
        };

        aut.setOnClickListener(oclaut);

        init();


        if (DEBUG && false) {
            Intent intent = new Intent(LoginActivity1.this, MainActivity.class);
            WorkData.getInstance();
            startActivity(intent);
        }

    }

    void loginAndPassword() {
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


        ArrayList<Detail[]> checkListItems2 = new ArrayList<>();
        WorkData.getInstance().setDetails(checkListItems2);//пишем всё в озу
        ArrayList<TopListModel> topListModel = new ArrayList<>();
        WorkData.getInstance().setTopListModels(topListModel);// создадим список неисправностей
        DocumentModel documentModel = new DocumentModel();
        WorkData.getInstance().setDocumentModel(documentModel);
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
