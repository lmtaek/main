package duke.command;

import duke.core.DukeException;
import duke.patient.PatientList;
import duke.storage.PatientStorage;
import duke.storage.Storage;
import duke.storage.TaskStorage;
import duke.task.TaskList;
import duke.core.Ui;

/**
 * Represents a command class received from user. It is an abstract
 * class that can not be instantiated, its child class represents different kind
 * of user command
 */
public abstract class Command {

    /**
     * run the command with the respect TaskList, UI, and storage.
     *
     * @param tasks   The task list where tasks are saved.
     * @param ui      The user interface.
     * @param storage object that handles local text file update
     * @throws DukeException throw exception during execution of the
     *                       command.
     */
    public abstract void execute(TaskList tasks, PatientList patientList, Ui ui, TaskStorage taskStorage, PatientStorage patientStorage) throws DukeException;

    /**
     * Decide whether duke should exist.
     *
     * @return A boolean. True if the command tells Duke to exit, false
     *         otherwise.
     */
    public abstract boolean isExit();

}