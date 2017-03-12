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
import com.a1101studio.mobile_helper.utils.License;

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




        String detailName = "Опоры";

        String[] defectsName = {"Коррозия опоры", "Коррозия элементов опоры", "Не заземлена перемычка", "ДКР в теле опоры", "Дерево в теле опоры", "Нумерация"
                , "Отсутствие на опоре предупреждающих плакатов", "Погнуты уголки опоры", "Оторван уголок", "Гнездо в опоре", "Изогнута траверса", "Трещина в теле опоры"
                , "Наклон опоры"};

        String[] s1 = {"ololo1", "ololo12", "ololo13"};
        String[] s2 = {"Нумерация"};
        String[] s3 = {"Выбор:"};
        String[] s21 = {"Не заземлена перемычка. Выбор:"};
        String[] s20 = {"Гнездо в опоре:"};
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
        ss3[0][0] = "Охранная зона";
        ss3[0][1] = "Не влезай, убьет";
        String[][] ss4 = new String[1][2];
        ss4[0][0] = "В теле опоры";
        ss4[0][1] = "Фаза";
        String[][] ss5 = new String[1][2];
        ss5[0][0] = "Грозотрос";
        ss5[0][1] = "ВОЛС";
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

        String[] s4 = {"Комментарий:"};//1
        String[] s5 = {"Комментарий:"};//2
        String[] s6 = {"Комментарий:"};//3
        String[] s7 = {"Высота", "Комментарий:"};//4
        String[] s8 = {"Количество деревьев:", "Высота:", "Комментарий:", "Расстояние до токоведущих частей:", "Комментарий:"};//5
        String[] s9 = {"Комментарий:"};//6
        String[] s10 = {"Комментарий:"};//7
        String[] s11 = {"Количество уголков:"};//8
        String[] s12 = {"Количество уголков:"};//9
        String[] s13 = {"Комментарий:"};//10
        String[] s14 = {"Фаза:"};//11
        String[] s15 = {"Глубина трещины:", "На какой высоте от земли:"};//12
        String[] s16 = {"Комментарий:"};//13
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
// Второе
        String[] s = {"Комментарий:"};//1
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
        s = new String[]{"Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//1
        s = new String[]{"Номер фундамента:", "Степень разрушения:"};//1
        LowModelsCommentsTitles2.add(s);//2
        s = new String[]{"Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//3
        s = new String[]{"Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//4
        s = new String[]{"Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//5
        s = new String[]{"Количество болтов:", "Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//6
        s = new String[]{"Количество гаек:", "Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//7
        s = new String[]{"Количество гаек:", "Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//8
        s = new String[]{"Количество гаек:", "Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//9
        s = new String[]{"Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//10
        s = new String[]{"Номер фундамента:"};//1
        LowModelsCommentsTitles2.add(s);//11
        s = new String[]{"Номер фундамента:", "Расстояние от фундамента до пяты опоры:"};//1
        LowModelsCommentsTitles2.add(s);//12
        String[] defectsName2 = {"Фундаменты засыпаны", "Разрушение фундамента", "Отсутствие контура заземления", "Просевший грунт под фундаментом",
                "Отсутствует болт крепления между пятой опоры и фундаментом", "Отсутствует контргайка на болту крепления пяты опоры к фундаменту"
                , "Отсутствует гайка на болту крепления пяты опоры к фундаменту", "Не затянута гайка/контргайка на болту крепления пяты опоры к фундаменту",
                "Забетонирован фундамент", "Необвалован фундамент", "Зазор между фундаментом и пятой опоры"};
//Третье
        String[] defectsName3 = {"Бой изоляции цепи 1", "Загрязнение изоляции цепи 1", "Наброс на изоляторах цепи 1", "Бой изоляции цепи 2", "Загрязнение изоляции цепи 2", "Наброс на изоляторах цепи 2", "Бой изоляции цепи 3", "Загрязнение изоляции цепи 3", "Наброс на изоляторах цепи 3", "Бой изоляции цепи 4", "Загрязнение изоляции цепи 4", "Наброс на изоляторах цепи 4",};
        ArrayList<String[]> LowModelsCHeckboxesTitles3 = new ArrayList<>();
        ArrayList<String[]> LowModelsCommentsTitles3 = new ArrayList<>();
        ArrayList<String[][]> lowlowModelsCHeckboxesTitles3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            s = new String[]{"Сторона:"};//1
            LowModelsCHeckboxesTitles3.add(s);
            LowModelsCHeckboxesTitles3.add(nulled);
            LowModelsCHeckboxesTitles3.add(nulled);
            ss3 = new String[1][2];
            ss3[0][0] = "Первая";
            ss3[0][1] = "Вторая";
            lowlowModelsCHeckboxesTitles3.add(ss3);
            lowlowModelsCHeckboxesTitles3.add(nulled2);
            lowlowModelsCHeckboxesTitles3.add(nulled2);
            s = new String[]{"Разное кол-во:", "По разным фазам:"};//1
            LowModelsCommentsTitles3.add(s);//7
            s = new String[]{"Фаза:", "Сторона:"};//1
            LowModelsCommentsTitles3.add(s);//7
            LowModelsCommentsTitles3.add(s);//7
        }


//Четвертая
        //String[] defectsName4 = {"Гнутая арматура", "Гаситель вибрации на проводе", "Повреждение арматуры"};
        String[] defectsName4 = {"Гнутая арматура", "Гаситель вибрации на проводе", "Повреждение арматуры", "Гаситель вибрации на тросу"};

        ArrayList<String[]> LowModelsCHeckboxesTitles4 = new ArrayList<>();
        s = new String[]{"Гаситель вибрации на проводе:"};//1
        s = new String[]{"Гаситель вибрации на проводе:", "Фаза"};//1
        LowModelsCHeckboxesTitles4.add(nulled);
        LowModelsCHeckboxesTitles4.add(s);
        LowModelsCHeckboxesTitles4.add(nulled);
        s = new String[]{"Гаситель вибрации на проводе:"};//1
        LowModelsCHeckboxesTitles4.add(s);

        ArrayList<String[]> LowModelsCommentsTitles4 = new ArrayList<>();
        s = new String[]{"Фаза:", "Сторона:"};//1
        LowModelsCommentsTitles4.add(s);//7
        LowModelsCommentsTitles4.add(nulled);//7
        s = new String[]{"Комментарий:"};//1
        LowModelsCommentsTitles4.add(s);//7
        LowModelsCommentsTitles4.add(nulled);//7

        ArrayList<String[][]> lowlowModelsCHeckboxesTitles4 = new ArrayList<>();
        ss3 = new String[1][2];
        ss3[0][0] = "Не установлен/Криво установлен/Изогнут";
        ss3[0][1] = "Смещен в пролет";
        ss3 = new String[2][3];
        ss3[0][0] = "Не установлен";
        ss3[0][1] = "Криво установлен/Изогнут";
        ss3[0][2] = "Смещен в пролет";
        ss3[1][0] = "Ж";
        ss3[1][1] = "З";
        ss3[1][2] = "К";
        lowlowModelsCHeckboxesTitles4.add(nulled2);
        lowlowModelsCHeckboxesTitles4.add(ss3);
        ss3 = new String[1][3];
        ss3[0][0] = "Не установлен";
        ss3[0][1] = "Криво установлен/Изогнут";
        ss3[0][2] = "Смещен в пролет";
        lowlowModelsCHeckboxesTitles4.add(nulled2);
        lowlowModelsCHeckboxesTitles4.add(ss3);

//5
        String[] defectsName5 = {"Распушен провод", "'Фонарь' на проводе", "Наброс на проводе", "Повреждение провода", "Соединитель на проводе", "Наброс на проводе"};

        ArrayList<String[]> LowModelsCHeckboxesTitles5 = new ArrayList<>();
        s = new String[]{"Гаситель вибрации на проводе:"};//1
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);
        LowModelsCHeckboxesTitles5.add(nulled);

        ArrayList<String[]> LowModelsCommentsTitles5 = new ArrayList<>();
        s = new String[]{"Фаза:", "Комментарий:"};//1
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
        String[] defectsName6 = {"Отсутствие в пролете грозотроса", "Распушен грозотрос", "'Фонарь' на грозотросе", "Наброс на тросу", "Гаситель вибрации на тросу", "Повреждение грозотроса"};
        ArrayList<String[]> LowModelsCHeckboxesTitles6 = new ArrayList<>();
        s = new String[]{"Гаситель вибрации на тросу:"};//1
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(nulled);
        LowModelsCHeckboxesTitles6.add(s);
        LowModelsCHeckboxesTitles6.add(nulled);
        ArrayList<String[]> LowModelsCommentsTitles6 = new ArrayList<>();
        s = new String[]{"Комментарий:"};//1
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
        ss3[0][0] = "Не установлен/Криво установлен/Изогнут";
        ss3[0][1] = "Смещен в пролет";
        lowlowModelsCHeckboxesTitles6.add(ss3);
        lowlowModelsCHeckboxesTitles6.add(nulled2);

        String[] defectsName7 = {"ДКР в пролете", "Боковые ДКР", "Дерево в пролете", "Боковые деревья", "В охранной зоне", "Пересечения в пролете"};

        ArrayList<String[]> LowModelsCHeckboxesTitles7 = new ArrayList<>();
        s = new String[]{"В охранной зоне:"};//1
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(nulled);
        LowModelsCHeckboxesTitles7.add(s);
        s = new String[]{"Пересечения в пролете:"};//1
        LowModelsCHeckboxesTitles7.add(s);

        ArrayList<String[]> LowModelsCommentsTitles7 = new ArrayList<>();
        s = new String[]{"Комментарий:"};//1
        LowModelsCommentsTitles7.add(s);//1
        s = new String[]{"Расстояние до токоведущих частей:", "Комментарий:"};//1
        LowModelsCommentsTitles7.add(s);//2
        s = new String[]{"Высота дерева:", "Расстояние до токоведущих частей:", "Количество деревьев:", "Комментарий:"};//1
        LowModelsCommentsTitles7.add(s);//3
        s = new String[]{"Расстояние до токоведущих частей:", "Комментарий:"};//1
        LowModelsCommentsTitles7.add(s);//4
        s = new String[]{"Расстояние от стойки до провода:", "Примечание"};//1
        LowModelsCommentsTitles7.add(s);//1
        s = new String[]{"Комментарий:",};//1
        LowModelsCommentsTitles7.add(s);//1

        ArrayList<String[][]> lowlowModelsCHeckboxesTitles7 = new ArrayList<>();
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        lowlowModelsCHeckboxesTitles7.add(nulled2);
        ss3 = new String[1][2];
        ss3[0][0] = "Сбор мусора";
        ss3[0][1] = "Отсыпка груна/щебня";
        lowlowModelsCHeckboxesTitles7.add(ss3);
        ss3 = new String[1][11];
        ss3[0][0] = "ЛЭП";
        ss3[0][1] = "Ж/Д";
        ss3[0][2] = "Контактная сеть Ж/Д";
        ss3[0][3] = "Дорога";
        ss3[0][4] = "Гаражи";
        ss3[0][5] = "Стоянка";
        ss3[0][6] = "Линия освещения";
        ss3[0][7] = "СИП";
        ss3[0][8] = "Низковольтная линия";
        ss3[0][9] = "Овраг";
        ss3[0][10] = "Болото";
        lowlowModelsCHeckboxesTitles7.add(ss3);
        //тут добавляется в лист всё
        //тут добавляется в лист всё состояние трассы
        tvDefect.setOnClickListener(v -> {
            if (!etSeatNubmer.getText().toString().trim().equals("")) {
                if (WorkData.getInstance().getTopListModels().size() < MAX_DEFECTS || License.noLimited)
                    if (WorkData.getInstance().getTopListModels().size() % 2 != 0) {
                        ArrayList<Detail> details = new ArrayList<>();
                        details.add(CreateDetail("Пролёт", defectsName5, LowModelsCHeckboxesTitles5, lowlowModelsCHeckboxesTitles5, LowModelsCommentsTitles5));
                        details.add(CreateDetail("Тросс", defectsName6, LowModelsCHeckboxesTitles6, lowlowModelsCHeckboxesTitles6, LowModelsCommentsTitles6));
                        details.add(CreateDetail("Состояние трассы", defectsName7, LowModelsCHeckboxesTitles7, lowlowModelsCHeckboxesTitles7, LowModelsCommentsTitles7));
                    /*details.add(CreateDetail("пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ",defectsName2,LowModelsCHeckboxesTitles2,lowlowModelsCHeckboxesTitles2,LowModelsCommentsTitles2));
                    details.add(CreateDetail("пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ",defectsName3,LowModelsCHeckboxesTitles3,lowlowModelsCHeckboxesTitles3,LowModelsCommentsTitles3));
                    details.add(CreateDetail("пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ",defectsName4,LowModelsCHeckboxesTitles4,lowlowModelsCHeckboxesTitles4,LowModelsCommentsTitles4));*/
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
                                        //пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ
                                        details.add(CreateDetail(detailName, defectsName, LowModelsCHeckboxesTitles, lowlowModelsCHeckboxesTitles, LowModelsCommentsTitles));
                                        details.add(CreateDetail("Фундамент", defectsName2, LowModelsCHeckboxesTitles2, lowlowModelsCHeckboxesTitles2, LowModelsCommentsTitles2));
                                        details.add(CreateDetail("Изоляторы", defectsName3, LowModelsCHeckboxesTitles3, lowlowModelsCHeckboxesTitles3, LowModelsCommentsTitles3));
                                        details.add(CreateDetail("Линеная арматура", defectsName4, LowModelsCHeckboxesTitles4, lowlowModelsCHeckboxesTitles4, LowModelsCommentsTitles4));
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
