# Yoru User Guide

![Yoru Logo](yoru.jpg)

**Yoru** is a task management chatbot designed for fast command-line usage. It helps you manage todos, deadlines, and events while automatically saving your progress.

> *"Drop your command and I'll handle it from the shadows."*

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing all tasks](#viewing-all-tasks)
  - [Adding a todo task](#adding-a-todo-task)
  - [Adding a deadline task](#adding-a-deadline-task)
  - [Adding an event task](#adding-an-event-task)
  - [Marking tasks as done](#marking-tasks-as-done)
  - [Unmarking tasks](#unmarking-tasks)
  - [Finding tasks](#finding-tasks)
  - [Deleting tasks](#deleting-tasks)
  - [Exiting the application](#exiting-the-application)
  - [Saving data](#saving-data)
- [Command Summary](#command-summary)
- [FAQ](#faq)
- [Known Issues](#known-issues)

## Quick Start

1. Ensure you have Java 17 or above installed.
2. Clone or download this repository.
3. Run `Yoru.main()` from your IDE.
4. Type commands in the terminal and press Enter.
5. Use `bye` to exit.

## Features

### Notes about command format

- Words in `UPPER_CASE` are user-supplied parameters.
- Task indexes are positive integers starting from `1`.
- Deadline date format must be `yyyy-MM-dd`.
- Event date-time format must be `yyyy-MM-dd HHmm` (24-hour clock).

### Viewing all tasks

Shows all tasks currently stored.

**Command Format:** `list`

**Example:**
```
list
```

### Adding a todo task

Creates a todo task with no date/time.

**Command Format:** `todo DESCRIPTION`

**Example:**
```
todo Buy groceries
```

**Expected Output (sample):**
```
_______________________________________
	Added. New objective locked:
	  [T][ ] Buy groceries
	You now have 1 targets on the board.
_______________________________________
```

### Adding a deadline task

Creates a task with a due date.

**Command Format:** `deadline DESCRIPTION /by yyyy-MM-dd`

**Example:**
```
deadline Submit assignment /by 2026-09-30
```

**Expected Output (sample):**
```
_______________________________________
	Added. New objective locked:
	  [D][ ] Submit assignment (by: Sep 30 2026)
	You now have 2 targets on the board.
_______________________________________
```

### Adding an event task

Creates a task with start and end date-time.

**Command Format:** `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm`

**Example:**
```
event Team meeting /from 2026-10-01 1400 /to 2026-10-01 1600
```

**Expected Output (sample):**
```
_______________________________________
	Added. New objective locked:
	  [E][ ] Team meeting (from: Oct 01 2026 14:00 to: Oct 01 2026 16:00)
	You now have 3 targets on the board.
_______________________________________
```

### Marking tasks as done

Marks a task as completed.

**Command Format:** `mark INDEX`

**Example:**
```
mark 1
```

**Expected Output (sample):**
```
_______________________________________
	Clean. Mission complete:
	  [T][X] Buy groceries
_______________________________________
```

### Unmarking tasks

Marks a completed task as not done.

**Command Format:** `unmark INDEX`

**Example:**
```
unmark 1
```

**Expected Output (sample):**
```
_______________________________________
	Not finished. Putting this back in play:
	  [T][ ] Buy groceries
_______________________________________
```

### Finding tasks

Finds tasks whose descriptions contain the given keyword (case-insensitive).

**Command Format:** `find KEYWORD`

**Example:**
```
find assignment
```

**Expected Output (sample):**
```
_______________________________________
	Here are the matching tasks in your list:
	1.[D][ ] Submit assignment (by: Sep 30 2026)
_______________________________________
```

### Deleting tasks

Deletes the task at the specified index.

**Command Format:** `delete INDEX`

**Example:**
```
delete 3
```

**Expected Output (sample):**
```
_______________________________________
	Gone. Objective removed:
	  [E][ ] Team meeting (from: Oct 01 2026 14:00 to: Oct 01 2026 16:00)
	2 targets remain.
_______________________________________
```

### Exiting the application

Ends the current Yoru session.

**Command Format:** `bye`

**Example:**
```
bye
```

**Expected Output:**
```
	I'm out. Call me when you need clean execution.
_______________________________________
```

### Saving data

Yoru saves tasks automatically to `data/yoru.txt` after successful task-modifying commands.

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| List | `list` | `list` |
| Todo | `todo DESCRIPTION` | `todo Buy groceries` |
| Deadline | `deadline DESCRIPTION /by yyyy-MM-dd` | `deadline Submit assignment /by 2026-09-30` |
| Event | `event DESCRIPTION /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm` | `event Team meeting /from 2026-10-01 1400 /to 2026-10-01 1600` |
| Mark | `mark INDEX` | `mark 1` |
| Unmark | `unmark INDEX` | `unmark 1` |
| Find | `find KEYWORD` | `find assignment` |
| Delete | `delete INDEX` | `delete 1` |
| Exit | `bye` | `bye` |

## FAQ

**Q: How do I transfer my data to another computer?**

A: Copy `data/yoru.txt` to the same location in the other copy of the project.

**Q: What happens if I enter an invalid deadline format?**

A: Yoru will reject it and prompt you to use `yyyy-MM-dd`.

**Q: What happens if an event end time is earlier than the start time?**

A: Yoru will reject the command because event end time must be after start time.

**Q: Are tasks saved automatically?**

A: Yes. Successful add/mark/unmark/delete operations are saved automatically.

## Known Issues

- `find` only checks task descriptions.
- If you manually corrupt lines in `data/yoru.txt`, those lines may be skipped during load.