import java.util.Scanner;
import java.util.ArrayList;

public class Chatowo {
    // initialise task list
    private static ArrayList<Task> list = new ArrayList<>();

    public static void reply(String msg) {
        String divider = "\n~*~*~*~*~*~*~*~*~*~*~*~*~*~";
        System.out.println(msg + divider);
    }

    public static void addTask(Task task) {
        list.add(task);
        Chatowo.reply("    Added " + task + " to task list! UwU\n" +
                "    You have " + list.size() + " tasks now!!");
    }

    public static void mark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            list.get(index).setDone();
            Chatowo.reply("    Okie! This task is done! ^w^\n      "
                    + list.get(index).toString());
        }
    }

    public static void unmark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            list.get(index).setNotDone();
            Chatowo.reply("    Oh... This task is not done yet... OwO\n      "
                    + list.get(index).toString());
        }
    }

    public static void delete(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            Task t = list.remove(index);
            list.trimToSize();
            Chatowo.reply("    Okie! This task has been deleted! ^w^\n    " + t);
        }
    }

    public static void addTodoTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Add a name for your todo task pwease... >w<");
        } else {
            Chatowo.addTask(new ToDo(input.substring(5)));
        }
    }

    public static void addDeadlineTask(String[] words, String input) throws ChatowoException {
        int deadlineindex = input.lastIndexOf(" /by ");
        if (words.length <= 1 || words[1].equals("/by")) {
            throw new ChatowoException("    Oopsies... Add a name for your deadline task pwease... >w<");
        } else if (deadlineindex == -1 || words[words.length - 1].equals("/by")) {
            throw new ChatowoException("    Oopsies... Add a /by for your deadline task pwease... >w<");
        } else {
            Chatowo.addTask(new Deadline(input.substring(9, deadlineindex),
                    input.substring(deadlineindex + 5)));
        }
    }

    public static void addEventTask(String[] words, String input) throws ChatowoException {
        int fromindex = input.lastIndexOf(" /from ");
        int toindex = input.lastIndexOf(" /to ");
        if (words.length <= 1 || words[1].equals("/from") || words[1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a name for your event task pwease... >w<");
        } else if (fromindex == -1 || toindex == -1 ||
                words[words.length - 1].equals("/from") || words[words.length - 1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a /to and /from for your event task pwease... >w<");
        } else {
            Chatowo.addTask(new Event(input.substring(6, fromindex),
                    input.substring(fromindex + 7, toindex),
                    input.substring(toindex + 5)));
        }
    }

    public static void main(String[] args) {
        // output greeting
        String greeting = "    Hewwo! I'm Chatowo. :3\n    What can I do for you? OwO";
        Chatowo.reply(greeting);

        // initialise scanner for input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // continuously check input
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                String listtext = "    Here are your tasks! >w<";
                for (int i = 0; i < list.size(); i++) {
                    String index = String.valueOf(i + 1);
                    listtext += ("\n    " + index + "." + list.get(i).toString());
                }
                Chatowo.reply(listtext);
            } else {
                String[] words = input.split(" ");

                try {
                    switch (words[0]) {
                        case "mark":
                            Chatowo.mark(words);
                            break;

                        case "unmark":
                            Chatowo.unmark(words);
                            break;

                        case "delete":
                            Chatowo.delete(words);
                            break;

                        case "todo":
                            Chatowo.addTodoTask(words, input);
                            break;

                        case "deadline":
                            Chatowo.addDeadlineTask(words, input);
                            break;

                        case "event":
                            Chatowo.addEventTask(words, input);
                            break;

                        default:
                            throw new ChatowoException("    Oh nyo... I don't know... what you're saying... ;w;");
                    }
                } catch (ChatowoException e) {
                    Chatowo.reply(e.getMessage());
                }
            }

            input = scanner.nextLine();
        }

        String exit = "    Byebye -w-";
        Chatowo.reply(exit);
    }
}
