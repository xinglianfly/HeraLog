package com.example.xiner.background.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiner.background.R;

import java.util.ArrayList;

/**
 * Created by seald on 2017/12/6.
 */

public class NotifyAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> arrayList;

    private LayoutInflater mInflater;

    public NotifyAdapter(Context context,ArrayList<String> arrayList){
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
            TextView textView = (TextView) view.findViewById(R.id.adapter_textnotify);
            textView.setText(arrayList.get(i));
        }
        return view;
    }

}
