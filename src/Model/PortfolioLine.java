package Model;

public class PortfolioLine {
    private String ticker;
    private int quantity;
    private double sharePrice;
    private double value;


    public PortfolioLine(String ticker, int quantity, double sharePrice) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.sharePrice = sharePrice;
        value = sharePrice * quantity;
    }

    public String getTicker() {
        return ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSharePrice() {
        return sharePrice;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Ticker: %s | Quantity: %d | Price: %.2f | Value: %.2f", ticker, quantity, sharePrice, value);

    }
}
