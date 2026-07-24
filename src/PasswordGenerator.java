import java.security.SecureRandom;

public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*()-_";

    private static final String CHARACTERS =
            UPPERCASE + LOWERCASE + NUMBERS + SYMBOLS;

    private static final SecureRandom random = new SecureRandom();

    public static String generatePassword(int length) {

        if (length < 8) {
            length = 8;
        }

        StringBuilder password = new StringBuilder();

        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        password.append(SYMBOLS.charAt(random.nextInt(SYMBOLS.length())));

        for (int i = 4; i < length; i++) {
            password.append(
                    CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
            );
        }

        return shuffle(password.toString());
    }

    private static String shuffle(String password) {

        char[] characters = password.toCharArray();

        for (int i = characters.length - 1; i > 0; i--) {

            int j = random.nextInt(i + 1);

            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }

        return new String(characters);
    }
}