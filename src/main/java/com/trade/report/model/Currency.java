package com.trade.report.model;

import java.time.DayOfWeek;
import java.util.Set;

/**
 * Created by sarathdr on 20/04/2017.
 */
public class Currency {

    private String symbol;
    private Set<DayOfWeek> holidays;

    public Currency(final String symbol, final Set<DayOfWeek> holidays) {
        this.symbol = symbol;
        this.holidays = holidays;
    }

    public boolean isHoliday(final DayOfWeek dayOfWeek) {
        return this.holidays.stream()
                .anyMatch(day -> day == dayOfWeek);
    }
}
