package service;

import model.Credential;
import storage.FileManager;

import java.util.ArrayList;
import java.util.List;

public class PasswordManager {

    private final List<Credential> credentials;

    public PasswordManager() {
        credentials = new ArrayList<>();
        FileManager.loadCredentials(this);
    }

    public void addCredential(Credential credential) {

        credentials.add(credential);
        FileManager.saveCredentials(credentials);

        System.out.println("\nCredential added successfully.");
    }

    public void addCredentialSilently(Credential credential) {
        credentials.add(credential);
    }

    public void viewCredentials() {

        if (credentials.isEmpty()) {

            System.out.println("\nNo credentials found.");
            return;
        }

        for (Credential credential : credentials) {
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

    public boolean updateUsername(String website, String newUsername) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credential.setUsername(newUsername);
        FileManager.saveCredentials(credentials);

        return true;
    }

    public boolean updatePassword(String website, String newPassword) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credential.setPassword(newPassword);
        FileManager.saveCredentials(credentials);

        return true;
    }

    public boolean deleteCredential(String website) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credentials.remove(credential);
        FileManager.saveCredentials(credentials);

        return true;
    }

    public boolean credentialExists(String website) {

        return searchCredential(website) != null;
    }

    public List<Credential> getCredentials() {
        return credentials;
    }

    public int getTotalCredentials() {
        return credentials.size();
    }

    public int getWeakPasswordCount() {

        int count = 0;

        for (Credential credential : credentials) {

            String strength = security.PasswordStrength.checkStrength(
                    credential.getPassword()
            );

            if (!strength.equals("Strong") &&
                !strength.equals("Very Strong")) {

                count++;
            }
        }

        return count;
    }

    public int getStrongPasswordCount() {

        int count = 0;

        for (Credential credential : credentials) {

            String strength = security.PasswordStrength.checkStrength(
                    credential.getPassword()
            );

            if (strength.equals("Strong") ||
                strength.equals("Very Strong")) {

                count++;
            }
        }

        return count;
    }
}