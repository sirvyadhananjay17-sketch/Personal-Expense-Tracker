
import java.time.LocalDate;
import java.sql.SQLException;
import java.util.List;

public class TestDAO {

    public static void main(String[] args) {

        ExpenseDAO dao = new ExpenseDAO();

        try {
            // Create a test expense
            Expense expense = new Expense(
                0,
                250.00,
                "Food",
                "DAO test expense",
                LocalDate.now()
            );

            // Save it in MySQL
            dao.addExpense(expense);
            System.out.println("Expense saved to MySQL!");

            // Retrieve all expenses
            List<Expense> expenses = dao.getAllExpenses();

            System.out.println("\n===== DATABASE EXPENSES =====");

            for (Expense e : expenses) {
                e.displayExpense();
                System.out.println("--------------------");
            }

        } catch (SQLException e) {
            System.out.println("Database operation failed:");
            System.out.println(e.getMessage());
        }
    }
}
