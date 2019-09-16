package duke.command;

import duke.Duke;
import duke.core.DukeException;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;
import duke.task.Task;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents a command class to add a task. The AddCommand class
 * extends from the Command class to represent user instruction
 * to add a new ToDo, Deadline or Event
 * task to the TaskList.
 */
public class AddCommand extends Command {
    /**
     * A new task to be added
     */
    private Task task;
    private Boolean isClash = false;

    /**
     * Constructs a AddCommand object.
     *
     * @param task Specifies the task to be added.
     */
    public AddCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Indicates whether Duke should exist
     *
     * @return A boolean. True if the command tells Duke to exit, false
     * otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * run the command with the respect TaskList, UI, and storage.
     *
     * @param tasks   The task list where tasks are saved.
     * @param ui      The user interface.
     * @param storage object that handles local text file update
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            ArrayList<Task> taskList = tasks.fullTaskList();
            String userAnswer;

            for (Task t : taskList) {
                if (t.getDate().equals(task.getDate()) && !t.isDone()) {
                    isClash = true;
                }
            }

            if (isClash) {
                userAnswer = ui.showClashWarning(taskList, task);
                if (userAnswer.equals("Y") || userAnswer.equals("y") ) {
                    tasks.addTask(task);
                    ui.taskAdded(task, tasks.getSize());
                    storage.save(tasks.fullTaskList());
                } else {
                    System.out.println("Alright , I have aborted the task.");
                }
            } else {
                tasks.addTask(task);
                ui.taskAdded(task, tasks.getSize());
                storage.save(tasks.fullTaskList());
            }

        } catch (DukeException e) {
            throw new DukeException("Fails to add task. " + e.getMessage());
        }
    }
}
