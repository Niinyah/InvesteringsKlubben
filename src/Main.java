import Controller.Controller;
import Repository.*;
import Repository.ITransactionRepository;
import Repository.IUserRepository;
import Service.*;
import UserInterface.TerminalUserInterface;

public class Main {
    public static void main(String[] args) {
        ITransactionRepository transactionRepository = new TransactionRepository();
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        IUserRepository userRepository = new UserRepository();
        ICurrencyRepository currencyRepository = new CurrencyRepository();
        ICurrencyService currencyService = new CurrencyService(currencyRepository);
        IStockMarketService stockMarketService = new StockMarketService(stockMarketRepository,currencyService );
        ITransactionService transactionService = new TransactionService(transactionRepository, stockMarketService);
        IUserService userService = new UserService(userRepository);
        IPortfolioService portfolioService = new PortfolioService(transactionService, stockMarketService, userService, currencyService);
        TerminalUserInterface terminalUserInterface = new TerminalUserInterface();
        Controller controller = new Controller(portfolioService, stockMarketService, transactionService, userService, terminalUserInterface, currencyService);
        controller.start();
    }
}

