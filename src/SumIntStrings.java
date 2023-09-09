import java.util.Scanner;
import java.util.StringTokenizer;

public class SumIntStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string of numbers: ");
        String num = sc.nextLine();
        sumNumbers(num);
    }
    public static void sumNumbers(String num){
        StringTokenizer tokens = new StringTokenizer(num,",");
        int sum=0;
        while(tokens.hasMoreTokens()){
            System.out.println(sum);
            String val = tokens.nextToken();
            System.out.println(val);
            if (Character.isDigit(val.charAt(0))) {
                sum=sum+ Integer.parseInt(val);
                System.out.println(sum);}
            System.out.println("---------------------------");
        }
        System.out.println("The sum of the digits is: "+sum);
    }
}
