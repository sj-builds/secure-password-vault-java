import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptionUtil {

    private static final SecretKey secretKey =
        KeyManager.getSecretKey();

    public static String encrypt(String plainText) {

        try {

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (Exception e) {

            throw new RuntimeException("Encryption failed.", e);

        }
    }

    public static String decrypt(String encryptedText) {

        try {

            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decodedBytes =
                    Base64.getDecoder().decode(encryptedText);

            byte[] decryptedBytes =
                    cipher.doFinal(decodedBytes);

            return new String(decryptedBytes);

        } catch (Exception e) {

            throw new RuntimeException("Decryption failed.", e);

        }
    }

}