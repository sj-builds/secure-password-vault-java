package security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class KeyManager {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 256;
    private static final Path KEY_PATH = Path.of("data", "secret.key");

    private KeyManager() {
        // Prevent instantiation
    }

    public static SecretKey getSecretKey() {

        try {

            if (Files.notExists(KEY_PATH)) {
                return generateAndSaveKey();
            }

            byte[] keyBytes = Files.readAllBytes(KEY_PATH);

            if (keyBytes.length == 0) {
                throw new IOException("Encryption key file is empty.");
            }

            return new SecretKeySpec(keyBytes, ALGORITHM);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load encryption key.", e);
        }
    }

    private static SecretKey generateAndSaveKey() throws Exception {

        Files.createDirectories(KEY_PATH.getParent());

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(KEY_SIZE);

        SecretKey secretKey = keyGenerator.generateKey();

        Files.write(KEY_PATH, secretKey.getEncoded());

        return secretKey;
    }
}