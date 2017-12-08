package com.example.xiner.background.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xiner.background.R;
import com.example.xiner.background.entity.Operation;

public class EditInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        Operation operation = (Operation) getIntent().getSerializableExtra("OPERATION");
        Log.v("BACKGROUND",operation.getTimestamp());
    }
}
