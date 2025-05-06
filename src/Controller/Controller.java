package Controller;

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
            case "1" -> stockMarketService.getStockMarket();
            case "2" ->
        }
    }

    public void buyAndSell() {
        switch (terminalUserInterface.chooseBuyAndSell()) {
            case "1" -> buy();
            case "2" -> sell();
        }
    }

    public void buy() {
        terminalUserInterface.printStockMarket(stockMarketService.getStockMarket());
        terminalUserInterface.whichStock();
        boolean tickerExists = true;
        String ticker = "";
        while (tickerExists) {
            ticker = terminalUserInterface.stringInput();
            tickerExists = stockMarketService.stockExists(ticker);
        }
        int quantity = terminalUserInterface.intNumberInput();
        if (portfolioService.canPurchase(userID, ticker, quantity)) {
            transactionService.createTransactionLine(userID, ticker, "buy", quantity);
        }


    }

    public void sell() {

    }

}
