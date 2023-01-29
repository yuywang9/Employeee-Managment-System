import java.util.ArrayList;
import java.util.Collections;

public class CmdListRoles implements Command {
    @Override
    public void execute(String[] cmdParts) {
        try {
            ArrayList<Team> teamsinvolved = new ArrayList<>();
            Employee e = Employee.searchEmployee(Company.getInstance().allEmployees, cmdParts[1]);
            if (e == null) {
                throw new ExEmployeenotfound();
            }
            for (Team team : e.head) {
                teamsinvolved.add(team);
            }
            for (Team team : e.member) {
                teamsinvolved.add(team);
            }
            if (teamsinvolved.size() == 0) {
                throw new ExNoRole();
            }
            Collections.sort(teamsinvolved);
            for (Team team : teamsinvolved) {
                if (team.getHead().getName().equals(e.getName())) {
                    System.out.println(team.getName() + " (Head of Team)");
                } else {
                    System.out.println(team.getName());
                }
            }
        } catch (ExNoRole e) {
            System.out.println(e.getMessage());
        } catch (ExEmployeenotfound e) {
            System.out.println(e.getMessage());
        }
    }
}
