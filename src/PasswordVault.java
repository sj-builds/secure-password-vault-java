import java.util.Scanner;

public class PasswordVault {
  
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    PasswordManager manager = new PasswordManager();

    System.out.println("=================================================");
    System.out.println("            SECURE PASSWORD VAULT");
    System.out.println("=================================================");

    System.out.println("Welcome to Secure Password Vault!");
    System.out.println("Project Started Successfully.");
    
    scanner.close();

  }
}