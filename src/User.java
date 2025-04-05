import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Collections;

public class User {
    private final UUID id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Date birthday;
    private final Date createdAt;
    private Date updatedAt;
    private boolean twoFactorAuth;
    private final List<Company> companies;
    private final List<Investment> investments;
    private final List<Alert> alerts;

    // Utility
    // Update the updatedAt field to the current date
    private void updateUpdatedAt() {
        this.updatedAt = new Date();
    }

    // Constructors
    // Default constructor
    public User() {
        this.id = UUID.randomUUID();
        this.companies = new ArrayList<Company>();
        this.investments = new ArrayList<Investment>();
        this.alerts = new ArrayList<Alert>();
        this.createdAt = new Date();
        this.twoFactorAuth = false;
    }

    // Constructor with parameters
    public User(String name, String email, String password, String phone, String address, Date birthday) {
        this();
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.birthday = birthday;
    }

    // Getters and Setters
    // id
    public UUID getId() {
        return id;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updateUpdatedAt();
    }

    // email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        updateUpdatedAt();
    }

    // password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        updateUpdatedAt();
    }

    // phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        updateUpdatedAt();
    }

    // address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        updateUpdatedAt();
    }

    // birthday
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        updateUpdatedAt();
    }

    // createdAt
    public Date getCreatedAt() {
        return createdAt;
    }

    // updatedAt
    public Date getUpdatedAt() {
        return updatedAt;
    }

    // twoFactorAuth
    public boolean isTwoFactorAuth() {
        return twoFactorAuth;
    }

    public void setTwoFactorAuth(boolean twoFactorAuth) {
        this.twoFactorAuth = twoFactorAuth;
        updateUpdatedAt();
    }

    // companies
    public List<Company> getCompanies() {
        return Collections.unmodifiableList(companies);
    }

    public void addCompany(Company company) {
        this.companies.add(company);
    }

    public void removeCompany(Company company) {
        this.companies.remove(company);
    }

    // investments
    public List<Investment> getInvestments() {
        return Collections.unmodifiableList(investments);
    }

    public void addInvestment(Investment investment) {
        this.investments.add(investment);
    }

    public void removeInvestment(Investment investment) {
        this.investments.remove(investment);
    }

    // alerts
    public List<Alert> getAlerts() {
        return Collections.unmodifiableList(alerts);
    }

    public void addAlert(Alert alert) {
        this.alerts.add(alert);
    }

    public void removeAlert(Alert alert) {
        this.alerts.remove(alert);
    }
}
