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

    private final ITransactionService transactionService;
    private final IStockMarketService stockMarketService;
    private final IUserService userService;
    private final ICurrencyService currencyService;

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
    public Portfolio createPortfolio(String userID, String selectedCurrency)  {
        List<TransactionLine> userTransactionLines = transactionService.getUserTransactionHistory(userID);
        HashMap<String, Integer> stocks = new HashMap<>();
        double rate = currencyService.getRate(selectedCurrency);

        double sold = 0;
        double bought = 0;

        for (TransactionLine transactionLine : userTransactionLines) {
            int quantity = 0;
            //Sætter key og value i hashmappen
            if (stocks.get(transactionLine.getTicker()) != null) {
                quantity = stocks.get(transactionLine.getTicker());
            }


            //lægger sammen hvor meget du har købt og solgt for, og regner prisen ud.
            if (transactionLine.getOrderType().equals("buy")) {
                stocks.put(transactionLine.getTicker(), quantity + transactionLine.getQuantity());

                double totalPrice = transactionLine.getQuantity() * transactionLine.getPrice();
                String listedCurrency = transactionLine.getCurrency();
                double totalConvertedPrice = currencyService.convertCurrency(totalPrice, listedCurrency, selectedCurrency) ;
                bought += totalConvertedPrice;

            } else {
                stocks.put(transactionLine.getTicker(), quantity - transactionLine.getQuantity());

                var value = transactionLine.getQuantity() * transactionLine.getPrice();
                sold +=  value / rate;
            }
        }

        double investmentValue = 0;
        for (String ticker : stocks.keySet()) {
            investmentValue += stockMarketService.getPrice(ticker, selectedCurrency) * stocks.get(ticker);
        }
        double balance = userService.getInitialCash(userID) / rate + sold - bought;
        double equity = balance + investmentValue;

        Portfolio portfolio = new Portfolio(userService.getFullName(userID), balance, investmentValue, equity, userTransactionLines, stocks, selectedCurrency);
        //Tilføjer aktielinjen til userens portfolio
        for (String s : stocks.keySet()) {
            double sharePrice = stockMarketService.getPrice(s, selectedCurrency);
            if (stocks.get(s) > 0) {
                String sector = stockMarketService.getStockSector(s);
                portfolio.setPortfolioLines(new PortfolioLine(s, stocks.get(s), sharePrice, sector));
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
