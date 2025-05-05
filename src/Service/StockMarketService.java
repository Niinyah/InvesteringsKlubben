package Service;

import Comparators.SectorComparator;
import Model.Stock;
import Repository.IStockMarketRepository;
import java.util.Comparator;
import java.util.List;

public class StockMarketService implements IStockMarketService {
    private final IStockMarketRepository stockMarketRepository;

    public StockMarketService(IStockMarketRepository StockMarketRepository) {
        this.stockMarketRepository = StockMarketRepository;
    }


    @Override
    public List<Stock> getStockMarket() {

        return stockMarketRepository.getStockMarket();
    }

    @Override
    public List<Stock> getStockSector() {
        Comparator<Stock> getComparator = new SectorComparator();
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        stocks.sort(getComparator);
        return stocks;
    }

    @Override
    public double getPrice(String ticker) {
        List<Stock> stocks = stockMarketRepository.getStockMarket();
        for (Stock stock : stocks) {
            if (ticker.equals(stock.getTicker())) {
                return stock.getPrice();
            }

        }
        return 0;
    }
}