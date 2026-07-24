import java.util.Scanner;

public class PasswordVault {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PasswordManager manager = new PasswordManager();

    public static void main(String[] args) {

        FileManager.loadCredentials(manager);

        boolean running = true;

        while (running) {

            displayMenu();

            System.out.print("\nEnter your choice: ");

            String input = scanner.nextLine();

            int choice;

            try {

                choice = Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("\nInvalid choice. Please enter a number.");
                continue;

            }

            switch (choice) {

                case 1:
                    addCredential();
                    break;

                case 2:
                    manager.viewCredentials();
                    break;

                case 3:
                    searchCredential();
                    break;

                case 4:
                    updateUsername();
                    break;

                case 5:
                    updatePassword();
                    break;

                case 6:
                    deleteCredential();
                    break;

                case 7:
                    generatePassword();
                    break;

                case 8:
                    checkPasswordStrength();
                    break;

                case 9:
                    FileManager.saveCredentials(manager.getCredentials());
                    break;

                case 10:
                    FileManager.saveCredentials(manager.getCredentials());
                    System.out.println("\nThank you for using Secure Password Vault.");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice.");

            }
        }

        scanner.close();
    }

    private static void displayMenu() {

        System.out.println("\n=========================================");
        System.out.println("        SECURE PASSWORD VAULT");
        System.out.println("=========================================");
        System.out.println("1. Add Credential");
        System.out.println("2. View All Credentials");
        System.out.println("3. Search Credential");
        System.out.println("4. Update Username");
        System.out.println("5. Update Password");
        System.out.println("6. Delete Credential");
        System.out.println("7. Generate Strong Password");
        System.out.println("8. Check Password Strength");
        System.out.println("9. Save Credentials");
        System.out.println("10. Exit");
        System.out.println("=========================================");
        System.out.println("Total Credentials: " + manager.getTotalCredentials());
    }

    private static void addCredential() {

        System.out.print("\nWebsite: ");
        String website = scanner.nextLine().trim();

        if (website.isEmpty()) {
            System.out.println("Website cannot be empty.");
            return;
        }

        if (manager.credentialExists(website)) {
            System.out.println("Credential already exists.");
            return;
        }

        System.out.print("Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        Credential credential = new Credential(
                website,
                username,
                password
        );

        manager.addCredential(credential);
    }

    private static void searchCredential() {

        System.out.print("\nEnter website to search: ");
        String website = scanner.nextLine().trim();

        Credential credential = manager.searchCredential(website);

        if (credential == null) {
            System.out.println("\nCredential not found.");
            return;
        }

        System.out.println("\nCredential Found:");
        System.out.println(credential);
    }

    private static void updateUsername() {

        System.out.print("\nEnter website: ");
        String website = scanner.nextLine().trim();

        if (!manager.credentialExists(website)) {
            System.out.println("\nCredential not found.");
            return;
        }

        System.out.print("Enter new username: ");
        String username = scanner.nextLine().trim();

        if (manager.updateUsername(website, username)) {
            System.out.println("\nUsername updated successfully.");
        } else {
            System.out.println("\nUpdate failed.");
        }
    }

    private static void updatePassword() {

        System.out.print("\nEnter website: ");
        String website = scanner.nextLine().trim();

        if (!manager.credentialExists(website)) {
            System.out.println("\nCredential not found.");
            return;
        }

        System.out.println("\n1. Enter Password Manually");
        System.out.println("2. Generate Strong Password");
        System.out.print("Choose an option: ");

        String option = scanner.nextLine();

        String password;

        if (option.equals("2")) {

            System.out.print("Password Length (Minimum 8): ");

            int length;

            try {
                length = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                length = 12;
            }

            password = PasswordGenerator.generatePassword(length);

            System.out.println("\nGenerated Password:");
            System.out.println(password);

        } else {

            System.out.print("Enter new password: ");
            password = scanner.nextLine();

        }

        System.out.println("Strength: " +
                PasswordStrength.checkStrength(password));

        if (manager.updatePassword(website, password)) {
            System.out.println("\nPassword updated successfully.");
        } else {
            System.out.println("\nUpdate failed.");
        }
    }

    private static void deleteCredential() {

        System.out.print("\nEnter website to delete: ");
        String website = scanner.nextLine().trim();

        if (manager.deleteCredential(website)) {
            System.out.println("\nCredential deleted successfully.");
        } else {
            System.out.println("\nCredential not found.");
        }
    }

    private static void generatePassword() {

        System.out.print("\nEnter password length (Minimum 8): ");

        int length;

        try {
            length = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            length = 12;
        }

        String password = PasswordGenerator.generatePassword(length);

        System.out.println("\nGenerated Password:");
        System.out.println(password);

        System.out.println("Strength: " +
                PasswordStrength.checkStrength(password));
    }

    private static void checkPasswordStrength() {

        System.out.print("\nEnter password: ");

        String password = scanner.nextLine();

        System.out.println("\nPassword Strength: " +
                PasswordStrength.checkStrength(password));
    }
}