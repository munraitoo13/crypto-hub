import java.util.Date;
import java.util.UUID;

public class Cryptocurrency {
    private final UUID id;
    private String name;
    private String symbol;
    private double previousPrice;
    private double price;
    private Date lastUpdated;

    // Constructors
    // Default constructor
    public Cryptocurrency() {
        this.id = UUID.randomUUID();
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
    // id
    public UUID getId() {
        return id;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // symbol
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    // price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.previousPrice = this.price;
        this.price = price;
        this.lastUpdated = new Date();
    }

    // previousPrice
    public double getPreviousPrice() {
        return previousPrice;
    }

    // lastUpdated
    public Date getLastUpdated() {
        return lastUpdated;
    }

    // Utility methods
    // Price change percentage
    public double getPriceChangePercentage() {
        if (previousPrice == 0) {
            return 0;
        }
        return ((price - previousPrice) / previousPrice) * 100;
    }
}
