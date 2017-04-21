package com.trade.report.data.loader.test;

import com.trade.report.model.Trade;
import com.trade.factory.DataLoader;
import org.junit.Assert;
import org.junit.Test;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

/**
 * Created by sarathdr on 20/04/2017.
 */
public class DataLoaderTest {

    @Test
    public void testLoadData() {

        DataLoader loader = new DataLoader("src/main/extra/data_test.csv");
        List<Trade> trades = loader.loadData();

        Assert.assertEquals(
                "Record size should be 6",
                6,
                trades.size()
        );

        Optional<Trade> tradeFoo = trades.stream()
                .filter(trade -> "foo".equals(trade.getEntity()))
                .findFirst();

        Assert.assertTrue("Must find item with entity foo",tradeFoo.isPresent());
        Assert.assertEquals(
                " Entity: 'foo' with action 'BUY' ",
                Trade.Action.BUY,
                tradeFoo.get().getAction()
        );

        Assert.assertEquals(
                " Entity: 'foo' with units '10' ",
                10,
                tradeFoo.get().getUnits()
        );

        Assert.assertEquals(
                " Entity: 'foo' with agreed Fx '1' ",
                1,
                tradeFoo.get().getAgreedFx(),
                0
        );

        Assert.assertEquals(
                " Entity: 'foo' with unit price Fx '10' ",
                10,
                tradeFoo.get().getPricePerUnit(),
                0
        );

        Assert.assertTrue(
                " Entity: 'foo' currency SGP holiday SUNDAY ",
                tradeFoo.get().getCurrency().isHoliday( DayOfWeek.SUNDAY)
        );
    }
}
