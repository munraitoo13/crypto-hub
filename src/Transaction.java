import java.util.Date;
import java.util.UUID;

public class Transaction extends BaseEntity {
    private Investment investment;
    private String type;
    private double amount;
    private double price;
    private final Date date;

    // Construtor padrão
    public Transaction() {
        super();
        this.date = new Date();
    }

    // Construtor completo
    public Transaction(Investment investment, String type, double amount, double price) {
        this();
        this.investment = investment;
        this.type = type;
        this.amount = amount;
        this.price = price;
    }

    // Sobrecarga de construtor com menos parâmetros
    public Transaction(String type, double amount) {
        this();
        this.type = type;
        this.amount = amount;
        this.price = 0.0;
    }

    // Getters e Setters
    public Investment getInvestment() { return investment; }
    public void setInvestment(Investment investment) { this.investment = investment; setUpdatedAtNow(); }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; setUpdatedAtNow(); }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; setUpdatedAtNow(); }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; setUpdatedAtNow(); }

    public Date getDate() { return date; }

    // Método utilitário
    public double getUnitPrice() {
        return amount != 0 ? price / amount : 0;
    }

    // Override de toString
    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}