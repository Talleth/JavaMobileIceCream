package com.example.icecream;

import java.util.ArrayList;

import android.content.Context;
import android.widget.Toast;

public class OrderItemManager {
    private ArrayList<OrderItem> _orderItems;
    private final Context _context;

    public OrderItemManager(Context context) {
        _context = context;
    }

    public void add_orderItem(OrderItem orderItem) {
        try {
            OrderDatabase orderDatabase = new OrderDatabase(_context);
            orderDatabase.addOrderItem(orderItem);
        }
        catch (Exception e) {
            Toast.makeText(_context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<OrderItem> get_orderItems() {
        ArrayList<OrderItem> result;
        OrderDatabase orderDatabase = new OrderDatabase(_context);
        result = orderDatabase.getAllItems();
        return result;
    }
}
