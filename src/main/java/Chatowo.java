import java.util.Scanner;
import java.util.ArrayList;

public class Chatowo {
    // initialise task list
    private static ArrayList<Task> list = new ArrayList<>();

    public static void reply(String msg) {
        String divider = "\n~*~*~*~*~*~*~*~*~*~*~*~*~*~";
        System.out.println(msg + divider);
    }

    public static void replyAddTask(Task task) {
        list.add(task);
        Chatowo.reply("    Added " + task + " to task list! UwU\n" +
                "    You have " + list.size() + " tasks now!!");
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

                switch(words[0]) {
                    case "mark":
                        if (words.length <= 1) {
                            Chatowo.reply("    Oopsies... Pwease specify a task number... >w<");
                        } else {
                            int index = Integer.parseInt(words[1]) - 1;
                            list.get(index).setDone();
                            Chatowo.reply("    Okie! This task is done! ^w^\n      "
                                    + list.get(index).toString());
                        }
                        break;

                    case "unmark":
                        if (words.length <= 1) {
                            Chatowo.reply("    Oopsies... Pwease specify a task number... >w<");
                        } else {
                            int index = Integer.parseInt(words[1]) - 1;
                            list.get(index).setNotDone();
                            Chatowo.reply("    Oh... This task is not done yet... OwO\n      "
                                    + list.get(index).toString());
                        }
                        break;

                    case "delete":
                        if (words.length <= 1) {
                            Chatowo.reply("    Oopsies... Pwease specify a task number... >w<");
                        } else {
                            int index = Integer.parseInt(words[1]) - 1;
                            Task t = list.remove(index);
                            list.trimToSize();
                            Chatowo.reply("    Okie! This task has been deleted! ^w^\n    " + t);
                        }
                        break;

                    case "todo":
                        if (words.length <= 1) {
                            Chatowo.reply("    Oopsies... Add a name for your todo task pwease... >w<");
                        } else {
                            Chatowo.replyAddTask(new ToDo(input.substring(5)));
                        }
                        break;

                    case "deadline":
                        int deadlineindex = input.lastIndexOf(" /by ");
                        if (words.length <= 1) {
                            Chatowo.reply("    Oopsies... Add a name for your deadline task pwease... >w<");
                        } else if (deadlineindex == -1) {
                            Chatowo.reply("    Oopsies... Add a /by for your deadline task pwease... >w<");
                        } else {
                            Chatowo.replyAddTask(new Deadline(input.substring(9, deadlineindex),
                                    input.substring(deadlineindex + 5)));
                        }
                        break;

                    case "event":
                        int fromindex = input.lastIndexOf(" /from ");
                        int toindex = input.lastIndexOf(" /to ");
                        if (words.length <= 1) {
                            Chatowo.reply("    Oopsies... Add a name for your event task pwease... >w<");
                        } else if (fromindex == -1 || toindex == -1) {
                            Chatowo.reply("    Oopsies... Add a /to and /from for your event task pwease... >w<");
                        } else {
                            Chatowo.replyAddTask(new Event(input.substring(6, fromindex),
                                    input.substring(fromindex + 7, toindex),
                                    input.substring(toindex + 5)));
                        }
                        break;

                    default:
                        Chatowo.reply("    Oh nyo... I don't know... what you're saying... ;w;");
                }
            }

            input = scanner.nextLine();
        }

        String exit = "    Byebye -w-";
        Chatowo.reply(exit);
    }
}
