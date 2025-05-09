package Controller;

import Model.Portfolio;
import Service.*;
import UserInterface.TerminalUserInterface;

import java.util.HashMap;

public class Controller {
    private final IPortfolioService portfolioService;
    private final IStockMarketService stockMarketService;
    private final ITransactionService transactionService;
    private final IUserService userService;
    private final TerminalUserInterface terminalUserInterface;
    private String userID;

    public Controller(IPortfolioService portfolioService, IStockMarketService stockMarketService,
                      ITransactionService transactionService, IUserService userService, TerminalUserInterface terminalUserInterface) {
        this.portfolioService = portfolioService;
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.terminalUserInterface = terminalUserInterface;
    }

    public void nameVerifier(String input) {
        String fullName = input;
        while (true) {
            String userID = userService.getUserID(fullName);
            if (userID != "") {
                this.userID = userID;
                return;
            }
            terminalUserInterface.wrongInputMSG();
            fullName = terminalUserInterface.stringInput();
        }
    }

    public void start() {
        boolean running = true;
        while (running) {
            terminalUserInterface.loggingInMSG();
            String input = terminalUserInterface.stringInput();
            if (input.equals("Admin")) {
                running = adminLogIn();
            } else {
                running = userLogIn(input);
            }
        }

    }

    public boolean userLogIn(String input) {
        nameVerifier(input);
        boolean loggedIn = true;
        while (loggedIn) {
            switch (terminalUserInterface.mainMenu()) {
                case "1" -> showStockMarket();
                case "2" -> buyAndSell();
                case "3" -> showPortfolio();
                case "4" -> showTransactionHistory();
                case "5" -> loggedIn = false;
                case "6" -> {
                    terminalUserInterface.scannerClose();
                    return false;
                }
                default -> terminalUserInterface.wrongInputMSG();
            }
        }
        return true;
    }

    public boolean adminLogIn() {
        boolean loggedIn = true;
        while (loggedIn) {
            switch (terminalUserInterface.adminMainMenu()) {
                case "1" -> showAllPortfolios();
                case "2" -> rankedPortfolios();
                case "3" -> loggedIn = false;
                case "4" -> {
                    terminalUserInterface.scannerClose();
                    return false;
                }
                default -> terminalUserInterface.wrongInputMSG();
            }
        }
        return true;
    }

    public void showStockMarket() {
        terminalUserInterface.printStockTable(stockMarketService.getStockMarket());
        returnToMenu();
    }

    public void buyAndSell() {
        switch (terminalUserInterface.chooseBuyOrSell()) {
            case "1" -> buy();
            case "2" -> sell();
            default -> {
            }
        }
    }

    public void buy() {
        terminalUserInterface.printStockTable(stockMarketService.getStockMarket());
        String buy = "buy";
        String bought = "bought";
        terminalUserInterface.whichStockMSG(buy);
        String ticker = getTicker();
        int quantity = getQuantity(buy);
        if (portfolioService.canPurchase(userID, ticker, quantity)) {
            transactionService.createTransactionLine(userID, ticker, buy, quantity);
            terminalUserInterface.printConfirmation(ticker, quantity, bought);
            if (returnToMenu()) {
                return;
            }
        }
        terminalUserInterface.insufficientFundsMSG();
        returnToMenu();


    }

    public void sell() {
        Portfolio portfolio = portfolioService.createPortfolio(userID);
        HashMap<String, Integer> stocks = portfolio.getStocks();
        String sell = "sell";
        String sold = "sold";
        boolean hasStocks = false;
        for (String stock : stocks.keySet()) {
            if (stocks.get(stock) > 0) {
                hasStocks = true;
            }
        }
        if (hasStocks) {
            terminalUserInterface.printUserPortfolioStocks(portfolio.getStocks());
            terminalUserInterface.whichStockMSG(sell);
            String ticker = getTicker();
            int quantity = getQuantity(sell);
            if (portfolioService.canSell(userID, ticker, quantity)) {
                transactionService.createTransactionLine(
                        userID, ticker, sell, quantity);
                terminalUserInterface.printConfirmation(
                        ticker, quantity, sold);
                if (returnToMenu()) {
                    return;
                }
            }
            terminalUserInterface.insufficientStocksMSG(ticker, quantity);
        } else {
            terminalUserInterface.printNoStocksMessage();
        }
        returnToMenu();


    }

    public void showAllPortfolios() {
        terminalUserInterface.printAllPortfolios(
                portfolioService.adminPortfolios());
        returnToMenu();
    }

    public void rankedPortfolios() {
        terminalUserInterface.printAllPortfolios(
                portfolioService.portfoliosSortedByInvestmentValue());
        returnToMenu();
    }

    // måske ændres
    public String getTicker() {
        boolean tickerDoesNotExists = true;
        String ticker = "";
        while (tickerDoesNotExists) {
            ticker = terminalUserInterface.stringInput();
            if (stockMarketService.stockDoesNotExists(ticker)) {
                terminalUserInterface.wrongInputMSG();

            }
            tickerDoesNotExists = stockMarketService.stockDoesNotExists(ticker);

        }
        return ticker.toUpperCase().trim();
    }

    public int getQuantity(String orderType) {
        terminalUserInterface.howManyMSG(orderType);
        return terminalUserInterface.intNumberInput();

    }

    public void showPortfolio() {
        Portfolio portfolio = portfolioService.createPortfolio(userID);
        terminalUserInterface.printPortfolio(
                portfolio.getName(),
                portfolio.getBalance(),
                portfolio.getEquity(),
                portfolio.getInvestmentValue(),
                portfolio.getPortfolioLines());
        returnToMenu();

    }

    public void showTransactionHistory() {
        Portfolio portfolio = portfolioService.createPortfolio(userID);
        terminalUserInterface.printTransactionHistory(portfolio.getHistory());
        returnToMenu();
    }

    public boolean returnToMenu() {
        terminalUserInterface.printReturnToMenuMSG();
        while (true) {
            String input = terminalUserInterface.stringInput();
            if (input.trim().equalsIgnoreCase("x")) {
                return true;
            }
        }
    }

}
