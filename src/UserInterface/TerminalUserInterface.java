package UserInterface;

import Model.Portfolio;
import Model.PortfolioLine;
import Model.Stock;
import Model.TransactionLine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TerminalUserInterface {
    private final Scanner scanner = new Scanner(System.in);

    public String mainMenu() {
        System.out.println(
                "\n+--------------------------------------+\n" +
                        "|        INVESTERINGSKLUBBEN MENU      |\n" +
                        "+--------------------------------------+\n" +
                        "|  1 | Stock Market                    |\n" +
                        "|  2 | Buy/Sell Stocks                 |\n" +
                        "|  3 | Portfolio Overview              |\n" +
                        "|  4 | Transaction History             |\n" +
                        "|  5 | Choose Currency                 |\n" +
                        "|  6 | Log Out                         |\n" +
                        "|  7 | Close Program                   |\n" +
                        "+--------------------------------------+\n" +
                        "Choose an action: "
        );
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2", "3", "4", "5", "6", "7": {
                    return input;
                }
                default: {
                    wrongInputMSG();
                }
            }
        }
    }

    public String adminMainMenu() {
        System.out.println();
        System.out.println(
                "\n+--------------------------------------+\n" +
                        "|               ADMIN MENU             |\n" +
                        "+--------------------------------------+\n" +
                        "|  1 | All Portfolios                  |\n" +
                        "|  2 | Rankings                        |\n" +
                        "|  3 | Choose Currency                 |\n" +
                        "|  4 | Add User                        |\n" +
                        "|  5 | Log Out                         |\n" +
                        "|  6 | Close Program                   |\n" +
                        "+--------------------------------------+\n" +
                        "Choose an action: "
        );
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2", "3", "4", "5", "6": {
                    return input;
                }
                default: {
                    wrongInputMSG();
                }
            }
        }
    }

    public String chooseBuyOrSell() {
        System.out.println("Choose buy or sell");
        System.out.println(
                """
                        1: buy
                        2: sell
                        x: return to menu""");
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2", "x": {
                    return input;
                }
                default: {
                    wrongInputMSG();
                }
            }
        }

    }

    public void loggingInMSG() {
        System.out.println("Welcome to Investeringsklubben!");
        System.out.println("Enter full name:");
    }

    public void wrongInputMSG() {
        System.out.println("Wrong input, please try again :-)");
    }

    public void whichStockMSG(String orderType) {
        System.out.println("Write the ticker you would like to " + orderType + "?");
    }

    public void whichUserMSG() {
        System.out.println("What is the name of the user you would like to add?");
    }

    public void howMuchInitialCashMSG() {
        System.out.println("How much initial cash should the user start with?");
    }

    public void whatIsUserEmailMSG() {
        System.out.println("What is their email?");
    }


    public void insufficientFundsMSG() {
        System.out.println("Insufficient funds");
    }

    public void insufficientStocksMSG(String ticker, int quantity) {
        System.out.println("You do not own " + quantity + " shares of " + ticker + " ;-(");
    }

    public String birthDateInput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String birthdate;
        while (true) {
            System.out.println("write your birthdate like this (dd-MM-yyyy)");
            try {
                birthdate = stringInput();
                LocalDate.parse(birthdate, formatter);
                return birthdate;
            } catch (Exception e) {
                wrongInputMSG();
            }
        }
    }

    public String stringInput() {
        return scanner.nextLine();
    }

    public int getQuantity(String orderType) {
        System.out.println("How many would you like to " + orderType + "?");
        return intNumberInput();
    }

    public int intNumberInput() {
        while (true) {
            int input;
            while (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                if (input > 0) {
                    return input;
                }
                System.out.println("Number has to be positive");

            }
            scanner.nextLine();
            wrongInputMSG();
        }
    }

    public double doubleNumberInput() {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.println("Has to be a number");
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;

    }

    public void scannerClose() {
        scanner.close();
    }

    public void printUserPortfolioStocks(Map<String, Integer> stocks) {
        for (String s : stocks.keySet()) {
            if (stocks.get(s) > 0) {
                System.out.println(s + " : " + stocks.get(s));
            }
        }
    }

    public void printPortfolio(String fullName, double balance, double equity, String currency, double investmentValue, List<PortfolioLine> portfolioLines) {
        final int LINE_WIDTH = 75;

        printLine("=", LINE_WIDTH);
        System.out.printf("Portfolio Overview for: %s%n", fullName);
        printLine("=", LINE_WIDTH);

        System.out.printf("%-30s %3s %10.2f%n", "Balance:", currency, balance);
        System.out.printf("%-30s %3s %10.2f%n", "Investment Value:", currency, investmentValue);
        System.out.printf("%-30s %3s %10.2f%n", "Equity:", currency, equity);

        printLine("-", LINE_WIDTH);
        System.out.printf("%-10s %-10s %11s %11s %20s%n", "Ticker", "Quantity", "Price", "Value", "Sector");
        printLine("-", LINE_WIDTH);

        if (portfolioLines.isEmpty()) {
            System.out.println("You currently do not own any stocks.");
        } else {
            for (PortfolioLine line : portfolioLines) {
                System.out.printf("%-10s %-10d %1s %9.2f  %9.2f %20s%n",
                        line.getTicker(),
                        line.getQuantity(),
                        currency, line.getSharePrice(),
                        line.getValue(),
                        line.getSector());
            }
        }

        printLine("=", LINE_WIDTH);
    }

    private void printLine(String symbol, int length) {
        System.out.println(symbol.repeat(length));
    }

    public void printStockTable(List<Stock> stocks) {
        System.out.println("+----------+---------------------------+----------------+-----------+--------+----------------------+--------------+");
        System.out.println("| Ticker   | Name                      | Sector         | Price     | Curr.  | Market               | Last Updated |");
        System.out.println("+----------+---------------------------+----------------+-----------+--------+----------------------+--------------+");

        for (Stock stock : stocks) {
            System.out.println("| " +
                    padRight(stock.getTicker(), 8) + " | " +
                    padRight(stock.getName(), 25) + " | " +
                    padRight(stock.getSector(), 14) + " | " +
                    padLeft(String.format("%.2f", stock.getPrice()), 9) + " | " +
                    padRight(stock.getCurrency(), 6) + " | " +
                    padRight(stock.getMarket(), 20) + " | " +
                    padRight(stock.getLastUpdated().toString(), 12) + " |");
        }

        System.out.println("+----------+---------------------------+----------------+-----------+--------+----------------------+--------------+");
    }

    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }

    private String padLeft(String text, int length) {
        return String.format("%" + length + "s", text);
    }


    public void printAllPortfolios(List<Portfolio> portfolios) {

        for (Portfolio portfolio : portfolios) {
            System.out.println();
            printPortfolio(portfolio.getName(), portfolio.getBalance(), portfolio.getEquity(), portfolio.getCurrency(), portfolio.getInvestmentValue(), portfolio.getPortfolioLines());
        }

    }

    public void printAllCurrencies(Map<String, Double> rates) {
        System.out.println("Which of these currencies would you like to be displayed in the program?");
        for (String valuta : rates.keySet()) {
            System.out.println(valuta);
        }
    }

    public void printTransactionHistory(List<TransactionLine> transactionLines) {
        for (TransactionLine t : transactionLines) {
            System.out.println(t);
        }
    }

    public void printReturnToMenuMSG() {
        System.out.println("Enter ‘X’ to show main menu");
    }

    public void printNoStocksMessage() {
        System.out.println("You dont have any stocks to sell");
    }

    public void printConfirmation(String ticker, int quantity, String orderType) {
        System.out.println("You have succesfully " + orderType + " " + quantity + " shares of " + ticker + " :-)");
    }

}
