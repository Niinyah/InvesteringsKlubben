package Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CurrencyRepository implements ICurrencyRepository{
    @Override
    public Map<String, Double> getCurrencies() {
        File file = new File("src/Data/currency.csv");
        Map<String, Double> exchangeRate = new HashMap<>();

        try {
            Scanner reader = new Scanner(file);
            String txtline = reader.nextLine();
            while (reader.hasNextLine()) {
                txtline = reader.nextLine();
                String[] splitOnComma = txtline.split(";");
                String[] commaFixForPrice = splitOnComma[2].split(",");
                String priceString;
                if (commaFixForPrice.length == 2) {
                    priceString = commaFixForPrice[0] + "." + commaFixForPrice[1];
                } else {
                    priceString = commaFixForPrice[0];
                }

                exchangeRate.put(splitOnComma[0], Double.parseDouble(priceString));

            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return exchangeRate;
    }
}
