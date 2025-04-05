import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Company {
    private final UUID id;
    private String name;
    private String cnpj;
    private String type;
    private final List<Investment> investments;

    // Constructors
    // Default constructor
    public Company() {
        this.id = UUID.randomUUID();
        this.investments = new ArrayList<Investment>();
    }

    // Constructor with parameters
    public Company(String name, String cnpj, String type) {
        this();
        this.name = name;
        this.cnpj = cnpj;
        this.type = type;
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
    }

    // cnpj
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    // type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
