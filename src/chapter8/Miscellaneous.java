package chapter8;

import java.util.Arrays;
import java.util.Scanner;

public class Miscellaneous {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string you would want me to operate on: ");
        String line = sc.nextLine();
        wordCount(line);
        //arrayToString(line);
        mostFrequent(line);
        replaceSubstring(line,"the","that");

    }
    public static void wordCount(String str){
        //count the number of words
        String[] tokens = str.split(" ");
        System.out.println("The string you passed has "+ tokens.length+" words.");
    }
    public static void arrayToString(char[] arr){
      //convert this char array to a string
        String str = "";
        for (char c:arr) {
            str+=c;
        }
        System.out.println("This is the string version of the char array you passed: "+str);
    }
    public static void mostFrequent(String str) {
        //counting the character that appears the most
        char big = str.charAt(0);
        int large = 0;
        for (int i = 0; i < str.length(); i++) {
            int count = 1;
            for (int j = 1; j < str.length(); j++) {
                if (str.charAt(j) == str.charAt(i)) {
                    count++;
                }
            }
            if (count > large) {
                large = count;
                big = str.charAt(i);
            }
        }
    }
    public static void replaceSubstring(String s1, String s2,String s3){
        String changed = s1.replaceAll(s2,s3);
        System.out.println("This is the altered string: ");
        System.out.println(changed);
    }
}
