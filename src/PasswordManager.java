import java.util.ArrayList;

public class PasswordManager {

    private ArrayList<Credential> credentials;

    public PasswordManager() {
        credentials = new ArrayList<>();
    }

    // Add a new credential
    public void addCredential(Credential credential) {
        credentials.add(credential);
        System.out.println("\nCredential added successfully!");
    }

    // View all credentials
    public void viewCredentials() {

        if (credentials.isEmpty()) {
            System.out.println("\nNo credentials found.");
            return;
        }

        System.out.println("\n===== SAVED CREDENTIALS =====");

        for (Credential credential : credentials) {
            System.out.println(credential);
        }
    }

    // Search credential by website
    public Credential searchCredential(String website) {

        for (Credential credential : credentials) {

            if (credential.getWebsite().equalsIgnoreCase(website)) {
                return credential;
            }

        }

        return null;
    }

    // Update password
    public boolean updatePassword(String website, String newPassword) {

        Credential credential = searchCredential(website);

        if (credential != null) {
            credential.setPassword(newPassword);
            return true;
        }

        return false;
    }

    // Update username
    public boolean updateUsername(String website, String newUsername) {

        Credential credential = searchCredential(website);

        if (credential != null) {
            credential.setUsername(newUsername);
            return true;
        }

        return false;
    }

    // Delete credential
    public boolean deleteCredential(String website) {

        Credential credential = searchCredential(website);

        if (credential != null) {
            credentials.remove(credential);
            return true;
        }

        return false;
    }

    // Check if website already exists
    public boolean credentialExists(String website) {

        return searchCredential(website) != null;

    }

    // Total credentials
    public int getTotalCredentials() {

        return credentials.size();

    }

    // Return all credentials
    public ArrayList<Credential> getCredentials() {

        return credentials;

    }

}