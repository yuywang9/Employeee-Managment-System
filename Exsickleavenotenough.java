public class Exsickleavenotenough extends Exception {
    public Exsickleavenotenough(int i) {
        super("Insufficient balance of sick leaves. " + i + " days left only!");
    }
}
