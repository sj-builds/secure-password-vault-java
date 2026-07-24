package storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Credential;
import security.EncryptionUtil;
import service.PasswordManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class FileManager {

    private static final String DATA_FOLDER = "data";
    private static final String FILE_NAME = DATA_FOLDER + "/passwords.json";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .findAndRegisterModules()
            .enable(SerializationFeature.INDENT_OUTPUT);

    private FileManager() {
        // Prevent instantiation
    }

    public static void saveCredentials(List<Credential> credentials) {

        try {

            File folder = new File(DATA_FOLDER);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            List<Credential> encryptedCredentials = new ArrayList<>();

            for (Credential credential : credentials) {

                Credential encryptedCredential = new Credential(
                        credential.getWebsite(),
                        credential.getUsername(),
                        EncryptionUtil.encrypt(credential.getPassword()),
                        credential.getCreatedAt(),
                        credential.getUpdatedAt()
                );

                encryptedCredentials.add(encryptedCredential);
            }

            OBJECT_MAPPER.writeValue(new File(FILE_NAME), encryptedCredentials);

            System.out.println("\nCredentials saved successfully.");

        } catch (IOException e) {

            System.out.println("\nError saving credentials: " + e.getMessage());

        }
    }

    public static void loadCredentials(PasswordManager manager) {

        File file = new File(FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            return;
        }

        try {

            List<Credential> credentials = OBJECT_MAPPER.readValue(
                    file,
                    new TypeReference<List<Credential>>() {}
            );

            for (Credential credential : credentials) {

                Credential decryptedCredential = new Credential(
                        credential.getWebsite(),
                        credential.getUsername(),
                        EncryptionUtil.decrypt(credential.getPassword()),
                        credential.getCreatedAt(),
                        credential.getUpdatedAt()
                );

                manager.addCredentialSilently(decryptedCredential);
            }

        } catch (IOException e) {

            System.out.println("\nUnable to load credentials: " + e.getMessage());

        }
    }
}