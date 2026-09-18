# Employee Payroll Management System

This is a small command-line Java app built for Programming in Java course. It’s a simple tool to manage staff records, you can add, view, update, search, and remove employees, and it automatically calculates their net pay. 

There are no GUI or databases to configure. It runs straight from terminal and saves everything to an CSV file.

## Overview of the project

Tracking employee pay manually in a notebook or some Excel sheet gets comlicated as the scale of a business or complany increases. And it becomes easy to lose track of who you just added, forget to update a salary after someone gets a raise, or end up with conflicting records. 
This project is an attempt at finding the middle ground. It is a program that lets us save employe details and automatically does the calculations for net salary, and keeps that data safe.

The code is broken down into three main pieces:
- **Employee**: Holds the data for a single person and handles the net salary math.
- **PayrollSystem**: Manages the active list of employees, handles the search/update logic, and takes care of reading/writing to the CSV file.
- **Main**: Runs the terminal menu, grabs what you type, and triggers the right methods.

Every time we change a record, the program immediately overwrites `data/employees.csv`. 

## Features

- Auto-generates employee IDs so they dont need to be tracked.
- Shows a cleanly formatted list of all staff.
- Updates records easily (just press Enter to leave a field blank if you don't want to change it).
- Searches and removes records by ID.
- Calculates net salary on the fly (`basic + allowances - deductions`).
- Handles typos and bad inputs (like typing text instead of numbers) without crashing.

## Steps to install & run the project 

**1. Check your Java installation:**
Open your terminal and run:
```bash
java -version
javac -version
```
If you don't get a version number, you will have to install JDK first.

**2. Download the project:**
```bash
git clone <repository-url>
cd employee-payroll-system
```

**3. Compile the code:**
```bash
javac -d bin src/*.java
```
This should run quietly and create a `bin/` folder full of `.class` files.

**4. Run it:**
```bash
java -cp bin Main
```
You'll see a numbered menu pop up. Just type a number (1–6) and press Enter to use the app. 

*Note: You don't even need to set up the data folder manually. The app creates the `data` folder and the `employees.csv` file the first time you add someone.*

## Instructions for testing 

There aren't any automated tests for this, so I recommend testing it by hand like this:
1. **Add** a couple of employees with different salaries and check if the math looks right.
2. **View** the list to make sure they show up.
3. **Update** someone, but try skipping a few fields to make sure it only changes what you want it to.
4. **Search** for a fake ID to make sure it gives you a "not found" error.
5. Try typing some random letters when it asks for a salary to make sure it catches the error and asks again instead of crashing.