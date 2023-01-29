public class Exleaveoverlapped extends Exception {
    public Exleaveoverlapped(String message) {
        super("Leave overlapped: The leave period " + message);
    }
}
