package ui;

import model.Credential;
import security.PasswordGenerator;
import security.PasswordStrength;
import service.PasswordManager;

import java.util.Scanner;

public class PasswordVault {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PasswordManager manager = new PasswordManager();

    public static void main(String[] args) {

        while (true) {

            printDashboard();

            System.out.println("""

                    1. Add Credential
                    2. View Credentials
                    3. Search Credential
                    4. Update Username
                    5. Update Password
                    6. Delete Credential
                    7. Generate Password
                    8. Check Password Strength
                    9. Exit
                    """);

            System.out.print("Choose an option: ");

            int choice;

            try {

                choice = Integer.parseInt(scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println("\nInvalid input.");
                continue;
            }

            switch (choice) {

                case 1 -> addCredential();

                case 2 -> manager.viewCredentials();

                case 3 -> searchCredential();

                case 4 -> updateUsername();

                case 5 -> updatePassword();

                case 6 -> deleteCredential();

                case 7 -> generatePassword();

                case 8 -> checkPasswordStrength();

                case 9 -> {

                    System.out.println("\nThank you for using Secure Password Vault.");

                    scanner.close();

                    System.exit(0);

                }

                default -> System.out.println("\nInvalid choice.");
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void printDashboard() {

        System.out.println("""

                =============================================
                      SECURE PASSWORD VAULT
                =============================================
                """);

        System.out.println("Total Credentials : " + manager.getTotalCredentials());
        System.out.println("Strong Passwords  : " + manager.getStrongPasswordCount());
        System.out.println("Weak Passwords    : " + manager.getWeakPasswordCount());

        System.out.println("=============================================");
    }

    private static void addCredential() {

        System.out.print("Website : ");
        String website = scanner.nextLine().trim();

        if (manager.credentialExists(website)) {

            System.out.println("\nCredential already exists.");

            return;
        }

        System.out.print("Username : ");
        String username = scanner.nextLine().trim();

        System.out.print("Password (leave blank to generate): ");
        String password = scanner.nextLine();

        if (password.isBlank()) {

            password = PasswordGenerator.generatePassword(12);

            System.out.println("\nGenerated Password : " + password);
        }

        if (manager.addCredential(new Credential(
                website,
                username,
                password))) {

            System.out.println("\nCredential added successfully.");

        } else {

            System.out.println("\nFailed to save credential.");
        }
    }

    private static void searchCredential() {

        System.out.print("Website : ");

        String website = scanner.nextLine();

        Credential credential = manager.searchCredential(website);

        if (credential == null) {

            System.out.println("\nCredential not found.");

            return;
        }

        System.out.println();

        System.out.println(credential);
    }

    private static void updateUsername() {

        System.out.print("Website : ");
        String website = scanner.nextLine().trim();

        if (!manager.credentialExists(website)) {

            System.out.println("\nCredential not found.");
            return;
        }

        System.out.print("New Username : ");
        String username = scanner.nextLine().trim();

        if (manager.updateUsername(website, username)) {

            System.out.println("\nUsername updated successfully.");

        } else {

            System.out.println("\nFailed to save changes.");
        }
    }

    private static void updatePassword() {

        System.out.print("Website : ");
        String website = scanner.nextLine().trim();

        if (!manager.credentialExists(website)) {

            System.out.println("\nCredential not found.");
            return;
        }

        System.out.print("New Password : ");
        String password = scanner.nextLine();

        if (manager.updatePassword(website, password)) {

            System.out.println("\nPassword updated successfully.");

        } else {

            System.out.println("\nFailed to save changes.");
        }
    }

    private static void deleteCredential() {

        System.out.print("Website : ");
        String website = scanner.nextLine().trim();

        if (!manager.credentialExists(website)) {

            System.out.println("\nCredential not found.");
            return;
        }

        if (manager.deleteCredential(website)) {

            System.out.println("\nCredential deleted successfully.");

        } else {

            System.out.println("\nFailed to delete credential.");
        }
    }

    private static void generatePassword() {

        System.out.print("Password Length : ");

        int length;

        try {

            length = Integer.parseInt(scanner.nextLine());

        } catch (NumberFormatException e) {

            System.out.println("\nInvalid length.");

            return;
        }

        try {

            String password = PasswordGenerator.generatePassword(length);

            System.out.println("\nGenerated Password : " + password);

        } catch (IllegalArgumentException e) {

            System.out.println("\n" + e.getMessage());

        }
    }

    private static void checkPasswordStrength() {

        System.out.print("Enter Password : ");

        String password = scanner.nextLine();

        System.out.println(
                "\nPassword Strength : "
                        + PasswordStrength.checkStrength(password));
    }
}