package Service;

import Model.Portfolio;

import java.util.List;

public interface IPortfolioService {

    Portfolio createPortfolio(String UserID);
    List<Portfolio> adminPortfolios();
    // lav forskellige sorteringer på samlet portfoilios

}
