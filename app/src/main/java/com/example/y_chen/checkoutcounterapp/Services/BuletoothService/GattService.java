package com.example.y_chen.checkoutcounterapp.Services.BuletoothService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Y_Chen on 2017/12/24.
 */

public class GattService {
    public String UUID;
    public String Name;
    public List<GattCharacteristic> Characteristics=new ArrayList<>();

    public GattService(String uuid,String name){
        UUID=uuid;
        Name=name;
    }

    public void addCharacteristic(GattCharacteristic gattCharacteristic){
        Characteristics.add(gattCharacteristic);
    }
}
