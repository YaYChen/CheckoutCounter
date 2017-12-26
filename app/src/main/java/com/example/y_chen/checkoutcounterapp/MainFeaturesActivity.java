package com.example.y_chen.checkoutcounterapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.y_chen.checkoutcounterapp.Services.BuletoothService.BluetoothServices;
import com.example.y_chen.checkoutcounterapp.Services.Interfaces.BluetoothDataReceiver;

import java.util.List;

public class MainFeaturesActivity extends AppCompatActivity {
    private final static String TAG = MainFeaturesActivity.class.getSimpleName();

    public static final String EXTRAS_DEVICE_NAME = "DEVICE_NAME";
    public static final String EXTRAS_DEVICE_ADDRESS = "DEVICE_ADDRESS";

    public static final String NOTIFY_SERVICE_UUID = "0000180d-0000-1000-8000-00805f9b34fb";
    public static final String NOTIFY_CHARACTERISTIC_UUID = "0000180d-0000-1000-8000-00805f9b34fb";

    private String mDeviceName;
    private String mDeviceAddress;

    private BluetoothServices mBluetoothService;
    private BluetoothGattCharacteristic mNotifyCharacteristic=null;

    private boolean mConnected = false;

    public Fragment mfragment=null;

    private Toolbar toolbar=null;
    private NavigationView navigationView=null;

    // Code to manage Service lifecycle.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mBluetoothService = ((BluetoothServices.LocalBinder) service).getService();
            if (!mBluetoothService.initialize()) {
                Log.e(TAG, "Unable to initialize Bluetooth");
                finish();
            }
            // Automatically connects to the device upon successful start-up initialization.
            mBluetoothService.connect(mDeviceAddress);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothService = null;
        }
    };

    private final void  startDeviceDataListener(){
        if(mNotifyCharacteristic!=null){
            final int charaProp = mNotifyCharacteristic.getProperties();
            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
                // If there is an active notification on a characteristic, clear
                // it first so it doesn't update the data field on the user interface.
                mBluetoothService.readCharacteristic(mNotifyCharacteristic);
            }
            if ((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
                mBluetoothService.setCharacteristicNotification(
                        mNotifyCharacteristic, true);
            }
        }

    }

    // Handles various events fired by the Service.
    // ACTION_GATT_CONNECTED: connected to a GATT server.
    // ACTION_GATT_DISCONNECTED: disconnected from a GATT server.
    // ACTION_GATT_SERVICES_DISCOVERED: discovered GATT services.
    // ACTION_DATA_AVAILABLE: received data from the device.  This can be a result of read
    //                        or notification operations.
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if (BluetoothServices.ACTION_GATT_CONNECTED.equals(action)) {
                mConnected = true;
                //updateConnectionState(R.string.connected);
                invalidateOptionsMenu();
            } else if (BluetoothServices.ACTION_GATT_DISCONNECTED.equals(action)) {
                mConnected = false;
                //updateConnectionState(R.string.disconnected);
                invalidateOptionsMenu();
                //clearUI();
            }
            else if (BluetoothServices.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                // Show all the supported services and characteristics on the user interface.
                getGattServices(mBluetoothService.getSupportedGattServices());
            } else if (BluetoothServices.ACTION_DATA_AVAILABLE.equals(action)) {
                receiveData(intent.getStringExtra(BluetoothServices.EXTRA_DATA));
            }
        }
    };

    // Demonstrates how to iterate through the supported GATT Services/Characteristics.
    // In this sample, we populate the data structure that is bound to the ExpandableListView
    // on the UI.
    private void getGattServices(List<BluetoothGattService> gattServices) {
        if (gattServices == null) return;
        // Loops through available GATT Services.
        for (BluetoothGattService gattService : gattServices) {
            if(gattService.getUuid().toString() == NOTIFY_SERVICE_UUID){
                List<BluetoothGattCharacteristic> gattCharacteristics =
                        gattService.getCharacteristics();
                for(BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics){
                    if(gattCharacteristic.getUuid().toString() == NOTIFY_CHARACTERISTIC_UUID){
                        mNotifyCharacteristic=gattCharacteristic;
                    }
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_features);
        toolbar=(Toolbar)findViewById(R.id.toolbar_activity_main_features);
        setSupportActionBar(toolbar);
        initToolbar();
        navigationView=(NavigationView)findViewById(R.id.navigation_view_main_features_activity);
        initNavigationView();

        final Intent intent = getIntent();
        mDeviceName = intent.getStringExtra(EXTRAS_DEVICE_NAME);
        mDeviceAddress = intent.getStringExtra(EXTRAS_DEVICE_ADDRESS);

        mfragment=new Fragment_main_features_default();
        replaceFragment(mfragment);

        Intent gattServiceIntent = new Intent(this, BluetoothServices.class);
        bindService(gattServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    private void initToolbar(){
        //设置menu
        toolbar.inflateMenu(R.menu.menu_main_features);
        //设置menu的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int menuItemId = item.getItemId();
                if (menuItemId == R.id.main_features_menu_include_products) {


                } else if (menuItemId == R.id.main_features_menu_list_inquiry) {


                } else if (menuItemId == R.id.main_features_menu_status_display) {


                } else if (menuItemId == R.id.main_features_menu_stock_control) {


                }
                return true;
            }
        });
        //设置左侧NavigationIcon点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initNavigationView(){
        navigationView.setCheckedItem(R.id.main_features_navigation_menu_item1);
        navigationView.setNavigationItemSelectedListener(new NavigationView.
                OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemId = item.getItemId();
                if(menuItemId==R.id.main_features_navigation_menu_item1){

                }
                return false;
            }
        });
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
                        mfragment=new Fragment_main_features_include_products();
                        replaceFragment(mfragment);
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

        if(mfragment!=null){
            replaceFragment(mfragment);
        }else{
            mfragment=new Fragment_main_features_default();
            replaceFragment(mfragment);
        }

        registerReceiver(mGattUpdateReceiver, makeGattUpdateIntentFilter());
        if (mBluetoothService != null) {
            final boolean result = mBluetoothService.connect(mDeviceAddress);
            Log.d(TAG, "Connect request result=" + result);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
        mBluetoothService = null;
    }

    private void updateConnectionState(final int resourceId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //mConnectionState.setText(resourceId);
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        if(mfragment!=fragment){
            mfragment=fragment;
        }
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.main_features_main_fragment_container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void receiveData(String data){
        if(mfragment instanceof BluetoothDataReceiver){
            ((BluetoothDataReceiver) mfragment).DataReceiver(data);
        }else{

        }
    }

    private static IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothServices.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothServices.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothServices.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothServices.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
}
