package Model;

public class PortfolioLine {
    private final String ticker;
    private final int quantity;
    private final double sharePrice;
    private final double value;
    private final String sector;


    public PortfolioLine(String ticker, int quantity, double sharePrice, String sector) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.sharePrice = sharePrice;
        value = sharePrice * quantity;
        this.sector = sector;
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

    public String getSector(){
        return sector;
    }

    @Override
    public String toString() {
        return String.format("Ticker: %s | Quantity: %d | Price: %.2f | Value: %.2f | sector: %.2s", ticker, quantity, sharePrice, value, sector);

    }
}
