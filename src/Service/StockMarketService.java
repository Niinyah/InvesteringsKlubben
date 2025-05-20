package Service;

import Model.Stock;
import Repository.IStockMarketRepository;

import java.util.List;

public class StockMarketService implements IStockMarketService {
    private final IStockMarketRepository stockMarketRepository;
    private final ICurrencyService currencyService;

    public StockMarketService(IStockMarketRepository StockMarketRepository, ICurrencyService currencyService) {
        this.stockMarketRepository = StockMarketRepository;
        this.currencyService = currencyService;
    }

    @Override
    public List<Stock> getStockMarketInSelectedCurrency(String userSelectedCurrency) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        for (Stock stock : stocks) {
            double price =  getPrice(stock.getTicker(), userSelectedCurrency);
            stock.setPrice(price);
            stock.setCurrency(userSelectedCurrency);
        }
        return stocks;
    }

    @Override
    public String getStockSector(String ticker) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        for (Stock s : stocks){
            if (s.getTicker().equalsIgnoreCase(ticker)){
                return s.getSector();
            }
        }
        return "";
    }


    @Override
    public double getPrice(String ticker, String selectedCurrency) {
        Stock stock = getStockFromTicker(ticker);
        return currencyService.convertCurrency(stock.getPrice(), stock.getCurrency(), selectedCurrency);
    }

    private Stock getStockFromTicker(String ticker) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        for (Stock stock : stocks) {
            if (ticker.equals(stock.getTicker())) {
                return stock;
            }
        }
        return null;
    }

    @Override
    public boolean stockDoesNotExists(String ticker) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        for (Stock stock : stocks) {
            if (ticker.equals((stock.getTicker()))) {
                return false;
            }
        }
        return true;
    }
}