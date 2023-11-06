package View;

import java.util.Scanner;

public class View {
    Scanner scan =new Scanner(System.in);

    public void printPrompt(String msg){
        System.out.println(msg);
    }

    public String inputString(){
        String st = scan.nextLine();
        return st;
    }
    public int inputInt(){
        int in = scan.nextInt();
        scan.nextLine();
        return in;
    }
}
