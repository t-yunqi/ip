import java.util.Objects;
import java.util.Scanner;

public class Chatowo {
    public static void main(String[] args) {
        String divider = "\n~*~*~*~*~*~*~*~*~*~*~*~*~*~";

        String greeting = "    Hewwo! I'm Chatowo. :3\n    What can I do for you? OwO";
        System.out.println(greeting + divider);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!Objects.equals(input, "bye")) {
            System.out.println("    " + input + divider);
            input = scanner.nextLine();
        }

        String exit = "    Byebye -w-";
        System.out.println(exit + divider);
    }
}
