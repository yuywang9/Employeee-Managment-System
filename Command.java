public interface Command {
    void execute(String[] cmdParts)
            throws ExEmployeexist, ExInsufficientcommand, ExAnnualLeavesexceed, ExTeamExist, ExEmployeenotfound;
}
