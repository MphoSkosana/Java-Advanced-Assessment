public interface IEmployee {

    double raiseSalary(double percentage) throws HighSalaryIncrease;

    double calcHouseAllowance();

    double calcGrossSalary();

    double calcTax();

    double calcNetSalary();
}