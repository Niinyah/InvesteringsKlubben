package Repository;

import Model.Currency;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CurrencyRepository implements ICurrencyRepository{
    @Override
    public Map<String, Double> getCurrencies() {
        File file = new File("src/Data/currency.csv");
        List<Currency> currencies = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Map<String, Double> exchangeRate = new HashMap<>();

        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String lines[] = line.split(";");
                String[] line2 = lines[2].split(",");
                String tal;
                if (line2.length == 2) {
                    tal = line2[0] + "." + line2[1];
                } else {
                    tal = line2[0];
                }

                Currency currency = new Currency(
                        lines[0],
                        lines[1],
                        Double.parseDouble(tal),
                        LocalDate.parse(lines[3], formatter));
                currencies.add(currency);
                exchangeRate.put(lines[0], Double.parseDouble(tal));

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return exchangeRate;
    }
}
