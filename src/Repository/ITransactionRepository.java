package Repository;

import Model.TransactionLine;

import java.util.List;

public interface ITransactionRepository {
    List<TransactionLine> getTransactions();
    void writeTransactionLine(TransactionLine transactionLine);
}
