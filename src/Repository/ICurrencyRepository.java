package Repository;

import Model.Currency;

import java.util.List;
import java.util.Map;

public interface ICurrencyRepository {
    Map<String, Double> getCurrencies();
}
