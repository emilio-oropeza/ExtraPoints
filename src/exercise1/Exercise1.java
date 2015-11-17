package exercise1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Exercise1 {
    static ArrayList<Product> products = new ArrayList<>();
    static int i = 1;    
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        try{            
            readTxt();
            System.out.println("Hi I'm a vending machine");
            System.out.println("This are my products:");
            products.forEach((s)->{
                System.out.println((i++)+"."+s.toString());
            });
            System.out.println("Choose one by number");
            int option = sc.nextInt();
            Product choose = products.get((option-1));
            double coins = 0;
            while( choose.getPrice() > coins ){
                System.out.println("Please insert coins");
                coins += sc.nextDouble();
            }
            if(coins > choose.getPrice()){
                System.out.println("Take you change: $"+(coins - choose.getPrice()));
            }
            System.out.println("Thank you");
        }catch (Exception e){
            System.out.println(e);
        }        
    }
    
    public static void readTxt() throws IOException {
        String file = "src/exercise1/exercise1.txt";
        Path p = Paths.get(file);
        Stream<String> stream = Files.lines(p);
        stream.forEach(
                (s)-> {
                    String [] array = s.split(":");
                    products.add(new Product(array[0], Double.parseDouble(array[1])));
                }
        );
        
    }   
}
