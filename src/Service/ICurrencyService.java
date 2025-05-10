package Service;

import Model.Currency;
import Model.Stock;

import java.util.Map;

public interface ICurrencyService {
    double convertCurrency(double price, String listedCurrency, String selectedCurrency);
    double getRate(String currency);
    boolean currencyIsValid(String currency);
    Map<String, Double> getRates();
}
