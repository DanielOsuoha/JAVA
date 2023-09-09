import java.util.Scanner;

public class NamePhone {
    public static void main(String[] args) {
        String[] names = new String[5];
        Scanner sc = new Scanner(System.in);
        String[] numbers = new String[5];
        for (int i = 0; i < names.length; i++) {
            System.out.println("Enter the name for person "+(i+1));
            names[i] = sc.nextLine();
            System.out.println("Enter the number for person "+(i+1));
            numbers[i] = sc.nextLine();
        }
        System.out.println("Enter the number of the person you wanna search for: ");
        String search = sc.nextLine();
        String result = searchName(search,names,numbers);
        if (result.equals("")){
            System.out.println("There is no match to the number entered");
        }else {
            System.out.println("The following names match the number entered: ");
            System.out.println(result);
        }

    }
    public static String searchName(String number, String[] names, String[] numbers){
        String s="";
        for (int i=0;i<numbers.length;i++) {
            if (numbers[i].regionMatches(true,0,number,0,number.length())) s+=names[i]+":"+numbers[i]+"\n";
            //we could also try this
 /*           if(numbers[i].contains(number)){
                System.out.println("The follo");
            }
 */       }
        return s;
    }
}
