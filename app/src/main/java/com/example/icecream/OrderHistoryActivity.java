package com.example.icecream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    ArrayList<OrderItem> orderItems;
    ArrayList<String> orderItemStrings;
    ListView orderListView;

    Button clearHistoryButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        setTitle(R.string.order_history);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        clearHistoryButton = findViewById(R.id.clear_button);

        Intent intent = getIntent();
        orderItems = (ArrayList<OrderItem>) intent.getSerializableExtra("OrderItems");

        orderListView = findViewById(R.id.order_listView);
        orderItemStrings = new ArrayList<>();

        for (OrderItem orderItem: orderItems) {
            orderItemStrings.add(orderItem.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                orderItemStrings
        );

        orderListView.setAdapter(adapter);
    }

    public void handleClearHistory(View v) {
        try {
            OrderDatabase orderDatabase = new OrderDatabase(this);
            orderDatabase.ClearData();
            orderListView.setAdapter(null);
            Toast.makeText(OrderHistoryActivity.this, R.string.items_cleared, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(OrderHistoryActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:{
                this.finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}