package chatowo;

import chatowo.task.Task;
import chatowo.task.TaskList;

/**
 * Handles user interface interactions and message formatting.
 * Provides consistent styling for bot responses.
 */
public class Ui {
    /**
     * Formats and displays a response message from the bot.
     *
     * @param msg Message to display
     */
    public void reply(String msg) {
        String divider = "\n~*~*~*~*~*~*~*~*~*~*~*~*~*~";
        System.out.println(msg + divider);
    }

    /**
     * Displays the welcome message when bot starts.
     */
    public void greet() {
        this.reply("    Hewwo! I'm Chatowo. :3\n    What can I do for you? OwO");
    }

    public void addTask(Task task, int size) {
        this.reply("    Added " + task + " to task list! UwU\n" +
                "    You have " + size + " tasks now!!");
    }

    public void listTasks(TaskList list) {
        String str = "    Here are your tasks! >w<" + list;
        this.reply(str);
    }

    public void listMatchingTasks(TaskList list) {
        String str = "    Here are the matching tasks! >w<" + list;
        this.reply(str);
    }

    public void doneTask(Task task) {
        this.reply("    Okie! This task is done! ^w^\n      " + task);
    }

    public void undoneTask(Task task) {
        this.reply("    Oh... This task is not done yet... OwO\n      " + task);
    }

    public void deleteTask(Task task) {
        this.reply("    Okie! This task has been deleted! ^w^\n    " + task);
    }

    public void dateTimeError() {
        this.reply("    Pwease put your date as yyyy-mm-dd format!! >m<");
    }

    public void bye() {
        this.reply("    Byebye -w-");
    }
}
