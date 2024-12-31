package com.example.icecream;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderItem implements Serializable {
    private static Random rand = new Random();
    private long _orderId;
    private LocalDateTime _orderDateTime;
    private boolean _peanutsChecked;
    private boolean _almondsChecked;
    private boolean _strawberriesChecked;
    private boolean _gummyBearsChecked;
    private boolean _mAndMsChecked;
    private boolean _browniesChecked;
    private boolean _oreosChecked;
    private boolean _marshmallowsChecked;
    private int _hotFudgeOunzes;
    private Flavor _flavor;
    private Size _size;

    enum Flavor { Vanilla, Chocolate, Strawberry }
    enum Size { Small, Medium, Large }

    public OrderItem() {
        _orderDateTime = LocalDateTime.now();
        _orderId = rand.nextInt(1000000);
        set_defaultValues();
    }

    // Getter functions *********************************************/
    public boolean get_peanutsChecked() { return _peanutsChecked; }
    public boolean get_almondsChecked() { return _almondsChecked; }
    public boolean get_strawberriesChecked() { return _strawberriesChecked; }
    public boolean get_gummyBearsChecked() { return _gummyBearsChecked; }
    public boolean get_mAndMsChecked() { return _mAndMsChecked; }
    public boolean get_browniesChecked() { return _browniesChecked; }
    public boolean get_oreosChecked() { return _oreosChecked; }
    public boolean get_marshmallowsChecked() {return _marshmallowsChecked; }
    public int get_hotFudgeOunzes() {
        return _hotFudgeOunzes;
    }
    public Flavor get_flavor() { return _flavor; }
    public Size get_size() { return _size; }
    public LocalDateTime get_orderDateTime() { return _orderDateTime; }
    public long get_orderId() { return _orderId; }

    // Setter functions *********************************************/
    public void set_peanutsChecked(boolean peanutsChecked) { _peanutsChecked = peanutsChecked; }
    public void set_almondsChecked(boolean almondsChecked) { _almondsChecked = almondsChecked; }
    public void set_strawberriesChecked(boolean strawberriesChecked) { _strawberriesChecked = strawberriesChecked; }
    public void set_gummyBearsChecked(boolean gummyBearsChecked) { _gummyBearsChecked = gummyBearsChecked; }
    public void set_mAndMsChecked(boolean mAndMsChecked) { _mAndMsChecked = mAndMsChecked; }
    public void set_browniesChecked(boolean browniesChecked) { _browniesChecked = browniesChecked; }
    public void set_oreosChecked(boolean oreosChecked) {
        _oreosChecked = oreosChecked;
    }
    public void set_marshmallowsChecked(boolean marshmallowsChecked) { _marshmallowsChecked = marshmallowsChecked; }
    public void set_hotFudgeOunzes(int hotFudgeOunzes) {
        _hotFudgeOunzes = hotFudgeOunzes;
    }
    public void set_flavor(Flavor flavor) { _flavor = flavor; }
    public void set_size(Size size) { _size = size; }
    public void set_orderId(long orderId) { _orderId = orderId; }
    public void set_orderDateTime(LocalDateTime orderDateTime) { _orderDateTime = orderDateTime; }

    public void set_defaultValues() {
        set_peanutsChecked(false);
        set_almondsChecked(false);
        set_strawberriesChecked(false);
        set_gummyBearsChecked(false);
        set_mAndMsChecked(false);
        set_browniesChecked(false);
        set_oreosChecked(false);
        set_marshmallowsChecked(false);
        set_flavor(Flavor.Vanilla);
        set_size(Size.Small);
        set_hotFudgeOunzes(1);
    }

    public void set_selectAllOptions() {
        set_peanutsChecked(true);
        set_almondsChecked(true);
        set_strawberriesChecked(true);
        set_gummyBearsChecked(true);
        set_mAndMsChecked(true);
        set_browniesChecked(true);
        set_oreosChecked(true);
        set_marshmallowsChecked(true);
        set_flavor(Flavor.Vanilla);
        set_size(Size.Large);
        set_hotFudgeOunzes(3);
    }

    @NonNull
    @Override
    public String toString() {
        String result = "\n";

        result += "Order Id: " + get_orderId() + "\n";
        result += "Date: " + get_orderDateTime().format(DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a")) + "\n";
        result += "Flavor: " + get_flavor().toString() + "\n";
        result += "Size: " + get_size().toString() + "\n";
        result += "Cost: " + OrderItemUtilities.get_formattedOrderItemSum(this) + "\n";

        return result;
    }
}
