package chapter8;

import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class WordSeparator {
    public static void main(String[] args) {
        System.out.println("Enter the string you want me to format: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder string = new StringBuilder(str);
        for (int i = 1; i < str.length(); i++) {
            char ch = string.charAt(i);
            if (Character.isUpperCase(ch)) {
                string.replace(i, i+1, (" " + String.valueOf(ch).toLowerCase()));
            }
        }
        System.out.println("Here's the formatted version of the string: " + string);
    }
}
