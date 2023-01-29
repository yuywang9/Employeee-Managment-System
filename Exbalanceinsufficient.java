
public class Exbalanceinsufficient extends Exception {
    public Exbalanceinsufficient(int i) {
        super("Insufficient balance of annual leaves. " + i + " days left only!");
    }
}
