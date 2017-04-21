package com.trade;

import com.trade.report.dao.TradeDao;
import com.trade.report.dao.impl.TradeDaoImpl;
import com.trade.report.model.Trade;

/**
 * Created by sarathdr on 21/04/2017.
 */
public class Application {

    public static void main(String[] args) {

        TradeDao tradeDao = new TradeDaoImpl();


        System.out.println("=================  Daily Outgoing ========================================");
        tradeDao
                .getDailyTradeAmountUsdByAction(Trade.Action.BUY)
                .forEach((k, v) -> System.out.println("Date : " + k + " Total Amount : " + v));

        System.out.println("=================  Daily Incoming ========================================");

        tradeDao
                .getDailyTradeAmountUsdByAction(Trade.Action.SELL)
                .forEach((k, v) -> System.out.println("Date : " + k + " Total Amount : " + v));

        System.out.println("=================  Ranking Outgoing ========================================");
        tradeDao
                .listRanking(Trade.Action.BUY)
                .forEach( item -> System.out.println("Rank: "+ item.getRank() + " Entity: " + item.getEntity() + " Amount: " + item.getTotalAmountInUsd() ));

        System.out.println("=================  Ranking Incoming ========================================");
        tradeDao
                .listRanking(Trade.Action.SELL)
                .forEach( item -> System.out.println("Rank: "+ item.getRank() + " Entity: " + item.getEntity() + " Amount: " + item.getTotalAmountInUsd() ));

    }
}
