package Service;

import Comparators.InvestmentComparator;
import Comparators.SectorComparator;
import Exceptions.UserIDException;
import Model.*;
import Repository.ICurrencyRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class PortfolioService implements IPortfolioService {

    private ITransactionService transactionService;
    private IStockMarketService stockMarketService;
    private IUserService userService;
    private ICurrencyService currencyService;

    public PortfolioService(ITransactionService transactionService,
                            IStockMarketService stockMarketService,
                            IUserService userService,
                            ICurrencyService currencyService) {
        this.transactionService = transactionService;
        this.stockMarketService = stockMarketService;
        this.userService = userService;
        this.currencyService = currencyService;
    }


    @Override
    public Portfolio createPortfolio(String userID, String currency)  {
        List<TransactionLine> userTransactionLines = transactionService.getUserTransactionHistory(userID);
        HashMap<String, Integer> stocks = new HashMap<>();
        Currency curr = currencyService.getCurrency(currency);

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
                var value = transactionLine.getQuantity() * transactionLine.getPrice();
                bought += value / curr.getRate();
            } else {
                stocks.put(transactionLine.getTicker(), quantity - transactionLine.getQuantity());
                var value = transactionLine.getQuantity() * transactionLine.getPrice();
                sold +=  value / curr.getRate();
            }
        }

        //Stående værdi af alle vores aktier
        double investmentValue = 0;
        for (String s : stocks.keySet()) {
            investmentValue += stockMarketService.getPrice(s, currency) * stocks.get(s);
        }
        //Regner balancen ud fra startkapitalen
        double balance = (userService.getInitialCash(userID) / curr.getRate()) + sold - bought;
        //Regner totalen af vores aktier samt tilgængelig balance
        double equity = balance + investmentValue;
        //Opretter et ny portfolio objekt
        Portfolio portfolio = new Portfolio(userService.getFullName(userID), balance, investmentValue, equity, userTransactionLines, stocks, curr);
        //Tilføjer aktielinjen til userens portfolio
        for (String s : stocks.keySet()) {
            double sharePrice = stockMarketService.getPrice(s, currency);
            if (stocks.get(s) > 0) {
                portfolio.setPortfolioLines(new PortfolioLine(s, stocks.get(s), sharePrice));
            }
        }
        return portfolio;
    }

    // Den laver alle portfolios ud fra userIDs
    @Override
    public List<Portfolio> adminPortfolios(String currency) {
        List<String> allUserIDs = userService.getAllUserIDs();
        List<Portfolio> allPortfolios = new ArrayList<>();
        for (String userID : allUserIDs) {
            allPortfolios.add(createPortfolio(userID, currency));
        }
        return allPortfolios;
    }

    public List<Portfolio> portfoliosSortedByInvestmentValue(String currency) {
        Comparator<Portfolio> comparator = new InvestmentComparator();
        List<String> allUserIDs = userService.getAllUserIDs();
        List<Portfolio> allPortfolios = new ArrayList<>();
        for (String userID : allUserIDs) {
            allPortfolios.add(createPortfolio(userID, currency));
        }
        allPortfolios.sort(comparator);
        return allPortfolios;
    }

    @Override
    public boolean canPurchase(String userID, String ticker, int quantity, String currency) {
        Portfolio portfolio = createPortfolio(userID, currency);
        double balance = portfolio.getBalance();
        double priceOfPurchase = stockMarketService.getPrice(ticker, currency) * quantity;
        if (balance >= priceOfPurchase) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canSell(String userID, String ticker, int quantity, String currency) {
        Portfolio portfolio = createPortfolio(userID, currency);
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
