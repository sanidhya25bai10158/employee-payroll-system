public class Employee {

    private int id;
    private String name;
    private String designation;
    private double basicSalary;
    private double allowances;
    private double deductions;

    public Employee(int id, String name, String designation,
                     double basicSalary, double allowances, double deductions) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.allowances = allowances;
        this.deductions = deductions;
    }

    // ---------- Getters ----------

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getAllowances() {
        return allowances;
    }

    public double getDeductions() {
        return deductions;
    }

    /**
     * Net salary = basic salary + allowances - deductions.
     */
    public double getNetSalary() {
        return basicSalary + allowances - deductions;
    }

    // ---------- Setters (used when modifying an employee) ----------

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setAllowances(double allowances) {
        this.allowances = allowances;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }


    public String toCSV() {
        return id + "," + name + "," + designation + "," +
                basicSalary + "," + allowances + "," + deductions;
    }

    public static Employee fromCSV(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        String designation = parts[2].trim();
        double basicSalary = Double.parseDouble(parts[3].trim());
        double allowances = Double.parseDouble(parts[4].trim());
        double deductions = Double.parseDouble(parts[5].trim());
        return new Employee(id, name, designation, basicSalary, allowances, deductions);
    }


    @Override
    public String toString() {
        return String.format(
                "ID: %-4d | Name: %-18s | Designation: %-15s | Basic: %-10.2f | Allowances: %-10.2f | Deductions: %-10.2f | Net Salary: %-10.2f",
                id, name, designation, basicSalary, allowances, deductions, getNetSalary());
    }
}
