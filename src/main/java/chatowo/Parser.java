package chatowo;

import chatowo.task.Deadline;
import chatowo.task.Event;
import chatowo.task.Task;
import chatowo.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Handles parsing and interpretation of user input commands.
 * Converts raw input strings into actionable commands.
 */
public class Parser {
    Chatowo chatowo;


    public Parser(Chatowo chatowo) {
        this.chatowo = chatowo;
    }

    /**
     * Parses user input and executes corresponding commands.
     */
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

    /**
     * Marks a task as done based on user input.
     *
     * @param words Array of input words containing task index
     * @throws ChatowoException if index is missing
     */
    public void mark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            chatowo.mark(index);
        }
    }

    /**
     * Unmarks a previously completed task.
     *
     * @param words Array of input words containing task index
     * @throws ChatowoException if index is missing
     */
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

    /**
     * Adds a new todo task from user input.
     *
     * @param words Array of input words
     * @param input Original input string
     * @throws ChatowoException if task description is missing
     */
    public void addTodoTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("    Oopsies... Add a name for your todo task pwease... >w<");
        } else {
            chatowo.addTask(new ToDo(input.substring(5)));
        }
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param words Array of input words containing task details
     * @param input Original input string
     * @throws Exception if task format is invalid
     */
    public void addDeadlineTask(String[] words, String input) throws Exception {
        int deadlineindex = input.lastIndexOf(" /by ");
        if (words.length <= 1 || words[1].equals("/by")) {
            throw new ChatowoException("    Oopsies... Add a name for your deadline task pwease... >w<");
        } else if (deadlineindex == -1 || words[words.length - 1].equals("/by")) {
            throw new ChatowoException("    Oopsies... Add a /by for your deadline task pwease... >w<");
        } else {
            Task d = new Deadline(input.substring(9, deadlineindex),
                    input.substring(deadlineindex + 5));
            chatowo.addTask(d);
        }
    }

    public void addEventTask(String[] words, String input) throws Exception {
        int fromindex = input.lastIndexOf(" /from ");
        int toindex = input.lastIndexOf(" /to ");
        if (words.length <= 1 || words[1].equals("/from") || words[1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a name for your event task pwease... >w<");
        } else if (fromindex == -1 || toindex == -1 ||
                words[words.length - 1].equals("/from") || words[words.length - 1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a /to and /from for your event task pwease... >w<");
        } else {
            Task e = new Event(input.substring(6, fromindex),
                    input.substring(fromindex + 7, toindex),
                    input.substring(toindex + 5));
            chatowo.addTask(e);
        }
    }
}