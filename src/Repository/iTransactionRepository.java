package Repository;

import Model.TransactionLine;

import java.util.List;

public interface iTransactionRepository {
    List<TransactionLine> getTransactions();
    void writeTransactionLine(TransactionLine transactionLine);
}
