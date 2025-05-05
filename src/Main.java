import Model.Stock;
import Model.TransactionLine;
import Model.User;
import Repository.*;
import Service.IStockMarketService;
import Service.ITransactionService;
import Service.StockMarketService;
import Service.TransactionService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ITransactionRepository transactionRepository = new TransactionRepository();
        IStockMarketRepository stockMarketRepository = new StockMarketRepository();
        IStockMarketService stockMarketService = new StockMarketService(stockMarketRepository);
        ITransactionService transactionService = new TransactionService(transactionRepository, stockMarketService);

        List<TransactionLine> lines = transactionService.getUserTransactionHistory("2");
        for (TransactionLine t : lines){
            System.out.println(t);
        }
        }
    }

