package Comparators;

import Model.Stock;

import java.util.Comparator;

public class SectorComparator implements Comparator<Stock> {
    @Override
    public int compare(Stock o1, Stock o2) {
        return o1.getSector().compareTo(o2.getSector());
    }
}
