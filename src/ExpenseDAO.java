
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    // Save an expense in MySQL
    public void addExpense(Expense expense) throws SQLException {

        String sql = """
            INSERT INTO expenses
            (amount, category, description, expense_date)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, expense.getAmount());
            ps.setString(2, expense.getCategory());
            ps.setString(3, expense.getDescription());
            ps.setDate(4, Date.valueOf(expense.getDate()));

            ps.executeUpdate();
        }
    }

    // Retrieve all expenses from MySQL
    public List<Expense> getAllExpenses() throws SQLException {

        List<Expense> expenses = new ArrayList<>();

        String sql = """
            SELECT id, amount, category, description, expense_date
            FROM expenses
            ORDER BY id
            """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Expense expense = new Expense(
                    rs.getInt("id"),
                    rs.getDouble("amount"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getDate("expense_date").toLocalDate()
                );

                expenses.add(expense);
            }
        }

        return expenses;
    }

    // Delete an expense using its database ID
    public boolean deleteExpense(int id) throws SQLException {

        String sql = "DELETE FROM expenses WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}
