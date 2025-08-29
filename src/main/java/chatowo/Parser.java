package chatowo;

import chatowo.task.Deadline;
import chatowo.task.Event;
import chatowo.task.Task;
import chatowo.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private Chatowo chatowo;

    public Parser(Chatowo chatowo) {
        this.chatowo = chatowo;
    }

    public void parse() {
        // initialise scanner for input
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        // continuously check input
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                chatowo.listTasks();
            } else {
                String[] words = input.split(" ");

                try {
                    switch (words[0]) {
                    case "mark":
                        this.mark(words);
                        break;

                    case "unmark":
                        this.unmark(words);
                        break;

                    case "delete":
                        this.delete(words);
                        break;

                    case "todo":
                        this.addTodoTask(words, input);
                        break;

                    case "deadline":
                        this.addDeadlineTask(words, input);
                        break;

                    case "event":
                        this.addEventTask(words, input);
                        break;

                    default:
                        throw new ChatowoException("    Oh nyo... I don't know... what you're saying... ;w;");
                    }
                } catch (Exception e) {
                    if (e instanceof DateTimeParseException) {
                        chatowo.dateTimeError();
                    } else {
                        chatowo.reply(e.getMessage());
                    }
                }
            }

            input = scanner.nextLine();
        }
    }

    public void mark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            chatowo.mark(index);
        }
    }

    public void unmark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            chatowo.unmark(index);
        }
    }

    public void delete(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            chatowo.delete(index);
        }
    }

    public void addTodoTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Add a name for your todo task pwease... >w<");
        } else {
            chatowo.addTask(new ToDo(input.substring(5)));
        }
    }

    public void addDeadlineTask(String[] words, String input) throws Exception {
        int deadlineIndex = input.lastIndexOf(" /by ");
        if (words.length <= 1 || words[1].equals("/by")) {
            throw new ChatowoException("    Oopsies... Add a name for your deadline task pwease... >w<");
        } else if (deadlineIndex == -1 || words[words.length - 1].equals("/by")) {
            throw new ChatowoException("    Oopsies... Add a /by for your deadline task pwease... >w<");
        } else {
            Task d = new Deadline(input.substring(9, deadlineIndex),
                    input.substring(deadlineIndex + 5));
            chatowo.addTask(d);
        }
    }

    public void addEventTask(String[] words, String input) throws Exception {
        int fromIndex = input.lastIndexOf(" /from ");
        int toIndex = input.lastIndexOf(" /to ");
        if (words.length <= 1 || words[1].equals("/from") || words[1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a name for your event task pwease... >w<");
        } else if (fromIndex == -1 || toIndex == -1 ||
                words[words.length - 1].equals("/from") || words[words.length - 1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a /to and /from for your event task pwease... >w<");
        } else {
            Task e = new Event(input.substring(6, fromIndex),
                    input.substring(fromIndex + 7, toIndex),
                    input.substring(toIndex + 5));
            chatowo.addTask(e);
        }
    }
}