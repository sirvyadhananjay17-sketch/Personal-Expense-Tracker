# Personal Expense Tracker

A console-based expense management application built using Core Java, JDBC, and MySQL. The application allows users to record expenses, review transaction history, calculate total spending, view category-wise summaries, and delete records.

## Features

- Add expenses with amount, category, description, and date
- View all recorded expenses
- Calculate total spending
- Generate category-wise spending summaries
- Delete expenses by ID
- Persist expense records in MySQL across application restarts
- Validate user input and handle database errors

## Tech Stack

- Java
- JDBC
- MySQL
- MySQL Connector/J
- VS Code

## Project Structure

- `Expense.java` — Expense data model
- `ExpenseTracker.java` — Console menu and application logic
- `DBConnection.java` — Database connection management
- `ExpenseDAO.java` — Database access operations
- `database/schema.sql` — Database schema

## Database Setup

1. Install MySQL Server.
2. Execute `database/schema.sql` in MySQL Workbench.
3. Configure the `DB_USER` and `DB_PASSWORD` environment variables.
4. Ensure the MySQL server is running.

## Run the Application

Compile the source files using the MySQL Connector/J JAR, then run `ExpenseTracker` with the compiled classes and JDBC JAR on the classpath.

## Learning Outcomes

Practiced object-oriented programming, JDBC connectivity, SQL CRUD operations, prepared statements, exception handling, input validation, and database persistence.
