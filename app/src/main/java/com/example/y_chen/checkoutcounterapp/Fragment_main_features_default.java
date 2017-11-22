package com.example.y_chen.checkoutcounterapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_main_features_default extends Fragment implements View.OnClickListener{

    public static final String TAG="default";

    private MainFeaturesActivity mactivity;
    private Fragment mfragment;

    private Button buttonIncludeProducts;
    private Button buttonListProducts;
    private Button buttonListInquiry;
    private Button buttonStockControl;

    public Fragment_main_features_default() {
        // Required empty public constructor

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mactivity = (MainFeaturesActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_main_features_default,
                container, false);
        buttonIncludeProducts=view.findViewById(R.id.fragment_default_button_include_products);
        buttonListInquiry=view.findViewById(R.id.fragment_default_button_list_inquiry);
        buttonListProducts=view.findViewById(R.id.fragment_default_button_list_products);
        buttonStockControl=view.findViewById(R.id.fragment_default_button_stock_control);

        buttonIncludeProducts.setOnClickListener(this);
        buttonListInquiry.setOnClickListener(this);
        buttonListProducts.setOnClickListener(this);
        buttonStockControl.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_default_button_include_products:
                mfragment=new Fragment_main_features_include_products();
                mactivity.replaceFragment(mfragment);
                break;
            case R.id.fragment_default_button_list_inquiry:

                mactivity.replaceFragment(mfragment);
                break;
            case R.id.fragment_default_button_list_products:

                mactivity.replaceFragment(mfragment);
                break;
            case R.id.fragment_default_button_stock_control:

                mactivity.replaceFragment(mfragment);
                break;
            default:
                break;

        }
    }

}
