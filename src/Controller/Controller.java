package Controller;

import Model.Portfolio;
import Service.*;
import UserInterface.TerminalUserInterface;

public class Controller {
    private IPortfolioService portfolioService;
    private IStockMarketService stockMarketService;
    private ITransactionService transactionService;
    private IUserService userService;
    private TerminalUserInterface terminalUserInterface;
    private String userID;

    public Controller(IPortfolioService portfolioService, IStockMarketService stockMarketService,
                      ITransactionService transactionService, IUserService userService, TerminalUserInterface terminalUserInterface) {
        this.portfolioService = portfolioService;
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.terminalUserInterface = terminalUserInterface;
    }

    public void nameVerifier() {
        while (true) {
            String fullName = terminalUserInterface.loggingIn();
            String userID = userService.getUserID(fullName);
            if (userID != "") {
                this.userID = userID;
                return;
            }
            terminalUserInterface.wrongInput();
        }
    }


    public void start() {
        nameVerifier();
        switch (terminalUserInterface.mainMenu()) {
            case "1" -> terminalUserInterface.printStockMarket(stockMarketService.getStockMarket());
            case "2" -> buyAndSell();
        }

    }

    public void buyAndSell() {
        switch (terminalUserInterface.chooseBuyAndSell()) {
            case "1" -> buy();
            case "2" -> sell();
            case "x" -> {
                return;
            }
        }
    }

    public void buy() {
        int orderType = 1;
        terminalUserInterface.printStockMarket(stockMarketService.getStockMarket());
        terminalUserInterface.whichStock(orderType);
        String ticker = getTicker();
        int quantity = getQuantity();
        if (portfolioService.canPurchase(userID, ticker, quantity)) {
            transactionService.createTransactionLine(userID, ticker, "buy", quantity);
            return;
        }
        terminalUserInterface.insufficientFunds();


    }

    public void sell() {
        Portfolio portfolio = portfolioService.createPortfolio(userID);
        terminalUserInterface.printUserPortfolioStocks(portfolio.getStocks());
        terminalUserInterface.whichStock(2);
        String ticker = getTicker();
        int quantity = getQuantity();
        if (portfolioService.canSell(userID, ticker, quantity)) {
            transactionService.createTransactionLine(userID, ticker, "sell", quantity);
            return;
        }
        terminalUserInterface.insufficientStocks();
        


    }

    public String getTicker() {
        boolean tickerDoesNotExists = true;
        String ticker = "";
        while (tickerDoesNotExists) {
            ticker = terminalUserInterface.stringInput();
            tickerDoesNotExists = stockMarketService.stockDoesNotExists(ticker);
            terminalUserInterface.wrongInput();
        }
        return ticker;
    }
    public int getQuantity() {
        terminalUserInterface.howMany();
        return terminalUserInterface.intNumberInput();

    }

}
