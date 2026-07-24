import java.time.LocalDateTime;

public class Credential {

    private String website;
    private String username;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Credential(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
        this.updatedAt = LocalDateTime.now();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        this.updatedAt = LocalDateTime.now();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public String getMaskedPassword() {

        if (password.length() <= 4) {
            return "*".repeat(password.length());
        }

        return "*".repeat(password.length() - 4)
                + password.substring(password.length() - 4);
    }

    @Override
    public String toString() {

        return "=========================================\n"
                + "Website : " + website + "\n"
                + "Username: " + username + "\n"
                + "Password: " + getMaskedPassword() + "\n"
                + "Created : " + createdAt + "\n"
                + "Updated : " + updatedAt + "\n"
                + "=========================================";
    }
}