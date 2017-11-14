package com.example.y_chen.checkoutcounterapp.Services.DataBaseService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.y_chen.checkoutcounterapp.Services.Model.Commodity;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by Y_Chen on 2017/11/13.
 */

public class MyDBService extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名字
     */
    private static final String DB_NAME = "test.db";
    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 1;

    /**
     * 用来存放Dao的地图
     */
    private Map<String, Dao> daos = new HashMap<String, Dao>();



    private static MyDBService instance;


    /**
     * 获取单例
     * @param context
     * @return
     */
    public static synchronized MyDBService getService(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (MyDBService.class) {
                if (instance == null) {
                    instance = new MyDBService(context);
                }
            }
        }
        return instance;
    }


    /**
     * 构造方法
     * @param context
     */
    public MyDBService(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 这里创建表
     */
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        // 创建表
        try {
            TableUtils.createTable(connectionSource, Commodity.class);
            //TableUtils.createTable(connectionSource, Photographer.class);
            //TableUtils.createTable(connectionSource, Theme.class);
            //TableUtils.createTable(connectionSource, Img.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里进行更新表操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        try
        {
            TableUtils.dropTable(connectionSource, Commodity.class, true);
            //TableUtils.dropTable(connectionSource, Photographer.class, true);
            //TableUtils.dropTable(connectionSource, Theme.class, true);
            //TableUtils.dropTable(connectionSource, Img.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过类来获得指定的Dao
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }


    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
        for (String key : daos.keySet()) {
            Dao dao = daos.get(key);
            dao = null;
        }
    }

}
