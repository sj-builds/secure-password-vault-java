import java.util.ArrayList;

import model.Credential;

public class PasswordManager {

    private ArrayList<Credential> credentials;

    public PasswordManager() {
        credentials = new ArrayList<>();
    }

    public void addCredential(Credential credential) {
        credentials.add(credential);
        System.out.println("\nCredential added successfully!");
    }

    // Used while loading from file
    public void addCredentialSilently(Credential credential) {
        credentials.add(credential);
    }

    public void viewCredentials() {

        if (credentials.isEmpty()) {
            System.out.println("\nNo credentials found.");
            return;
        }

        System.out.println("\n========== SAVED CREDENTIALS ==========");

        int index = 1;

        for (Credential credential : credentials) {

            System.out.println("Credential #" + index++);
            System.out.println(credential);

        }
    }

    public Credential searchCredential(String website) {

        for (Credential credential : credentials) {

            if (credential.getWebsite().equalsIgnoreCase(website)) {
                return credential;
            }

        }

        return null;
    }

    public boolean updatePassword(String website, String newPassword) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credential.setPassword(newPassword);
        return true;
    }

    public boolean updateUsername(String website, String newUsername) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credential.setUsername(newUsername);
        return true;
    }

    public boolean deleteCredential(String website) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credentials.remove(credential);
        return true;
    }

    public boolean credentialExists(String website) {
        return searchCredential(website) != null;
    }

    public int getTotalCredentials() {
        return credentials.size();
    }

    public ArrayList<Credential> getCredentials() {
        return credentials;
    }

}