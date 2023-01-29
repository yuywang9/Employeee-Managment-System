import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee> {
    private String name;
    private int annualLeaves;
    public int sickleaves = 135;
    ArrayList<Team> head = new ArrayList<>();
    ArrayList<Team> member = new ArrayList<>();
    ArrayList<Leave> leaves = new ArrayList<>();
    ArrayList<Leave> acting_period = new ArrayList<>();

    public Employee(String n, int al) {
        name = n;
        annualLeaves = al;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public int getannualLeaves() {
        return annualLeaves;
    }

    public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) {
        for (Employee e : list) {
            if (e.getName().equals(nameToSearch)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public int compareTo(Employee another) {
        return this.name.compareTo(another.name);
    }

    public static void list(ArrayList<Employee> allEmployees) {
        for (Employee e : allEmployees) {
            System.out.printf("%s (Entitled Annual Leaves: %d days)\n", e.name, e.annualLeaves);
        }
    }

    public static void listLeaves() {
        for (Employee e : Company.getInstance().allEmployees) {
            System.out.println(e.getName() + ":");
            if (e.leaves.size() == 0) {
                System.out.println("No leave record");
                continue;
            }
            Collections.sort(e.leaves);
            for (Leave l : e.leaves) {
                String start = l.start;
                String end = l.end;
                if (l.start.charAt(0) == '0') {
                    start = l.start.substring(1, l.start.length());
                }
                if (l.end.charAt(0) == '0') {
                    end = l.end.substring(1, l.end.length());
                }
                System.out.println(start + " to " + end + " [" + l.type + "]");
            }
        }
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
}
