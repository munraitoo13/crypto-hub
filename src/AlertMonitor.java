import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AlertMonitor {
    private static AlertMonitor instance;
    private List<Alert> alerts;
    private final NotificationManager notificationManager;

    private AlertMonitor() {
        alerts = new ArrayList<>();
        notificationManager = NotificationManager.getInstance();
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new VerifyAlerts(), 0, 5 * 60 * 1000);
    }

    public static AlertMonitor getInstance() {
        if (instance == null) {
            instance = new AlertMonitor();
        }
        return instance;
    }

    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    public void removeAlert(Alert alert) {
        alerts.remove(alert);
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    // Opcional: caso você queira substituir a lista inteira de alertas
    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    public List<Alert> getAlertByUser(User user) {
        List<Alert> userAlerts = new ArrayList<>();
        for (Alert alert : alerts) {
            if (alert.getUser().getId().equals(user.getId())) {  // Comparação corrigida
                userAlerts.add(alert);
            }
        }
        return userAlerts;
    }

    private class VerifyAlerts extends TimerTask {
        @Override
        public void run() {
            List<Alert> triggeredAlerts = new ArrayList<>();
            for (Alert alert : alerts) {
                if (alert.isTriggered()) {
                    triggeredAlerts.add(alert);
                    notificationManager.notifyAlert(alert);
                }
            }
            alerts.removeAll(triggeredAlerts);
        }
    }
}
