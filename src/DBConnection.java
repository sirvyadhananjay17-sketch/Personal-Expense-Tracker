
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/expense_tracker";

    public static Connection getConnection() throws SQLException {

        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        if (user == null || password == null) {
            throw new SQLException(
                "Set DB_USER and DB_PASSWORD environment variables."
            );
        }

        return DriverManager.getConnection(URL, user, password);
    }
}
