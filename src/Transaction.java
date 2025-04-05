import java.util.Date;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private Investment investment;
    private String type;
    private double amount;
    private double price;
    private final Date date;

    // Constructor
    // Default constructor
    public Transaction() {
        this.id = UUID.randomUUID();
        this.date = new Date();
    }

    // Constructor with parameters
    public Transaction(Investment investment, String type, double amount, double price) {
        this();
        this.investment = investment;
        this.type = type;
        this.amount = amount;
        this.price = price;
    }

    // Getters and Setters
    // id
    public UUID getId() {
        return id;
    }

    // investment
    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    // type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // amount
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    // price
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // date
    public Date getDate() {
        return date;
    }

    // Utility methods
    // Gets the unit price of the transaction
    public double getUnitPrice() {
        return price / amount;
    }
}
