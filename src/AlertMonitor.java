import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AlertMonitor {
    private static AlertMonitor instance;
    private final List<Alert> alerts;
    private final NotificationManager notificationManager;

    private AlertMonitor() {
        alerts = new ArrayList<>();
        notificationManager = NotificationManager.getInstance();
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new VerifyAlerts(), 0, 5 * 60 * 1000);
    }

    // Singleton pattern to ensure only one instance of AlertMonitor
    public static AlertMonitor getInstance() {
        if (instance == null) {
            instance = new AlertMonitor();
        }

        return instance;
    }

    // Method to add an alert to the list
    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    // Method to remove an alert from the list
    public void removeAlert(Alert alert) {
        alerts.remove(alert);
    }

    // Method to get the list of alerts
    public List<Alert> getAlerts() {
        return alerts;
    }

    // Method to get the alert by user
    public List<Alert> getAlertByUser(User user) {
        List<Alert> userAlerts = new ArrayList<>();

        for (Alert alert : alerts) {
            if (alert.getUser().getId() == user.getId()) {
                userAlerts.add(alert);
            }
        }

        return userAlerts;
    }

    // Method to verify alerts
    private class VerifyAlerts extends TimerTask {
        @Override
        public void run() {
            List<Alert> triggeredAlerts = new ArrayList<>();

            for (Alert alert : alerts) {
                if (alert.isTriggered()) {
                    triggeredAlerts.add(alert);
                    notificationManager.notifyAlert(alert);
                    removeAlert(alert);
                }
            }

            alerts.removeAll(triggeredAlerts);
        }
    }
}
