import java.util.Collections;

public class CmdAddTeamMember extends RecordedCommand {
    private Employee addone;
    private Team addedteam;

    @Override
    public void execute(String[] cmdParts) {
        try {
            if (cmdParts.length < 3) {
                throw new ExInsufficientcommand();
            }
            Company c = Company.getInstance();
            int jud = 0;
            for (Team team : c.allTeams) {
                if (cmdParts[1].equals(team.getName())) {
                    jud++;
                    Employee aim = Employee.searchEmployee(c.allEmployees, cmdParts[2]);
                    if (aim == null) {
                        throw new ExEmployeenotfound();
                    }
                    for (Team t : aim.head) {
                        if (t.equals(team)) {
                            throw new ExEmployeeJoined();
                        }
                    }
                    for (Team t : aim.member) {
                        if (t.equals(team)) {
                            throw new ExEmployeeJoined();
                        }
                    }
                    team.people.add(aim);
                    aim.member.add(team);
                    Collections.sort(team.people);
                    Collections.sort(aim.member);
                    addone = aim;
                    addedteam = team;
                }
            }
            if (jud == 0) {
                throw new ExTeamdoesntexist();
            }
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (ExInsufficientcommand e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeeJoined e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeenotfound e) {
            System.out.println(e.getMessage());
        } catch (ExTeamdoesntexist e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        Company c = Company.getInstance();
        c.undoaddteammember(addedteam, addone);
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        Company c = Company.getInstance();
        c.redoaddteammember(addedteam, addone);
        addUndoCommand(this);
    }
}
