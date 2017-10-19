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

public class CustomAdapter2 extends ArrayAdapter<ListData2> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ListData2> listData;

    public CustomAdapter2(Context context, int layoutResourceId, ArrayList<ListData2> listData) {
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

        TextView tvtext1 = (TextView) row.findViewById(R.id.custom_row_gridView1);
        TextView tvtext2 = (TextView) row.findViewById(R.id.custom_row_gridView2);
        TextView tvtext3 = (TextView) row.findViewById(R.id.custom_row_gridView3);
        TextView tvtext4 = (TextView) row.findViewById(R.id.custom_row_gridView4);
        TextView tvtext5 = (TextView) row.findViewById(R.id.custom_row_gridView5);
        TextView tvtext6 = (TextView) row.findViewById(R.id.custom_row_gridView6);
        TextView tvtext7 = (TextView) row.findViewById(R.id.custom_row_gridView7);

        tvtext1.setText(listData.get(position).getText1());
        tvtext2.setText(listData.get(position).getText2());
        tvtext3.setText(listData.get(position).getText3());
        tvtext4.setText(listData.get(position).getText4());
        tvtext5.setText(listData.get(position).getText5());
        tvtext6.setText(listData.get(position).getText6());
        tvtext7.setText(listData.get(position).getText7());


        return row;
    }
}