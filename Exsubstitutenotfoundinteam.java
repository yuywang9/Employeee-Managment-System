public class Exsubstitutenotfoundinteam extends Exception {
    public Exsubstitutenotfoundinteam(String name, String team) {
        super("Employee (" + name + ") not found for " + team + "!");
    }
}
