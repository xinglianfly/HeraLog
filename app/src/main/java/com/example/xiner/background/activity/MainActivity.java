package com.example.xiner.background.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiner.background.R;
import com.example.xiner.background.adapter.NotifyAdapter;
import com.example.xiner.background.entity.ModifyRes;
import com.example.xiner.background.entity.Operation;
import com.example.xiner.background.http.HttpBase;
import com.example.xiner.background.http.HttpInterface;
import com.example.xiner.background.service.NotificationSurvice;
import com.example.xiner.background.view.OnLoadMoreListener;
import com.example.xiner.background.view.SwipeRefreshView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,OnLoadMoreListener,AdapterView.OnItemClickListener{

    private static final int RESPONSEFAILED = 101;
    private String TAG ="BACKGROUND";
    private SwipeRefreshView swipeRefreshLayout;
    HttpBase base = new HttpBase();
    List<Operation> list;
    NotifyAdapter notifyAdapter;
    Handler handler;
    private final int RESPONSESUCCESS =100;
    final   Message message = new Message();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshView) findViewById(R.id.swipenotify);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setLoadMoreListener(this);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case RESPONSESUCCESS:
                        notifyAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this,"加载成功",Toast.LENGTH_SHORT).show();

                        break;
                    case RESPONSEFAILED:
                        swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(MainActivity.this,"加载失败",Toast.LENGTH_SHORT).show();

                }

                super.handleMessage(msg);

            }
        };


        list = new ArrayList<>();

        ListView notifyList = (ListView) findViewById(R.id.list_notify);
        notifyAdapter = new NotifyAdapter(this,list);
        notifyList.setAdapter(notifyAdapter);
        notifyList.setOnItemClickListener(this);



        swipeRefreshLayout.setRefreshing(true);
        base.getTopk("api/operation/top_k", new HttpInterface() {
            @Override
            public void onSuccess(ModifyRes res) {
                Log.v(TAG,res.getMessage());
                List<Operation> operations =  res.getData().getOperations();
                list.addAll(operations);

                message.what = RESPONSESUCCESS;
                handler.sendMessage(message);
            }

            @Override
            public void onFailed() {
                message.what = RESPONSEFAILED;
                handler.sendMessage(message);

            }
        });

        Intent nofiIntent = new Intent(this, NotificationSurvice.class);
        startService(nofiIntent);

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


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onRefresh() {
        base.getTopk("api/operation/top_k", new HttpInterface() {
            @Override
            public void onSuccess(ModifyRes res) {
                Log.v(TAG,res.getMessage());
                List<Operation> operations =  res.getData().getOperations();
                list.addAll(operations);
              
                message.what = RESPONSESUCCESS;
                handler.sendMessage(message);
            }

            @Override
            public void onFailed() {
                message.what = RESPONSEFAILED;
                handler.sendMessage(message);

            }
        });



    }

    @Override
    public void loadMore() {
        Toast.makeText(this,"loadMore",Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setLoading(false);

            }
        },3000);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = new Intent(this,EditInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("OPERATION", (Serializable) list.get(i));
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
