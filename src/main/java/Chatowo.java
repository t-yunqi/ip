import java.util.Scanner;

public class Chatowo {
    public static void main(String[] args) {
        String divider = "\n~*~*~*~*~*~*~*~*~*~*~*~*~*~";

        // output greeting
        String greeting = "    Hewwo! I'm Chatowo. :3\n    What can I do for you? OwO";
        System.out.println(greeting + divider);

        // initialise task list
        Task[] list = new Task[100];
        int count = 0;

        // initialise scanner for input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // continuously check input
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    String index = String.valueOf(i + 1);
                    System.out.println("    " + index + "." + list[i].toString());
                }
                System.out.println(divider);
            } else {
                String[] words = input.split(" ");
                if (words[0].equals("mark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    list[index].setDone();
                    System.out.println("    Okie! This task is done! ^w^\n      "
                            + list[index].toString() + divider);
                } else if (words[0].equals("unmark")) {
                    int index = Integer.parseInt(words[1]) - 1;
                    list[index].setNotDone();
                    System.out.println("    Oh... This task is not done yet... OwO\n      "
                            + list[index].toString() + divider);
                } else {
                    list[count] = new Task(input);
                    count++;
                    System.out.println("    added: " + input + divider);
                }
            }

            input = scanner.nextLine();
        }

        String exit = "    Byebye -w-";
        System.out.println(exit + divider);
    }
}
