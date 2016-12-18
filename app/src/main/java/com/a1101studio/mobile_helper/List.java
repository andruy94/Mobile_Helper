package com.a1101studio.mobile_helper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a1101studio.mobile_helper.adapters.ListAdapter;
import com.a1101studio.mobile_helper.models.CheckListItem;
import com.a1101studio.mobile_helper.singleton.WorkData;

public class List extends AppCompatActivity {
    private ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.lv1);
        String[] strings={"ads","asdas","asdasdasd"};//тут пишешь что надо

        CheckListItem[] checkListItems=new CheckListItem[3];//оглавления

        CheckListItem[] innerCheckListItems=new CheckListItem[3];//количество вложеный штук

        innerCheckListItems[0]=new CheckListItem("ololo1",false);
        innerCheckListItems[1]=new CheckListItem("ololo2",false);
        innerCheckListItems[2]=new CheckListItem("ololo3",false);

        CheckListItem[] innerCheckListItems1=new CheckListItem[3];//количество вложеный штук

        innerCheckListItems1[0]=new CheckListItem("ololo1",false);
        innerCheckListItems1[1]=new CheckListItem("ololo2",false);
        innerCheckListItems1[2]=new CheckListItem("ololo3",false);

        CheckListItem[] innerCheckListItems2=new CheckListItem[3];//количество вложеный штук

        innerCheckListItems2[0]=new CheckListItem("ololo1",false);
        innerCheckListItems2[1]=new CheckListItem("ololo2",false);
        innerCheckListItems2[2]=new CheckListItem("ololo3",false);


        checkListItems[0]=new CheckListItem("ololo1",false,innerCheckListItems);
        checkListItems[1]=new CheckListItem("ololo2",false,innerCheckListItems1);
        checkListItems[2]=new CheckListItem("ololo3",false,innerCheckListItems2);

        WorkData.getInstance().setCheckListItemList(checkListItems);//пишем всё в озу

        ListAdapter listAdapter=new ListAdapter(this,checkListItems);
        listView.setAdapter(listAdapter);
    }
}
