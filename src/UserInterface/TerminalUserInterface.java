package UserInterface;

import Model.Portfolio;
import Model.PortfolioLine;
import Model.Stock;
import Model.TransactionLine;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TerminalUserInterface {
    private Scanner scanner = new Scanner(System.in);


    public void loggingIn() {
        System.out.println("Welcome to Investeringsklubben!");
        System.out.println("Enter full name:");
    }

    public void wrongInput() {
        System.out.println("Wrong input, please try again :-)");
    }

    public String mainMenu() {
        System.out.println();
        System.out.println(
                "\n+--------------------------------------+\n" +
                        "|        INVESTERINGSKLUBBEN MENU      |\n" +
                        "+--------------------------------------+\n" +
                        "|  1 | Stock Market                    |\n" +
                        "|  2 | Buy/Sell Stocks                 |\n" +
                        "|  3 | Portfolio Overview              |\n" +
                        "|  4 | Transaction History             |\n" +
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
                    wrongInput();
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
                        "|  3 | Log Out                         |\n" +
                        "|  4 | Close Program                   |\n" +
                        "+--------------------------------------+\n" +
                        "Choose an action: "
        );
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2", "3", "4": {
                    return input;
                }
                default: {
                    wrongInput();
                }
            }
        }


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

    // Helper methods to pad strings
    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }

    private String padLeft(String text, int length) {
        return String.format("%" + length + "s", text);
    }

    public String chooseBuyAndSell() {
        System.out.println("Choose buy or sell");
        System.out.println("" +
                "1: buy\n" +
                "2: sell\n" +
                "x: return to menu");
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2", "x": {
                    return input;
                }
                default: {
                    wrongInput();
                }
            }
        }

    }

    public void whichStock(String orderType) {
        System.out.println("Write the ticker you would like to " + orderType + "?");
    }


    public void howMany(String orderType) {

        System.out.println("How many would you like to " + orderType + "?");
    }


    public void printUserPortfolioStocks(Map<String, Integer> stocks) {
        for (String s : stocks.keySet()) {
            if (stocks.get(s) > 0) {
                System.out.println(s + " : " + stocks.get(s));
            }
        }
    }

    public void insufficientFunds() {
        System.out.println("Insufficient funds");
    }

    //public void message(String mes){
    //switch "buy" ->
    public void insufficientStocks(String ticker, int quantity) {
        System.out.println("You do not own " + quantity + " shares of " + ticker + " ;-(");
    }


    public String stringInput() {
        return scanner.nextLine();
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
            wrongInput();
        }
    }
    public void scannerClose(){
        scanner.close();
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

    public void printPortfolio(String fullName, double balance, double equity, double investmentValue, List<PortfolioLine> portfolioLines) {
        final int LINE_WIDTH = 60;
        final String CURRENCY = "DKK";

        printLine("=", LINE_WIDTH);
        System.out.printf("Portfolio Overview for: %s%n", fullName);
        printLine("=", LINE_WIDTH);

        // Align currency and values to the right with consistent width
        System.out.printf("%-30s %3s %10.2f%n", "Balance:", CURRENCY, balance);
        System.out.printf("%-30s %3s %10.2f%n", "Investment Value:", CURRENCY, investmentValue);
        System.out.printf("%-30s %3s %10.2f%n", "Equity:", CURRENCY, equity);

        printLine("-", LINE_WIDTH);
        System.out.printf("%-10s %-10s %10s %15s%n", "Ticker", "Quantity", "Price", "Value");
        printLine("-", LINE_WIDTH);

        if (portfolioLines.isEmpty()) {
            System.out.println("You currently do not own any stocks.");
        } else {
            for (PortfolioLine line : portfolioLines) {
                System.out.printf("%-10s %-10d %3s %7.2f %9s %7.2f%n",
                        line.getTicker(),
                        line.getQuantity(),
                        CURRENCY, line.getSharePrice(),
                        CURRENCY, line.getValue());
            }
        }

        printLine("=", LINE_WIDTH);
    }

    private void printLine(String symbol, int length) {
        System.out.println(symbol.repeat(length));
    }
    public void printAllPortfolios(List<Portfolio> portfolios){

        for(Portfolio portfolio : portfolios){
            System.out.println();
            printPortfolio(portfolio.getName(),portfolio.getBalance(), portfolio.getEquity(), portfolio.getInvestmentValue(), portfolio.getPortfolioLines());
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
