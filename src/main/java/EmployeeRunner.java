/**
 * Main class: creates employees, raises salaries, promotes and prints details.
 */
public class EmployeeRunner {

    private static final double RAISE_PERCENTAGE = 15;
    private static final int YEARS_OF_EXPERIENCE = 7;

    public static void main(String[] args) {
        Employee[] employees = new Employee[4];

        try {
            employees[0] = new Employee("EM123", "Mike", "Developer", Positions.JUNIOR, "IT", 25000);
            employees[1] = new Employee("EM124", "Thabo", "Recruiter", Positions.DIRECTOR, "HR", 26000);
            employees[2] = new Employee("EM125", "Jessica", "Tester", Positions.INTERMEDIATE, "IT", 31000);
            employees[3] = new Employee("EM126", "Lerato", "Accountant", Positions.SENIOR, "FINANCE", 34000);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid employee data: " + e.getMessage());
            return;
        }

        for (Employee employee : employees) {
            try {
                employee.raiseSalary(RAISE_PERCENTAGE);
                employee.promote(YEARS_OF_EXPERIENCE);
                System.out.println(employee);
            } catch (HighSalaryIncrease e) {
                System.out.println(e.getMessage());
            }
        }
    }
}