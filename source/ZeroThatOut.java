import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ZeroThatOut {

    public static void main(String[] args) {
        /*
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> integer = new Stack<Integer>();
        int integerCount = 0;
        int currentInteger = 0;
        int sum = 0;

        System.out.print("Number of integers: ");
        try {
        integerCount = Integer.parseInt(reader.readLine());

        for (int i = 0; i < integerCount; i++) {
        System.out.print("Integer: ");
        currentInteger = Integer.parseInt(reader.readLine());
        if (currentInteger == 0) {
        integer.pop();
        } else {
        integer.push(currentInteger);
        }
        }
        } catch (NumberFormatException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        }

        while (!integer.isEmpty()) {
        sum = sum + integer.pop();
        }
        System.out.println(sum);
         */
        /*
        String str = "1,1";
        String[] r = str.split(",", 2);
        System.out.println(r[0] + " " + r[1]);
         */
        System.out.println(System.getProperty("user.home"));
    }
}