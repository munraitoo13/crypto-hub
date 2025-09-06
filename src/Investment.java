import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Investment extends BaseEntity {
    private User user;
    private Company company;
    private Cryptocurrency cryptocurrency;
    private double amount;
    private double price;
    private final List<Transaction> transactions;

    // Constructors
    public Investment() {
        super();
        this.transactions = new ArrayList<>();
    }

    public Investment(User user, Company company, Cryptocurrency cryptocurrency, double amount, double price) {
        this();
        this.user = user;
        this.company = company;
        this.cryptocurrency = cryptocurrency;
        this.amount = amount;
        this.price = price;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setUpdatedAtNow();
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
        setUpdatedAtNow();
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
        setUpdatedAtNow();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        setUpdatedAtNow();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        setUpdatedAtNow();
    }

    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        setUpdatedAtNow();
    }

    // Utility methods
    public double getTotalValue() {
        return amount * price;
    }

    public double getCurrentValue() {
        return amount * cryptocurrency.getPrice();
    }

    public double getProfitOrLoss() {
        return getCurrentValue() - getTotalValue();
    }

    public double getProfitOrLossPercentage() {
        if (getTotalValue() == 0) {
            return 0;
        }
        return (getProfitOrLoss() / getTotalValue()) * 100;
    }

    // Overload: accepts a tolerance margin
    public double getProfitOrLossPercentage(double tolerance) {
        double percentage = getProfitOrLossPercentage();
        return (Math.abs(percentage) < tolerance) ? 0 : percentage;
    }

    // Override: returns a summary of the investment
    @Override
    public String toString() {
        return "Investment in " + cryptocurrency.getName() + " by " + user.getName() +
                " | Amount: " + amount +
                " | Purchase Price: " + price +
                " | Current Value: " + getCurrentValue() +
                " | Profit/Loss: " + getProfitOrLossPercentage() + "%";
    }
}