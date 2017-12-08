package com.example.xiner.background.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiner.background.R;
import com.example.xiner.background.entity.Operation;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seald on 2017/12/6.
 */

public class NotifyAdapter extends BaseAdapter{
    Context context;
    List<Operation> arrayList;

    private LayoutInflater mInflater;

    public NotifyAdapter(Context context,List<Operation> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = mInflater.inflate(R.layout.adapter_notify,null);
            TextView modiUser = (TextView) view.findViewById(R.id.text_modifyuser);
            TextView modiDate = (TextView) view.findViewById(R.id.text_modifydate);
            modiUser.setText(arrayList.get(i).getReport().getUser().getUsername());

            Timestamp ts = new Timestamp(Long.parseLong(arrayList.get(i).getTimestamp()));
            String tsStr = "";
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                tsStr = sdf.format(ts);

                modiDate.setText(tsStr);
            //textView.setText(arrayList.get(i));
        }
        return view;
    }

}
