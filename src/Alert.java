import java.util.Date;
import java.util.UUID;

public class Alert {
    private final UUID id;
    private User user;
    private Cryptocurrency cryptocurrency;
    private double targetPrice;
    private String alertType;
    private boolean isActive;
    private boolean isTriggered;
    private Date createdAt;
    private Date triggeredAt;

    // Constructor
    // Default constructor
    public Alert() {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
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

    // cryptocurrency
    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    // targetPrice
    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    // alertType
    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    // isActive
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    // isTriggered
    public boolean isTriggered() {
        return isTriggered;
    }

    public void setTriggered(boolean isTriggered) {
        this.isTriggered = isTriggered;
        if (isTriggered) {
            this.triggeredAt = new Date();
        }
    }

    // createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // triggeredAt
    public Date getTriggeredAt() {
        return triggeredAt;
    }

    // Utility methods
    // Trigger alert
    public void triggerAlert() {
        if (!isActive || isTriggered || cryptocurrency == null) {
            return;
        }

        double currentPrice = cryptocurrency.getPrice();

        switch (alertType.toLowerCase()) {
            case "above":
                if (currentPrice >= targetPrice) {
                    setTriggered(true);
                }
                break;
            case "below":
                if (currentPrice <= targetPrice) {
                    setTriggered(true);
                }
                break;
        }
    }
}