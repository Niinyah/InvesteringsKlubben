package Service;

import Repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StockMarketServiceTest {

    static IStockMarketService stockMarketService;

    @BeforeAll
    public static void setup() {
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        ICurrencyRepository currencyRepository = new CurrencyRepository();
        ICurrencyService currencyService = new CurrencyService(currencyRepository);
        stockMarketService = new StockMarketService(stockMarketRepository, currencyService);
    }

    @Test
    //
    void getPriceIsCorrect() {
       double price = stockMarketService.getPrice("NOVO-B", "EUR");
       double expected = 710 / 7.45;
       assertEquals(expected, price);
    }

    @Test
    void getPriceIsWrong(){
        double price = stockMarketService.getPrice("VWS", "USD");
        double expected = 198 * 6.9;
        assertEquals(expected, price);
    }
}