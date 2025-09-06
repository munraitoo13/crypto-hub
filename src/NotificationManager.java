import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotificationManager {
    private static NotificationManager instance;
    private List<Notification> notifications;

    private NotificationManager() {
        notifications = new ArrayList<>();
    }

    // Singleton pattern to ensure only one instance of NotificationManager
    public static NotificationManager getInstance() {
        if (instance == null) {
            instance = new NotificationManager();
        }
        return instance;
    }

    // Getter for notifications
    public List<Notification> getNotifications() {
        return notifications;
    }

    // Setter for notifications (optional, based on your design)
    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    // Method to add a notification to the list
    public void addNotification(Notification notification) {
        notifications.add(notification);
    }

    // Method to send notifications
    public void sendNotifications() {
        List<Notification> processedNotifications = new ArrayList<>();
        for (Notification notification : notifications) {
            if (notification.send()) {
                processedNotifications.add(notification);
            }
        }
        notifications.removeAll(processedNotifications);
    }

    // Method to notify the user about an alert
    public void notifyAlert(Alert alert) {
        User user = alert.getUser();
        Cryptocurrency cryptocurrency = alert.getCryptocurrency();

        String direction = alert.getAlertType().equalsIgnoreCase("above") ? "has reached or exceeded" : "has reached or dropped below";
        String message = String.format(
                "Price Alert: %s (%s) %s the target price of %.2f. Current price: %.2f.",
                cryptocurrency.getName(),
                cryptocurrency.getSymbol(),
                direction,
                alert.getTargetPrice(),
                cryptocurrency.getPrice()
        );

        List<String> channels = Arrays.asList("WhatsApp", "Telegram", "Email", "Platform");
        for (String channel : channels) {
            Notification notification = new Notification(user, message, channel);
            addNotification(notification);
        }

        sendNotifications();
    }

    // Override toString to describe the state of NotificationManager
    @Override
    public String toString() {
        return "NotificationManager managing " + notifications.size() + " notifications.";
    }
}