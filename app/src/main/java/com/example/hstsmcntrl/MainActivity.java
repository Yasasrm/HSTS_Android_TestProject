package com.example.hstsmcntrl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private ProgressBar spinner;
    private STS sts = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (ProgressBar) findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);
        refreshApp(false);
    }

    public void startSync(String url) throws IOException {
        testCallApi tac = new testCallApi();
        programPara.callingUrl = url;
        tac.execute();
    }

    public STS getDetails() {
        STS sts = null;
        try {
            Gson gson = new Gson();
            sts = gson.fromJson(JsonValues.json, STS.class);
            System.out.println("sts: " + sts);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JsonValues.json == null ? null : sts;
    }

    public void lightOneChange(View view) {
        spinner.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            public void run() {
                changeLightStatus(1);
            }
        }).start();
    }

    public void lightTwoChange(View view) {
        spinner.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            public void run() {
                changeLightStatus(2);
            }
        }).start();
    }

    public void changeLightStatus(int lightNumber) {
        try {
            STS sts = refreshApp(false);
            JsonValues.json = null;
            switch (lightNumber) {
                case 1:
                    startSync(sts.getLight1().equals(AppConfig.ON.getCode()) ? AppConfig.LIGHT_ONE_OFF.getCode() : AppConfig.LIGHT_ONE_ON.getCode());
                    break;
                case 2:
                    startSync(sts.getLight2().equals(AppConfig.ON.getCode()) ? AppConfig.LIGHT_TWO_OFF.getCode() : AppConfig.LIGHT_TWO_ON.getCode());
                    break;
            }
            sts = getDetails();
            while (sts == null) {
                Thread.sleep(200);
                sts = getDetails();
            }
            updateInterface(sts);
            spinner.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refreshStatus(View view) {
        spinner.setVisibility(View.VISIBLE);
        new Thread(new Runnable() {
            public void run() {
                refreshApp(true);
            }
        }).start();
    }

    public STS refreshApp(boolean isButton) {
        JsonValues.json = null;
        STS sts = null;
        try {
            startSync(AppConfig.GET_HOUSE_STATUS.getCode());
            sts = getDetails();
            while (sts == null) {
                Thread.sleep(200);
                sts = getDetails();
            }
            updateInterface(sts);
            if(isButton)
                spinner.setVisibility(View.GONE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sts;
    }

    private void updateInterface(STS sts) {
        final TextView lightOne = (TextView) findViewById(R.id.textView2);
        final TextView lightTwo = (TextView) findViewById(R.id.textView3);
        final TextView doorOne = (TextView) findViewById(R.id.textView4);
        final TextView doorTwo = (TextView) findViewById(R.id.textView5);
        lightOne.setText(sts == null ? "Please Refresh" : sts.getLight1().equals(AppConfig.ON.getCode()) ? AppConfig.LIGHT_ONE_ON.getName() : AppConfig.LIGHT_ONE_OFF.getName());
        lightTwo.setText(sts == null ? "Please Refresh" : sts.getLight2().equals(AppConfig.ON.getCode()) ? AppConfig.LIGHT_TWO_ON.getName() : AppConfig.LIGHT_TWO_OFF.getName());
        doorOne.setText(sts == null ? "Please Refresh" : sts.getDoor1().equals(AppConfig.OPEN.getCode()) ? AppConfig.OPEN_DOOR_ONE.getName() : AppConfig.CLOSE_DOOR_ONE.getName());
        doorTwo.setText(sts == null ? "Please Refresh" : sts.getDoor2().equals(AppConfig.OPEN.getCode()) ? AppConfig.OPEN_DOOR_TWO.getName() : AppConfig.CLOSE_DOOR_TWO.getName());
    }
}