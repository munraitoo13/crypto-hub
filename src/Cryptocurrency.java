import java.util.Date;
import java.util.UUID;

public class Cryptocurrency extends BaseEntity {
    private String name;
    private String symbol;
    private double previousPrice;
    private double price;
    private Date lastUpdated;

    // Default constructor
    public Cryptocurrency() {
        super();
        this.lastUpdated = new Date();
    }

    // Constructor with parameters
    public Cryptocurrency(String name, String symbol, double previousPrice, double price) {
        this();
        this.name = name;
        this.symbol = symbol;
        this.previousPrice = previousPrice;
        this.price = price;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        this.previousPrice = this.price;
        this.price = price;
        this.lastUpdated = new Date();
        setUpdatedAtNow();
    }

    public double getPreviousPrice() { return previousPrice; }

    public Date getLastUpdated() { return lastUpdated; }

    // Overloaded method
    public double getPriceChangePercentage() {
        if (previousPrice == 0) return 0;
        return ((price - previousPrice) / previousPrice) * 100;
    }

    public double getPriceChangePercentage(double referencePrice) {
        if (referencePrice == 0) return 0;
        return ((price - referencePrice) / referencePrice) * 100;
    }

    // Overridden method
    @Override
    public String toString() {
        return String.format("%s (%s) - Price: %.2f", name, symbol, price);
    }
}

