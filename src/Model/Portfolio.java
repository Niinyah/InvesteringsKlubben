package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Portfolio {
    private String name;
    private double balance;
    private double investmentValue;
    private double equity;
    private List<TransactionLine> history;
    private HashMap<String, Integer> stocks;
    private List<PortfolioLine> portfolioLines = new ArrayList<>();

    public Portfolio(String name, double balance, double investmentValue, double equity, List<TransactionLine> history, HashMap<String, Integer> stocks) {
        this.name = name;
        this.balance = balance;
        this.investmentValue = investmentValue;
        this.equity = equity;
        this.history = history;
        this.stocks = stocks;
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
