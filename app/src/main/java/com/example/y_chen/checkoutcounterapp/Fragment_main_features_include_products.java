package com.example.y_chen.checkoutcounterapp;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.y_chen.checkoutcounterapp.Services.Interfaces.BluetoothDataReceiver;


public class Fragment_main_features_include_products extends Fragment
        implements BluetoothDataReceiver {

    public static final String TAG="include_products";

    public MainFeaturesActivity activity;

    public Fragment_main_features_include_products() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_main_features_include_products,
                container,
                false);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=(MainFeaturesActivity)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void DataReceiver(String data){

    }

}
