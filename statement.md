# Statement — Employee Payroll Management System

## Problem statement
Tracking employee pay manually in a notebook or some Excel sheet gets comlicated as the scale of a business or complany increases. And it becomes easy to lose track of who you just added, forget to update a salary after someone gets a raise, or end up with conflicting records. 


This project is an attempt at finding the middle ground. It's a small program that lets us save employee details, automatically does the calculations for their net salary, and keeps that data safe.

## Scope of the Project
To separate it from a commerical HR software the scope has been set at a local pc level, with the data being saved ia a Excel file.
- It's a single-user command line app. There are no login screens or admin roles.
- Manages employee records saved locally in a single file (`data/employees.csv`).
- Handles: employee ID, name, designation, basic pay, allowances, and deductions. It calculates the net salary from that data.
- Does not handle advanced HR tasks like tax brackets, sick leave, or generating PDF payslips.
- Run's entirely in the terminal on any computer with a Java Runtime Environment present asa prerequisite.

## Target users 
- Small businesses or managers: like small shop owners, tutors, or people managing freelancers who just want to keep salary records straight without buying expensive software.
- Students and teachers: It works as a clean, readable example of OOPs, basic error catching, and file handling in Java.
- Beginner coders: The whole codebase is short enough to read in one sitting if you want to see how a CLI app works.

## High level Features
- Add a new employee (the system automatically assigns them an ID).
- View the whole team in a clean, readable list.
- Update specific details without having to re-type the fields you want to leave alone.
- Search for or remove employees by their ID.
- Automatically calculate net salary (`basic + allowances - deductions`) so you don't have to do the math.
- Save everything to a CSV instantly, so nothing is lost when you close the terminal.
- Catch bad inputs gracefully (like accidentally typing letters into a salary field) instead of just crashing the program.