import Controller.Controller;
import Model.Stock;
import Model.TransactionLine;
import Model.User;
import Repository.*;
import Service.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ITransactionRepository transactionRepository = new TransactionRepository();
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        IUserRepository userRepository = new UserRepository();
        IStockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
        ITransactionService transactionService = new TransactionService(transactionRepository, stockMarketService);
        IUserService userService = new UserService(userRepository);
        // IPortfolioService portfolioService = new PortfolioService(stockMarketService, transactionService, userService);
        // IUserinterface userinterface = new TerminalUserinterface();
        // Controller controller = new Controller(portfolioService, stockMarketService, transactionService);


    }
}

