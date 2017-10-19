package com.example.administrator.project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-10-17.
 */

public class CustomAdapter3 extends ArrayAdapter<ListData3> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ListData3> listData;

    public CustomAdapter3(Context context, int layoutResourceId, ArrayList<ListData3> listData) {
        super(context, layoutResourceId, listData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listData = listData;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        TextView tvtext1 = (TextView) row.findViewById(R.id.custom_member_textView1);
        TextView tvtext2 = (TextView) row.findViewById(R.id.custom_member_textView2);
        TextView tvtext3 = (TextView) row.findViewById(R.id.custom_member_textView3);
        TextView tvtext4 = (TextView) row.findViewById(R.id.custom_member_textView4);

        tvtext1.setText(listData.get(position).getText1());
        tvtext2.setText(listData.get(position).getText2());
        tvtext3.setText(listData.get(position).getText3());
        tvtext4.setText(listData.get(position).getText4());


        return row;
    }
}