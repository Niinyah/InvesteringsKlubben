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

    public StockMarketService(IStockMarketRepository StockMarketRepository, ICurrencyService currencyService ) {
        this.stockMarketRepository = StockMarketRepository;
        this.currencyService = currencyService;
    }

    @Override
    public List<Stock> getStockMarket(String currency) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        Currency currency1 = currencyService.getCurrency(currency);
        for (Stock s : stocks){
            s.setPrice(s.getPrice() / currency1.getRate());
            s.setCurrency(currency);
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
    public double getPrice(String ticker, String currency) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        Currency currency1 = currencyService.getCurrency(currency);
        for (Stock stock : stocks) {
            if (ticker.equals(stock.getTicker())) {
                return stock.getPrice() / currency1.getRate();
            }

        }
        return 0;
    }
    public boolean stockDoesNotExists(String ticker){
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        for (Stock stock : stocks) {
            if (ticker.equals((stock.getTicker()))) {
                return false;
            }
        }
        return true;
    }
}