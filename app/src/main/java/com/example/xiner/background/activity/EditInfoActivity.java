package com.example.xiner.background.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.telecom.PhoneAccountHandle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.xiner.background.R;
import com.example.xiner.background.adapter.recordEditGrid;
import com.example.xiner.background.entity.EditMaterial;
import com.example.xiner.background.entity.Editinfo;
import com.example.xiner.background.entity.Fee;
import com.example.xiner.background.entity.Materials;
import com.example.xiner.background.entity.Operation;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class EditInfoActivity extends AppCompatActivity {

    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Operation operation = (Operation) getIntent().getSerializableExtra("OPERATION");
        Log.v("BACKGROUND",operation.getTimestamp());

        TextView dateview = (TextView) findViewById(R.id.text_infodata);
        TextView userview = (TextView) findViewById(R.id.text_infouser);
        Timestamp ts = new Timestamp(Long.parseLong(operation.getTimestamp()));
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        tsStr = sdf.format(ts);
        dateview.setText(tsStr);
        userview.setText(operation.getUser().getUsername()+" 编辑了订单 "+operation.getReport().getNumber());
        List<Editinfo> editinfos = operation.getReport().getRecordEdit();
//        GridView gridView = (GridView) findViewById(R.id.grid_recordEdit);
//        gridView.setAdapter(new recordEditGrid(this,editinfos));

        if (editinfos.size()>2){


            LinearLayout lleditRecord = (LinearLayout) findViewById(R.id.ll_recordedit);
            TextView textReEdit = (TextView) findViewById(R.id.text_head);
            textReEdit.setVisibility(View.VISIBLE);
            int feeCount =0;
            for (int i =0;i< editinfos.size();i++){
                if ((!"updatedAt".equals(editinfos.get(i).getPath().get(0))) && (!"__v".equals(editinfos.get(i).getPath().get(0)))) {

                    if (!"fee".equals(editinfos.get(i).getPath().get(0))){
                        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View recordv = vi.inflate(R.layout.item_recordedit, null);
                        TextView name = (TextView) recordv.findViewById(R.id.name);
                        TextView oldvalue = (TextView) recordv.findViewById(R.id.old_value);
                        TextView newvalue = (TextView)recordv.findViewById(R.id.new_value);
                        if ("carNumber".equals(editinfos.get(i).getPath().get(0))){
                            name.setText("车牌号");

                        }else if ("originalOrder".equals(editinfos.get(i).getPath().get(0))){
                            name.setText("原始单号");


                        }else if ("outDate".equals(editinfos.get(i).getPath().get(0))){
                            name.setText("时间");

                        }else if ("inStock".equals(editinfos.get(i).getPath().get(0))){
                            name.setText("入库项目");


                        }else if ("outStock".equals(editinfos.get(i).getPath().get(0))){
                            name.setText("出库项目");

                        }


                        if (editinfos.get(i).getOld()!=null){
                            oldvalue.setText("旧值:" + editinfos.get(i).getOld());

                        }else{
                            oldvalue.setText("旧值:");
                        }

                        newvalue.setText("新值:" + editinfos.get(i).getNew().toString());
                        newvalue.setTextColor(Color.parseColor("#f40a3b"));

                        lleditRecord.addView(recordv);

                    }else{

                        if(feeCount==0){
                            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View recordfee = vi.inflate(R.layout.item_recordfee, null);
                            TextView oldcar = (TextView) recordfee.findViewById(R.id.old_car);
                            TextView oldsort = (TextView) recordfee.findViewById(R.id.old_sort);
                            TextView oldother1 = (TextView)recordfee.findViewById(R.id.old_other1);
                            TextView oldother2 = (TextView)recordfee.findViewById(R.id.old_other2);
                            Fee oldFee = null;
                            Fee newFee = gson.fromJson(editinfos.get(i).getNew().toString(),Fee.class);
                            TextView newcar = (TextView) recordfee.findViewById(R.id.new_car);
                            TextView newsort = (TextView) recordfee.findViewById(R.id.new_sort);
                            TextView newother1 = (TextView)recordfee.findViewById(R.id.new_other1);
                            TextView newother2 = (TextView)recordfee.findViewById(R.id.new_other2);
                            newcar.setText("车费:"+newFee.getCar());
                            newsort.setText("整理费:"+newFee.getSort());
                            newother1.setText("其他费用1:"+newFee.getOther1());
                            newother2.setText("其他费用2:"+newFee.getOther2());

                            if (editinfos.get(i).getOld()!=null){
                                oldFee = gson.fromJson(editinfos.get(i).getOld().toString(),Fee.class);
                                oldcar.setText("车费:"+oldFee.getCar());
                                oldsort.setText("整理费:"+oldFee.getSort());
                                oldother1.setText("其他费用1:"+oldFee.getOther1());
                                oldother2.setText("其他费用2:"+oldFee.getOther2());


                                if (oldFee.getCar()!=newFee.getCar()){
                                    newcar.setTextColor(Color.parseColor("#f40a3b"));

                                }

                                if (oldFee.getSort()!=newFee.getSort()){
                                    newsort.setTextColor(Color.parseColor("#f40a3b"));

                                }

                                if (oldFee.getOther1()!=newFee.getOther1()){
                                    newother1.setTextColor(Color.parseColor("#f40a3b"));

                                }

                                if (oldFee.getOther2()!=newFee.getOther2()){
                                    newother2.setTextColor(Color.parseColor("#f40a3b"));
                                }
                            }else{
                                oldcar.setText("车费:0.0");
                                oldsort.setText("整理费:0.0");
                                oldother1.setText("其他费用1:0.0");
                                oldother2.setText("其他费用2:0.0");
                                newcar.setTextColor(Color.parseColor("#f40a3b"));
                                newsort.setTextColor(Color.parseColor("#f40a3b"));
                                newother1.setTextColor(Color.parseColor("#f40a3b"));
                                newother2.setTextColor(Color.parseColor("#f40a3b"));

                            }







                            lleditRecord.addView(recordfee);
//                            name.setText("条目名称:" + editinfos.get(i).getPath().get(0));
//                            oldvalue.setText("旧值:" + editinfos.get(i).getOld());
//                            newvalue.setText("新值:" + editinfos.get(i).getNew().toString());
//                            lleditRecord.addView(recordv);

                        }
                       feeCount = feeCount+1;
                    }




//                    TextView Path1 = new TextView(this);
//                    Path1.setTextSize(15);
//                    TextView Old1 = new TextView(this);
//                    Old1.setTextSize(15);
//                    TextView New1 = new TextView(this);
//                    New1.setTextSize(15);
//                    LinearLayout line = new LinearLayout(this);
//                    LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 3);
//                    line.setLayoutParams(llParams);
//                    line.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
//
//
//                    Path1.setText("条目名称:" + editinfos.get(i).getPath().toString());
//                    Old1.setText("旧值:" + editinfos.get(i).getOld());
//                    New1.setText("新值:" + editinfos.get(i).getNew().toString());
//                    lleditRecord.addView(Path1);
//                    lleditRecord.addView(Old1);
//                    lleditRecord.addView(New1);
//                    lleditRecord.addView(line);
                }
            }

        }

        List<EditMaterial> entryedits = operation.getReport().getEntryEdit();

        if (entryedits.size()!=0){
            LinearLayout llentryEdit = (LinearLayout) findViewById(R.id.ll_entryedit);
            TextView textEnEdit = (TextView) findViewById(R.id.text_entryedit);
            textEnEdit.setVisibility(View.VISIBLE);

            for(int i =0;i<entryedits.size();i++){
                LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v = vi.inflate(R.layout.item_entryedit, null);

                TextView oldname = (TextView) v.findViewById(R.id.old_name);
                TextView oldcount = (TextView) v.findViewById(R.id.textold_count);
                TextView oldsize = (TextView) v.findViewById(R.id.old_size);
                TextView oldtype = (TextView) v.findViewById(R.id.old_type);

                TextView newname = (TextView) v.findViewById(R.id.new_name);
                TextView newcount = (TextView) v.findViewById(R.id.new_count);
                TextView newsize = (TextView) v.findViewById(R.id.new_size);
                TextView newtype = (TextView) v.findViewById(R.id.new_type);


                EditMaterial material = entryedits.get(i);
                Materials  oldMaterial = material.getOld();
                Materials newMaterial = material.getNew();

                oldname.setText("名称:"+oldMaterial.getName());
                oldcount.setText("数量:"+String.valueOf(oldMaterial.getCount()));
                oldsize.setText("规格:"+oldMaterial.getSize());
                oldtype.setText("类型:"+oldMaterial.getType());


                newname.setText("名称:"+newMaterial.getName());
                newcount.setText("数量:"+String.valueOf(newMaterial.getCount()));
                newsize.setText("规格:"+newMaterial.getSize());
                newtype.setText("类型:"+newMaterial.getType());

                if (!oldMaterial.getName().equals(newMaterial.getName())){
                    newname.setTextColor(Color.parseColor("#f40a3b"));
                }

                if (oldMaterial.getCount()!=newMaterial.getCount()){
                    newcount.setTextColor(Color.parseColor("#f40a3b"));

                }

                if (!oldMaterial.getSize().equals(newMaterial.getSize())){
                    newsize.setTextColor(Color.parseColor("#f40a3b"));

                }
                if (!oldMaterial.getType().equals(newMaterial.getType())){
                    newtype.setTextColor(Color.parseColor("#f40a3b"));

                }

                llentryEdit.addView(v,0,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                //llentryEdit.addView(line);
            }
        }

        List<Materials> entryAdds = operation.getReport().getEntryAdd();
        if(entryAdds.size()!=0){
            LinearLayout llentryAdd = (LinearLayout) findViewById(R.id.ll_entryadd);
            TextView textAdd = (TextView) findViewById(R.id.text_entryadd);
            textAdd.setVisibility(View.VISIBLE);
            for (int i =0;i<entryAdds.size();i++){

                LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vadd = vi.inflate(R.layout.item_entryadd, null);


                TextView addname = (TextView) vadd.findViewById(R.id.old_name);
                TextView addcount = (TextView)vadd.findViewById(R.id.textold_count);
                TextView addsize = (TextView) vadd.findViewById(R.id.old_size);
                TextView addtype = (TextView) vadd.findViewById(R.id.old_type);

                Materials addMaterial = entryAdds.get(i);

                addname.setText("名称:"+addMaterial.getName());
                addcount.setText("数量:"+String.valueOf(addMaterial.getCount()));
                addsize.setText("规格:"+addMaterial.getSize());
                addtype.setText("类型:"+addMaterial.getType());

                llentryAdd.addView(vadd);
            }
        }

        List<Materials> entryRemove = operation.getReport().getEntryRemove();
        if (entryRemove.size()!=0){
            LinearLayout llentryRemove = (LinearLayout) findViewById(R.id.ll_entryremove);
            TextView textEnRemove = (TextView) findViewById(R.id.text_entryremove);
            textEnRemove.setVisibility(View.VISIBLE);
            for(int i =0;i<entryRemove.size();i++){
                LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View vremove = vi.inflate(R.layout.item_entryadd, null);


                TextView addname = (TextView) vremove.findViewById(R.id.old_name);
                TextView addcount = (TextView)vremove.findViewById(R.id.textold_count);
                TextView addsize = (TextView) vremove.findViewById(R.id.old_size);
                TextView addtype = (TextView) vremove.findViewById(R.id.old_type);

                Materials addMaterial = entryRemove.get(i);

                addname.setText("名称:"+addMaterial.getName());
                addcount.setText("数量:"+String.valueOf(addMaterial.getCount()));
                addsize.setText("规格:"+addMaterial.getSize());
                addtype.setText("类型:"+addMaterial.getType());
                llentryRemove.addView(vremove);
            }
        }


    }

}
