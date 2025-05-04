package Repository;

import Model.Stock;
import Model.TransactionLine;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionRepository implements iTransactionRepository {
    @Override
    public List<TransactionLine> getTransactions(){
        File file = new File("src/Data/transactions.csv");
        List<TransactionLine> transactionLines = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try {
            Scanner reader = new Scanner(file);
            String line;
            while (reader.hasNextLine()){
                line = reader.nextLine();
                String[] lines = line.split(";");

                LocalDate lastUpdate = LocalDate.parse(lines[2], formatter);


                TransactionLine transactionLine = new TransactionLine(lines[0], lines[1], lastUpdate, lines[3], Double.parseDouble(lines[4]), lines[5], lines[6], Integer.parseInt(lines[7]));
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
            outFile.write(line.getId() + ";" + line.getUser_id() + ";" +
                    line.getDate() + ";" + line.getTicker() + ";" + line.getPrice()
                    + ";" + line.getCurrency() + ";" + line.getOrderType() + ";" + line.getOrderType());
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }
}
