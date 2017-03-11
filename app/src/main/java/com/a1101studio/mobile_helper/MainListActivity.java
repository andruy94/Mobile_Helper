package com.a1101studio.mobile_helper;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.a1101studio.mobile_helper.adapters.TopListAdapter;
import com.a1101studio.mobile_helper.models.Detail;
import com.a1101studio.mobile_helper.models.TopListModel;
import com.a1101studio.mobile_helper.singleton.WorkData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.a1101studio.mobile_helper.adapters.TopListAdapter.REQUEST_IMAGE_CAPTURE;
import static com.a1101studio.mobile_helper.adapters.TopListAdapter.jakers;
import static com.a1101studio.mobile_helper.models.Detail.CreateDetail;
import static com.a1101studio.mobile_helper.utils.FileHelper.CreateOrGetFileDir;
import static com.a1101studio.mobile_helper.utils.FileHelper.saveFile;
import static com.a1101studio.mobile_helper.utils.FileHelper.saveFileWithColision;

public class MainListActivity extends AppCompatActivity {

    private static final int MAX_DEFECTS = 5;
    private ArrayList<TopListModel> topListModels;
    ListView listView;
    TopListAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        listView.deferNotifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.topListModels = WorkData.getInstance().getTopListModels();

        listView = (ListView) findViewById(R.id.lv1);


        adapter = new TopListAdapter(this, WorkData.getInstance().getTopListModels());
        listView.setAdapter(adapter);

        View rowView = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.top_list_item, null, false);
        EditText etSeatNubmer = (EditText) rowView.findViewById(R.id.etSeatNumber);

        TextView tvDefect = (TextView) rowView.findViewById(R.id.tvDefect);
        ImageButton imageView = (ImageButton) rowView.findViewById(R.id.ibPhoto);
        imageView.setVisibility(View.INVISIBLE);
        imageView.setOnClickListener(v -> {
            if (etSeatNubmer.getText().toString().trim().equals("")) {
                new AlertDialog.Builder(this).setMessage(getString(R.string.fill)).setPositiveButton("OK", (dialog, which) -> dialog.cancel()).show();
            } else {
                final Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                MainListActivity.this.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        });




        String detailName = "�����";

        String[] defectsName = {"�������� �����", "�������� ��������� �����", "�� ��������� ���������", "��� � ���� �����", "������ � ���� �����", "���������"
                , "���������� �� ����� ��������������� ��������", "������� ������ �����", "������� ������", "������ � �����", "�������� ��������", "������� � ���� �����"
                , "������ �����"};

        String[] s1 = {"ololo1", "ololo12", "ololo13"};
        String[] s2 = {"���������"};
        String[] s3 = {"�����:"};
        String[] s21 = {"�� ��������� ���������. �����:"};
        String[] s20 = {"������ � �����:"};
        String[] nulled = {};
        ArrayList<String[]> LowModelsCHeckboxesTitles = new ArrayList<>();
        LowModelsCHeckboxesTitles.add(nulled);
        LowModelsCHeckboxesTitles.add(nulled);//2
        LowModelsCHeckboxesTitles.add(s21);//3
        LowModelsCHeckboxesTitles.add(nulled);//4
        LowModelsCHeckboxesTitles.add(nulled);//5
        LowModelsCHeckboxesTitles.add(nulled);//6
        LowModelsCHeckboxesTitles.add(nulled);//7
        LowModelsCHeckboxesTitles.add(nulled);//8
        LowModelsCHeckboxesTitles.add(nulled);//9
        LowModelsCHeckboxesTitles.add(s20);//10


        String[][] nulled2 = {};
        String[][] ss3 = new String[1][3];
        ss3[0][0] = "�������� ����";
        ss3[0][1] = "�� ������, �����";
        String[][] ss4 = new String[1][2];
        ss4[0][0] = "� ���� �����";
        ss4[0][1] = "����";
        String[][] ss5 = new String[1][2];
        ss5[0][0] = "���������";
        ss5[0][1] = "����";
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles = new ArrayList<>();
        lowlowModelsCHeckboxesTitles.add(nulled2);
        lowlowModelsCHeckboxesTitles.add(nulled2);//2
        lowlowModelsCHeckboxesTitles.add(ss5);//3
        lowlowModelsCHeckboxesTitles.add(nulled2);//4
        lowlowModelsCHeckboxesTitles.add(nulled2);//5
        lowlowModelsCHeckboxesTitles.add(nulled2);//6
        lowlowModelsCHeckboxesTitles.add(nulled2);//7
        lowlowModelsCHeckboxesTitles.add(nulled2);//8
        lowlowModelsCHeckboxesTitles.add(nulled2);//9
        lowlowModelsCHeckboxesTitles.add(ss4);//10

        String[] s4 = {"�����������:"};//1
        String[] s5 = {"�����������:"};//2
        String[] s6 = {"�����������:"};//3
        String[] s7 = {"������", "�����������:"};//4
        String[] s8 = {"���������� ��������:", "������:", "�����������:", "���������� �� ����������� ������:", "�����������:"};//5
        String[] s9 = {"�����������:"};//6
        String[] s10 = {"�����������:"};//7
        String[] s11 = {"���������� �������:"};//8
        String[] s12 = {"���������� �������:"};//9
        String[] s13 = {"�����������:"};//10
        String[] s14 = {"����:"};//11
        String[] s15 = {"������� �������:", "�� ����� ������ �� �����:"};//12
        String[] s16 = {"�����������:"};//13
        ArrayList<String[]> LowModelsCommentsTitles = new ArrayList<>();
        LowModelsCommentsTitles.add(s4);//1
        LowModelsCommentsTitles.add(new String[]{});//2
        LowModelsCommentsTitles.add(s6);//3
        LowModelsCommentsTitles.add(s7);//4
        LowModelsCommentsTitles.add(s8);//5
        LowModelsCommentsTitles.add(s9);//6
        LowModelsCommentsTitles.add(s10);//3
        LowModelsCommentsTitles.add(s11);//4
        LowModelsCommentsTitles.add(s12);//5
        LowModelsCommentsTitles.add(s13);//6
        LowModelsCommentsTitles.add(s14);//4
        LowModelsCommentsTitles.add(s15);//5
        LowModelsCommentsTitles.add(s16);//6
// ������
        String[] s = {"�����������:"};//1
        ArrayList<String[]> LowModelsCHeckboxesTitles2 = new ArrayList<>();
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        LowModelsCHeckboxesTitles2.add(nulled);
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles2 = new ArrayList<>();
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        lowlowModelsCHeckboxesTitles2.add(nulled2);//2
        ArrayList<String[]> LowModelsCommentsTitles2 = new ArrayList<>();
        s = new String[]{"����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//1
        s = new String[]{"����� ����������:", "������� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//2
        s = new String[]{"����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//3
        s = new String[]{"����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//4
        s = new String[]{"����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//5
        s = new String[]{"���������� ������:", "����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//6
        s = new String[]{"���������� ����:", "����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//7
        s = new String[]{"���������� ����:", "����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//8
        s = new String[]{"���������� ����:", "����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//9
        s = new String[]{"����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//10
        s = new String[]{"����� ����������:"};//1
        LowModelsCommentsTitles2.add(s);//11
        s = new String[]{"����� ����������:", "���������� �� ���������� �� ���� �����:"};//1
        LowModelsCommentsTitles2.add(s);//12
        String[] defectsName2 = {"���������� ��������", "���������� ����������", "���������� ������� ����������", "��������� ����� ��� �����������",
                "����������� ���� ��������� ����� ����� ����� � �����������", "����������� ���������� �� ����� ��������� ���� ����� � ����������"
                , "����������� ����� �� ����� ��������� ���� ����� � ����������", "�� �������� �����/���������� �� ����� ��������� ���� ����� � ����������",
                "������������� ���������", "����������� ���������", "����� ����� ����������� � ����� �����"};
//������
        String[] defectsName3 = {"��� �������� ���� 1", "����������� �������� ���� 1", "������ �� ���������� ���� 1", "��� �������� ���� 2", "����������� �������� ���� 2", "������ �� ���������� ���� 2", "��� �������� ���� 3", "����������� �������� ���� 3", "������ �� ���������� ���� 3", "��� �������� ���� 4", "����������� �������� ���� 4", "������ �� ���������� ���� 4",};
        ArrayList<String[]> LowModelsCHeckboxesTitles3 = new ArrayList<>();
        ArrayList<String[]> LowModelsCommentsTitles3 = new ArrayList<>();
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            s = new String[]{"�������:"};//1
            LowModelsCHeckboxesTitles3.add(s);
            LowModelsCHeckboxesTitles3.add(nulled);
            LowModelsCHeckboxesTitles3.add(nulled);
            ss3 = new String[1][2];
            ss3[0][0] = "������";
            ss3[0][1] = "������";
            lowlowModelsCHeckboxesTitles3.add(ss3);
            lowlowModelsCHeckboxesTitles3.add(nulled2);
            lowlowModelsCHeckboxesTitles3.add(nulled2);
            s = new String[]{"������ ���-��:", "�� ������ �����:"};//1
            LowModelsCommentsTitles3.add(s);//7
            s = new String[]{"����:", "�������:"};//1
            LowModelsCommentsTitles3.add(s);//7
            LowModelsCommentsTitles3.add(s);//7
        }


//���������
        //String[] defectsName4 = {"������ ��������", "�������� �������� �� �������", "����������� ��������"};
        String[] defectsName4 = {"������ ��������", "�������� �������� �� �������", "����������� ��������", "�������� �������� �� �����"};

        ArrayList<String[]> LowModelsCHeckboxesTitles4 = new ArrayList<>();
        s = new String[]{"�������� �������� �� �������:"};//1
        s = new String[]{"�������� �������� �� �������:", "����"};//1
        LowModelsCHeckboxesTitles4.add(nulled);
        LowModelsCHeckboxesTitles4.add(s);
        LowModelsCHeckboxesTitles4.add(nulled);
        s = new String[]{"�������� �������� �� �������:"};//1
        LowModelsCHeckboxesTitles4.add(s);

        ArrayList<String[]> LowModelsCommentsTitles4 = new ArrayList<>();
        s = new String[]{"����:", "�������:"};//1
        LowModelsCommentsTitles4.add(s);//7
        LowModelsCommentsTitles4.add(nulled);//7
        s = new String[]{"�����������:"};//1
        LowModelsCommentsTitles4.add(s);//7
        LowModelsCommentsTitles4.add(nulled);//7

        ArrayList<String[][]> lowlowModelsCHeckboxesTitles4 = new ArrayList<>();
        ss3 = new String[1][2];
        ss3[0][0] = "�� ����������/����� ����������/�������";
        ss3[0][1] = "������ � ������";
        ss3 = new String[2][3];
        ss3[0][0] = "�� ����������";
        ss3[0][1] = "����� ����������/�������";
        ss3[0][2] = "������ � ������";
        ss3[1][0] = "�";
        ss3[1][1] = "�";
        ss3[1][2] = "�";
        lowlowModelsCHeckboxesTitles4.add(nulled2);
        lowlowModelsCHeckboxesTitles4.add(ss3);
        ss3 = new String[1][3];
        ss3[0][0] = "�� ����������";
        ss3[0][1] = "����� ����������/�������";
        ss3[0][2] = "������ � ������";
        lowlowModelsCHeckboxesTitles4.add(nulled2);
        lowlowModelsCHeckboxesTitles4.add(ss3);

//5
        String[] defectsName5 = {"�������� ������", "'������' �� �������", "������ �� �������", "����������� �������", "����������� �� �������", "������ �� �������"};

        ArrayList<String[]> LowModelsCHeckboxesTitles5 = new ArrayList<>();
        s = new String[]{"�������� �������� �� �������:"};//1
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);

        ArrayList<String[]> LowModelsCommentsTitles5 = new ArrayList<>();
        s = new String[]{"����:", "�����������:"};//1
        LowModelsCommentsTitles5.add(s);//1
        LowModelsCommentsTitles5.add(s);//2
        LowModelsCommentsTitles5.add(s);//3
        LowModelsCommentsTitles5.add(s);//4
        LowModelsCommentsTitles5.add(s);//1
        LowModelsCommentsTitles5.add(s);//1
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles5 = new ArrayList<>();
        lowlowModelsCHeckboxesTitles5.add(nulled2);
        lowlowModelsCHeckboxesTitles5.add(nulled2);
        lowlowModelsCHeckboxesTitles5.add(nulled2);
        lowlowModelsCHeckboxesTitles5.add(nulled2);
        lowlowModelsCHeckboxesTitles5.add(nulled2);
        lowlowModelsCHeckboxesTitles5.add(nulled2);
//6
        String[] defectsName6 = {"���������� � ������� ����������", "�������� ���������", "'������' �� ����������", "������ �� �����", "�������� �������� �� �����", "����������� ����������"};
        ArrayList<String[]> LowModelsCHeckboxesTitles6 = new ArrayList<>();
        s = new String[]{"�������� �������� �� �����:"};//1
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(s);
        LowModelsCHeckboxesTitles6.add(nulled);
        ArrayList<String[]> LowModelsCommentsTitles6 = new ArrayList<>();
        s = new String[]{"�����������:"};//1
        LowModelsCommentsTitles6.add(s);//1
        LowModelsCommentsTitles6.add(s);//2
        LowModelsCommentsTitles6.add(s);//3
        LowModelsCommentsTitles6.add(s);//4
        LowModelsCommentsTitles6.add(s);//1
        LowModelsCommentsTitles6.add(s);//1
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles6 = new ArrayList<>();
        lowlowModelsCHeckboxesTitles6.add(nulled2);
        lowlowModelsCHeckboxesTitles6.add(nulled2);
        lowlowModelsCHeckboxesTitles6.add(nulled2);
        lowlowModelsCHeckboxesTitles6.add(nulled2);
        ss3 = new String[1][2];
        ss3[0][0] = "�� ����������/����� ����������/�������";
        ss3[0][1] = "������ � ������";
        lowlowModelsCHeckboxesTitles6.add(ss3);
        lowlowModelsCHeckboxesTitles6.add(nulled2);

        String[] defectsName7 = {"��� � �������", "������� ���", "������ � �������", "������� �������", "� �������� ����", "����������� � �������"};

        ArrayList<String[]> LowModelsCHeckboxesTitles7 = new ArrayList<>();
        s = new String[]{"� �������� ����:"};//1
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(s);
        s = new String[]{"����������� � �������:"};//1
        LowModelsCHeckboxesTitles7.add(s);

        ArrayList<String[]> LowModelsCommentsTitles7 = new ArrayList<>();
        s = new String[]{"�����������:"};//1
        LowModelsCommentsTitles7.add(s);//1
        s = new String[]{"���������� �� ����������� ������:", "�����������:"};//1
        LowModelsCommentsTitles7.add(s);//2
        s = new String[]{"������ ������:", "���������� �� ����������� ������:", "���������� ��������:", "�����������:"};//1
        LowModelsCommentsTitles7.add(s);//3
        s = new String[]{"���������� �� ����������� ������:", "�����������:"};//1
        LowModelsCommentsTitles7.add(s);//4
        s = new String[]{"���������� �� ������ �� �������:", "����������"};//1
        LowModelsCommentsTitles7.add(s);//1
        s = new String[]{"�����������:",};//1
        LowModelsCommentsTitles7.add(s);//1

        ArrayList<String[][]> lowlowModelsCHeckboxesTitles7 = new ArrayList<>();
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        ss3 = new String[1][2];
        ss3[0][0] = "���� ������";
        ss3[0][1] = "������� �����/�����";
        lowlowModelsCHeckboxesTitles7.add(ss3);
        ss3 = new String[1][11];
        ss3[0][0] = "���";
        ss3[0][1] = "�/�";
        ss3[0][2] = "���������� ���� �/�";
        ss3[0][3] = "������";
        ss3[0][4] = "������";
        ss3[0][5] = "�������";
        ss3[0][6] = "����� ���������";
        ss3[0][7] = "���";
        ss3[0][8] = "������������� �����";
        ss3[0][9] = "�����";
        ss3[0][10] = "������";
        lowlowModelsCHeckboxesTitles7.add(ss3);
        //��� ����������� � ���� ��
        //��� ����������� � ���� �� ��������� ������
        tvDefect.setOnClickListener(v -> {
            if (!etSeatNubmer.getText().toString().trim().equals("")) {
                if (WorkData.getInstance().getTopListModels().size() < MAX_DEFECTS)
                    if (WorkData.getInstance().getTopListModels().size() % 2 != 0) {
                        ArrayList<Detail> details = new ArrayList<>();
                        details.add(CreateDetail("�����", defectsName5, LowModelsCHeckboxesTitles5, lowlowModelsCHeckboxesTitles5, LowModelsCommentsTitles5));
                        details.add(CreateDetail("�����", defectsName6, LowModelsCHeckboxesTitles6, lowlowModelsCHeckboxesTitles6, LowModelsCommentsTitles6));
                        details.add(CreateDetail("��������� ������", defectsName7, LowModelsCHeckboxesTitles7, lowlowModelsCHeckboxesTitles7, LowModelsCommentsTitles7));
                    /*details.add(CreateDetail("���������",defectsName2,LowModelsCHeckboxesTitles2,lowlowModelsCHeckboxesTitles2,LowModelsCommentsTitles2));
                    details.add(CreateDetail("���������",defectsName3,LowModelsCHeckboxesTitles3,lowlowModelsCHeckboxesTitles3,LowModelsCommentsTitles3));
                    details.add(CreateDetail("������� ��������",defectsName4,LowModelsCHeckboxesTitles4,lowlowModelsCHeckboxesTitles4,LowModelsCommentsTitles4));*/
                        //---
                        Detail[] detailArray = new Detail[details.size()];
                        for (int i = 0; i < details.size(); i++) {
                            detailArray[i] = details.get(i);
                        }
                        WorkData.getInstance().getTopListModels().add(new TopListModel("...", etSeatNubmer.getText().toString()));
                        Intent intent = new Intent(MainListActivity.this, TilesActivity.class);
                        WorkData.getInstance().getDetails().add(detailArray);
                        intent.putExtra("k", WorkData.getInstance().getTopListModels().size() - 1);



                        try {
                            saveFile("tmp",new File(CreateOrGetFileDir("/"+etSeatNubmer.getText().toString()+"/",MainListActivity.this),"tmp.txt"));
                            //saveFileWithColision("", "/mobile_helper/" + fileName + "/", fileName + "_" + new Date().getTime() + ".jpg", MainListActivity.this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        etSeatNubmer.setText("");
                        tvDefect.setText("");
                        startActivity(intent);
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainListActivity.this);
                        builder.setTitle(R.string.choose_type);
                        builder.setSingleChoiceItems(R.array.types, -1, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                        ArrayList<Detail> details = new ArrayList<>();
                                        //��� ���������� ��������
                                        details.add(CreateDetail(detailName, defectsName, LowModelsCHeckboxesTitles, lowlowModelsCHeckboxesTitles, LowModelsCommentsTitles));
                                        details.add(CreateDetail("���������", defectsName2, LowModelsCHeckboxesTitles2, lowlowModelsCHeckboxesTitles2, LowModelsCommentsTitles2));
                                        details.add(CreateDetail("���������", defectsName3, LowModelsCHeckboxesTitles3, lowlowModelsCHeckboxesTitles3, LowModelsCommentsTitles3));
                                        details.add(CreateDetail("������� ��������", defectsName4, LowModelsCHeckboxesTitles4, lowlowModelsCHeckboxesTitles4, LowModelsCommentsTitles4));
                                        //---
                                        Detail[] detailArray = new Detail[details.size()];
                                        for (int i = 0; i < details.size(); i++) {
                                            detailArray[i] = details.get(i);
                                        }
                                        boolean isSeat = true;



                                        WorkData.getInstance().getTopListModels().add(new TopListModel("...", isSeat, getResources().getStringArray(R.array.types)[which], etSeatNubmer.getText().toString()));
                                        Intent intent = new Intent(MainListActivity.this, TilesActivity.class);
                                        WorkData.getInstance().getDetails().add(detailArray);
                                        intent.putExtra("k", WorkData.getInstance().getTopListModels().size() - 1);



                                        try {
                                            saveFile("tmp",new File(CreateOrGetFileDir("/"+etSeatNubmer.getText().toString()+"/",MainListActivity.this),"tmp.txt"));
                                            //saveFileWithColision("", "/mobile_helper/" + fileName + "/", fileName + "_" + new Date().getTime() + ".jpg", MainListActivity.this);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }


                                        etSeatNubmer.setText("");
                                        tvDefect.setText("");

                                        startActivity(intent);
                                    }
                                }
                        );
                        builder.show();
                    }


            } else {
                Toast.makeText(this, R.string.fill, Toast.LENGTH_SHORT).show();
            }
        });

        listView.addFooterView(rowView);
        View viewHeader = ((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(R.layout.header_top_list, null, false);
        listView.addHeaderView(viewHeader);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.e("TAG", "k=" + data.getIntExtra("lol", 0));
            final String fileName = jakers;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    saveFileWithColision(imageBitmap, "/" + fileName + "/", fileName + "_" + new Date().getTime() + ".jpg", MainListActivity.this);
                }
            }).start();
        }
    }


}
