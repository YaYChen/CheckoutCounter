package com.example.y_chen.checkoutcounterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainFeaturesActivity extends AppCompatActivity {
    private final static String TAG = MainFeaturesActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_features);
    }
}
