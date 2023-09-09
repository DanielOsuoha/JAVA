import java.util.Scanner;
import java.util.StringTokenizer;

public class CheckWriter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
       /* System.out.println("Enter a the name of the person: ");
        String name = sc.nextLine();
        System.out.println("Enter a the date: ");
        String date = sc.nextLine();*/
        String date="88/0/9";
        String name="joe";
        System.out.println("Enter the amount: ");
        double amount;
        while (!sc.hasNextDouble()){
            System.out.print("Invalid input (Enter a number): ");
            amount = sc.nextDouble();
        }
        amount = sc.nextDouble();
        writeCheck(name,date,amount);
    }
    public static void writeCheck(String name,String date, double amount){
        String words="";
        StringTokenizer token = new StringTokenizer(String.valueOf(amount),".");
        String first = token.nextToken();
        String sec = token.nextToken();
        int val=Integer.parseInt(first);
        words+=numWords(val)+" and "+sec+" cents";
        System.out.println("                                            Date: "+date);
        System.out.println("Pay to the order of:  "+name+"                   $"+amount);
        System.out.println(words);
    }
    public static String numWords(int num){
        String words="";
        int val =num;
        //remember this program should later be able to work with even one million
        //but for now let's keep it simple
        int unit,tens,hund,thou;
        unit = val%10;
        val-=unit;
        tens=val%100;
        val-=tens;
        hund = val%1000;
        val-=hund;
        thou=val%10000;
        words=thou+" "+hund+" "+tens+" ";
        if (unit!=0){
            words+=unit;
        }
        words = writeWords(thou)+" "+writeWords(hund)+" "+ writeWords(tens)+" "+writeWords(unit);
        return words;
    }
    public static String writeWords(int val){
        String word="";
        switch (val){
            case 1:
                word = "one";
                break;
            case 2:
                word = "two";
                break;
            case 3:
                word = "three";
                break;
            case 4:
                word = "four";
                break;

            case 5:
                word = "five";
                break;
            case 6:
                word = "six";
                break;
            case 7:
                word = "seven";
                break;
            case 8:
                word = "eight";
                break;
            case 9:
                word = "nine";
                break;
            case 10:
                word = "ten";
                break;
            case 11:
                word = "eleven";
                break;
            case 12:
                word = "twelve";
                break;
            case 13:
                word = "thirteen";
                break;
            case 14:
                word = "fourteen";
                break;
            case 15:
                word = "fifteen";
                break;
            case 16:
                word = "sixteen";
                break;
            case 17:
                word = "seventeen";
                break;
            case 18:
                word = "eighteen";
                break;
            case 19:
                word = "nineteen";
                break;
            case 20:
                word = "twenty";
                break;
            case 30:
                word = "thirteen";
                break;
            case 40:
                word = "forty";
                break;
            case 50:
                word = "fifty";
                break;
            case 60:
                word = "sixty";
                break;
            case 70:
                word = "seventy";
                break;
            case 80:
                word = "eighty";
                break;
            case 90:
                word = "ninety";
                break;
            case 100:
                word = "one hundred";
                break;
            case 200:
                word = "two hundred";
                break;
            case 300:
                word = "three hundred";
                break;
            case 400:
                word = "four hundred";
                break;
            case 500:
                word = "five hundred";
                break;
            case 600:
                word = "six hundred";
                break;
            case 700:
                word = "seven hundred";
                break;
            case 800:
                word = "eight hundred";
                break;
            case 900:
                word = "nine hundred";
                break;
            case 1000:
                word = "one thousand";
                break;
            case 2000:
                word = "two thousand";
                break;
            case 3000:
                word = "three thousand";
                break;
            case 4000:
                word = "four thousand";
                break;
            case 5000:
                word = "five thousand";
                break;
            case 6000:
                word = "six thousand";
                break;
            case 7000:
                word = "seven thousand";
                break;
            case 8000:
                word = "eight thousand";
                break;
            case 9000:
                word = "nine thousand";
                break;
            case 10000:
                word = "ten thousand";
                break;
            default:
                word = " ";
        }
        return word;
    }

}
