/**
 * Thrown when a salary increase is more than the allowed threshold.
 */
public class HighSalaryIncrease extends Exception {

    public HighSalaryIncrease(String message) {
        super(message);
    }
}