package Controller;

import Model.Portfolio;
import Service.*;
import UserInterface.TerminalUserInterface;

import java.time.LocalDate;
import java.util.HashMap;

public class Controller {
    private final IPortfolioService portfolioService;
    private final IStockMarketService stockMarketService;
    private final ITransactionService transactionService;
    private final IUserService userService;
    private final TerminalUserInterface terminalUserInterface;
    private final ICurrencyService currencyService;
    private String userID;
    private String currency = "DKK";

    public Controller(IPortfolioService portfolioService, IStockMarketService stockMarketService,
                      ITransactionService transactionService, IUserService userService,
                      TerminalUserInterface terminalUserInterface, ICurrencyService currencyService) {

        this.portfolioService = portfolioService;
        this.stockMarketService = stockMarketService;
        this.transactionService = transactionService;
        this.userService = userService;
        this.terminalUserInterface = terminalUserInterface;
        this.currencyService = currencyService;

    }

    public boolean nameVerifier(String input) {
        String fullName = input;
        while (true) {
            if (fullName.equals("Admin")){
                return true;
            }
                String userID = userService.getUserID(fullName);
            if (!userID.isEmpty()) {
                this.userID = userID;
                return false;
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
            if (nameVerifier(input)) {
                running = adminLogIn();
            } else {
                running = userLogIn();
            }
        }

    }

    public boolean userLogIn() {
        boolean loggedIn = true;
        while (loggedIn) {
            switch (terminalUserInterface.mainMenu()) {
                case "1" -> showStockMarket();
                case "2" -> buyAndSell();
                case "3" -> showPortfolio();
                case "4" -> showTransactionHistory();
                case "5" -> chooseCurrency();
                case "6" -> loggedIn = false;
                case "7" -> {
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
                case "3" -> chooseCurrency();
                case "4" -> addUser();
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

    public void chooseCurrency() {
        terminalUserInterface.printAllCurrencies(currencyService.getRates());
        while (true) {
            String userInput = terminalUserInterface.stringInput();
            boolean currencyIsValid = currencyService.currencyIsValid(userInput);
            if (currencyIsValid) {
                this.currency = userInput;
                return;
            }
            terminalUserInterface.wrongInputMSG();
        }
    }

    public void addUser() {
        terminalUserInterface.whichUserMSG();
        String fullName = terminalUserInterface.stringInput();
        terminalUserInterface.howMuchInitialCashMSG();
        double initialCash = 0;
        boolean inValidInitialCash = true;
        while (inValidInitialCash) {
            initialCash = terminalUserInterface.doubleNumberInput();
            if (initialCash > 0) {
                inValidInitialCash = false;
                continue;
            }
            terminalUserInterface.negativeNumberMSG();
        }
        terminalUserInterface.whatIsUserEmailMSG();
        String email = terminalUserInterface.stringInput();
        String birthDate = "";
        boolean inValidYear = true;
        while (inValidYear) {
            birthDate = terminalUserInterface.birthDateInput();
            String[] split = birthDate.split("-");
            int year = Integer.parseInt(split[2]);
            int year18 = LocalDate.now().getYear() - 18;
            int year110 = LocalDate.now().getYear() - 110;
            if (year < year18 && year > year110) {
                inValidYear = false;
                continue;
            }
            terminalUserInterface.wrongBirthdayMSG();
        }

        userService.addUser(fullName, initialCash, email, birthDate);
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
        terminalUserInterface.printStockTable(stockMarketService.getStockMarketInSelectedCurrency(currency));
        String buy = "buy";
        String bought = "bought";
        terminalUserInterface.whichStockMSG(buy);
        String ticker = getTicker();
        int quantity = terminalUserInterface.getQuantity(buy);
        if (portfolioService.canPurchase(userID, ticker, quantity, currency)) {
            transactionService.createTransactionLine(userID, ticker, buy, quantity, currency);
            terminalUserInterface.printConfirmation(ticker, quantity, bought);
            if (returnToMenu()) {
                return;
            }
        }
        terminalUserInterface.insufficientFundsMSG();
        returnToMenu();


    }

    public void sell() {
        Portfolio portfolio = portfolioService.createPortfolio(userID, currency);
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
            int quantity = terminalUserInterface.getQuantity(sell);
            if (portfolioService.canSell(userID, ticker, quantity, currency)) {
                transactionService.createTransactionLine(
                        userID, ticker, sell, quantity, currency);
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
                portfolioService.adminPortfolios(currency));
        returnToMenu();
    }

    public void showStockMarket() {
        terminalUserInterface.printStockTable(stockMarketService.getStockMarketInSelectedCurrency(currency));
        returnToMenu();
    }

    public void rankedPortfolios() {
        terminalUserInterface.printAllPortfolios(
                portfolioService.portfoliosSortedByInvestmentValue(currency));
        returnToMenu();
    }

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

    public void showPortfolio() {
        Portfolio portfolio = portfolioService.createPortfolio(userID, currency);
        terminalUserInterface.printPortfolio(
                portfolio.getName(),
                portfolio.getBalance(),
                portfolio.getEquity(),
                portfolio.getCurrency(),
                portfolio.getInvestmentValue(),

                portfolio.getPortfolioLines());
        returnToMenu();

    }

    public void showTransactionHistory() {
        Portfolio portfolio = portfolioService.createPortfolio(userID, currency);
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
