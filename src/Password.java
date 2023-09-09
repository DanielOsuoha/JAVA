import java.util.Arrays;
import java.util.Scanner;

public class Password {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a password and i will validate it: ");
        String pass = sc.nextLine();
        boolean correct = true;
        while (correct) {
            if (passwordValidate(pass)) {
                System.out.println("The password is strong enough");
                correct = false;
            } else{
                System.out.println("The password is not strong enough");
                System.out.println("Enter a password and i will validate it: ");
                pass = sc.nextLine();
            }
        }
    }

    private static boolean passwordValidate(String pass) {
        boolean upper = false, lower=false, digit = false;
        for (int i = 0; i < pass.length() ; i++) {
            char ch = pass.charAt(i);
            if (Character.isDigit(ch)) digit=true;
            if (Character.isLowerCase(ch)) lower=true;
            if (Character.isUpperCase(ch)) upper =true;
        }
        if (pass.length()<6)return false;
        else
            return lower && upper && digit;
    }
}
