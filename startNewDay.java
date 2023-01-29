import java.util.ArrayList;

public class startNewDay extends RecordedCommand {
    public static ArrayList<String> dates = new ArrayList<>();
    public static ArrayList<String> dump = new ArrayList<>();

    public static void initialize() {
        dates.add(SystemDate.getInstance().toString());
    }

    @Override
    public void execute(String[] cmdParts) {
        SystemDate sys = SystemDate.getInstance();
        String newdate = cmdParts[1];
        dates.add(newdate);
        sys.set(newdate);
        addUndoCommand(this);
        clearRedoList();
        System.out.println("Done.");
    }

    @Override
    public void undoMe() {
        SystemDate sys = SystemDate.getInstance();
        int index = dates.size() - 1;
        dump.add(dates.get(index));
        dates.remove(index);
        sys.set(dates.get(index - 1));
        addRedoCommand(this);
    }

    @Override
    public void redoMe() {
        SystemDate sys = SystemDate.getInstance();
        int indexdump = dump.size() - 1;
        sys.set(dump.get(indexdump));
        dates.add(dump.get(indexdump));
        dump.remove(indexdump);
        addUndoCommand(this);
    }
}
