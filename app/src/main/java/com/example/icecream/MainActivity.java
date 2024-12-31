package com.example.icecream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SeekBar hotFudgeSeekBar;
    CheckBox peanutsCheckBox, almondsCheckBox, strawberriesCheckBox, gummyBearsCheckBox;
    CheckBox mAndMsCheckBox, browniesCheckBox, oreosCheckBox, marshmallowsCheckBox;
    TextView amountTextView, hotFudgeAmountTextView;
    Spinner flavorSpinner, sizeSpinner;
    OrderItem currentOrderItem;
    OrderItemManager orderItemManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentOrderItem        = new OrderItem();
        orderItemManager        = new OrderItemManager(this);
        hotFudgeSeekBar         = findViewById(R.id.hot_fudge_seekBar);
        hotFudgeAmountTextView  = findViewById(R.id.hot_fudge_amount_textView);
        peanutsCheckBox         = findViewById(R.id.peanuts_checkBox);
        almondsCheckBox         = findViewById(R.id.almonds_checkBox);
        strawberriesCheckBox    = findViewById(R.id.strawberries_checkBox);
        gummyBearsCheckBox      = findViewById(R.id.gummy_bears_checkBox);
        mAndMsCheckBox          = findViewById(R.id.m_and_m_checkBox);
        browniesCheckBox        = findViewById(R.id.brownies_checkBox);
        oreosCheckBox           = findViewById(R.id.oreos_checkBox);
        marshmallowsCheckBox    = findViewById(R.id.marshmallows_checkBox);
        amountTextView          = findViewById(R.id.amount_textView);
        flavorSpinner           = findViewById(R.id.flavor_spinner);
        sizeSpinner             = findViewById(R.id.size_spinner);

        // HotFudge SeekBar listener interface handlers
        hotFudgeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress > 0)
                    hotFudgeAmountTextView.setText(getString(R.string.ounces, progress));
                else
                    hotFudgeAmountTextView.setText(R.string.none);
                currentOrderItem.set_hotFudgeOunzes(progress);
                updatePrice();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Stubbed out for interface
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Stubbed out for interface
            }
        });

        // Flavor Spinner listener interface handlers
        flavorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentOrderItem.set_flavor(OrderItem.Flavor.values()[position]);
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Stubbed out for interface
            }
        });

        // Size Spinner listener interface handlers
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentOrderItem.set_size(OrderItem.Size.values()[position]);
                updatePrice();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Stubbed out for interface
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_orderHistory) {
            Intent intent = new Intent(MainActivity.this, OrderHistoryActivity.class);
            intent.putExtra("OrderItems", orderItemManager.get_orderItems());
            startActivity(intent);
            return true;
        }
        if (id == R.id.menu_about) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updatePrice() {
        try {
            amountTextView.setText(OrderItemUtilities.get_formattedOrderItemSum(currentOrderItem));
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void handleChecked(View v) {
        if (v.getId() == R.id.peanuts_checkBox) { currentOrderItem.set_peanutsChecked(peanutsCheckBox.isChecked()); }
        else if (v.getId() == R.id.almonds_checkBox) { currentOrderItem.set_almondsChecked(almondsCheckBox.isChecked()); }
        else if (v.getId() == R.id.strawberries_checkBox) { currentOrderItem.set_strawberriesChecked(strawberriesCheckBox.isChecked()); }
        else if (v.getId() == R.id.gummy_bears_checkBox) { currentOrderItem.set_gummyBearsChecked(gummyBearsCheckBox.isChecked()); }
        else if (v.getId() == R.id.m_and_m_checkBox) { currentOrderItem.set_mAndMsChecked(mAndMsCheckBox.isChecked()); }
        else if (v.getId() == R.id.brownies_checkBox) { currentOrderItem.set_browniesChecked(browniesCheckBox.isChecked()); }
        else if (v.getId() == R.id.oreos_checkBox) { currentOrderItem.set_oreosChecked(oreosCheckBox.isChecked()); }
        else if (v.getId() == R.id.marshmallows_checkBox) { currentOrderItem.set_marshmallowsChecked(marshmallowsCheckBox.isChecked()); }
        updatePrice();
    }

    public void handleTheWorks(View v) {
        peanutsCheckBox.setChecked(true);
        almondsCheckBox.setChecked(true);
        strawberriesCheckBox.setChecked(true);
        gummyBearsCheckBox.setChecked(true);
        mAndMsCheckBox.setChecked(true);
        browniesCheckBox.setChecked(true);
        oreosCheckBox.setChecked(true);
        marshmallowsCheckBox.setChecked(true);
        hotFudgeSeekBar.setProgress(3);
        sizeSpinner.setSelection(OrderItem.Size.Large.ordinal());

        currentOrderItem.set_selectAllOptions();
        updatePrice();
    }

    public void handleReset(View v) {
        peanutsCheckBox.setChecked(false);
        almondsCheckBox.setChecked(false);
        strawberriesCheckBox.setChecked(false);
        gummyBearsCheckBox.setChecked(false);
        mAndMsCheckBox.setChecked(false);
        browniesCheckBox.setChecked(false);
        oreosCheckBox.setChecked(false);
        marshmallowsCheckBox.setChecked(false);
        currentOrderItem = new OrderItem();
        hotFudgeSeekBar.setProgress(1);
        flavorSpinner.setSelection(OrderItem.Flavor.Vanilla.ordinal());
        sizeSpinner.setSelection(OrderItem.Size.Small.ordinal());

        currentOrderItem.set_defaultValues();
        updatePrice();
    }
    public void handleOrder(View v) {
        try {
            orderItemManager.add_orderItem(currentOrderItem);
            handleReset(v);
            currentOrderItem = new OrderItem();
            Toast.makeText(MainActivity.this, R.string.order_completed, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}