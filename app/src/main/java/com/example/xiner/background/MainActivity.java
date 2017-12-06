package com.example.xiner.background;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiner.background.adapter.NotifyAdapter;
import com.example.xiner.background.service.NotificationSurvice;
import com.example.xiner.background.view.OnLoadMoreListener;
import com.example.xiner.background.view.SwipeRefreshView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,OnLoadMoreListener{

    private String TAG ="BACKGROUND";
    private SwipeRefreshView swipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshView) findViewById(R.id.swipenotify);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setLoadMoreListener(this);


        ArrayList<String> list = new ArrayList<>();
        for(int i =0;i<40;i++){
            list.add("a"+i);
        }
        ListView view = (ListView) findViewById(R.id.list_notify);
        view.setAdapter(new NotifyAdapter(this,list));


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
        Toast.makeText(MainActivity.this,"refresh",Toast.LENGTH_SHORT).show();
        swipeRefreshLayout.setRefreshing(false);

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
}
