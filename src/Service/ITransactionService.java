package Service;

import Model.TransactionLine;

import java.util.List;

public interface ITransactionService {

    void createTransactionLine(String userID, String ticker, String orderType, int quantity, String currency);

    List<TransactionLine> getUserTransactionHistory(String userID);
}
