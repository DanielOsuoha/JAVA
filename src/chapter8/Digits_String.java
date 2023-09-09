package chapter8;

import java.util.Scanner;

public class Digits_String {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string you want me to operate on: ");
        String num = sc.nextLine();
        //convert the input to an array
        String[] numbers = num.split("");
        //sum of the digits
        int sum =0;
        int largest=Integer.parseInt(numbers[0]);
        int smallest=Integer.parseInt(numbers[0]);
        for (String element: numbers) {
            int hold = Integer.parseInt(element);
            if (hold>largest)largest=hold;
            if (hold<smallest)smallest=hold;
            sum+=hold;
        }
        System.out.println("Sum: "+sum);
        System.out.println("Largest: "+largest);
        System.out.println("Smallest: "+smallest);
    }
}
