import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    private static final double DELTA = 0.01;
    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee("EM123", "Mike", "Developer", Positions.JUNIOR, "IT", 25000);
    }

    // ---------- raiseSalary ----------

    @Test
    @DisplayName("raiseSalary increases basic salary by 15% and returns the new salary")
    void raiseSalary_increasesBasicSalaryByPercentage() throws HighSalaryIncrease {
        assertEquals(28750.0, employee.raiseSalary(15), DELTA);
        assertEquals(28750.0, employee.getBasicSalary(), DELTA);
    }

    @Test
    @DisplayName("raiseSalary throws HighSalaryIncrease when increase is more than R5000")
    void raiseSalary_throwsWhenIncreaseExceedsThreshold() {
        Employee lerato = new Employee("EM126", "Lerato", "Accountant", Positions.SENIOR, "FINANCE", 34000);
        HighSalaryIncrease ex = assertThrows(HighSalaryIncrease.class, () -> lerato.raiseSalary(15));
        assertEquals("Salary increase of 5100.0 for Lerato is more than the threshold R5000", ex.getMessage());
        assertEquals(34000.0, lerato.getBasicSalary(), DELTA); // salary unchanged
    }

    @Test
    @DisplayName("raiseSalary allows an increase of exactly R5000")
    void raiseSalary_allowsIncreaseOfExactlyThreshold() throws HighSalaryIncrease {
        Employee e = new Employee("EM200", "Sam", "Tester", Positions.JUNIOR, "IT", 50000);
        assertEquals(55000.0, e.raiseSalary(10), DELTA);
    }

    // ---------- calcHouseAllowance ----------

    @Test
    @DisplayName("calcHouseAllowance returns 8% of basic salary")
    void calcHouseAllowance_returnsEightPercentOfBasic() {
        assertEquals(2000.0, employee.calcHouseAllowance(), DELTA);
    }

    // ---------- calcGrossSalary ----------

    @Test
    @DisplayName("calcGrossSalary returns basic salary plus house allowance")
    void calcGrossSalary_returnsBasicPlusAllowance() {
        assertEquals(27000.0, employee.calcGrossSalary(), DELTA);
    }

    // ---------- calcTax ----------

    @Test
    @DisplayName("calcTax returns 15% of gross salary")
    void calcTax_returnsFifteenPercentOfGross() {
        assertEquals(4050.0, employee.calcTax(), DELTA);
    }

    // ---------- calcNetSalary ----------

    @Test
    @DisplayName("calcNetSalary returns gross salary minus tax deduction")
    void calcNetSalary_returnsGrossMinusTax() {
        assertEquals(22950.0, employee.calcNetSalary(), DELTA);
    }

    // ---------- validation & promotion ----------

    @Test
    @DisplayName("Constructor throws when employee id does not start with EM")
    void constructor_throwsWhenIdDoesNotStartWithEM() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("XX123", "Mike", "Developer", Positions.JUNIOR, "IT", 25000));
    }

    @Test
    @DisplayName("Constructor throws when basic salary is less than R4000")
    void constructor_throwsWhenSalaryBelowMinimum() {
        assertThrows(IllegalArgumentException.class,
                () -> new Employee("EM123", "Mike", "Developer", Positions.JUNIOR, "IT", 3999));
    }

    @Test
    @DisplayName("promote moves Intermediate to Senior with 7 years of experience")
    void promote_intermediateToSeniorWithSevenYears() {
        Employee e = new Employee("EM125", "Jessica", "Tester", Positions.INTERMEDIATE, "IT", 31000);
        assertEquals(Positions.SENIOR, e.promote(7));
    }
}