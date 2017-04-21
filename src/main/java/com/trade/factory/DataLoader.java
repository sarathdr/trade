package com.trade.factory;

import com.trade.report.helper.DateHelper;
import com.trade.report.model.Currency;
import com.trade.report.model.Trade;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class to load the data from a  csv file
 */
public class DataLoader {

    private static final String SEPARATOR = ",";
    private static final String FILE_NAME = "src/main/extra/data.csv";

    private String fileName;

    public DataLoader() {
        this.fileName = FILE_NAME;
    }

    public DataLoader(String fileName) {
        this.fileName = fileName;
    }

    private static final Set<DayOfWeek> HOLIDAYS = Stream.of(
            DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY)
            .collect(Collectors.toSet());

    private static final Set<DayOfWeek> AED_SAR_HOLIDAYS = Stream.of(
            DayOfWeek.FRIDAY,
            DayOfWeek.SATURDAY)
            .collect(Collectors.toSet());


    public List<Trade> loadData() {

        try (BufferedReader reader = new BufferedReader(new FileReader(this.fileName))) {
            return reader.lines()
                    .parallel()
                    .map(line -> processLine(line))
                    .filter(trade -> trade != null)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    Trade processLine(String line) {
        String[] values = line.split(SEPARATOR);

        // Read only valid data, skips invalid data
        if (values.length == 8) {

            final Trade trade = new Trade();

            trade.setEntity(values[0].trim());
            trade.setAction(Trade.Action.getEnum(values[1].trim()));
            trade.setAgreedFx(Double.parseDouble(values[2].trim()));
            final String currencySymbol = values[3].trim();
            Set<DayOfWeek> holidays =
                    ("AED".equals(currencySymbol) || "SAR".equals(currencySymbol))
                            ? AED_SAR_HOLIDAYS
                            : HOLIDAYS;

            trade.setCurrency(new Currency(currencySymbol, holidays));
            trade.setInstructionDate(
                    DateHelper.formatStringToLocalDate(values[4].trim())
            );

            trade.setActualSettlementDate(
                    DateHelper.formatStringToLocalDate(values[5].trim())
            );

            trade.setUnits(Integer.parseInt(values[6].trim()));
            trade.setPricePerUnit(Double.parseDouble(values[7].trim()));

            return trade;
        }

        return null;
    }
}
