import java.util.*;
import java.io.*;

public class Main {
    

    static ArrayList<Expense> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Expense Tracker Started");

        Scanner sc = new Scanner(System.in);

        loadData();

        while (true) {
            System.out.println("\nSimple Expense Tracker");
            System.out.println("1 Add");
            System.out.println("2 View");
            System.out.println("3 Total");
            System.out.println("4 Delete");
            System.out.println("5 Exit");

            int ch = sc.nextInt();

            if (ch == 1) {
                System.out.print("Enter id: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter category: ");
                String cat = sc.nextLine();

                System.out.print("Enter amount: ");
                double amt = sc.nextDouble();

                list.add(new Expense(id, cat, amt));
                saveData();
            }

            else if (ch == 2) {
                for (Expense e : list) {
                    e.display();
                }
            }

            else if (ch == 3) {
                double sum = 0;
                for (Expense e : list) {
                    sum = sum + e.amount;
                }
                System.out.println("Total = " + sum);
            }

            else if (ch == 4) {
                System.out.print("Enter id to delete: ");
                int id = sc.nextInt();

                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).id == id) {
                        list.remove(i);
                        break;
                    }
                }
                saveData();
            }

            else if (ch == 5) {
                break;
            }

            else {
                System.out.println("Wrong choice");
            }
        }
    }

    static void saveData() {
        try {
            FileWriter fw = new FileWriter("data.txt");
            for (Expense e : list) {
                fw.write(e.toFileString() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving");
        }
    }

    static void loadData() {
        try {
            File f = new File("data.txt");
            if (!f.exists()) return;

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] p = line.split(",");

                int id = Integer.parseInt(p[0]);
                String cat = p[1];
                double amt = Double.parseDouble(p[2]);

                list.add(new Expense(id, cat, amt));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading");
        }
    }
}