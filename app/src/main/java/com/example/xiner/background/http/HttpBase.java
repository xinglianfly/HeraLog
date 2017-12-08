package com.example.xiner.background.http;

import android.util.Log;

import com.example.xiner.background.entity.ModifyRes;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by seald on 2017/12/7.
 */

public class HttpBase {
    public static String baseUrl = "http://sealday.com:3000/";
    private final OkHttpClient client = new OkHttpClient();
    private String TAG ="BACKGROUND";
    Gson gson = new Gson();


    public  void getTopk(String url, final HttpInterface httpInterface){
        Request request = new Request.Builder()
                .url(baseUrl+url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                httpInterface.onFailed();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    //Log.v(TAG,responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }
                String jsonStr = response.body().string();
                Log.v(TAG,jsonStr);
               ModifyRes modifyRes = gson.fromJson(jsonStr,ModifyRes.class);
               Log.v(TAG,modifyRes.toString());

             httpInterface.onSuccess(modifyRes);

            }
        });
    }

}
