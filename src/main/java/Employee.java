/**
 * Represents an employee and calculates their salary breakdown.
 */
public class Employee implements IEmployee {

    public static final double TAX = 0.15;                 // 15%
    public static final double HOUSE_ALLOWANCE = 0.08;     // 8%
    public static final double MAX_INCREASE = 5000.0;      // R5000
    public static final double MIN_BASIC_SALARY = 4000.0;  // R4000
    private static final String ID_PREFIX = "EM";

    private String employeeId;
    private String employeeName;
    private String jobTitle;
    private Positions position;
    private String department;
    private double basicSalary;

    public Employee() {
    }

    public Employee(String employeeId, String employeeName, String jobTitle, Positions position, String department, double basicSalary) {
        setEmployeeId(employeeId);
        setEmployeeName(employeeName);
        setJobTitle(jobTitle);
        setPosition(position);
        setDepartment(department);
        setBasicSalary(basicSalary);
    }

    // ---------------- Getters and setters ----------------

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        if (employeeId == null || !employeeId.startsWith(ID_PREFIX)) {
            throw new IllegalArgumentException(
                    "Employee ID must start with '" + ID_PREFIX + "' but was: " + employeeId);
        }
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Positions getPosition() {
        return position;
    }

    public void setPosition(Positions position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        if (basicSalary < MIN_BASIC_SALARY) {
            throw new IllegalArgumentException(
                    "Basic salary must be at least R" + MIN_BASIC_SALARY + " but was: R" + basicSalary);
        }
        this.basicSalary = basicSalary;
    }

    // ---------------- Business methods ----------------

    /**
     * Increases the basic salary by the given percentage (e.g. 15 for 15%).
     *
     * @return the new basic salary
     * @throws HighSalaryIncrease if the increase is more than R5000
     */
    @Override
    public double raiseSalary(double percentage) throws HighSalaryIncrease {
        double increase = round(basicSalary * percentage / 100);
        if (increase > MAX_INCREASE) {
            throw new HighSalaryIncrease("Salary increase of " + increase + " for "
                    + employeeName + " is more than the threshold R" + (int) MAX_INCREASE);
        }
        basicSalary = round(basicSalary + increase);
        return basicSalary;
    }

    /**
     * Promotes the employee based on years of experience.
     *
     * @return the (possibly unchanged) position of the employee
     */
    public Positions promote(int years) {
        if (years >= 3 && years <= 5) {
            if (position == Positions.JUNIOR) {
                position = Positions.INTERMEDIATE;
            } else if (position == Positions.INTERMEDIATE) {
                position = Positions.SENIOR;
            }
        } else if (years >= 6 && years <= 10) {
            if (position == Positions.INTERMEDIATE) {
                position = Positions.SENIOR;
            } else if (position == Positions.SENIOR) {
                position = Positions.DIRECTOR;
            }
        } else if (years > 10) {
            if (position == Positions.SENIOR) {
                position = Positions.DIRECTOR;
            } else if (position == Positions.DIRECTOR) {
                position = Positions.EXECUTIVE;
            }
        }
        return position;
    }

    @Override
    public double calcHouseAllowance() {
        return round(basicSalary * HOUSE_ALLOWANCE);
    }

    @Override
    public double calcGrossSalary() {
        return round(basicSalary + calcHouseAllowance());
    }

    @Override
    public double calcTax() {
        return round(calcGrossSalary() * TAX);
    }

    @Override
    public double calcNetSalary() {
        return round(calcGrossSalary() - calcTax());
    }

    /** Rounds to 2 decimal places to avoid floating point noise. */
    private static double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return "Employee: [" + employeeId + "] " + employeeName + "\n"
                + "Title: " + jobTitle + "\n"
                + "Position: " + position + "\n"
                + "Department: " + department + "\n"
                + "======= Salary Breakdown ======= \n"
                + "Basic: R" + basicSalary + "\n"
                + "House Allowance: R" + calcHouseAllowance() + "\n"
                + "Gross: R" + calcGrossSalary() + "\n"
                + "Tax Deduction: R" + calcTax() + "\n"
                + "Net: R" + calcNetSalary() + "\n"
                + "++++++++++++++++++++++++++++++++++++";
    }
}