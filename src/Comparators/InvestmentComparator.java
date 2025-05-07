package Comparators;

import Model.Portfolio;
import Model.Stock;

import java.util.Comparator;

public class InvestmentComparator implements Comparator<Portfolio> {
    @Override
    public int compare(Portfolio o1, Portfolio o2) {
        return Double.compare(o1.getInvestmentValue(), o2.getInvestmentValue());
    }
}