package com.example.xiner.background.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.xiner.background.activity.EditInfoActivity;

import com.example.xiner.background.R;

import com.example.xiner.background.entity.Operation;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class NotificationSurvice extends Service {

    private String TAG ="BACKGROUND";
    Gson gson = new Gson();
    Operation operation;
    int notifyID=0;


    private Socket mSocket;
    {
        try {
            //1.初始化socket.io，设置链接

            mSocket = IO.socket("http://sealday.com:4000");

        } catch (URISyntaxException e) {
            Log.v(TAG, "hello world1");

        }
    }

    public NotificationSurvice() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.v(TAG, "hello world2");

        mSocket.connect();
        mSocket.on("msg:danger", listener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSocket.disconnect();
        mSocket.off("msg:danger", listener);
    }




    private Emitter.Listener listener = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {



            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.v(TAG, "runrunrun");

                    JSONObject data = (JSONObject) args[0];
                    Log.v(TAG, data.toString());


                     operation = gson.fromJson(data.toString(),Operation.class);


                        Timestamp ts = new Timestamp(Long.parseLong(operation.getTimestamp()));
                        String tsStr = "";
                        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        tsStr = sdf.format(ts);

                        showNotification(operation.getUser().getUsername()+" 编辑了订单号 "+operation.getReport().getNumber(),tsStr,notifyID);


                }
            }).start();

        }
    };



    private void showNotification(String contentTitle,String contentText,int notificationId){
        // The id of the channel.
        String CHANNEL_ID = "my_channel_01";
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(contentTitle)
                        .setContentText(contentText);
// Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, EditInfoActivity.class);
        resultIntent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("OPERATION",operation);
        resultIntent.putExtras(bundle);

// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your app to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(EditInfoActivity.class);
     //   stackBuilder.addParentStack(EditInfoActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// mNotificationId is a unique integer your app uses to identify the
// notification. For example, to cancel the notification, you can pass its ID
// number to NotificationManager.cancel().
        mNotificationManager.notify(notificationId, mBuilder.build());
        notifyID++;

    }


}
