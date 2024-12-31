package com.example.icecream;

import android.widget.Toast;

import java.text.NumberFormat;

public class OrderItemUtilities {
    public static double get_orderItemSum (OrderItem orderItem) {
        double currentUnformattedTotal = 0.0;

        if (orderItem.get_almondsChecked()) {
            currentUnformattedTotal += Prices.ALMONDS;
        }
        if (orderItem.get_browniesChecked()) {
            currentUnformattedTotal += Prices.BROWNIE;
        }
        if (orderItem.get_gummyBearsChecked()) {
            currentUnformattedTotal += Prices.GUMMY_BEARS;
        }
        if (orderItem.get_mAndMsChecked()) {
            currentUnformattedTotal += Prices.M_AND_M;
        }
        if (orderItem.get_marshmallowsChecked()) {
            currentUnformattedTotal +=  + Prices.MARSHMALLOWS;
        }
        if (orderItem.get_oreosChecked()) {
            currentUnformattedTotal += + Prices.OREOS;
        }
        if (orderItem.get_peanutsChecked()) {
            currentUnformattedTotal += Prices.PEANUTS;
        }
        if (orderItem.get_strawberriesChecked()) {
            currentUnformattedTotal += Prices.STRAWBERRIES;
        }
        switch (orderItem.get_hotFudgeOunzes()) {
            case 1: { currentUnformattedTotal += Prices.HOT_FUDGE_ONE_OUNCE; break; }
            case 2: { currentUnformattedTotal += Prices.HOT_FUDGE_TWO_OUNCES; break; }
            case 3: { currentUnformattedTotal += Prices.HOT_FUDGE_THREE_OUNCES; break; }
        }
        switch (orderItem.get_size()) {
            case Small: {currentUnformattedTotal += Prices.SIZE_SMALL; break;}
            case Medium: {currentUnformattedTotal += Prices.SIZE_MEDIUM; break;}
            case Large: {currentUnformattedTotal += Prices.SIZE_LARGE; break;}
        }

        return currentUnformattedTotal;
    }

    public static String get_formattedOrderItemSum(OrderItem orderItem) {
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return fmt.format(OrderItemUtilities.get_orderItemSum(orderItem));
    }
}
