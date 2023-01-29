import java.util.ArrayList;
import java.util.Collections;

public class CmdtakeLeave extends RecordedCommand {
    public Leave leave;
    public Employee aim;
    public int length;
    ArrayList<Employee> subss = new ArrayList<Employee>();

    @Override
    public void execute(String[] cmdParts) {
        leave = new Leave(cmdParts[2], cmdParts[3], cmdParts[4]);
        aim = Employee.searchEmployee(Company.getInstance().allEmployees, cmdParts[1]);
        ArrayList<Team> copy = new ArrayList<Team>(aim.head);
        try {
            int balance = aim.getannualLeaves();
            int sick = 135;
            boolean jud = false;
            for (Leave l : aim.leaves) {
                if (Leave.parsedate(l.start) <= Leave.parsedate(leave.start)
                        && Leave.parsedate(l.end) >= Leave.parsedate(leave.start)) {
                    String start = l.start;
                    String end = l.end;
                    if (l.start.charAt(0) == '0') {
                        start = l.start.substring(1, l.start.length());
                    }
                    if (l.end.charAt(0) == '0') {
                        end = l.end.substring(1, l.end.length());
                    }
                    throw new Exleaveoverlapped(start + " to " + end + " [" + l.type + "] is found!");
                }
                if (Leave.parsedate(l.start) <= Leave.parsedate(leave.end)
                        && Leave.parsedate(l.end) >= Leave.parsedate(leave.end)) {
                    String start = l.start;
                    String end = l.end;
                    if (l.start.charAt(0) == '0') {
                        start = l.start.substring(1, l.start.length());
                    }
                    if (l.end.charAt(0) == '0') {
                        end = l.end.substring(1, l.end.length());
                    }
                    throw new Exleaveoverlapped(start + " to " + end + " [" + l.type + "] is found!");
                }
                if (Leave.parsedate(l.start) >= Leave.parsedate(leave.start)
                        && Leave.parsedate(leave.end) >= Leave.parsedate(l.end)) {
                    String start = l.start;
                    String end = l.end;
                    if (l.start.charAt(0) == '0') {
                        start = l.start.substring(1, l.start.length());
                    }
                    if (l.end.charAt(0) == '0') {
                        end = l.end.substring(1, l.end.length());
                    }
                    throw new Exleaveoverlapped(start + " to " + end + " [" + l.type + "] is found!");
                }
                if (l.type.equals("AL") || l.type.equals("BL")) {
                    balance = (int) (balance - Day.difference(Leave.parsedatesting(l.start) + " 00:00:00",
                            Leave.parsedatesting(l.end) + " 00:00:00"));
                }
                if (l.type.equals("BL")) {
                    jud = true;
                }
                if (l.type.equals("SL")) {
                    sick = (int) (sick - Day.difference(Leave.parsedatesting(l.start) + " 00:00:00",
                            Leave.parsedatesting(l.end) + " 00:00:00"));
                }
            }
            for (Leave l : aim.acting_period) {
                if (Leave.parsedate(l.start) <= Leave.parsedate(leave.start)
                        && Leave.parsedate(l.end) >= Leave.parsedate(leave.start)) {
                    String start = l.start;
                    String end = l.end;
                    if (l.start.charAt(0) == '0') {
                        start = l.start.substring(1, l.start.length());
                    }
                    if (l.end.charAt(0) == '0') {
                        end = l.end.substring(1, l.end.length());
                    }
                    int i = 0;
                    Team d = null;
                    for (Employee e : l.sss) {
                        if (e.equals(aim)) {
                            d = l.team.get(i);
                        }
                        i++;
                    }
                    throw new Exactleaveconflict("Cannot take leave. " + aim.getName() + " is the acting head of "
                            + d.getName() + " during " + start + " to " + end + "!");
                }
                if (Leave.parsedate(l.start) <= Leave.parsedate(leave.end)
                        && Leave.parsedate(l.end) >= Leave.parsedate(leave.end)) {
                    String start = l.start;
                    String end = l.end;
                    if (l.start.charAt(0) == '0') {
                        start = l.start.substring(1, l.start.length());
                    }
                    if (l.end.charAt(0) == '0') {
                        end = l.end.substring(1, l.end.length());
                    }
                    int i = 0;
                    Team d = null;
                    for (Employee e : l.sss) {
                        if (e.equals(aim)) {
                            d = l.team.get(i);
                        }
                        i++;
                    }
                    throw new Exactleaveconflict("Cannot take leave. " + aim.getName() + " is the acting head of "
                            + d.getName() + " during " + start + " to " + end + "!");
                }
                if (Leave.parsedate(l.start) >= Leave.parsedate(leave.start)
                        && Leave.parsedate(leave.end) >= Leave.parsedate(l.end)) {
                    String start = l.start;
                    String end = l.end;
                    if (l.start.charAt(0) == '0') {
                        start = l.start.substring(1, l.start.length());
                    }
                    if (l.end.charAt(0) == '0') {
                        end = l.end.substring(1, l.end.length());
                    }
                    int i = 0;
                    Team d = null;
                    for (Employee e : l.sss) {
                        if (e.equals(aim)) {
                            d = l.team.get(i);
                        }
                        i++;
                    }
                    throw new Exactleaveconflict("Cannot take leave. " + aim.getName() + " is the acting head of "
                            + d.getName() + " during " + start + " to " + end + "!");
                }
            }
            if ((7 > balance - Day.difference(Leave.parsedatesting(leave.start) + " 00:00:00",
                    Leave.parsedatesting(leave.end) + " 00:00:00") && (!jud)) && (leave.type.equals("AL"))) {
                throw new Exnoamountforblock("The annual leave is invalid.\nThe current balance is " + balance
                        + " days only.\nThe employee has not taken any block leave yet.\nThe employee can take at most "
                        + (balance - 7)
                        + " days of non-block annual leave\nbecause of the need to reserve 7 days for a block leave.");
            }
            if (balance < Day.difference(Leave.parsedatesting(leave.start) + " 00:00:00",
                    Leave.parsedatesting(leave.end) + " 00:00:00")
                    && (leave.type.equals("AL") || leave.type.equals("BL"))) {
                throw new Exbalanceinsufficient(balance);
            }
            if (sick < Day.difference(Leave.parsedatesting(leave.start) + " 00:00:00",
                    Leave.parsedatesting(leave.end) + " 00:00:00") && (leave.type.equals("SL"))) {
                throw new Exsickleavenotenough(sick);
            }
            if (Leave.parsedate(leave.start) < Leave.parsedate(SystemDate.getInstance().toString())) {
                throw new ExLeaveEarly(SystemDate.getInstance().toString() + ")!");
            }
            if (Day.difference(Leave.parsedatesting(leave.start) + " 00:00:00",
                    Leave.parsedatesting(leave.end) + " 00:00:00") <= 6
                    && leave.type.equals("BL")) {
                throw new ExshoulduseAL();
            }
            if (Day.difference(Leave.parsedatesting(leave.start) + " 00:00:00",
                    Leave.parsedatesting(leave.end) + " 00:00:00") > 6
                    && leave.type.equals("AL")) {
                throw new ExshoulduseBL();
            }
            if (cmdParts.length >= 7) {
                length = cmdParts.length;
                for (int t = 6; t < length; t = t + 2) {
                    Team x = Team.searchTeam(Company.getInstance().allTeams, cmdParts[t - 1]);
                    copy.remove(x);
                }
                if (copy.size() > 0) {
                    throw new Exmissinput(copy.get(0).getName());
                }
                for (int t = 6; t < length; t = t + 2) {
                    Employee substitute = Employee.searchEmployee(Company.getInstance().allEmployees, cmdParts[t]);
                    Team x = Team.searchTeam(Company.getInstance().allTeams, cmdParts[t - 1]);
                    if (substitute == null) {
                        throw new Exsubstitutenotfoundinteam(cmdParts[t], x.getName());
                    }
                }
                boolean ou = false;
                Team ji = new Team();
                Employee yt = new Employee();
                for (int t = 6; t < length; t = t + 2) {
                    Team x = Team.searchTeam(Company.getInstance().allTeams, cmdParts[t - 1]);
                    Employee substitute = Employee.searchEmployee(Company.getInstance().allEmployees, cmdParts[t]);
                    boolean loo = false;
                    for (Employee employee : x.people) {
                        if (substitute.equals(employee)) {
                            loo = true;
                        } else {
                            continue;
                        }
                    }
                    if (loo == false) {
                        ou = true;
                        ji = x;
                        yt = substitute;
                    }
                }
                if (ou) {
                    throw new Exsubstitutenotfoundinteam(yt.getName(), ji.getName());
                }
                for (int t = 6; t < length; t = t + 2) {
                    Employee substitute = Employee.searchEmployee(Company.getInstance().allEmployees, cmdParts[t]);
                    boolean look = false;
                    Team x = Team.searchTeam(Company.getInstance().allTeams, cmdParts[t - 1]);
                    for (Employee employee : x.people) {
                        if (substitute.equals(employee)) {
                            look = true;
                        }
                    }
                    if (!look) {
                        throw new Exsubstitutenotfoundinteam(substitute.getName(), x.getName());
                    }
                    for (Leave le : substitute.leaves) {
                        if (Leave.parsedate(leave.start) <= Leave.parsedate(le.start)
                                && Leave.parsedate(leave.end) >= Leave.parsedate(le.start)) {
                            String start = le.start;
                            String end = le.end;
                            if (le.start.charAt(0) == '0') {
                                start = le.start.substring(1, le.start.length());
                            }
                            if (le.end.charAt(0) == '0') {
                                end = le.end.substring(1, le.end.length());
                            }
                            throw new Exsubconflict(substitute.getName() + " is on leave during " + start + " to " + end
                                    + " [" + le.type + "]!");
                        }
                        if (Leave.parsedate(leave.start) <= Leave.parsedate(le.end)
                                && Leave.parsedate(le.end) <= Leave.parsedate(leave.end)) {
                            String start = le.start;
                            String end = le.end;
                            if (le.start.charAt(0) == '0') {
                                start = le.start.substring(1, le.start.length());
                            }
                            if (le.end.charAt(0) == '0') {
                                end = le.end.substring(1, le.end.length());
                            }
                            throw new Exsubconflict(substitute.getName() + " is on leave during " + start + " to " + end
                                    + " [" + le.type + "]!");
                        }
                        if (Leave.parsedate(le.start) <= Leave.parsedate(leave.start)
                                && Leave.parsedate(leave.end) <= Leave.parsedate(le.end)) {
                            String start = le.start;
                            String end = le.end;
                            if (le.start.charAt(0) == '0') {
                                start = le.start.substring(1, le.start.length());
                            }
                            if (le.end.charAt(0) == '0') {
                                end = le.end.substring(1, le.end.length());
                            }
                            throw new Exsubconflict(substitute.getName() + " is on leave during " + start + " to " + end
                                    + " [" + le.type + "]!");
                        }
                    }
                    leave.team.add(x);
                    leave.sss.add(substitute);
                    subss.add(substitute);
                    substitute.acting_period.add(leave);
                    Collections.sort(substitute.leaves);
                }
                aim.leaves.add(leave);
            }
            if (cmdParts.length == 5) {
                aim.leaves.add(leave);
                length = 5;
            }
            Collections.sort(aim.leaves);
            addUndoCommand(this);
            clearRedoList();
            System.out.println("Done.");
        } catch (Exleaveoverlapped x) {
            System.out.println(x.getMessage());
        } catch (ExLeaveEarly e) {
            System.out.println(e.getMessage());
        } catch (ExshoulduseAL e) {
            System.out.println(e.getMessage());
        } catch (ExshoulduseBL e) {
            System.out.println(e.getMessage());
        } catch (Exbalanceinsufficient e) {
            System.out.println(e.getMessage());
        } catch (Exnoamountforblock e) {
            System.out.println(e.getMessage());
        } catch (Exsickleavenotenough e) {
            System.out.println(e.getMessage());
        } catch (Exsubconflict e) {
            System.out.println(e.getMessage());
        } catch (Exsubstitutenotfoundinteam e) {
            System.out.println(e.getMessage());
        } catch (Exmissinput e) {
            System.out.println(e.getMessage());
        } catch (Exactleaveconflict e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void undoMe() {
        if (length == 5) {
            aim.leaves.remove(leave);

        } else if (length > 5) {
            aim.leaves.remove(leave);
            for (Employee e : subss) {
                e.acting_period.remove(leave);
            }
        }
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        if (length == 5) {
            aim.leaves.add(leave);
            Collections.sort(aim.leaves);
        } else if (length > 5) {
            aim.leaves.add(leave);
            Collections.sort(aim.leaves);
            for (Employee e : subss) {
                e.acting_period.add(leave);
                Collections.sort(e.acting_period);
            }
        }
        addUndoCommand(this);
    }
}
