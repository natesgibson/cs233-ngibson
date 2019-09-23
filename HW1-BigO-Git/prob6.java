import java.util.Scanner;

public class prob6 {
    public static void main(String[] args) {
        if (args.length == 1) {
            System.out.println(kOf(Integer.parseInt(args[0])));
        } else {
            System.out.println("Please input number:");
            Scanner console = new Scanner(System.in);
            System.out.println(kOf(console.nextInt()));
        }

    }

    public static int kOf(int n) {
        int k = 0;
        int curr = n;
        String numbers = "0123456789";

        while (numbers.length() > 0) {
            k++;
            curr = n*k;
            String currString = Integer.toString(curr);
            int currLength = currString.length();
            for (int i = 0; i < currLength; i++) {
                String currChar = Character.toString(currString.charAt(i));
                if (numbers.contains(currChar)) {
                    numbers = numbers.replace(currChar, "");
                }
            }
        }

        return k;
    }
}
