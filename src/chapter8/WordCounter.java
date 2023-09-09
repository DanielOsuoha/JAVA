package chapter8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Enter the name of a file and i will count the number of words that are in the file");
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        try {
            File file = new File("chapter8/test");
            Scanner read = new Scanner(file);
            int count=0;
            while (read.hasNext()){
                count++;
                System.out.println(read.next());
            }
        }catch (Exception e){
            System.out.println("The file was not found");
            e.printStackTrace();
        }

    }
}
