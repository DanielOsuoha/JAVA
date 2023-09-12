import java.util.Scanner;

public class PigLatin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string you want me to operate on: ");
        String str = sc.nextLine();
        String[] arr = str.split(" ");
        String newString = "";
        for (String s:arr) {
            StringBuilder build = new StringBuilder(s);
            build.append(s.charAt(0)).append("AY ");
            build.deleteCharAt(0);
            newString += build;
        }
        System.out.println("Here's the pig latin version of what you've entered: "+newString);
    }
}
