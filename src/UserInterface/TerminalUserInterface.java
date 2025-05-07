package UserInterface;

import Model.Stock;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class TerminalUserInterface {
    private Scanner scanner = new Scanner(System.in);


    public String loggingIn() {
        System.out.println("Welcome to Investeringsklubben!");
        System.out.println("Enter full name:");
        return scanner.nextLine();
    }

    public void wrongInput() {
        System.out.println("Wrong input, please try again :-)");
    }

    public String mainMenu() {
        System.out.println();
        System.out.println(
                "\n+--------------------------------------+\n" +
                        "|        INVESERINGSKLUBBEN MENU       |\n" +
                        "+--------------------------------------+\n" +
                        "|  1 | Stock Market                    |\n" +
                        "|  2 | Buy/Sell Stocks                 |\n" +
                        "|  3 | Portfolio Overview              |\n" +
                        "|  4 | Transaction History             |\n" +
                        "|  5 | Log Out                         |\n" +
                        "+--------------------------------------+\n" +
                        "Choose an action: "
        );
        /*System.out.println(
               "Choose an action: " +
                        "\n1: Stock Market" +
                        "\n2: Buy/Sell" +
                        "\n3: Portfolio" +
                        "\n4: Transaction History" +
                        "\n5: Log out");*/

        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2", "3", "4", "5": {
                    return input;
                }
                default: {
                    wrongInput();
                }
            }
        }


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
    public void whichStock(int orderType){
        if (orderType==1) {
            System.out.println("Write the ticker you would like to purchase?");
            return;
        }
        System.out.println("Write the ticker you would like to sell?");
    }


    public void howMany(){
        System.out.println("How many would you like to buy?");
    }

    public void printStockMarket(List<Stock> stocks) {
        for (Stock s : stocks) {
            System.out.println(s);
        }

    }

    public void printUserPortfolioStocks (Map<String, Integer> stocks){
        for (String s : stocks.keySet()){
            System.out.println(s + " : " + stocks.get(s));
        }
    }

    public void insufficientFunds(){
        System.out.println("Insufficient funds");
    }

    //public void message(String mes){
    //switch "buy" ->
    public void insufficientStocks(){
        System.out.println("You cant sell that");
    }

    

    public String stringInput() {
        return scanner.nextLine();
    }

    /*public int intNumberInput(){
        while (!scanner.hasNextInt()){
            scanner.nextLine();
            System.out.println("Has to be a number");
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;

    }*/
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

    public double doubleNumberInput() {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.println("Has to be a number");
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;

    }


}
