package Service;

import Model.Stock;

import java.util.List;

public interface IStockMarketService {
    List<Stock> getStockMarket();
    List<Stock> getStockSector();
    double getPrice(String ticker);
}
