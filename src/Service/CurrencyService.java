package Service;

import Repository.ICurrencyRepository;
import java.util.Map;

public class CurrencyService implements ICurrencyService {

    private final Map<String, Double> allRates;

    public CurrencyService(ICurrencyRepository currencyRepository) {
        allRates = currencyRepository.getCurrencies();
    }

    private double getPriceInDKK(double price, String currency) {
        double rate = allRates.get(currency);
        return price * rate;
    }

    private double getPriceFromDKK(double price, String currency) {
        double rate = allRates.get(currency);
        return price / rate;
    }

    public double convertCurrency(double price, String listedCurrency, String selectedCurrency) {
        double priceInDKK = getPriceInDKK(price, listedCurrency);
        return getPriceFromDKK(priceInDKK, selectedCurrency);
    }

    public double getRate(String currency) {
        return allRates.get(currency);
    }
    public Map<String, Double> getRates(){
        return allRates;
    }
    public boolean currencyIsValid(String userInput) {
        return allRates.containsKey(userInput);
    }


}
