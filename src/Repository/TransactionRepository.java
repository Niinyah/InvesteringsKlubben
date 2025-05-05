package Repository;

import Model.TransactionLine;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionRepository implements ITransactionRepository {
    @Override
    public List<TransactionLine> getTransactions(){
        File file = new File("src/Data/transactions.csv");
        List<TransactionLine> transactionLines = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Scanner reader = new Scanner(file);
            String line = reader.nextLine();
            while (reader.hasNextLine()){
                line = reader.nextLine();
                String[] lines = line.split(";");

                LocalDate lastUpdate = LocalDate.parse(lines[2], formatter);


                String[] col4 = lines[4].split(",");
                String commaFix;
                if (col4.length == 2){
                    commaFix = col4[0] + "." + col4[1];
                } else {
                    commaFix = col4[0];
                }


                TransactionLine transactionLine = new TransactionLine(lines[0], lines[1], lastUpdate, lines[3],
                        Double.parseDouble(commaFix), lines[5], lines[6], Integer.parseInt(lines[7]));
                transactionLines.add(transactionLine);
            }
            reader.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return transactionLines;
    }

    @Override
    public void writeTransactionLine(TransactionLine line){
        try {
            File file = new File("src/Data/transactions.csv");
            FileWriter outFile = new FileWriter(file, true);
            //String split = ";";
            outFile.write("\n" +line.getId() + ";" + line.getUser_id() + ";" +
                    line.getDate() + ";" + line.getTicker() + ";" + line.getPrice()
                    + ";" + line.getCurrency() + ";" + line.getOrderType() + ";" + line.getQuantity());
            outFile.close();
            /*
        Path path = Paths.get("transactions.csv");
            try {BufferedWriter writer = Files.newBufferedWriter(
                                            path, StandardOpenOption.APPEND);
                writer.write(line.getId() + ";" + line.getUser_id() + ";" +
                line.getDate() + ";" + line.getTicker() + ";" + line.getPrice()
                + ";" + line.getCurrency() + ";" + line.getOrderType() + ";" + line.getOrderType());


             */
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }
}
