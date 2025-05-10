package Service;

import Repository.ICurrencyRepository;
import java.util.Map;

public class CurrencyService implements ICurrencyService {

    private final Map<String, Double> rates;

    public CurrencyService(ICurrencyRepository currencyRepository) {
        rates = currencyRepository.getCurrencies();
    }

    private double getPriceInDKK(double price, String currency) {
        var rate = rates.get(currency);
        return price * rate;
    }

    private double getPriceFromDKK(double price, String currency) {
        var rate = rates.get(currency);
        return price / rate;
    }

    public double convertCurrency(double price, String listedCurrency, String selectedCurrency) {
        double priceInDKK = getPriceInDKK(price, listedCurrency);
        return getPriceFromDKK(priceInDKK, selectedCurrency);
    }

    public double getRate(String currency) {
        return rates.get(currency);
    }
    public Map<String, Double> getRates(){
        return rates;
    }
    public boolean currencyIsValid(String userInput) {
        return rates.containsKey(userInput);
    }


}
