import java.util.Date;
import java.util.UUID;

public class Notification extends BaseEntity {
    private User user;
    private String message;
    private String channel;
    private boolean isSent;
    private boolean isRead;
    private Date sentAt;
    private Date readAt;
    private Alert alert;

    // Constructors
    public Notification() {
        super();
        this.isSent = false;
        this.isRead = false;
    }

    public Notification(User user, String message, String channel) {
        this();
        this.user = user;
        this.message = message;
        this.channel = channel;
    }

    // Getters and Setters
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; setUpdatedAtNow(); }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; setUpdatedAtNow(); }

    public String getChannel() { return channel; }
    public void setChannel(String channel) { this.channel = channel; setUpdatedAtNow(); }

    public boolean isSent() { return isSent; }
    public void setSent(boolean isSent) {
        this.isSent = isSent;
        if (isSent) this.sentAt = new Date();
        setUpdatedAtNow();
    }

    public boolean isRead() { return isRead; }
    public void setRead(boolean isRead) {
        this.isRead = isRead;
        if (isRead) this.readAt = new Date();
        setUpdatedAtNow();
    }

    public Date getSentAt() { return sentAt; }
    public Date getReadAt() { return readAt; }

    public Alert getAlert() { return alert; }
    public void setAlert(Alert alert) { this.alert = alert; setUpdatedAtNow(); }

    // Utility Methods
    public boolean send() {
        this.isSent = true;
        this.sentAt = new Date();
        setUpdatedAtNow();
        return true;
    }

    // Overload - Allow sending with a custom message
    public boolean send(String customMessage) {
        this.message = customMessage;
        return send();
    }

    public void markAsRead() {
        this.isRead = true;
        this.readAt = new Date();
        setUpdatedAtNow();
    }

    // Override toString for better readability
    @Override
    public String toString() {
        return "Notification to " + (user != null ? user.getName() : "Unknown User") +
                " via " + channel + ": " + message;
    }
}
