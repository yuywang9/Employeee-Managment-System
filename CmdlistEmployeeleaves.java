import java.util.Collections;

public class CmdlistEmployeeleaves implements Command {
    @Override
    public void execute(String[] cmdParts) {
        Company c = Company.getInstance();
        Employee aim = Employee.searchEmployee(c.allEmployees, cmdParts[1]);
        if (aim.leaves.size() == 0) {
            System.out.println("No leave record");
        }
        Collections.sort(aim.leaves);
        for (Leave l : aim.leaves) {
            String start = l.start;
            String end = l.end;
            if (l.start.charAt(0) == '0') {
                start = l.start.substring(1, start.length());
            }
            if (l.end.charAt(0) == '0') {
                end = l.end.substring(1, end.length());
            }
            System.out.println(start + " to " + end + " [" + l.type + "]");
        }
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }
}
