import java.util.Date;
import java.util.UUID;

public class Alert extends BaseEntity {
    private User user;
    private Cryptocurrency cryptocurrency;
    private double targetPrice;
    private String alertType;
    private boolean isActive;
    private boolean isTriggered;
    private Date triggeredAt;

    // Default constructor
    public Alert() {
        super();
        this.isActive = true;
        this.isTriggered = false;
    }

    // Constructor with parameters
    public Alert(User user, Cryptocurrency cryptocurrency, double targetPrice, String alertType) {
        this();
        this.user = user;
        this.cryptocurrency = cryptocurrency;
        this.targetPrice = targetPrice;
        this.alertType = alertType;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setUpdatedAtNow();
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
        setUpdatedAtNow();
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
        setUpdatedAtNow();
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
        setUpdatedAtNow();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
        setUpdatedAtNow();
    }

    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
        if (isTriggered) {
            this.triggeredAt = new Date();
            setUpdatedAtNow();
        }
    }

    public Date getTriggeredAt() {
        return triggeredAt;
    }

    // Overloaded Methods (Polimorfismo Estático)
    public void triggerAlert() {
        triggerAlert(false);
    }

    public void triggerAlert(boolean logAction) {
        if (!isActive || isTriggered || cryptocurrency == null) {
            return;
        }

        double currentPrice = cryptocurrency.getPrice();

        switch (alertType.toLowerCase()) {
            case "above":
                if (currentPrice >= targetPrice) {
                    setTriggered(true);
                    if (logAction) {
                        System.out.println("Alert triggered: price is above target.");
                    }
                }
                break;
            case "below":
                if (currentPrice <= targetPrice) {
                    setTriggered(true);
                    if (logAction) {
                        System.out.println("Alert triggered: price is below target.");
                    }
                }
                break;
        }
    }

    // Overridden toString Method (Polimorfismo Dinâmico)
    @Override
    public String toString() {
        return "Alert{" +
                "user=" + (user != null ? user.getName() : "null") +
                ", cryptocurrency=" + (cryptocurrency != null ? cryptocurrency.getName() : "null") +
                ", targetPrice=" + targetPrice +
                ", alertType='" + alertType + '\'' +
                ", isActive=" + isActive +
                ", isTriggered=" + isTriggered +
                ", triggeredAt=" + (triggeredAt != null ? triggeredAt.toString() : "Not Triggered Yet") +
                '}';
    }
}
