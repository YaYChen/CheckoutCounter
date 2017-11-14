package com.example.y_chen.checkoutcounterapp.Services.Dao;

/**
 * Define data access object, add, delete, modify the specified table
 * Created by Y_Chen on 2017/11/14.
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.y_chen.checkoutcounterapp.Services.DataBaseService.MyDBService;
import com.example.y_chen.checkoutcounterapp.Services.Model.Commodity;
import com.j256.ormlite.dao.Dao;


public class CommodityDao {
    private Dao<Commodity, Integer> commodityDao;
    private MyDBService dbService;

    /**
     * Construction method
     * Get the database help class instance,
     * by passing in the Class object to get the corresponding Dao
     * @param context
     */
    public CommodityDao(Context context) {
        try {
            dbService = MyDBService.getService(context);
            commodityDao = dbService.getDao(Commodity.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a record
     * @param commodity
     */
    public void add(Commodity commodity) {
        try {
            commodityDao.create(commodity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a record
     * @param commodity
     */
    public void delete(Commodity commodity) {
        try {
            commodityDao.delete(commodity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update a record
     * @param commodity
     */
    public void update(Commodity commodity) {
        try {
            commodityDao.update(commodity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Query a record
     * @param id
     * @return
     */
    public Commodity queryForId(int id) {
        Commodity commodity = null;
        try {
            commodity = commodityDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodity;
    }


    /**
     * Query all record
     * @return
     */
    public List<Commodity> queryForAll() {
        List<Commodity> commodities = new ArrayList<>();
        try {
            commodities = commodityDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commodities;
    }

}
