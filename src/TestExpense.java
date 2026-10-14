
import java.time.LocalDate;

public class TestExpense {
    public static void main(String[] args) {

        Expense e = new Expense(
            1,
            200.0,
            "Food",
            "Lunch",
            LocalDate.of(2026, 10, 14)
        );

        e.displayExpense();
    }
}
