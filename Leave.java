import java.util.ArrayList;

public class Leave implements Comparable<Leave> {
    public String type;
    public String start;
    public String end;
    public Employee sub;
    ArrayList<Team> team = new ArrayList<>();
    ArrayList<Employee> sss = new ArrayList<>();

    public Leave(String type, String start, String end) {
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public static int parsedate(String date) {
        String[] parts1 = date.split("-");
        String month1 = "";
        if (parts1[1].equals("Jan")) {
            month1 = "01";
        }
        if (parts1[1].equals("Feb")) {
            month1 = "02";
        }
        if (parts1[1].equals("Mar")) {
            month1 = "03";
        }
        if (parts1[1].equals("Apr")) {
            month1 = "04";
        }
        if (parts1[1].equals("May")) {
            month1 = "05";
        }
        if (parts1[1].equals("Jun")) {
            month1 = "06";
        }
        if (parts1[1].equals("Jul")) {
            month1 = "07";
        }
        if (parts1[1].equals("Aug")) {
            month1 = "08";
        }
        if (parts1[1].equals("Sep") || parts1[1].equals("Sept")) {
            month1 = "09";
        }
        if (parts1[1].equals("Oct")) {
            month1 = "10";
        }
        if (parts1[1].equals("Nov")) {
            month1 = "11";
        }
        if (parts1[1].equals("Dec")) {
            month1 = "12";
        }
        String first = parts1[2] + month1 + parts1[0];
        return Integer.parseInt(first);
    }

    public static String parsedatesting(String date) {
        String[] parts1 = date.split("-");
        String month1 = "";
        if (parts1[1].equals("Jan")) {
            month1 = "01";
        }
        if (parts1[1].equals("Feb")) {
            month1 = "02";
        }
        if (parts1[1].equals("Mar")) {
            month1 = "03";
        }
        if (parts1[1].equals("Apr")) {
            month1 = "04";
        }
        if (parts1[1].equals("May")) {
            month1 = "05";
        }
        if (parts1[1].equals("Jun")) {
            month1 = "06";
        }
        if (parts1[1].equals("Jul")) {
            month1 = "07";
        }
        if (parts1[1].equals("Aug")) {
            month1 = "08";
        }
        if (parts1[1].equals("Sep") || parts1[1].equals("Sept")) {
            month1 = "09";
        }
        if (parts1[1].equals("Oct")) {
            month1 = "10";
        }
        if (parts1[1].equals("Nov")) {
            month1 = "11";
        }
        if (parts1[1].equals("Dec")) {
            month1 = "12";
        }
        String first = parts1[0] + "-" + month1 + "-" + parts1[2];
        return first;
    }

    @Override
    public int compareTo(Leave another) {
        String[] parts1 = this.start.split("-");
        String[] parts2 = another.start.split("-");
        String month1;
        String month2;
        month1 = "";
        month2 = "";
        if (parts1[1].equals("Jan")) {
            month1 = "01";
        }
        if (parts1[1].equals("Feb")) {
            month1 = "02";
        }
        if (parts1[1].equals("Mar")) {
            month1 = "03";
        }
        if (parts1[1].equals("Apr")) {
            month1 = "04";
        }
        if (parts1[1].equals("May")) {
            month1 = "05";
        }
        if (parts1[1].equals("Jun")) {
            month1 = "06";
        }
        if (parts1[1].equals("Jul")) {
            month1 = "07";
        }
        if (parts1[1].equals("Aug")) {
            month1 = "08";
        }
        if (parts1[1].equals("Sep") || parts1[1].equals("Sept")) {
            month1 = "09";
        }
        if (parts1[1].equals("Oct")) {
            month1 = "10";
        }
        if (parts1[1].equals("Nov")) {
            month1 = "11";
        }
        if (parts1[1].equals("Dec")) {
            month1 = "12";
        }
        if (parts2[1].equals("Jan")) {
            month2 = "01";
        }
        if (parts2[1].equals("Feb")) {
            month2 = "02";
        }
        if (parts2[1].equals("Mar")) {
            month2 = "03";
        }
        if (parts2[1].equals("Apr")) {
            month2 = "04";
        }
        if (parts2[1].equals("May")) {
            month2 = "05";
        }
        if (parts2[1].equals("Jun")) {
            month2 = "06";
        }
        if (parts2[1].equals("Jul")) {
            month2 = "07";
        }
        if (parts2[1].equals("Aug")) {
            month2 = "08";
        }
        if (parts2[1].equals("Sep") || parts1[1].equals("Sept")) {
            month2 = "09";
        }
        if (parts2[1].equals("Oct")) {
            month2 = "10";
        }
        if (parts2[1].equals("Nov")) {
            month2 = "11";
        }
        if (parts2[1].equals("Dec")) {
            month2 = "12";
        }
        String first = parts1[2] + month1 + parts1[0];
        String second = parts2[2] + month2 + parts2[0];
        if (Integer.parseInt(first) > Integer.parseInt(second)) {

            return 1;
        }
        if (Integer.parseInt(first) == Integer.parseInt(second)) {

            return 0;
        }
        if (Integer.parseInt(first) < Integer.parseInt(second)) {

            return -1;
        } else {
            return 0;
        }
    }
}
