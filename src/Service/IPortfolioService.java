package Service;

import Model.Portfolio;

import java.util.List;

public interface IPortfolioService {

    Portfolio createPortfolio(String userID, String currency);
    List<Portfolio> adminPortfolios(String currency);
    // lav forskellige sorteringer på samlet portfoilios
    boolean canPurchase(String userID, String ticker, int quantity, String currency);
    boolean canSell(String userID, String ticker, int quantity, String currency);
    List<Portfolio> portfoliosSortedByInvestmentValue(String currency);
}
