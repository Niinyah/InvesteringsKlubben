package Service;

import Exceptions.UserIDException;
import Model.Portfolio;
import Repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioServiceTest {
    static IPortfolioService portfolioService;

//    @BeforeAll
//    public static void setup() {
//        ITransactionRepository transactionRepository = new TransactionRepository();
//        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
//        IUserRepository userRepository = new UserRepository();
//        IStockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
//        ITransactionService transactionService = new TransactionService(transactionRepository, stockMarketService);
//        IUserService userService = new UserService(userRepository);
//        portfolioService = new PortfolioService(transactionService, stockMarketService, userService);
//    }
//
//    @Test
//    void doesNotThrowErrorWhenUserIDIsCorrect() {
//        assertDoesNotThrow(() -> {
//            portfolioService.createPortfolio("asd");
//        });
//    }
//
//    @Test
//    void throwsErrorWhenUserIDIsIncorrect() {
//        Exception exception = assertThrows(UserIDException.class, () -> {
//            portfolioService.createPortfolio("abc");
//        });
//        assertTrue(exception.getMessage().contains("does not exist"));
//    }
}