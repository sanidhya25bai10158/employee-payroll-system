import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class PayrollSystem {

    private final List<Employee> employees;
    private final String filePath;

    public PayrollSystem(String filePath) {
        this.filePath = filePath;
        this.employees = new ArrayList<>();
        loadFromFile();
    }


    private void loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                // Skip the header row ("id,name,designation,...")
                if (firstLine && line.toLowerCase().startsWith("id,")) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;
                employees.add(Employee.fromCSV(line));
            }
        } catch (IOException e) {
            System.out.println("Warning: could not read data file (" + e.getMessage() + "). Starting with an empty list.");
        }
    }

    public void saveToFile() {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            writer.println("id,name,designation,basicSalary,allowances,deductions");
            for (Employee e : employees) {
                writer.println(e.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error: could not save data file (" + e.getMessage() + ").");
        }
    }


    public int getNextId() {
        int max = 0;
        for (Employee e : employees) {
            if (e.getId() > max) {
                max = e.getId();
            }
        }
        return max + 1;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        saveToFile();
    }


    public boolean removeEmployee(int id) {
        Employee target = findEmployee(id);
        if (target == null) {
            return false;
        }
        employees.remove(target);
        saveToFile();
        return true;
    }

    public Employee findEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }


    public boolean updateEmployee(int id, String name, String designation,
                                   Double basicSalary, Double allowances, Double deductions) {
        Employee e = findEmployee(id);
        if (e == null) {
            return false;
        }
        if (name != null) e.setName(name);
        if (designation != null) e.setDesignation(designation);
        if (basicSalary != null) e.setBasicSalary(basicSalary);
        if (allowances != null) e.setAllowances(allowances);
        if (deductions != null) e.setDeductions(deductions);

        saveToFile();
        return true;
    }

    public List<Employee> getAllEmployees() {
        return employees;
    }
}
