import Model.Stock;
import Repository.IStockMarketRepository;
import Repository.StockMarketRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        List<Stock> a = stockMarketRepository.getStockMarket();

        for (Stock s : a){
            System.out.println(s);
        }
    }
}
