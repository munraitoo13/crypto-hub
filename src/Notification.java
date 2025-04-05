import java.util.Date;
import java.util.UUID;

public class Notification {
    private final UUID id;
    private User user;
    private String message;
    private String channel;
    private boolean isSent;
    private boolean isRead;
    private final Date createdAt;
    private Date sentAt;
    private Date readAt;
    private Alert alert;

    // Constructor
    // Default constructor
    public Notification() {
        this.id = UUID.randomUUID();
        this.createdAt = new Date();
        this.isSent = false;
        this.isRead = false;
    }

    // Constructor with parameters
    public Notification(User user, String message, String channel) {
        this();
        this.user = user;
        this.message = message;
        this.channel = channel;
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

    // message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // channel
    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    // isSent
    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean isSent) {
        this.isSent = isSent;
        if (isSent) {
            this.sentAt = new Date();
        }
    }

    // isRead
    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
        if (isRead) {
            this.readAt = new Date();
        }
    }

    // createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // sentAt
    public Date getSentAt() {
        return sentAt;
    }

    // readAt
    public Date getReadAt() {
        return readAt;
    }

    // alert
    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    // Utility methods
    // Send notification
    public boolean send() {
        // Logic to send the notification based on the channel

        this.isSent = true;
        this.sentAt = new Date();
        return true;
    }

    // Mark notification as read
    public void markAsRead() {
        this.isRead = true;
        this.readAt = new Date();
    }
}
