import java.util.Objects;
import java.util.Scanner;

public class Chatowo {
    public static void main(String[] args) {
        String divider = "\n~*~*~*~*~*~*~*~*~*~*~*~*~*~";

        String greeting = "    Hewwo! I'm Chatowo. :3\n    What can I do for you? OwO";
        System.out.println(greeting + divider);

        String[] list = new String[100];
        int count = 0;

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                for (int i = 0; i < count; i++) {
                    String index = String.valueOf(i + 1);
                    System.out.println("    " + index + ". " + list[i]);
                }
            } else {
                list[count] = input;
                count++;
                System.out.println("    added: " + input + divider);
            }

            input = scanner.nextLine();
        }

        String exit = "    Byebye -w-";
        System.out.println(exit + divider);
    }
}
