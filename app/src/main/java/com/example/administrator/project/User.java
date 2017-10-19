package com.example.administrator.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class User extends AppCompatActivity {

    private ArrayList<ListData1> listDataArray = new ArrayList<ListData1>();

    private Button mReturnButton;
    private ListView officelabel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        mReturnButton = (Button)findViewById(R.id.returnback);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        officelabel = (ListView) findViewById(R.id.officelabel);

        ListData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(User.this,AddOffice.class) ;
                startActivityForResult(intent4, 1);

            }
        });

        officelabel.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent5 = new Intent(User.this,BonBu.class) ;
                startActivity(intent5);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result=data.getStringExtra("officeName");
       // Log.i("온엑티비티 들어옴", "들어옴" + result);
        ListData1 data6 = new ListData1(result, "0명", "0명","0명");
        listDataArray.add(data6);

        officelabel = (ListView) findViewById(R.id.officelabel);
        CustomAdapter1 customAdapter = new CustomAdapter1(this, R.layout.custom_list_row, listDataArray);
        officelabel.setAdapter(customAdapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void ListData(){
        ListData1 data1 = new ListData1("본부중대", "50명", "10명", "40명");
        listDataArray.add(data1);

        ListData1 data2 = new ListData1("급양대", "60명", "10명", "50명");
        listDataArray.add(data2);

        ListData1 data3 = new ListData1("정비대", "70명", "10명", "60명");
        listDataArray.add(data3);

        ListData1 data4 = new ListData1("수송대대", "100명", "10명", "90명");
        listDataArray.add(data4);

        ListData1 data5 = new ListData1("보급대", "40명", "10명", "30명");
        listDataArray.add(data5);

        officelabel = (ListView) findViewById(R.id.officelabel);
        CustomAdapter1 customAdapter = new CustomAdapter1(this, R.layout.custom_list_row, listDataArray);
        officelabel.setAdapter(customAdapter);
    }



    public void back_to_login(View view) {
        //setContentView(R.layout.login);
        Intent intent3 = new Intent(User.this,Login.class) ;
        startActivity(intent3);
        finish();

    }
}
