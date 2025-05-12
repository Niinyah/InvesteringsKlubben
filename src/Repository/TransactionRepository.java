package Repository;

import Model.TransactionLine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionRepository implements ITransactionRepository {
    @Override
    public List<TransactionLine> getTransactions(){
        File file = new File("src/Data/transactions.csv");
        List<TransactionLine> transactionLines = new ArrayList<>();

        try {
            Scanner reader = new Scanner(file);
            String txtLine = reader.nextLine();
            while (reader.hasNextLine()){
                txtLine = reader.nextLine();
                String[] txtLineSplit = txtLine.split(";");

                String[] splitOnComma = txtLineSplit[4].split(",");
                String commaFix;
                if (splitOnComma.length == 2){
                    commaFix = splitOnComma[0] + "." + splitOnComma[1];
                } else {
                    commaFix = splitOnComma[0];
                }

                TransactionLine transactionLine = new TransactionLine(txtLineSplit[0], txtLineSplit[1],
                        txtLineSplit[2], txtLineSplit[3], Double.parseDouble(commaFix),
                        txtLineSplit[5], txtLineSplit[6], Integer.parseInt(txtLineSplit[7]));

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

            outFile.write("\n" +line.getId() + ";" + line.getUser_id() + ";" +
                    line.getDate() + ";" + line.getTicker() + ";" + line.getPrice()
                    + ";" + line.getCurrency() + ";" + line.getOrderType() + ";" + line.getQuantity());
            outFile.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }

    }
}
