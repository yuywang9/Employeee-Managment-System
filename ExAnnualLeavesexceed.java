public class ExAnnualLeavesexceed extends Exception {
    public ExAnnualLeavesexceed() {
        super("Out of range (Entitled Annual Leaves should be within 0-300)!");
    }
}