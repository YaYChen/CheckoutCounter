package com.example.y_chen.checkoutcounterapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainFeaturesActivity extends AppCompatActivity {
    private final static String TAG = MainFeaturesActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    private final String LIST_NAME = "NAME";
    private final String LIST_UUID = "UUID";

    public Fragment fragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle(R.string.activity_title_main_features);
        setContentView(R.layout.activity_main_features);

        fragment=new Fragment_main_features_default();
        replaceFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_features, menu);
        menu.findItem(R.id.main_features_menu_include_products)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        return false;
                    }
                });
        menu.findItem(R.id.main_features_menu_list_products)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        return false;
                    }
                });
        menu.findItem(R.id.main_features_menu_list_inquiry)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        return false;
                    }
                });
        menu.findItem(R.id.main_features_menu_stock_control)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
                {
                    @Override
                    public boolean onMenuItemClick(MenuItem item)
                    {

                        return false;
                    }
                });
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(fragment!=null){
            replaceFragment(fragment);
        }else{
            fragment=new Fragment_main_features_default();
            replaceFragment(fragment);
        }

    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.main_features_main_fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
