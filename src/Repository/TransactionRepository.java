package Repository;

import Model.TransactionLine;

import java.util.List;

public class TransactionRepository implements iTransactionRepository {
    @Override
    public List<TransactionLine> getTransactions(){
        return List.of();
    }

    @Override
    public void writeTransactionLine(TransactionLine transactionLine){

    }
}
