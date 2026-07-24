public class PasswordStrength {

    public static String checkStrength(String password) {

        if (password == null || password.isEmpty()) {
            return "Invalid Password";
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

        if (password.matches(".*[!@#$%&*()\\-_].*")) {
            score++;
        }

        switch (score) {
            case 5:
                return "Very Strong";
            case 4:
                return "Strong";
            case 3:
                return "Medium";
            case 2:
                return "Weak";
            default:
                return "Very Weak";
        }
    }
}