package Service;

import Model.Currency;
import Repository.ICurrencyRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CurrencyService implements ICurrencyService{

    private final ICurrencyRepository currencyRepository;

    public CurrencyService(ICurrencyRepository currencyRepository){
        this.currencyRepository = currencyRepository;
    }
    @Override
    public Currency getCurrency(String currency){
        List<Currency> currencies = currencyRepository.getCurrencies();
        for (Currency c : currencies){
            if (c.getCurrency().equalsIgnoreCase(currency)){
                return c;
            }
        }
        return currencies.getFirst();
    }
}
