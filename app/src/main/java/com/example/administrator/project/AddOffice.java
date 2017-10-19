package com.example.administrator.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-10-19.
 */

public class AddOffice extends AppCompatActivity {

    private ArrayList<ListData1> listDataArray = new ArrayList<ListData1>();

    private String officeName;
    private EditText edit_office;

    private ListView officelabel;
    private  int count = 5;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_office);

        officelabel = (ListView) findViewById(R.id.officelabel);
        edit_office = (EditText) findViewById(R.id.edit_office);

        Button button = (Button) findViewById(R.id.add_office);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                officeName = edit_office.getText().toString();
                Intent intent = new Intent();
                intent.putExtra("officeName",officeName);
                setResult(Activity.RESULT_OK, intent);


//                ListData1 data1 = new ListData1(officeName);
//                listDataArray.add(data1);
//
//                officelabel = (ListView) findViewById(R.id.officelabel);
//                CustomAdapter1 customAdapter = new CustomAdapter1(AddOffice.this, R.layout.custom_list_row, listDataArray);
//             //   officelabel.setAdapter(customAdapter);
//                Toast.makeText(getApplicationContext(), officeName,Toast.LENGTH_LONG).show();
//                customAdapter.notifyDataSetChanged();
                finish();
            }
        });
    }
}