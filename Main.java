import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final PayrollSystem system = new PayrollSystem("data/employees.csv");

    public static void main(String[] args) {
        System.out.println("   Employee Payroll Management System");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    removeEmployee();
                    break;
                case 5:
                    searchEmployee();
                    break;
                case 6:
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Update Employee");
        System.out.println("4. Remove Employee");
        System.out.println("5. Search Employee by ID");
        System.out.println("6. Exit");
    }

    // ---------- Menu actions ----------

    private static void addEmployee() {
        System.out.println("\n--- Add New Employee ---");

        int id = system.getNextId();
        System.out.println("Assigned Employee ID: " + id);

        String name = readNonEmptyString("Enter name: ");
        String designation = readNonEmptyString("Enter designation: ");
        double basicSalary = readDouble("Enter basic salary: ");
        double allowances = readDouble("Enter allowances: ");
        double deductions = readDouble("Enter deductions: ");

        Employee employee = new Employee(id, name, designation, basicSalary, allowances, deductions);
        system.addEmployee(employee);

        System.out.println("Employee added successfully!");
    }

    private static void viewAllEmployees() {
        System.out.println("\n--- All Employees ---");

        List<Employee> all = system.getAllEmployees();
        if (all.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        for (Employee e : all) {
            System.out.println(e);
        }
    }

    private static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");

        int id = readInt("Enter Employee ID to update: ");
        Employee existing = system.findEmployee(id);
        if (existing == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Current details: " + existing);
        System.out.println("(Press Enter without typing anything to keep the current value)");

        String name = readOptionalString("New name [" + existing.getName() + "]: ");
        String designation = readOptionalString("New designation [" + existing.getDesignation() + "]: ");
        Double basicSalary = readOptionalDouble("New basic salary [" + existing.getBasicSalary() + "]: ");
        Double allowances = readOptionalDouble("New allowances [" + existing.getAllowances() + "]: ");
        Double deductions = readOptionalDouble("New deductions [" + existing.getDeductions() + "]: ");

        system.updateEmployee(id, name, designation, basicSalary, allowances, deductions);
        System.out.println("Employee updated successfully!");
    }

    private static void removeEmployee() {
        System.out.println("\n--- Remove Employee ---");

        int id = readInt("Enter Employee ID to remove: ");
        boolean removed = system.removeEmployee(id);

        System.out.println(removed ? "Employee removed successfully!" : "Employee not found.");
    }

    private static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");

        int id = readInt("Enter Employee ID: ");
        Employee employee = system.findEmployee(id);

        System.out.println(employee != null ? employee.toString() : "Employee not found.");
    }

    // ---------- Input helpers (basic validation so the program never crashes on bad input) ----------

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    /** Returns null if the user just presses Enter (used by "update" to mean "keep current value"). */
    private static String readOptionalString(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : input;
    }

    /** Returns null if the user just presses Enter (used by "update" to mean "keep current value"). */
    private static Double readOptionalDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number, or press Enter to keep the current value.");
            }
        }
    }
}
