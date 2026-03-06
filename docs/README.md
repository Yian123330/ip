# Yiyi User Guide

Yiyi is a **desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI)**. If you can type fast, Yiyi can help you manage your to-dos, deadlines, and events much faster than traditional GUI apps.

## Quick Start

1.  **Ensure you have Java `17` or above installed** on your computer.
2.  **Download the latest `.jar` file** from [here].
3.  **Copy the file** to the folder you want to use as the home folder for Yiyi.
4.  **Open a command terminal**, `cd` into the folder you put the jar file in, and use the `java -jar Yiyi.jar` command to run the application.
5.  **Type a command** in the terminal and press Enter to execute it.
    - Example: `list` shows all your tasks.
    - Example: `todo read book` adds a new todo task.
6.  **Refer to the Features below** for details of all commands.

## Features

**Notes about the command format:**
- Words in `UPPER_CASE` are parameters you need to supply. e.g., in `todo DESCRIPTION`, `DESCRIPTION` is a parameter like `todo read book`.
- Commands and keywords are case-insensitive. `todo` and `ToDo` are treated the same.

### Viewing all tasks: `list`

Shows a list of all tasks in your list.

Format: `list`

### Adding a Todo task: `todo`

Adds a simple task without any date/time.

Format: `todo DESCRIPTION`

Examples:
- `todo read book`
- `todo buy groceries`

### Adding a Deadline task: `deadline`

Adds a task with a specific due date.

Format: `deadline DESCRIPTION /by DATE`

Examples:
- `deadline return book /by Sunday`
- `deadline submit report /by 2026-03-10`

### Adding an Event task: `event`

Adds a task with a start and end time.

Format: `event DESCRIPTION /from START /to END`

Examples:
- `event project meeting /from 2pm /to 4pm`
- `event conference /from Mon 9am /to Wed 5pm`

### Marking a task as done: `mark`

Marks a specific task as completed.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`.
- The index refers to the number shown in the task list when you use the `list` command.
- The index **must be a positive integer** (1, 2, 3, ...).

Examples:
- `list` followed by `mark 2` marks the 2nd task in the list as done.

### Marking a task as not done: `unmark`

Marks a specific task as not completed.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`.
- The index **must be a positive integer**.

Examples:
- `list` followed by `unmark 1` unmarks the 1st task in the list.

### Deleting a task: `delete`

Deletes a specific task from the list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index **must be a positive integer**.

Examples:
- `list` followed by `delete 3` deletes the 3rd task in the list.

### Finding a task: `find`

Finds tasks whose descriptions contain any of the given keywords.

Format: `find KEYWORD`
- The search is **case-insensitive**. e.g., `book` will match `Book`.
- The search matches partial words. e.g., `boo` will match `book`.

Examples:
- `find book` returns `read book`, `return book`, `book club meeting`.

### Exiting the program: `bye`

Exits the application.

Format: `bye`

### Saving the data

Yiyi's task data are saved in the hard disk automatically after any command that changes the data. There is no need for you to save manually. The data is stored in a file named `yiyi.txt` inside a `data` folder located in the same directory as the JAR file (e.g., `./data/yiyi.txt`).

### Editing the data file

Advanced users are welcome to update data directly by editing the `yiyi.txt` data file. The format is: `TYPE | IS_DONE | DESCRIPTION | [ADDITIONAL_INFO]`.

**Caution:** If your changes to the data file make its format invalid, Yiyi will discard all data and start with an empty file at the next run. It is highly recommended to take a backup of the file before editing it.

## Command Summary

| Action     | Format, Examples                                                                 |
| :--------- | :------------------------------------------------------------------------------- |
| **List**   | `list`                                                                           |
| **Todo**   | `todo DESCRIPTION` <br> e.g., `todo read book`                                   |
| **Deadline** | `deadline DESCRIPTION /by DATE` <br> e.g., `deadline return book /by Sunday`     |
| **Event**  | `event DESCRIPTION /from START /to END` <br> e.g., `event meeting /from 2pm /to 4pm` |
| **Mark**   | `mark INDEX` <br> e.g., `mark 2`                                                 |
| **Unmark** | `unmark INDEX` <br> e.g., `unmark 1`                                             |
| **Delete** | `delete INDEX` <br> e.g., `delete 3`                                             |
| **Find**   | `find KEYWORD` <br> e.g., `find book`                                            |
| **Exit**   | `bye`                                                                            |
