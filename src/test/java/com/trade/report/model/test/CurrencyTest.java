package com.trade.report.model.test;

import com.trade.report.model.Currency;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by sarathdr on 20/04/2017.
 */
public class CurrencyTest {

    public static final Set<DayOfWeek> MOCK_HOLIDAYS = Stream.of(
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY)
            .collect(Collectors.toSet());

    public  static final Currency MOCK_CURRENCY = new Currency(
            "SGP",
            MOCK_HOLIDAYS);

    @Test
    public void testCurrencyHoliday() {
        Assert.assertTrue(MOCK_CURRENCY.isHoliday(DayOfWeek.SATURDAY));
        Assert.assertFalse(MOCK_CURRENCY.isHoliday(DayOfWeek.TUESDAY));
    }
}
