package UserInterface;

import Model.Stock;

import java.util.List;
import java.util.Locale;
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
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "1", "2": {
                    return input;
                }
                default: {
                    wrongInput();
                }
            }
        }

    }
    public void whichStock(){
        System.out.println("Write the ticker you would like to purchase?");
    }

    public void printStockMarket(List<Stock> stocks) {
        for (Stock s : stocks) {
            System.out.println(s);
        }

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
