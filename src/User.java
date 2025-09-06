import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class User extends BaseEntity {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private Date birthday;
    private boolean twoFactorAuth;
    private final List<Company> companies;
    private final List<Investment> investments;
    private final List<Alert> alerts;

    // Constructors
    public User() {
        super();
        this.companies = new ArrayList<>();
        this.investments = new ArrayList<>();
        this.alerts = new ArrayList<>();
        this.twoFactorAuth = false;
    }

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
    public String getName() { return name; }
    public void setName(String name) { this.name = name; setUpdatedAtNow(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; setUpdatedAtNow(); }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; setUpdatedAtNow(); }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; setUpdatedAtNow(); }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; setUpdatedAtNow(); }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; setUpdatedAtNow(); }

    public boolean isTwoFactorAuth() { return twoFactorAuth; }
    public void setTwoFactorAuth(boolean twoFactorAuth) { this.twoFactorAuth = twoFactorAuth; setUpdatedAtNow(); }

    public List<Company> getCompanies() { return Collections.unmodifiableList(companies); }
    public void addCompany(Company company) { this.companies.add(company); }
    public void addCompany(String name, String cnpj, String type) { this.companies.add(new Company(name, cnpj, type)); }
    public void removeCompany(Company company) { this.companies.remove(company); }

    public List<Investment> getInvestments() { return Collections.unmodifiableList(investments); }
    public void addInvestment(Investment investment) { this.investments.add(investment); }
    public void removeInvestment(Investment investment) { this.investments.remove(investment); }

    public List<Alert> getAlerts() { return Collections.unmodifiableList(alerts); }
    public void addAlert(Alert alert) { this.alerts.add(alert); }
    public void removeAlert(Alert alert) { this.alerts.remove(alert); }

    // Override do toString para representar o usuário
    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "', twoFactorAuth=" + twoFactorAuth + "}";
    }
}