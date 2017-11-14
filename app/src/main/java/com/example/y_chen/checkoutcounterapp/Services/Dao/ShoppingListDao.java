package com.example.y_chen.checkoutcounterapp.Services.Dao;

import android.content.Context;

import com.example.y_chen.checkoutcounterapp.Services.DataBaseService.MyDBService;
import com.example.y_chen.checkoutcounterapp.Services.Model.ShoppingList;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Define data access object, add, delete, modify the specified table
 * Created by Y_Chen on 2017/11/14.
 */

public class ShoppingListDao {
    private Dao<ShoppingList, Integer> ShoppingListDao;
    private MyDBService dbService;

    /**
     * Construction method
     * Get the database help class instance,
     * by passing in the Class object to get the corresponding Dao
     * @param context
     */
    public ShoppingListDao(Context context) {
        try {
            dbService = MyDBService.getService(context);
            ShoppingListDao = dbService.getDao(ShoppingList.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a record
     * @param shoppingList
     */
    public void add(ShoppingList shoppingList) {
        try {
            ShoppingListDao.create(shoppingList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete a record
     * @param shoppingList
     */
    public void delete(ShoppingList shoppingList) {
        try {
            ShoppingListDao.delete(shoppingList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Update a record
     * @param shoppingList
     */
    public void update(ShoppingList shoppingList) {
        try {
            ShoppingListDao.update(shoppingList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Query a record
     * @param id
     * @return
     */
    public ShoppingList queryForId(int id) {
        ShoppingList shoppingList = null;
        try {
            shoppingList = ShoppingListDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingList;
    }


    /**
     * Query all record
     * @return
     */
    public List<ShoppingList> queryForAll() {
        List<ShoppingList> shoppingLists = new ArrayList<>();
        try {
            shoppingLists = ShoppingListDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shoppingLists;
    }
}
