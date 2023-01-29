public class setupTeam extends RecordedCommand {
    private Team t;

    @Override
    public void execute(String[] cmdParts) {
        Company c = Company.getInstance();
        try {
            if (cmdParts.length <= 2) {
                throw new ExInsufficientcommand();
            }
            boolean jud = false;
            for (Employee em : Company.getInstance().allEmployees) {
                if (em.getName().equals(cmdParts[2])) {
                    jud = true;
                }
            }
            if (jud == false) {
                throw new ExEmployeenotfound();
            }
            for (Team em : Company.getInstance().allTeams) {
                if (em.getName().equals(cmdParts[1])) {
                    throw new ExTeamExist();
                }
            }
            t = c.createTeam(cmdParts[1], cmdParts[2]);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientcommand e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeenotfound e) {
            System.out.println(e.getMessage());
        } catch (ExTeamExist e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Company c = Company.getInstance();
        c.removeTeam(t);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company c = Company.getInstance();
        c.addTeam(t);
        addUndoCommand(this);
    }
}
