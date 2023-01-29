public class CmdHire extends RecordedCommand {
    private Employee e;

    @Override
    public void execute(String[] cmdParts) {
        try {
            Company c = Company.getInstance();
            if (cmdParts.length <= 2) {
                throw new ExInsufficientcommand();
            }
            if (Integer.parseInt(cmdParts[2]) > 300) {
                throw new ExAnnualLeavesexceed();
            }
            for (Employee em : Company.getInstance().allEmployees) {
                if (em.getName().equals(cmdParts[1])) {
                    throw new ExEmployeexist();
                }
            }
            e = c.createEmployee(cmdParts[1], Integer.parseInt(cmdParts[2]));
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientcommand e) {
            System.out.println(e.getMessage());
        } catch (ExAnnualLeavesexceed e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeexist e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Company c = Company.getInstance();
        c.removeEmployee(e);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company c = Company.getInstance();
        c.addEmployee(e);
        addUndoCommand(this);
    }
}
