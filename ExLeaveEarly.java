public class ExLeaveEarly extends Exception {
    public ExLeaveEarly(String message) {
        super("Wrong Date. Leave start date must not be earlier than the system date (" + message);
    }
}
