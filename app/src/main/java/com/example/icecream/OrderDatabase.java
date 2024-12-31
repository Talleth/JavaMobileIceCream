package com.example.icecream;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderDatabase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "OrderItems.db";
    private static final String CREATE_TABLE_ITEMS = "CREATE TABLE order_items ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "OrderItemId TEXT,"
            + "OrderDateTime TEXT,"
            + "BrowniesChecked TEXT,"
            + "AlmondsChecked TEXT,"
            + "MandMsChecked TEXT,"
            + "MarshmallowsChecked TEXT,"
            + "OreosChecked TEXT,"
            + "PeanutsChecked TEXT,"
            + "StrawberriesChecked TEXT,"
            + "GummyBearsChecked TEXT,"
            + "HotFudgeOunces TEXT,"
            + "Flavor TEXT,"
            + "Size TEXT)";

    private Context _context;

    public OrderDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    public void addOrderItem(OrderItem orderItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OrderItemId", orderItem.get_orderId());
        values.put("OrderDateTime", orderItem.get_orderDateTime().toString());
        values.put("BrowniesChecked", orderItem.get_browniesChecked());
        values.put("AlmondsChecked", orderItem.get_almondsChecked());
        values.put("MandMsChecked", orderItem.get_mAndMsChecked());
        values.put("MarshmallowsChecked", orderItem.get_marshmallowsChecked());
        values.put("OreosChecked", orderItem.get_oreosChecked());
        values.put("PeanutsChecked", orderItem.get_peanutsChecked());
        values.put("StrawberriesChecked", orderItem.get_strawberriesChecked());
        values.put("GummyBearsChecked", orderItem.get_gummyBearsChecked());
        values.put("HotFudgeOunces", orderItem.get_hotFudgeOunzes());
        values.put("Flavor", orderItem.get_flavor().ordinal());
        values.put("Size", orderItem.get_size().ordinal());

        db.insert("order_items", null, values);
        db.close();
    }

    @SuppressLint("Range")
    public ArrayList<OrderItem> getAllItems() {
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        String selectQuery = "SELECT * FROM order_items";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                OrderItem orderItem = new OrderItem();

                orderItem.set_orderId(cursor.getInt(cursor.getColumnIndex("OrderItemId")));
                orderItem.set_orderDateTime(LocalDateTime.parse(cursor.getString(cursor.getColumnIndex("OrderDateTime"))));
                orderItem.set_size(OrderItem.Size.values()[cursor.getInt(cursor.getColumnIndex("Size"))]);
                orderItem.set_flavor(OrderItem.Flavor.values()[cursor.getInt(cursor.getColumnIndex("Flavor"))]);
                orderItem.set_oreosChecked(cursor.getInt(cursor.getColumnIndex("OreosChecked")) == 1);
                orderItem.set_marshmallowsChecked(cursor.getInt(cursor.getColumnIndex("MarshmallowsChecked")) == 1);
                orderItem.set_browniesChecked(cursor.getInt(cursor.getColumnIndex("BrowniesChecked")) == 1);
                orderItem.set_mAndMsChecked(cursor.getInt(cursor.getColumnIndex("MandMsChecked")) == 1);
                orderItem.set_gummyBearsChecked(cursor.getInt(cursor.getColumnIndex("GummyBearsChecked")) == 1);
                orderItem.set_strawberriesChecked(cursor.getInt(cursor.getColumnIndex("StrawberriesChecked")) == 1);
                orderItem.set_peanutsChecked(cursor.getInt(cursor.getColumnIndex("PeanutsChecked")) == 1);
                orderItem.set_almondsChecked(cursor.getInt(cursor.getColumnIndex("AlmondsChecked")) == 1);
                orderItem.set_hotFudgeOunzes(cursor.getInt(cursor.getColumnIndex("HotFudgeOunces")));

                orderItems.add(orderItem);
            }
            while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return item list
        return orderItems;
    }

    public void ClearData() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM order_items");
        db.execSQL("VACUUM");

        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE_ITEMS);
        }
        catch (Exception e) {
            Toast.makeText(this._context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
