package chatowo;

import java.time.format.DateTimeParseException;

import chatowo.task.Deadline;
import chatowo.task.Event;
import chatowo.task.Task;
import chatowo.task.ToDo;

/**
 * Handles parsing and interpretation of user input commands.
 * Converts raw input strings into actionable commands.
 */
public class Parser {
    private Chatowo chatowo;

    public Parser(Chatowo chatowo) {
        this.chatowo = chatowo;
    }

    /**
     * Parses user input and executes corresponding commands.
     */
    public String parse(String input) {

        String[] words = input.split(" ");

        try {
            switch (words[0]) {
            case "list":
                return chatowo.listTasks();

            case "mark":
                return this.mark(words);

            case "unmark":
                return this.unmark(words);

            case "delete":
                return this.delete(words);

            case "todo":
                return this.addTodoTask(words, input);

            case "deadline":
                return this.addDeadlineTask(words, input);

            case "event":
                return this.addEventTask(words, input);

            case "find":
                return this.findTask(words, input);

            default:
                throw new ChatowoException("Oh nyo... I don't know... what you're saying... ;w;");
            }
        } catch (Exception e) {
            if (e instanceof DateTimeParseException) {
                return chatowo.dateTimeError();
            } else {
                return e.getMessage();
            }
        }
    }

    /**
     * Marks a task as done based on user input.
     *
     * @param words Array of input words containing task index
     * @throws ChatowoException if index is missing
     */
    public String mark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            return chatowo.mark(index);
        }
    }

    /**
     * Unmarks a previously completed task.
     *
     * @param words Array of input words containing task index
     * @throws ChatowoException if index is missing
     */
    public String unmark(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            return chatowo.unmark(index);
        }
    }

    public String delete(String[] words) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Pwease specify a task number... >w<");
        } else {
            int index = Integer.parseInt(words[1]) - 1;
            return chatowo.delete(index);
        }
    }

    /**
     * Adds a new todo task from user input.
     *
     * @param words Array of input words
     * @param input Original input string
     * @throws ChatowoException if task description is missing
     */
    public String addTodoTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Add a name for your todo task pwease... >w<");
        } else {
            return chatowo.addTask(new ToDo(input.substring(5)));
        }
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param words Array of input words containing task details
     * @param input Original input string
     * @throws Exception if task format is invalid
     */
    public String addDeadlineTask(String[] words, String input) throws Exception {
        int deadlineIndex = input.lastIndexOf(" /by ");
        if (words.length <= 1 || words[1].equals("/by")) {
            throw new ChatowoException("Oopsies... Add a name for your deadline task pwease... >w<");
        } else if (deadlineIndex == -1 || words[words.length - 1].equals("/by")) {
            throw new ChatowoException("Oopsies... Add a /by for your deadline task pwease... >w<");
        } else {
            Task d = new Deadline(input.substring(9, deadlineIndex),
                    input.substring(deadlineIndex + 5));
            return chatowo.addTask(d);
        }
    }

    public String addEventTask(String[] words, String input) throws Exception {
        int fromIndex = input.lastIndexOf(" /from ");
        int toIndex = input.lastIndexOf(" /to ");
        if (words.length <= 1 || words[1].equals("/from") || words[1].equals("/to")) {
            throw new ChatowoException("Oopsies... Add a name for your event task pwease... >w<");
        } else if (fromIndex == -1 || toIndex == -1
                || words[words.length - 1].equals("/from")
                || words[words.length - 1].equals("/to")) {
            throw new ChatowoException("    Oopsies... Add a /to and /from for your event task pwease... >w<");
        } else {
            Task e = new Event(input.substring(6, fromIndex),
                    input.substring(fromIndex + 7, toIndex),
                    input.substring(toIndex + 5));
            return chatowo.addTask(e);
        }
    }

    public String findTask(String[] words, String input) throws ChatowoException {
        if (words.length <= 1) {
            throw new ChatowoException("Oopsies... Say what you want to find pwease... >w<");
        } else {
            String phrase = input.substring(5);
            return chatowo.find(phrase);
        }
    }
}
