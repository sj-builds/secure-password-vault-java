import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {

    private static final String FILE_NAME = "passwords.txt";

    public static void saveCredentials(ArrayList<Credential> credentials) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Credential credential : credentials) {

                writer.write(
                        credential.getWebsite() + "," +
                        credential.getUsername() + "," +
                        credential.getPassword()
                );

                writer.newLine();
            }

            System.out.println("\nCredentials saved successfully.");

        } catch (IOException e) {

            System.out.println("\nError saving credentials: " + e.getMessage());

        }
    }

    public static void loadCredentials(PasswordManager manager) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length == 3) {

                    Credential credential = new Credential(
                            data[0],
                            data[1],
                            data[2]
                    );

                    manager.addCredentialSilently(credential);
                }
            }

        } catch (IOException e) {

            System.out.println("\nNo saved credentials found.");

        }
    }
}