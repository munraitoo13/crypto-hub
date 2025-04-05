import java.util.*;

public class Investment {
    private final UUID id;
    private User user;
    private Company company;
    private Cryptocurrency cryptocurrency;
    private double amount;
    private double price;
    private Date date;
    private final List<Transaction> transactions;

    // Constructors
    // Default constructor
    public Investment() {
        this.id = UUID.randomUUID();
        this.date = new Date();
        this.transactions = new ArrayList<Transaction>();
    }

    // Constructor with parameters
    public Investment(User user, Company company, Cryptocurrency cryptocurrency, double amount, double price) {
        this();
        this.user = user;
        this.company = company;
        this.cryptocurrency = cryptocurrency;
        this.amount = amount;
        this.price = price;
    }

    // Getters and Setters
    // id
    public UUID getId() {
        return id;
    }

    // user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // company
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // cryptocurrency
    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
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

    public void setDate(Date date) {
        this.date = date;
    }

    // transactions
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    // Utility methods
    // Gets the total value of the investment
    public double getTotalValue() {
        return amount * price;
    }

    // Gets the current value of the investment
    public double getCurrentValue() {
        return amount * cryptocurrency.getPrice();
    }

    // Gets the profit or loss of the investment
    public double getProfitOrLoss() {
        return getCurrentValue() - getTotalValue();
    }

    // Gets percentage of profit or loss
    public double getProfitOrLossPercentage() {
        if (getTotalValue() == 0) {
            return 0;
        }
        return (getProfitOrLoss() / getTotalValue()) * 100;
    }
}
