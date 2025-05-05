import Model.Stock;
import Repository.IStockMarketRepository;
import Repository.StockMarketRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        List<Stock> a = stockMarketRepository.getStockMarket();

        for (Stock s : a){
            System.out.println(s);
        }
        Scanner sc = new Scanner(System.in);
        String f = sc.nextLine();
        int b = sc.nextInt();

        System.out.println(f +" " + b);
    }
}
