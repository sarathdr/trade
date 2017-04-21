package com.trade.report.model;

/**
 * Created by sarathdr on 21/04/2017.
 */
public class TradeRank {

    private String entity;
    private int rank;
    private double totalAmountInUsd;

    public TradeRank(String entity, int rank, double totalAmountInUsd) {
        this.entity = entity;
        this.rank = rank;
        this.totalAmountInUsd = totalAmountInUsd;
    }


    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getTotalAmountInUsd() {
        return totalAmountInUsd;
    }

    public void setTotalAmountInUsd(double totalAmountInUsd) {
        this.totalAmountInUsd = totalAmountInUsd;
    }
}
