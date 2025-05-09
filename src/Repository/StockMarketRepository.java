package Repository;

import Model.Stock;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StockMarketRepository implements IStockMarketRepository {

    @Override
    public List<Stock> getStockMarket() {
        File file = new File("src/Data/stockMarket.csv");
        List<Stock> stocks = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Scanner reader = new Scanner(file);
            String txtLine = reader.nextLine();
            while (reader.hasNextLine()) {
                txtLine = reader.nextLine();
                String[] splitOnComma = txtLine.split(";");


                String[] commaFixForPrice = splitOnComma[3].split(",");
                String priceString;
                if (commaFixForPrice.length == 2) {
                    priceString = commaFixForPrice[0] + "." + commaFixForPrice[1];
                } else {
                    priceString = commaFixForPrice[0];
                }
                double price = Double.parseDouble(priceString);
                String[] commaFixForDividendYield = splitOnComma[6].split(",");
                String dividendYieldString;
                if (commaFixForDividendYield.length == 2) {
                    dividendYieldString = commaFixForDividendYield[0] + "." + commaFixForDividendYield[1];
                } else {
                    dividendYieldString = commaFixForDividendYield[0];
                }

                double dividendYield = Double.parseDouble(dividendYieldString);
                LocalDate lastUpdate = LocalDate.parse(splitOnComma[8], formatter);

                Stock stock = new Stock(splitOnComma[0], splitOnComma[1], splitOnComma[2], price, splitOnComma[4], splitOnComma[5], dividendYield, splitOnComma[7], lastUpdate);
                stocks.add(stock);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stocks;
    }
}
