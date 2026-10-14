
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

public class ExpenseTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ExpenseDAO dao = new ExpenseDAO();  
        int choice = 0;

        do {
            System.out.println("\n===== PERSONAL EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Total Spending");
            System.out.println("4. View Category Summary");
            System.out.println("5. Delete Expense");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            // Validate menu input
            if (!sc.hasNextInt()) {
                System.out.println(
                    "Invalid choice. Please enter a number from 1 to 6."
                );
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // 1. Add Expense
                
case 1: {
    System.out.print("Enter amount: ");

    if (!sc.hasNextDouble()) {
        System.out.println("Invalid amount. Enter a number.");
        sc.nextLine();
        break;
    }

    double amount = sc.nextDouble();
    sc.nextLine();

    if (!Double.isFinite(amount) || amount <= 0) {
        System.out.println("Amount must be greater than zero.");
        break;
    }

    System.out.print("Enter category: ");
    String category = sc.nextLine().trim();

    if (category.isEmpty()) {
        System.out.println("Category cannot be empty.");
        break;
    }

    System.out.print("Enter description: ");
    String description = sc.nextLine().trim();

    if (description.isEmpty()) {
        System.out.println("Description cannot be empty.");
        break;
    }

    Expense expense = new Expense(
        0, amount, category, description, LocalDate.now()
    );

    try {
        dao.addExpense(expense);
        System.out.println("Expense saved to MySQL!");
    } catch (SQLException e) {
        System.out.println("Could not save expense: " + e.getMessage());
    }
    break;
}


                // 2. View Expenses
                
case 2: {
    try {
        List<Expense> expenses = dao.getAllExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            System.out.println("\n===== ALL EXPENSES =====");

            for (Expense e : expenses) {
                e.displayExpense();
                System.out.println("--------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println("Could not retrieve expenses: " + e.getMessage());
    }
    break;
}


                // 3. View Total Spending
                
case 3: {
    try {
        List<Expense> expenses = dao.getAllExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses to calculate.");
        } else {
            double total = 0;

            for (Expense e : expenses) {
                total += e.getAmount();
            }

            System.out.printf("Total Spending: Rs. %.2f%n", total);
        }
    } catch (SQLException e) {
        System.out.println("Could not calculate total: " + e.getMessage());
    }
    break;
}


                // 4. View Category Summary
                
case 4: {
    try {
        List<Expense> expenses = dao.getAllExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expenses found.");
        } else {
            Map<String, Double> summary = new LinkedHashMap<>();

            for (Expense e : expenses) {
                String category = e.getCategory();

                summary.put(
                    category,
                    summary.getOrDefault(category, 0.0) + e.getAmount()
                );
            }

            System.out.println("\n===== CATEGORY SUMMARY =====");

            for (Map.Entry<String, Double> entry : summary.entrySet()) {
                System.out.printf(
                    "%s: Rs. %.2f%n",
                    entry.getKey(),
                    entry.getValue()
                );
            }
        }
    } catch (SQLException e) {
        System.out.println("Could not load category summary: " + e.getMessage());
    }
    break;
}


                // 5. Delete Expense
                
case 5: {
    System.out.print("Enter expense ID to delete: ");

    if (!sc.hasNextInt()) {
        System.out.println("Invalid ID. Enter a whole number.");
        sc.nextLine();
        break;
    }

    int id = sc.nextInt();
    sc.nextLine();

    if (id <= 0) {
        System.out.println("ID must be greater than zero.");
        break;
    }

    try {
        boolean removed = dao.deleteExpense(id);

        if (removed) {
            System.out.println("Expense deleted successfully!");
        } else {
            System.out.println("Expense ID not found.");
        }
    } catch (SQLException e) {
        System.out.println("Could not delete expense: " + e.getMessage());
    }
    break;
}


                // 6. Exit
                case 6:
                    System.out.println(
                        "Exiting Expense Tracker..."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Enter a number from 1 to 6."
                    );
            }

        } while (choice != 6);

        sc.close();
    }
}
