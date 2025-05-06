import Repository.*;
import Repository.ITransactionRepository;
import Repository.IUserRepository;
import Service.*;

public class Main {
    public static void main(String[] args) {
        ITransactionRepository transactionRepository = new TransactionRepository();
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        IUserRepository userRepository = new UserRepository();
        IStockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
        Service.ITransactionService transactionService = new TransactionService(transactionRepository, stockMarketService);
        Service.IUserService userService = new UserService(userRepository);
        IPortfolioService portfolioService = new PortfolioService(transactionService, stockMarketService, userService);
        // Controller controller = new Controller(portfolioService, stockMarketService, transactionService);
        // IUserinterface userinterface = new TerminalUserinterface();


    }
}

