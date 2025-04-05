import java.util.Date;

public class Authentication {
    private User user;
    private String verificationCode;
    private Date expirationDate;
    private boolean verified;

    // Constructor
    public Authentication(User user) {
        this.user = user;
        this.verified = false;
    }

    // Getters and Setters
    // user
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // verificationCode
    public String getVerificationCode() {
        return verificationCode;
    }

    // expirationDate
    public Date getExpirationDate() {
        return expirationDate;
    }

    // verified
    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    // Utility methods
    // Generate a verification code
    public void generateVerificationCode() {
        // Logic to generate a TOTP
    }

    // Check if the verification code is valid
    public void validateVerificationCode(String code) {
        // Logic to check if the provided TOTP matches the generated code
    }

    // Send verification code to user
    public void sendVerificationCode() {
        // Logic to send the verification code based on the channel chosen
    }
}
