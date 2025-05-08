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
            String line = reader.nextLine();
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] lines = line.split(";");


                String[] line3 = lines[3].split(",");
                String tal;
                if (line3.length == 2) {
                    tal = line3[0] + "." + line3[1];
                } else {
                    tal = line3[0];
                }
                double price = Double.parseDouble(tal);
                String[] line6 = lines[6].split(",");
                String dy;
                if (line6.length == 2) {
                    dy = line6[0] + "." + line6[1];
                } else {
                    dy = line6[0];
                }

                double dividendYield = Double.parseDouble(dy);
                LocalDate lastUpdate = LocalDate.parse(lines[8], formatter);

                Stock stock = new Stock(lines[0], lines[1], lines[2], price, lines[4], lines[5], dividendYield, lines[7], lastUpdate);
                stocks.add(stock);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return stocks;
    }
}
