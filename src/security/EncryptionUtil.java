package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class EncryptionUtil {

    private static final String ALGORITHM = "AES";

    private static final SecretKey SECRET_KEY = KeyManager.getSecretKey();

    private EncryptionUtil() {
        // Prevent instantiation
    }

    public static String encrypt(String plainText) {

        try {

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, SECRET_KEY);

            byte[] encryptedBytes = cipher.doFinal(
                    plainText.getBytes(StandardCharsets.UTF_8)
            );

            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {
            throw new RuntimeException("Encryption failed.", e);
        }
    }

    public static String decrypt(String encryptedText) {

        try {

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, SECRET_KEY);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);

            byte[] decryptedBytes = cipher.doFinal(decodedBytes);

            return new String(decryptedBytes, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Decryption failed.", e);
        }
    }
}