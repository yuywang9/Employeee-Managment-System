import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {
    private String teamName;
    private Employee head;
    private Day dateSetup; // the date this team was setup
    public ArrayList<Employee> people = new ArrayList<>();

    public Team(String n, Employee hd) {
        this.teamName = n;
        this.head = hd;
        dateSetup = SystemDate.getInstance().clone();
    }

    public Team() {

    }

    public static void list(ArrayList<Team> list) {
        // Learn: "-" means left-aligned
        System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
        for (Team t : list)
            System.out.printf("%-30s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup); // display t.teamName, //
                                                                                               // etc..
    }

    public static Team searchTeam(ArrayList<Team> list, String nameToSearch) {
        for (Team e : list) {
            if (e.getName().equals(nameToSearch)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Team another) {
        return this.teamName.compareTo(another.teamName);
    }

    public String getName() {
        return this.teamName;
    }

    public Employee getHead() {
        return this.head;
    }

    public static void listteammembers(ArrayList<Team> list) {
        for (Team t : list) {
            System.out.println(t.getName() + ":");
            ArrayList<Employee> subs = new ArrayList<>();
            ArrayList<Leave> subperiod = new ArrayList<>();
            for (Employee e : t.people) {
                if (e.getName().equals(t.getHead().getName())) {
                    System.out.println(e.getName() + " (Head of Team)");
                } else {
                    System.out.println(e.getName());
                }
                if (e.acting_period.size() > 0) {
                    if (!e.equals(t.head)) {
                        subs.add(e);
                    }
                }
            }
            for (Employee e : subs) {
                for (Leave leave : e.acting_period) {
                    leave.sub = e;
                    subperiod.add(leave);
                    Collections.sort(subperiod);
                }
            }
            if (subperiod.size() > 0) {
                System.out.println("Acting heads:");
                for (Leave actperiod : subperiod) {
                    String start = actperiod.start;
                    String end = actperiod.end;
                    if (start.charAt(0) == '0') {
                        start = start.substring(1, start.length());
                    }
                    if (end.charAt(0) == '0') {
                        end = end.substring(1, end.length());
                    }
                    System.out.println(start + " to " + end + ": " + actperiod.sub.getName());
                }
                System.out.println();
            }
        }
    }
}
