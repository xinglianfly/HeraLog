package com.example.xiner.background.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiner.background.R;
import com.example.xiner.background.entity.Editinfo;

import java.util.List;

/**
 * Created by seald on 2017/12/8.
 */

public class recordEditGrid extends BaseAdapter {
    Context context;
    List<Editinfo> editinfos;
    private LayoutInflater mInflater;


    public recordEditGrid(Context context, List<Editinfo> editinfos){
        this.context = context;
        this.editinfos = editinfos;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return editinfos.size();
    }

    @Override
    public Object getItem(int i) {
        return editinfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = mInflater.inflate(R.layout.grid_recordedit,null);
            TextView editname = (TextView) view.findViewById(R.id.gtext_recordedit);
            if (i%3==0){
                editname.setText(editinfos.get(i).getPath().toString());

            }else if (i%3==1){
                editname.setText(editinfos.get(i).getOld().toString());
            }else {
                editname.setText(editinfos.get(i).getNew().toString());
            }

        }
        return view;
    }
}
