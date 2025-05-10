package Service;

import Comparators.SectorComparator;
import Model.Currency;
import Model.Stock;
import Repository.IStockMarketRepository;

import java.util.Comparator;
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
//            if (!s.getCurrency().equalsIgnoreCase(currency)) {
//                Currency c = currencyService.getCurrency(currency);
//                if (s.getCurrency().equalsIgnoreCase("DKK")) {
//                    s.setPrice(s.getPrice() / c.getRate());
//                } else {
//                    Currency stock = currencyService.getCurrency(s.getCurrency());
//                    var rate = stock.getRate();
//                    var pris = currencyService.getPriceInDKK(s);
//                    var price = pris / rate;
//                    s.setPrice(price);
//                }
//                s.setCurrency(currency);
//            }

        }

        return stocks;
    }

    @Override
    public List<Stock> getStockSector() {
        Comparator<Stock> getComparator = new SectorComparator();
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        stocks.sort(getComparator);
        return stocks;
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