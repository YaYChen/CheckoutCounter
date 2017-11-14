package com.example.y_chen.checkoutcounterapp.Services.Model;

/**
 *  ShoppingList
 * Created by Y_Chen on 2017/11/14.
 */

import java.io.Serializable;
import java.sql.Blob;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "ShoppingList")
public class ShoppingList implements Serializable {
    @DatabaseField(generatedId = true)
    public int Id;
    @DatabaseField(canBeNull = false)
    public String CreateTime;
    @DatabaseField(canBeNull = false)
    public double TotalPrice;
    @DatabaseField
    public Blob Img;
    @DatabaseField
    public String Description;
    @DatabaseField
    public String Detail;
    @DatabaseField
    public String Remark;

    // 一个套餐可以对应多个主题
    @ForeignCollectionField(eager = true) // 必须
    public ForeignCollection<Commodity> commodities;


    // 外部对象，一个套餐只对应一个摄影师，一个摄影师可以对应多个套餐
    //@DatabaseField(foreign = true)
    //public ShoppingList shoppingList;

    @Override
    public String toString() {
        return "Package [" +
                "id=" + Id + ", " +
                "createTime=" + CreateTime + ", " +
                "totalPrice=" + TotalPrice + ", " +
                "Img=" + Img + "," +
                " description=" + Description + ", " +
                "detail=" + Detail + ", " +
                "remark=" + Remark + "" +
                "]";
    }
}
