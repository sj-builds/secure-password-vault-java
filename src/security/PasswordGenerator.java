package security;

import java.security.SecureRandom;

public final class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%&*()-_";

    private static final String ALL_CHARACTERS = UPPERCASE +
            LOWERCASE +
            NUMBERS +
            SYMBOLS;

    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordGenerator() {
        // Prevent instantiation
    }

    public static String generatePassword(int length) {

        if (length < 8) {
            throw new IllegalArgumentException(
                    "Password length must be at least 8 characters.");
        }

        StringBuilder password = new StringBuilder(length);

        password.append(randomCharacter(UPPERCASE));
        password.append(randomCharacter(LOWERCASE));
        password.append(randomCharacter(NUMBERS));
        password.append(randomCharacter(SYMBOLS));

        while (password.length() < length) {
            password.append(randomCharacter(ALL_CHARACTERS));
        }

        return shuffle(password.toString());
    }

    private static char randomCharacter(String characters) {

        return characters.charAt(
                RANDOM.nextInt(characters.length()));
    }

    private static String shuffle(String password) {

        char[] characters = password.toCharArray();

        for (int i = characters.length - 1; i > 0; i--) {

            int j = RANDOM.nextInt(i + 1);

            char temp = characters[i];
            characters[i] = characters[j];
            characters[j] = temp;
        }

        return new String(characters);
    }
}