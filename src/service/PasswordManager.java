package service;

import model.Credential;
import security.PasswordStrength;
import storage.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PasswordManager {

    private final List<Credential> credentials;

    public PasswordManager() {
        credentials = new ArrayList<>();
        FileManager.loadCredentials(this);
    }

    public boolean addCredential(Credential credential) {

        credentials.add(credential);

        try {

            FileManager.saveCredentials(credentials);
            return true;

        } catch (IOException e) {

            // Roll back
            credentials.remove(credentials.size() - 1);
            return false;
        }
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

        String oldUsername = credential.getUsername();
        credential.setUsername(newUsername);

        try {

            FileManager.saveCredentials(credentials);
            return true;

        } catch (IOException e) {

            credential.setUsername(oldUsername);
            return false;
        }
    }

    public boolean updatePassword(String website, String newPassword) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        String oldPassword = credential.getPassword();
        credential.setPassword(newPassword);

        try {

            FileManager.saveCredentials(credentials);
            return true;

        } catch (IOException e) {

            credential.setPassword(oldPassword);
            return false;
        }
    }

    public boolean deleteCredential(String website) {

        Credential credential = searchCredential(website);

        if (credential == null) {
            return false;
        }

        credentials.remove(credential);

        try {

            FileManager.saveCredentials(credentials);
            return true;

        } catch (IOException e) {

            credentials.add(credential);
            return false;
        }
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

            String strength = PasswordStrength.checkStrength(
                    credential.getPassword());

            if (!strength.equals("Strong")
                    && !strength.equals("Very Strong")) {

                count++;
            }
        }

        return count;
    }

    public int getStrongPasswordCount() {

        int count = 0;

        for (Credential credential : credentials) {

            String strength = PasswordStrength.checkStrength(
                    credential.getPassword());

            if (strength.equals("Strong")
                    || strength.equals("Very Strong")) {

                count++;
            }
        }

        return count;
    }
}