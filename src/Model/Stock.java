package Model;

import Service.ICurrencyService;

import java.time.LocalDate;

public class Stock {
    private final String ticker;
    private final String name;
    private final String sector;
    private double price;
    private String currency;
    private final String rating;
    private final double dividendYield;
    private final String market;
    private final LocalDate lastUpdated;


    public Stock(String ticker, String name, String sector,
                 double price, String currency, String rating,
                 double dividendYield, String market,
                 LocalDate lastUpdated) {
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

    public void setPrice(double price){
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency){
        this.currency = currency;
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
