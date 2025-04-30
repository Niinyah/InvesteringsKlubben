package Model;

import java.time.LocalDate;

public class TransactionLine {
    private String id;
    private String user_id;
    private LocalDate date;
    private String ticker;
    private double price;
    private String currency;
    private String orderType;
    private int quantity;

    public TransactionLine(String id, String user_id, LocalDate date, String ticker, double price, String currency, String orderType, int quantity) {
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

    public LocalDate getDate(){
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
}
