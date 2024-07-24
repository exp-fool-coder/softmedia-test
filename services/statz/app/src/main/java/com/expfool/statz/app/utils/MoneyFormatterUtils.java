package com.expfool.statz.app.utils;

import java.text.NumberFormat;

public class MoneyFormatterUtils {

    public static String formatDefaultRubble(int amount) {
        NumberFormat formatter = NumberFormat.getNumberInstance();
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        return formatter.format(amount / 100.0) + " руб.";
    }
}
