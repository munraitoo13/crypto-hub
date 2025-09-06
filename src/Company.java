import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company extends BaseEntity {
    private String name;
    private String cnpj;
    private String type;
    private final List<Investment> investments;

    // Constructors
    public Company() {
        super();
        this.investments = new ArrayList<>();
    }

    public Company(String name, String cnpj, String type) {
        this();
        this.name = name;
        this.cnpj = cnpj;
        this.type = type;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; setUpdatedAtNow(); }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; setUpdatedAtNow(); }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; setUpdatedAtNow(); }

    public List<Investment> getInvestments() {
        return Collections.unmodifiableList(investments);
    }

    public void addInvestment(Investment investment) {
        this.investments.add(investment);
        setUpdatedAtNow();
    }

    public void removeInvestment(Investment investment) {
        this.investments.remove(investment);
        setUpdatedAtNow();
    }

    // Polimorfismo - Override
    @Override
    public String toString() {
        return "Company{name='" + name + "', cnpj='" + cnpj + "', type='" + type + "'}";
    }
}