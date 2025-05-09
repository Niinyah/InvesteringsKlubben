package Model;

import java.time.LocalDate;

public class Currency {
    private final String currency;
    private final String quote;
    private final double rate;
    private final LocalDate lastUpdated;

    public Currency(String currency, String quote, double rate, LocalDate lastUpdated) {
        this.currency = currency;
        this.quote = quote;
        this.rate = rate;
        this.lastUpdated = lastUpdated;
    }

    public String getCurrency() {
        return currency;
    }

    public String getQuote() {
        return quote;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return currency;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
}
