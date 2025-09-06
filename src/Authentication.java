import java.util.Date;

public class Authentication extends BaseEntity {
    private User user;
    private String verificationCode;
    private Date expirationDate;
    private boolean verified;

    // Construtor padrão
    public Authentication(User user) {
        this.user = user;
        this.verified = false;
    }

    // Getters e Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        setUpdatedAtNow();
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
        setUpdatedAtNow();
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
        setUpdatedAtNow();
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
        setUpdatedAtNow();
    }

    // Sobrecarga (Overload)
    public void validateVerificationCode() {
        validateVerificationCode(this.verificationCode);
    }

    // Validação do código fornecido
    public void validateVerificationCode(String code) {
        // Lógica para validar o código (a ser implementada)
        if (this.verificationCode != null && this.verificationCode.equals(code)) {
            this.verified = true;
        } else {
            this.verified = false;
        }
        setUpdatedAtNow();
    }

    // Gerar o código de verificação (simulado)
    public void generateVerificationCode() {
        // Lógica fictícia de geração
        this.verificationCode = "123456"; // Substituir por lógica real de TOTP
        this.expirationDate = new Date(System.currentTimeMillis() + 5 * 60 * 1000); // 5 minutos de validade
        setUpdatedAtNow();
    }

    // Envio do código (simulado)
    public void sendVerificationCode() {
        // Lógica de envio (simulado)
        System.out.println("Enviando código para o usuário " + user.getName() + ": " + verificationCode);
    }

    // Sobrescrita (Override)
    @Override
    public String toString() {
        return "Authentication [User=" + user.getName() +
                ", Verified=" + verified +
                ", ExpirationDate=" + expirationDate + "]";
    }
}