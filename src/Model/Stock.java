package Model;

import java.time.LocalDate;

public class Stock {
    private String ticker;
    private String name;
    private String sector;
    private double price;
    private String currency;
    private String rating;
    private double dividendYield;
    private String market;
    private LocalDate lastUpdated;


    public Stock(String ticker, String name, String sector, double price, String currency, String rating, double dividendYield, String market, LocalDate lastUpdated) {
        this.ticker = ticker;
        this.name = name;
        this.sector = sector;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.dividendYield = dividendYield;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }

    public String getTicker() {
        return ticker;
    }

    public String getName() {
        return name;
    }

    public String getSector() {
        return sector;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRating() {
        return rating;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", rating='" + rating + '\'' +
                ", dividendYield=" + dividendYield +
                ", market='" + market + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }

    public String getMarket() {
        return market;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
}
