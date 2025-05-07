package Model;

import java.time.LocalDate;

public class TransactionLine {
    private String id;
    private String user_id;
    private String date;
    private String ticker;
    private double price;
    private String currency;
    private String orderType;
    private int quantity;

    public TransactionLine(String id, String user_id, String date, String ticker, double price, String currency, String orderType, int quantity) {
        this.id = id;
        this.user_id = user_id;
        this.date = date;
        this.ticker = ticker;
        this.price = price;
        this.currency = currency;
        this.orderType = orderType;
        this.quantity = quantity;
    }

    public String getId(){
        return id;
    }

    public String getUser_id(){
        return user_id;
    }

    public String getDate(){
        return date;
    }

    public String getTicker(){
        return ticker;
    }

    public double getPrice(){
        return price;
    }

    public String getCurrency(){
        return currency;
    }

    public String getOrderType(){
        return orderType;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%-12s %-8s %-9.2f %-8s %-8d %-8s", date, ticker, price, currency, quantity, orderType);
    }


}
