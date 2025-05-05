package Service;

import Model.Portfolio;
import Model.PortfolioLine;
import Model.Stock;
import Model.TransactionLine;

import java.util.HashMap;
import java.util.List;

public class PortfolioService implements IPortfolioService {

  private ITransactionService transactionService;
  private IStockMarketService stockMarketService;
  private IUserService userService;

  public PortfolioService (ITransactionService transactionService, IStockMarketService stockMarketService, IUserService userService ){
      this.transactionService = transactionService;
      this.stockMarketService = stockMarketService;
      this.userService = userService;
  }



    @Override
    public Portfolio createPortfolio(String userID) {
        List<TransactionLine> transactionLines = transactionService.getUserTransactionHistory(userID);
        HashMap<String, Integer>  stocks = new HashMap<>();

        double sold = 0;
        double bought = 0;

        for (TransactionLine t : transactionLines){
            int quantity = stocks.get(t.getTicker());
            stocks.put(t.getTicker(), quantity +t.getQuantity());
            if (t.getOrderType().equals("buy")){
                bought += t.getQuantity() * t.getPrice();
            } else {
                sold += t.getQuantity() * t.getPrice();
            }
        }

        double investmentValue = 0;

        for (String s : stocks.keySet()){
            investmentValue += stockMarketService.getPrice(s) * stocks.get(s);
        }
        double balance = userService.getInitialCash(userID) + sold - bought;
        double equity = balance + investmentValue;
        Portfolio portfolio = new Portfolio(userService.getFullName(userID), balance, investmentValue, equity , transactionLines, stocks);

        for (String s : stocks.keySet()){
            double sharePrice = stockMarketService.getPrice(s);
            portfolio.setPortfolioLines(new PortfolioLine(s, stocks.get(s),sharePrice ));
        }
        return portfolio;
    }

    @Override
    public List<Portfolio> adminPortfolios() {
        return List.of();
    }
}
