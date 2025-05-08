package Service;

import Model.Stock;

import java.util.List;


public interface IStockMarketService {
    List<Stock> getStockMarket(String currency);
    List<Stock> getStockSector();
    double getPrice(String ticker, String currency);
    boolean stockDoesNotExists(String ticker);
}
