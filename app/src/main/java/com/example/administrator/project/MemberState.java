package com.example.administrator.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;




public class MemberState extends AppCompatActivity {

    private ArrayList<ListData3> listDataArray = new ArrayList<ListData3>();
    private ListView memberstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.member_state);

        memberstate = (ListView) findViewById(R.id.list_memberState);

        ListData();

    }

    public void ListData(){
        ListData3 data1 = new ListData3("병장", "홍길동", "부대", "생활관");
        listDataArray.add(data1);

        ListData3 data2 = new ListData3("상병", "이주형", "휴가", "무기고");
        listDataArray.add(data2);

        ListData3 data3 = new ListData3("일병", "이순신", "파견", "무기고");
        listDataArray.add(data3);

        ListData3 data4 = new ListData3("상병", "김유신", "근무", "당직실");
        listDataArray.add(data4);

        ListData3 data5 = new ListData3("병장", "장보고", "입실", "무기고");
        listDataArray.add(data5);

        memberstate = (ListView) findViewById(R.id.list_memberState);
        CustomAdapter3 customAdapter = new CustomAdapter3(this, R.layout.custom_member_row, listDataArray);
        memberstate.setAdapter(customAdapter);
    }
}
