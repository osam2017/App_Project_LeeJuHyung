package com.example.administrator.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class BonBu extends AppCompatActivity {

    private ArrayList<ListData2> listDataArray = new ArrayList<ListData2>();
    private GridView officelabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bonbu);



        officelabel = (GridView) findViewById(R.id.grid_bonbu);

        GridData();

        officelabel.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent6 = new Intent(BonBu.this,MemberState.class) ;
                startActivity(intent6);
            }
        });
    }

    public void GridData(){
        ListData2 data1 = new ListData2("1생활관", "7명", "3명", "4명", "7정", "3정", "4정" );
        listDataArray.add(data1);

        ListData2 data2 = new ListData2("2생활관", "6명", "3명", "3명", "7정", "3정", "4정");
        listDataArray.add(data2);

        ListData2 data3 = new ListData2("3생활관", "4명", "0명", "4명", "7정", "3정", "4정");
        listDataArray.add(data3);

        ListData2 data4 = new ListData2("4생활관", "5명", "1명", "4명", "7정", "3정", "4정");
        listDataArray.add(data4);

        ListData2 data5 = new ListData2("5생활관", "8명", "2명", "6명", "7정", "3정", "4정");
        listDataArray.add(data5);

        officelabel = (GridView) findViewById(R.id.grid_bonbu);
        CustomAdapter2 customAdapter = new CustomAdapter2(this, R.layout.custom_grid_row, listDataArray);
        officelabel.setAdapter(customAdapter);
    }
}
