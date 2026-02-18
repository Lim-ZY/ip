# Mark User Guide

![Product screenshot of Mark](https://Lim-ZY.github.io/ip/Ui.png)

Mark is a desktop app for managing tasks, such as to-dos, deadlines, and events.
It is optimised for use via a Command Line Interface (CLI) while still having the benefits
of a Graphical User Interface (GUI).

---

* [Quick Start](#quick-start)
* [Features](#features)
  * [Adding a Todo: `todo`](#adding-a-todo-todo)
  * [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
  * [Adding an event: `event`](#adding-an-event-event)
  * [Marking/Unmarking a task as Done: `mark`/`unmark`](#markingunmarking-a-task-as-done-mark--unmark)
  * [Deleting a task: `delete`](#deleting-a-task-delete)
  * [Finding a task: `find`](#finding-a-task-find)
  * [Listing all tasks: `list`](#listing-all-tasks-list)
  * [Updating a task: `update`](#updating-a-task-update)
  * [Exiting the program: `bye`](#exiting-the-program-bye)
* [FAQ](#faq)
* [Command Summary](#command-summary)

---

## Quick Start
1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest mark.jar file from [here](https://github.com/Lim-ZY/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your task list.
4. Open a command terminal, cd into the folder, and run: java -jar mark.jar.
5. The GUI should appear. Mark will greet you with a welcome message.
6. Type a command in the command box and press Enter to execute it.

---

## Features

> [!NOTE]  
> **Notes about the command format:**
> * Words in angled brackets `<>` are the parameters to be supplied by the user.
>   * e.g. in `todo <taskName>`, `<taskName>` is a parameter: `todo read book`.
> * Commands with `...` indicate that any number of arguments can be specified.
> * Commands are case-sensitive.

### Adding a Todo: `todo`
Adds a simple task without any date or time attached to it.
* **Format:** `todo <taskName>`
* **Example:** `todo Feed my cat`

### Adding a Deadline: `deadline`
Adds a task with a specific deadline.
* **Format:** `deadline <taskName> /by <YYYY-MM-DD> <HHMM>`
* **Example:** `deadline Submit application /by 2030-01-31 2359`

### Adding an Event: `event`
Adds a task that starts and ends at specific times.
* **Format:** `event <taskName> /from <YYYY-MM-DD> <HHMM> /to <YYYY-MM-DD> <HHMM>`
* **Example:** `event Meet to eat /from 2030-01-31 1200 /to 2030-01-31 1400`

### Marking/Unmarking a task as Done: `mark` / `unmark`
Marks/unmarks a task as done.
* **Format:** `mark <taskID>` / `unmark <taskID>`
* **Example:** `mark 3`

### Deleting a task: `delete`
Deletes a task.
* **Format:** `delete <taskID>`
* **Example:** `delete 3`

### Finding a task: `find`
Finds a task corresponding to a keyword.
* **Format:** `find <keyword>`
* **Example:** `find cat`

### Listing all tasks: `list`
Shows a list of all current tasks in the task list.
* **Format:** `list`

### Updating a task: `update`
Updates specific fields of an existing task in the list.
* **Format:** `update <taskID> <taskFieldName> <taskFieldValue> ...`
* **Example:** `update 3 /taskName Feed my dog /by 2026-01-31 2359`

### Exiting the program: `bye`
Exits the application.
* **Format:** `bye`

---

## FAQ
**Q: How do I save my tasks?**  
**A:** Mark saves your data automatically to the hard disk after you end the session. You don't need to save manually.  
**Q: Where is my data stored?**  
**A:** Data is stored locally in a text file within the same directory as the application. No data will be sent
to third-party cloud services.  

---

## Command Summary

| :Action            | :Command                                                             | :Example                                                      |
|--------------------|----------------------------------------------------------------------|---------------------------------------------------------------|
| Add a Todo         | `todo <taskName>`                                                    | `todo Feed my cat`                                            |
| Add a Deadline     | `deadline <taskName> /by <YYYY-MM-DD> <HHMM>`                        | `deadline Submit application /by 2030-01-31 2359`             |
| Add an Event       | `event <taskName> /from <YYYY-MM-DD> <HHMM> /to <YYYY-MM-DD> <HHMM>` | `event Meet to eat /from 2030-01-31 1200 /to 2030-01-31 1400` |
| Mark/unmark a task | `mark` / `unmark`                                                    | `mark 3` / `unmark 3`                                         |
| Delete a task      | `delete`                                                             | `delete 3`                                                    |
| Find a task        | `find`                                                               | `find cat`                                                    |
| List all tasks     | `list`                                                               | -                                                             |
| Update a task      | `update <taskID> <taskFieldName> <taskFieldValue> ...`               | `update 3 /taskName Feed my dog`                              |
| Exit               | `bye`                                                                | -                                                             |
