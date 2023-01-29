import java.util.ArrayList;
import java.util.Collections;

public class Company {
    public ArrayList<Employee> allEmployees;
    public ArrayList<Team> allTeams;
    public static ArrayList<String> dates = new ArrayList<>();
    private static Company instance = new Company();

    private Company() {
        allEmployees = new ArrayList<>();
        allTeams = new ArrayList<>();
    }

    public static Company getInstance() {
        return instance;
    }

    public void listTeams() {
        Team.list(allTeams);
    }

    public void listTeamMembers() {
        Team.listteammembers(allTeams);
    }

    public void listEmployees() {
        Employee.list(allEmployees);
    }

    public void listLeave() {
        Employee.listLeaves();
    }

    public Employee createEmployee(String n, int al) {
        Employee e = new Employee(n, al);
        allEmployees.add(e);
        Collections.sort(allEmployees);
        return e;
    }

    public Team createTeam(String tn, String hn) {
        Employee e = Employee.searchEmployee(allEmployees, hn);
        Team t = new Team(tn, e);
        t.people.add(e);
        e.head.add(t);
        Collections.sort(t.people);
        Collections.sort(e.head);
        allTeams.add(t);
        Collections.sort(allTeams);
        return t;
    }

    public void addEmployee(Employee e) {
        this.allEmployees.add(e);
        Collections.sort(allEmployees);
    }

    public void removeEmployee(Employee e) {
        this.allEmployees.remove(e); // .remove is a method of ArrayList
    }

    public void addTeam(Team t) {
        this.allTeams.add(t);
        t.getHead().head.add(t);
        Collections.sort(t.getHead().head);
        Collections.sort(allTeams);// .remove is a method of ArrayList
    }

    public void removeTeam(Team t) {
        this.allTeams.remove(t);
        t.getHead().head.remove(t);
    }

    public void undoaddteammember(Team t, Employee e) {
        t.people.remove(e);
        e.member.remove(t);
    }

    public void redoaddteammember(Team t, Employee e) {
        t.people.add(e);
        e.member.add(t);
        Collections.sort(t.people);
        Collections.sort(e.member);
    }
} // The instance created when the class is loaded. ..