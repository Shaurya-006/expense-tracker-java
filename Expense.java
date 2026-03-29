public class Expense {
    int id;
    String category;
    double amount;

    Expense(int id, String category, double amount) {
        this.id = id;
        this.category = category;
        this.amount = amount;
    }

    void display() {
        System.out.println(id + " " + category + " " + amount);
    }

    String toFileString() {
        return id + "," + category + "," + amount;
    }
}