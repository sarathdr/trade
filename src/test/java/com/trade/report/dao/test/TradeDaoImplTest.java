package com.trade.report.dao.test;

import com.trade.factory.DataLoader;
import com.trade.report.dao.TradeDao;
import com.trade.report.dao.impl.TradeDaoImpl;
import com.trade.report.model.Trade;
import com.trade.report.model.TradeRank;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by sarathdr on 21/04/2017.
 */
public class TradeDaoImplTest {


    @Test
    public void testDailyReport() {

        DataLoader loader = new DataLoader("src/main/extra/data_test.csv");
        TradeDao tradeDao = new TradeDaoImpl(loader);

        Map<String, Double> report = tradeDao.getDailyTradeAmountUsdByAction(Trade.Action.BUY);

        Optional<Map.Entry<String, Double>> foundEntry = report.entrySet().stream()
                .filter(map -> map.getKey().equals("2016-01-04")).findFirst();

        Assert.assertTrue(foundEntry.isPresent());
        Assert.assertEquals(325.0, foundEntry.get().getValue(), 0);

    }


    @Test
    public void testListRanking() {

        DataLoader loader = new DataLoader("src/main/extra/data_test.csv");
        TradeDao tradeDao = new TradeDaoImpl(loader);

        List<TradeRank> rankList = tradeDao.listRanking(Trade.Action.BUY);

        Optional<TradeRank> rank1 = rankList.stream()
                .filter(tradeRank -> tradeRank.getEntity().equals("mmm"))
                .findFirst();

        Assert.assertTrue(rank1.isPresent());
        Assert.assertEquals(1, rank1.get().getRank());

        List<TradeRank> rank3 = rankList.stream()
                .filter(tradeRank -> tradeRank.getRank() == 3)
                .collect(Collectors.toList());

        Assert.assertEquals(2, rank3.size());
    }
}
