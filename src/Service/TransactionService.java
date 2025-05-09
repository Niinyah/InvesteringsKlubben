package Service;

import Model.Stock;
import Model.TransactionLine;
import Repository.ITransactionRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final IStockMarketService stockMarketService;

    public TransactionService(ITransactionRepository transactionRepository, IStockMarketService stockMarketService) {
        this.transactionRepository = transactionRepository;
        this.stockMarketService = stockMarketService;
    }

    @Override
    public void createTransactionLine(String userID, String ticker, String orderType, int quantity, String currency) {
        List<TransactionLine> allTransactionLines = transactionRepository.getTransactions();
        List<Stock> stockMarket = stockMarketService.getStockMarket(currency);


        //Få sidste tal fra repository +1
        int lastIdNumber = Integer.parseInt(allTransactionLines.getLast().getId()) + 1;
        String lineId = lastIdNumber + "";


        //giver den aktuelle pris og currency
        double priceNow = 0;
        String currencyOfStock = null;
        for (Stock stock : stockMarket) {
            if (ticker.equals(stock.getTicker())) {
                priceNow = stock.getPrice();
                currencyOfStock = stock.getCurrency();
            }
        }

        //Giver aktuel tid
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String timeNow = LocalDate.now().format(formatter);


        TransactionLine transactionLine = new TransactionLine(lineId, userID, timeNow,
                ticker, priceNow, currencyOfStock, orderType, quantity);

        transactionRepository.writeTransactionLine(transactionLine);

    }

    @Override
    public List<TransactionLine> getUserTransactionHistory(String userID) {
        List<TransactionLine> allTransactionLines = transactionRepository.getTransactions();
        ArrayList<TransactionLine> userTransactionLines = new ArrayList<>();

        for (TransactionLine transactionLine : allTransactionLines) {
            if (userID.equals(transactionLine.getUser_id())) {
                userTransactionLines.add(transactionLine);
            }
        }
        return userTransactionLines;
    }
}
