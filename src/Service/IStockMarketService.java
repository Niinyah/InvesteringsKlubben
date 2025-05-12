package Service;

import Model.Stock;

import java.util.List;


public interface IStockMarketService {
    List<Stock> getStockMarketInSelectedCurrency(String currency);
    String getStockSector(String ticker);
    double getPrice(String ticker, String currency);
    boolean stockDoesNotExists(String ticker);
}
