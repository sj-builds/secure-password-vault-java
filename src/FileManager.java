import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Credential;
import security.EncryptionUtil;

public class FileManager {

    private static final String DATA_FOLDER = "data";
    private static final String FILE_NAME = DATA_FOLDER + "/passwords.txt";

    public static void saveCredentials(ArrayList<Credential> credentials) {

        File folder = new File(DATA_FOLDER);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Credential credential : credentials) {

                String encryptedPassword =
                        EncryptionUtil.encrypt(credential.getPassword());

                writer.write(
                        credential.getWebsite() + "," +
                        credential.getUsername() + "," +
                        encryptedPassword
                );

                writer.newLine();
            }

            System.out.println("\nCredentials saved successfully.");

        } catch (IOException e) {

            System.out.println("\nError saving credentials: " + e.getMessage());

        }
    }

    public static void loadCredentials(PasswordManager manager) {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length != 3) {
                    continue;
                }

                String decryptedPassword =
                        EncryptionUtil.decrypt(data[2]);

                Credential credential = new Credential(
                        data[0],
                        data[1],
                        decryptedPassword
                );

                manager.addCredentialSilently(credential);
            }

        } catch (Exception e) {

            System.out.println("\nUnable to load credentials.");

        }
    }

}