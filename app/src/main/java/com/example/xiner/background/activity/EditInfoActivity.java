package com.example.xiner.background.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.util.Log;
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
import com.example.xiner.background.entity.Materials;
import com.example.xiner.background.entity.Operation;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class EditInfoActivity extends AppCompatActivity {

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
        userview.setText(operation.getUser().getUsername());
        List<Editinfo> editinfos = operation.getReport().getRecordEdit();
//        GridView gridView = (GridView) findViewById(R.id.grid_recordEdit);
//        gridView.setAdapter(new recordEditGrid(this,editinfos));

        if (editinfos.size()!=0){

            LinearLayout lleditRecord = (LinearLayout) findViewById(R.id.ll_recordedit);

            for (int i =0;i< editinfos.size();i++){
                TextView Path1 = new TextView(this);
                TextView Old1 = new TextView(this);
                TextView New1 = new TextView(this);
                LinearLayout line = new LinearLayout(this);
                LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,5);
                line.setLayoutParams(llParams);
                line.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
//                RelativeLayout.LayoutParams headPathLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                RelativeLayout.LayoutParams headOldLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                RelativeLayout.LayoutParams headNewLP = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                Path1.setLayoutParams(headPathLP);
//                Old1.setLayoutParams(headOldLP);
//                New1.setLayoutParams(headNewLP
//                );
//                RelativeLayout.LayoutParams PathLP = (RelativeLayout.LayoutParams) Path1.getLayoutParams();
//                PathLP.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//                Path1.setLayoutParams(PathLP);
//
//                RelativeLayout.LayoutParams OldLP = (RelativeLayout.LayoutParams) Old1.getLayoutParams();
//                OldLP.addRule(RelativeLayout.CENTER_IN_PARENT);
//                Old1.setLayoutParams(OldLP);
//
//                RelativeLayout.LayoutParams NewLP = (RelativeLayout.LayoutParams) New1.getLayoutParams();
//                NewLP.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                New1.setLayoutParams(NewLP);


                Path1.setText("条目名称:"+editinfos.get(i).getPath().toString());
                Old1.setText("旧值:"+editinfos.get(i).getOld());
                New1.setText("新值:"+editinfos.get(i).getNew().toString());
                lleditRecord.addView(Path1);
                lleditRecord.addView(Old1);
                lleditRecord.addView(New1);
                lleditRecord.addView(line);
            }


//            TableLayout tableLayout = (TableLayout) findViewById(R.id.table_recordedit);
//            TableRow headTablerow = new TableRow(this);
//            TableRow.LayoutParams headtablerowLP = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            headTablerow.setLayoutParams(headtablerowLP);
//
//            //第一行recordEdit
//            TextView headPath = new TextView(this);
//            TextView headOld = new TextView(this);
//            TextView headNew = new TextView(this);
//
//            headPath.setText("条目名称");
//            headOld.setText("旧值");
//            headNew.setText("新值");
//
//            TableRow.LayoutParams headPathLP = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
//            TableRow.LayoutParams headOldLP = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
//            TableRow.LayoutParams headNewLP = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
//            headPath.setLayoutParams(headPathLP);
//            headOld.setLayoutParams(headOldLP);
//            headNew.setLayoutParams(headNewLP);
//            headTablerow.addView(headPath);
//            headTablerow.addView(headOld);
//            headTablerow.addView(headNew);
//            tableLayout.addView(headTablerow);
//
//            for (int i =0;i<editinfos.size();i++){
//                TableRow row1 = new TableRow(this);
//                TableRow.LayoutParams row1LP = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                row1.setLayoutParams(row1LP);
//                TextView Path1 = new TextView(this);
//                TextView Old1 = new TextView(this);
//                TextView New1 = new TextView(this);
//                Path1.setText(editinfos.get(i).getPath().toString());
//                Old1.setText(editinfos.get(i).getOld());
//                New1.setText(editinfos.get(i).getNew().toString());
//                TableRow.LayoutParams Path1LP = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
//                TableRow.LayoutParams Old1LP = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
//                TableRow.LayoutParams New1LP = new TableRow.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,1);
//                Path1.setLayoutParams(Path1LP);
//                Old1.setLayoutParams(Old1LP);
//                New1.setLayoutParams(New1LP);
//                row1.addView(Path1);
//                row1.addView(Old1);
//                row1.addView(New1);
//                tableLayout.addView(row1);
//            }
        }

        List<EditMaterial> entryedits = operation.getReport().getEntryEdit();

        if (entryedits.size()!=0){
            LinearLayout llentryEdit = (LinearLayout) findViewById(R.id.ll_entryedit);
            for(int i =0;i<entryedits.size();i++){
                TextView textEntryEdit = new TextView(this);
                EditMaterial material = entryedits.get(i);
                Materials  oldMaterial = material.getOld();
                Materials newMaterial = material.getNew();
                textEntryEdit.setText("旧值：\n"+oldMaterial.toString()+"\n"+"新值：\n"+newMaterial.toString());
                llentryEdit.addView(textEntryEdit);
            }
        }

        List<Materials> entryAdds = operation.getReport().getEntryAdd();
        if(entryAdds.size()!=0){
            LinearLayout llentryAdd = (LinearLayout) findViewById(R.id.ll_entryadd);
            for (int i =0;i<entryAdds.size();i++){
                TextView textEntryAdd = new TextView(this);
                textEntryAdd.setText("增加的条目为:\n"+entryAdds.get(i).toString());
                llentryAdd.addView(textEntryAdd);
            }
        }

        List<Materials> entryRemove = operation.getReport().getEntryRemove();
        if (entryRemove.size()!=0){
            LinearLayout llentryRemove = (LinearLayout) findViewById(R.id.ll_entryremove);
            for(int i =0;i<entryRemove.size();i++){
                TextView textEntryRemove = new TextView(this);
                textEntryRemove.setText("删除条目:\n"+entryRemove.get(i).toString());
                llentryRemove.addView(textEntryRemove);
            }
        }


    }

}
