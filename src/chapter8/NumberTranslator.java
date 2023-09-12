package chapter8;

import java.util.Scanner;

public class NumberTranslator {
   // String phone = "";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the 10-character telephone number in this format: XXX-XXX-XXXX");
        String phone = sc.nextLine();
        for (int i = 0; i < phone.length(); i++) {
            char ch = phone.charAt(i);
            if (Character.isAlphabetic(ch)){
                String replace = numberTranslate(ch);
                phone = phone.replaceAll(String.valueOf(ch),replace);
            }
        }
        System.out.println("The telephone number is: "+phone);

    }
    public static String numberTranslate(char ch){
        if (ch=='A'||ch=='B'||ch=='C'){
           return "2";
        }else if ((ch=='D'||ch=='E'||ch=='F')){
            return "3";
        }else if ((ch=='G'||ch=='H'||ch=='I')){
            return "4";
        }else if ((ch=='J'||ch=='K'||ch=='L')){
            return "5";
        }else if ((ch=='M'||ch=='N'||ch=='O')){
            return "6";
        }else if ((ch=='P'||ch=='Q'||ch=='R'||ch=='S')){
            return "7";
        }else if ((ch=='T'||ch=='U'||ch=='V')){
            return "8";
        }else {
            return "9";
        }
    }
}
