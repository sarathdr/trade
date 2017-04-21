package com.trade.report.model;

import java.time.LocalDate;


/**
 * Created by sarathdr on 20/04/2017.
 */
public class Trade {

    public enum Action {

        BUY("B"),
        SELL("S");

        private String value;

        Action(final String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        /**
         * Get enum from custom string value
         *
         * @param value the string value
         * @return the enum
         */
        public static Action getEnum(final String value) {

            for (Action action : values()) {
                if (action.getValue().equalsIgnoreCase(value)) {
                    return action;
                }
            }

            throw new IllegalArgumentException("Action not found for the value" + value);
        }
    }

    private int units;
    private double pricePerUnit;
    private Action action;
    private String entity;
    private LocalDate instructionDate;
    private LocalDate actualSettlementDate;
    private double agreedFx;
    private Currency currency;

    public Trade() {
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(LocalDate instructionDate) {
        this.instructionDate = instructionDate;
    }


    public void setActualSettlementDate(LocalDate actualSettlementDate) {
        this.actualSettlementDate = actualSettlementDate;
    }

    public LocalDate getActualSettlementDate() {
        return actualSettlementDate;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public double getTotalAmountInUsd() {
        // TODO this can be cached
        return this.pricePerUnit * this.units * this.agreedFx;
    }


    /**
     * Sets the proposed settlement date based
     * on holiday
     *
     * @return the proposed settlement date
     */
    public LocalDate getProposedSettlementDate() {

        // TODO this can be cached
        LocalDate proposedDate = this.actualSettlementDate;
        while (this.currency.isHoliday(proposedDate.getDayOfWeek())) {
            proposedDate = proposedDate.plusDays(1);
        }

        return proposedDate;
    }

}
