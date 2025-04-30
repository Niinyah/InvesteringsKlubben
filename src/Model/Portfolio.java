package Model;

import java.util.HashMap;
import java.util.List;

public class Portfolio {
    private String user;
    private double balance;
    private List<TransactionLine> history;
    private HashMap<Stock, Integer> stocks;
    private double equity;
}
