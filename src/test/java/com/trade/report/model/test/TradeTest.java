package com.trade.report.model.test;

import com.trade.report.helper.DateHelper;
import com.trade.report.model.Trade;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

/**
 * Created by sarathdr on 20/04/2017.
 */
public class TradeTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testTradeValidEnumValues() {
        Assert.assertEquals("B", Trade.Action.BUY.getValue());
        Assert.assertEquals("S" , Trade.Action.SELL.getValue());
        Assert.assertEquals(Trade.Action.BUY, Trade.Action.getEnum("B"));
        Assert.assertEquals(Trade.Action.SELL, Trade.Action.getEnum("S"));
    }

    @Test
    public void testTradeInValidEnumValue() {
        exception.expect(IllegalArgumentException.class);
        Trade.Action.getEnum("FFF");
    }

    @Test
    public void testTradeEmptyEnumValue() {
        exception.expect(IllegalArgumentException.class);
        Trade.Action.getEnum("");
    }

    @Test
    public void testTradeNullEnumValue() {
        exception.expect(IllegalArgumentException.class);
        Trade.Action.getEnum(null);
    }

    @Test
    public void testTradeProposedSettlementDate() {

        Trade trade  = new Trade();

        trade.setEntity("foo");
        trade.setCurrency(CurrencyTest.MOCK_CURRENCY);
        trade.setActualSettlementDate(
                // Saturday - Holiday for currency 'SGP'
                DateHelper.formatStringToLocalDate("14 Jan 2017")
        );

        Assert.assertTrue(
                DateHelper.formatStringToLocalDate("16 Jan 2017")
                .equals(trade.getProposedSettlementDate())
        );

        Assert.assertFalse(
                DateHelper.formatStringToLocalDate("14 Jan 2017")
                        .equals(trade.getProposedSettlementDate())
        );

        trade.setActualSettlementDate(
                // Sunday - Holiday for currency 'SGP'
                DateHelper.formatStringToLocalDate("11 Jan 2017")
        );

        System.out.println(trade.getProposedSettlementDate());

        Assert.assertTrue(
                DateHelper.formatStringToLocalDate("11 Jan 2017")
                        .equals(trade.getProposedSettlementDate())
        );

        trade.setActualSettlementDate(
                // Sunday - Holiday for currency 'SGP'
                DateHelper.formatStringToLocalDate("15 Jan 2017")
        );

        Assert.assertTrue(
                DateHelper.formatStringToLocalDate("16 Jan 2017")
                        .equals(trade.getProposedSettlementDate())
        );

    }

    @Test
    public void testTradeTotalAmountInUsd() {
        Trade trade  = new Trade();

        trade.setEntity("foo");
        trade.setAction(Trade.Action.BUY);
        trade.setUnits(10);
        trade.setPricePerUnit(5);
        trade.setAgreedFx(2);
        trade.setCurrency(CurrencyTest.MOCK_CURRENCY);

        Assert.assertEquals( 100, trade.getTotalAmountInUsd(), 0);
    }
}
