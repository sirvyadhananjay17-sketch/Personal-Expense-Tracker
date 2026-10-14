import java.time.LocalDate;
public class Expense {
     private int id;
    private double amount;
    private String category;
    private String description;
    private LocalDate date;

      // Constructor
    public Expense(int id, double amount, String category,
                   String description, LocalDate date) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    
public int getId() {
    return id;
}

public double getAmount() {
    return amount;
}

public String getCategory() {
    return category;
}

public String getDescription() {
    return description;
}

public LocalDate getDate() {
    return date;
}


public void displayExpense() {
    System.out.println("ID: " + id);
    System.out.println("Amount: " + amount);
    System.out.println("Category: " + category);
    System.out.println("Description: " + description);
    System.out.println("Date: " + date);
}

}
