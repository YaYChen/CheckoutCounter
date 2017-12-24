package com.example.y_chen.checkoutcounterapp.Services.BuletoothService;

/**
 * Created by Y_Chen on 2017/11/13.
 */
import java.util.HashMap;
public class GattAttributesMap {
    private HashMap<String, String> attributes = new HashMap();

    private static GattAttributesMap instance;
    private GattAttributesMap (){}
    public static synchronized GattAttributesMap getInstance() {
        if (instance == null) {
            instance = new GattAttributesMap();
        }
        return instance;
    }

    public String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }

    public boolean checkUUID(String uuid){
        if(attributes.get(uuid)!=null){
            return true;
        }else{
            return false;
        }
    }

    public String getName(String uuid){
        String name = attributes.get(uuid);
        return name == null ? "Unknown" : name;
    }


}
