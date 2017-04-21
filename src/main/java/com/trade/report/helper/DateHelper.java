package com.trade.report.helper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by sarathdr on 20/04/2017.
 */
public class DateHelper {

    private static DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("d MMM uuuu");

    public  static LocalDate formatStringToLocalDate(final String dateString) {
        return LocalDate.parse(dateString,FORMAT);
    }
}
