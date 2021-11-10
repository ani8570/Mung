package me.Mung.util;

import ch.qos.logback.core.util.CachingDateFormatter;

import java.text.DecimalFormat;

public class Textformat {
    private static DecimalFormat formatter = new DecimalFormat("#,##0.00");
    public static String D2S(Double d) {
        return formatter.format(d);
    }
}
