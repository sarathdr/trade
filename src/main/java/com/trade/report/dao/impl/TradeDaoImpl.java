package com.trade.report.dao.impl;

import com.trade.factory.DataLoader;
import com.trade.report.model.Trade;
import com.trade.report.dao.TradeDao;
import com.trade.report.model.TradeRank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by sarathdr on 20/04/2017.
 */
public class TradeDaoImpl implements TradeDao {

    private DataLoader loader;
    private List<Trade> trades;

    public TradeDaoImpl() {
        this.loader = new DataLoader();
        this.trades = this.loader.loadData();
    }

    public TradeDaoImpl(DataLoader loader) {
        this.loader = loader;
        this.trades = this.loader.loadData();
    }

    @Override
    public Map<String, Double> getDailyTradeAmountUsdByAction(Trade.Action action) {

        return this.trades
                .stream()
                .filter(trade -> trade.getAction() == action)
                .collect(
                        Collectors.groupingBy(
                                trade -> trade.getProposedSettlementDate().toString(),
                                Collectors.summingDouble(trade -> trade.getTotalAmountInUsd())
                        ));
    }

    @Override
    public List<TradeRank> listRanking(Trade.Action action) {

        List<TradeRank> rankList = new ArrayList<>();
        this.trades
                .stream()
                .filter(trade -> trade.getAction() == action)
                .sorted(Comparator.comparingDouble(Trade::getTotalAmountInUsd).reversed())
                .forEach(trade -> {
                    int rank = 1;
                    double amountInUsd = trade.getTotalAmountInUsd();

                    if (!rankList.isEmpty()) {

                        // Find last inserted item
                        TradeRank previousItem = rankList.get(rankList.size() - 1);

                        if (Double.compare(previousItem.getTotalAmountInUsd(), amountInUsd) == 0) {
                            // If amount equal do not change rank
                            rank = previousItem.getRank();
                        } else {
                            rank = previousItem.getRank() + 1;
                        }
                    }

                    rankList.add(new TradeRank(
                            trade.getEntity(),
                            rank,
                            amountInUsd
                    ));
                });

        return rankList;
    }
}
