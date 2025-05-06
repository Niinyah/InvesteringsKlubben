package Service;

import Model.Portfolio;

import java.util.List;

public interface IPortfolioService {

    Portfolio createPortfolio(String userID);
    List<Portfolio> adminPortfolios();
    // lav forskellige sorteringer på samlet portfoilios
    boolean canPurchase(String userID, String ticker, int quantity);
    boolean canSell(String userID, String ticker, int quantity);
}
