package com.trade.report.dao;

import com.trade.report.model.Trade;
import com.trade.report.model.TradeRank;

import java.util.List;
import java.util.Map;

/**
 * Created by sarathdr on 20/04/2017.
 */
public interface TradeDao {

    Map<String, Double> getDailyTradeAmountUsdByAction(Trade.Action action);

    List<TradeRank> listRanking(Trade.Action action);
}
