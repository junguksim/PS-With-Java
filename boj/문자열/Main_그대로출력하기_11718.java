package boj.문자열;
import java.util.Scanner;

public class Main_그대로출력하기_11718 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(input);
        }
        scanner.close();
    }
}
