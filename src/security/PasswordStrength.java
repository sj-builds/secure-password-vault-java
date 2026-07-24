package security;

public final class PasswordStrength {

    private PasswordStrength() {
    }

    public static String checkStrength(String password) {

        if (password == null || password.isEmpty()) {
            return "Very Weak";
        }

        int score = 0;

        if (password.length() >= 8) {
            score++;
        }

        if (password.matches(".*[A-Z].*")) {
            score++;
        }

        if (password.matches(".*[a-z].*")) {
            score++;
        }

        if (password.matches(".*\\d.*")) {
            score++;
        }

        if (password.matches(".*[^A-Za-z0-9].*")) {
            score++;
        }

        return switch (score) {
            case 5 -> "Very Strong";
            case 4 -> "Strong";
            case 3 -> "Medium";
            case 2 -> "Weak";
            default -> "Very Weak";
        };
    }
}