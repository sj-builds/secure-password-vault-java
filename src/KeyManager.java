import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class KeyManager {

    private static final String KEY_FILE = "data/secret.key";

    public static SecretKey getSecretKey() {

        try {

            File file = new File(KEY_FILE);

            if (!file.exists()) {

                file.getParentFile().mkdirs();

                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(256);

                SecretKey key = keyGenerator.generateKey();

                try (FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(key.getEncoded());
                }

                return key;
            }

            byte[] keyBytes = new byte[(int) file.length()];

            try (FileInputStream fis = new FileInputStream(file)) {
                fis.read(keyBytes);
            }

            return new javax.crypto.spec.SecretKeySpec(
                    keyBytes,
                    "AES"
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Unable to load encryption key.",
                    e
            );

        }

    }

}