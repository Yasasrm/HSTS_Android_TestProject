package com.example.hstsmcntrl;

import android.os.AsyncTask;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class testCallApi extends AsyncTask {

    public testCallApi() {
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try{
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            Request request = new Request.Builder()
                    .url("https://hsm272e.herokuapp.com/monitor/"+ programPara.callingUrl)
                    .method("GET", null)
                    .addHeader("Authorization", "Basic eWFzYXNybTphMXMyZDNmNA==")
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            JsonValues.json = body.string();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
