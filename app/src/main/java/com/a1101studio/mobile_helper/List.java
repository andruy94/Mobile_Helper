package com.a1101studio.mobile_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.ListView;

import com.a1101studio.mobile_helper.adapters.ListAdapter;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class List extends AppCompatActivity {
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.lv1);



        String[] title={"Трещины в бетоне","Раковины, щели, пятна на бетоне","Отклонение опор","Заделка опор"};//оглавление
        ArrayList<String[]> descs=new ArrayList<String[]>();
        String[] descs1={"Поперечные трещины шириной раскрытия менее 0,3 мм","Поперечные трещины шириной раскрытия от 0,3 до 0,6 мм","Поперечные трещины шириной раскрытия более 0,6 мм","Продольные трещины шириной раскрытия до 0,05 мм независимо от количества трещин","Продольные трещины шириной раскрытия от 0,05 до 0,3 мм независимо от количества трещин",
        "Продольные трещины раскрытия от 0,3 до0,6 мм при количестве трещинне более двух в одном сечении",
        "Продольные трещины шириной раскрытия более 0,3 мм при количестве трещин более двух в одном сечении"};//описание к первому оглавлению
        String[] descs2={"На поверхности бетона выступают темные полосы, расположенные по виткам поперечной арматуры",
                "Оголена поперечная арматура (на длине не более 1,5-2 м вдоль опоры)",
                "Пористый бетон или узкая щель вдоль стойки",
                "На поверхности бетона выступают пятна и потеки цвета ржавчины, свидетельствующие о наличии в бетоне инородных включений (глины, руды)",
                "Шершавая поверхность бетона вследствие отслоения поверхностного слоя толщиной 3-5 мм",
                "В бетоне раковины размером 10'10 мм и глубиной 10 мм",
                "В бетоне раковина или сквозное отверстие площадью более 25 см2"};//описание к 2му оглавлению
        String[] descs3={"Отклонение стойки одностоечной свободностоящей опоры от вертикальной оси на значение, большее ее диаметра вверху",
                "Отклонение одностоечной опоры с оттяжками от перекальной оси вдоль и поперек линии",
                "Искажение геометрической формы портальной опоры на оттяжках",
                "Искривление стоек одностоечных свободностоящих опор"};//описание к 3му оглавлению
        String[] descs4={"Грунт в заделке опор не уплотнен: котлован не полностью, засыпан грунтом. Признаки коррозии арматуры в фундаментной части опоры.",
                "Признаки коррозии арматуры в фундаментной части опоры",
                "Опора заделана в грунт на глубину менее проектной. Ригели находятся на поверхности",
                "Сколы бетона оголовника фундамента"};//описание к 3му оглавлению

        descs.add(descs1);
        descs.add(descs2);
        descs.add(descs3);
        descs.add(descs4);

        CheckListItem[] checkListItems=CheckListItem.CreateCheckListitem(title,descs);

        WorkData.getInstance().setCheckListItemList(checkListItems);//пишем всё в озу

        ListAdapter listAdapter=new ListAdapter(this,checkListItems);
        listView.setAdapter(listAdapter);
    }
}
