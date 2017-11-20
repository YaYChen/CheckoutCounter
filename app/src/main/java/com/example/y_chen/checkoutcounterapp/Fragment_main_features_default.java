package com.example.y_chen.checkoutcounterapp;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

    private MainFeaturesActivity activity=(MainFeaturesActivity)getActivity();

    private Button buttonIncludeProducts;
    private Button buttonListProducts;
    private Button buttonListInquiry;
    private Button buttonStockControl;

    public Fragment_main_features_default() {
        // Required empty public constructor

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

        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.fragment_default_button_include_products:
                if (activity.fragment == null) {
                    //activity.fragment = new ContactFragment();
                }
                replaceFragment(activity.fragment);

                break;
            case R.id.fragment_default_button_list_inquiry:
                replaceFragment(activity.fragment);
                break;
            case R.id.fragment_default_button_list_products:

                replaceFragment(activity.fragment);
                break;
            case R.id.fragment_default_button_stock_control:

                replaceFragment(activity.fragment);
                break;
            default:
                break;

        }
        ft.commit();
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=activity.getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.main_features_main_fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
