package Service;

import Comparators.InvestmentComparator;
import Comparators.SectorComparator;
import Model.Portfolio;
import Model.PortfolioLine;
import Model.Stock;
import Model.TransactionLine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PortfolioService implements IPortfolioService {

    private ITransactionService transactionService;
    private IStockMarketService stockMarketService;
    private IUserService userService;

    public PortfolioService(ITransactionService transactionService, IStockMarketService stockMarketService, IUserService userService) {
        this.transactionService = transactionService;
        this.stockMarketService = stockMarketService;
        this.userService = userService;
    }


    @Override
    public Portfolio createPortfolio(String userID) {
        List<TransactionLine> userTransactionLines = transactionService.getUserTransactionHistory(userID);
        HashMap<String, Integer> stocks = new HashMap<>();

        double sold = 0;
        double bought = 0;

        for (TransactionLine transactionLine : userTransactionLines) {
            int quantity = 0;
            if (stocks.get(transactionLine.getTicker()) != null) {

                quantity = stocks.get(transactionLine.getTicker());
            }

            //Sætter key og value i hashmappen

            //lægger sammen hvor meget du har købt og solgt for, og regner prisen ud.
            if (transactionLine.getOrderType().equals("buy")) {
                stocks.put(transactionLine.getTicker(), quantity + transactionLine.getQuantity());
                bought += transactionLine.getQuantity() * transactionLine.getPrice();
            } else {
                stocks.put(transactionLine.getTicker(), quantity - transactionLine.getQuantity());
                sold += transactionLine.getQuantity() * transactionLine.getPrice();
            }
        }

        //Stående værdi af alle vores aktier
        double investmentValue = 0;
        for (String s : stocks.keySet()) {
            investmentValue += stockMarketService.getPrice(s) * stocks.get(s);
        }
        //Regner balancen ud fra startkapitalen
        double balance = userService.getInitialCash(userID) + sold - bought;
        //Regner totalen af vores aktier samt tilgængelig balance
        double equity = balance + investmentValue;
        //Opretter et ny portfolio objekt
        Portfolio portfolio = new Portfolio(userService.getFullName(userID), balance, investmentValue, equity, userTransactionLines, stocks);
        //Tilføjer aktielinjen til userens portfolio
        for (String s : stocks.keySet()) {
            double sharePrice = stockMarketService.getPrice(s);
            if (stocks.get(s) > 0) {
                portfolio.setPortfolioLines(new PortfolioLine(s, stocks.get(s), sharePrice));
            }
        }
        return portfolio;
    }

    // Den laver alle portfolios ud fra userIDs
    @Override
    public List<Portfolio> adminPortfolios() {
        List<String> allUserIDs = userService.getAllUserIDs();
        List<Portfolio> allPortfolios = new ArrayList<>();
        for (String userID : allUserIDs) {
            allPortfolios.add(createPortfolio(userID));
        }
        return allPortfolios;
    }

    public List<Portfolio> portfoliosSortedByInvestmentValue() {
        Comparator<Portfolio> comparator = new InvestmentComparator();
        List<String> allUserIDs = userService.getAllUserIDs();
        List<Portfolio> allPortfolios = new ArrayList<>();
        for (String userID : allUserIDs) {
            allPortfolios.add(createPortfolio(userID));
        }
        allPortfolios.sort(comparator);
        return allPortfolios;
    }

    @Override
    public boolean canPurchase(String userID, String ticker, int quantity) {
        Portfolio portfolio = createPortfolio(userID);
        double balance = portfolio.getBalance();
        double priceOfPurchase = stockMarketService.getPrice(ticker) * quantity;
        if (balance >= priceOfPurchase) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canSell(String userID, String ticker, int quantity) {
        Portfolio portfolio = createPortfolio(userID);
        HashMap<String, Integer> stocks = portfolio.getStocks();

        for (String s : stocks.keySet()) {
            if (s.equals(ticker)) {
                if (stocks.get(s) >= quantity) {
                    return true;
                }
            }
        }
        return false;
    }


}
