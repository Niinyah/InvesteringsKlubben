package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Portfolio {
    private final String name;
    private final double balance;
    private final double investmentValue;
    private final double equity;
    private final List<TransactionLine> history;
    private final HashMap<String, Integer> stocks;
    private final List<PortfolioLine> portfolioLines = new ArrayList<>();
    private final String currency;

    public Portfolio(String name, double balance, double investmentValue, double equity, List<TransactionLine> history, HashMap<String, Integer> stocks, String currency) {
        this.name = name;
        this.balance = balance;
        this.investmentValue = investmentValue;
        this.equity = equity;
        this.history = history;
        this.stocks = stocks;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public double getInvestmentValue() {
        return investmentValue;
    }

    public double getEquity() {
        return equity;
    }

    public String getCurrency(){
        return currency;
    }

    public List<TransactionLine> getHistory() {
        return history;
    }

    public HashMap<String, Integer> getStocks() {
        return stocks;
    }

    public void setPortfolioLines(PortfolioLine portfolioLine) {
        portfolioLines.add(portfolioLine);
    }

    public List<PortfolioLine> getPortfolioLines() { return portfolioLines; }

}
